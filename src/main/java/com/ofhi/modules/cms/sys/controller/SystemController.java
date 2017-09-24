package com.ofhi.modules.cms.sys.controller;

import com.ofhi.common.base.BaseController;
import com.ofhi.common.response.ResponseCode;
import com.ofhi.common.security.DES;
import com.ofhi.common.util.RedisUtil;
import com.ofhi.common.util.StringHelper;
import com.ofhi.common.Assist;
import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.common.response.InterfaceResult;
import com.ofhi.common.security.MD5;
import com.ofhi.common.security.ValidationCodeUtils;
import com.ofhi.common.user.UserConst;
import com.ofhi.common.user.UserHelper;
import com.ofhi.modules.cms.sys.entity.pojo.SysUser;
import com.ofhi.modules.cms.sys.entity.vo.UserLogin;
import com.ofhi.modules.cms.sys.entity.vo.UserRegister;
import com.ofhi.modules.cms.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;

    /**
     * 跳转到登入页面
     * @return
     */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
           return "redirect:/user/index.shtml";
        }
		return "sys/login2";
	}

    /**
     * 登入
     * @param userLogin
     * @param sign
     * @param timestamp
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = produces)
	public String login(UserLogin userLogin, String sign, Long timestamp) {
		logger.info("login.do ====>{}",userLogin.toString());
		if (StringHelper.isNotEmpty( userLogin.getValidationCode())) {
            String validationCode = StringHelper.toString(RedisUtil.get(StringHelper.toString(getCurrentSessionId())));
            if (!userLogin.getValidationCode().equalsIgnoreCase(validationCode)) {
                return InterfaceResult.getErrorResult(ResponseCode.verify_captcha_error).toString();
            }
        }
		InterfaceResult result = UserHelper.checkLoginParams(userLogin, sign, timestamp);
		if (result.getCode() != 10000) {
			return result.toString();
		}
        int count = StringHelper.passInteger(RedisUtil.get(userLogin.getUsername()));
		logger.debug("=== login cont:{}",count);
        if (count >= 4) {
            return InterfaceResult.getErrorResult(ResponseCode.request_frequently.getCode(), "登入次数过多，请在半个小时之后重试").toString();
        }

        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(), MD5.encryption(userLogin.getPassword()));
            token.setRememberMe(userLogin.isRememberMe());
            subject.login(token);
            count = 0;
            RedisUtil.delete(userLogin.getUsername());
		} catch (IncorrectCredentialsException ice) {
            count ++ ;
			logger.info("密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("账户未激活");
            result = InterfaceResult.getErrorResult(ResponseCode.account_not_activate);
        } catch (DisabledAccountException dae) {
        	logger.info("用户被禁用，不能登入！");
			result = InterfaceResult.getErrorResult(ResponseCode.forbidden_account);
        } catch (UnknownAccountException uae) {
        	logger.info("用户不存在");
			result = InterfaceResult.getErrorResult(ResponseCode.unknown_account);
        } catch (ExcessiveAttemptsException eae) {
        	logger.info("登录失败次数过多");
			result = InterfaceResult.getErrorResult(ResponseCode.request_frequently);
        }  catch (AuthenticationException ae) {
        	logger.info(ae.getMessage());
			result = InterfaceResult.getErrorResult(ae.getMessage());
        }
        if (count > 0) {
            RedisUtil.setDefaultTimeOut(userLogin.getUsername(), count);
            if (count > 2) { //需要验证码
              return  InterfaceResult.getErrorResult(ResponseCode.verify_captcha_error.getCode(), String.format("密码错误，再输错%s次该账户将被锁定30分钟", 5 - count)).toString();
            }
            result = InterfaceResult.getErrorResult(ResponseCode.password_incorrect.getCode(), String.format("密码错误，再输错%s次该账户将被锁定30分钟", 5 - count));
        }
		return result.toString();
	}

    /**
     * 检查账户是否存在
     * @param username
     * @param email
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/checkAccount", method = RequestMethod.POST, produces = produces)
	public String checkUsernameAndEmailExists(String username,String email) {
        logger.info("===> checkAccount,username:{},email:{}", username, email);
        try {
            sysUserService.checkUsernameAndEmailExists(username, email);
            return InterfaceResult.getSuccess().toString();
        } catch (RequestErrorException e) {
            return InterfaceResult.getErrorResult(e.getErrCode(), e.getErrMsg()).toString();
        }
    }

    /**
     * 生成图片验证码
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/createValidationCode", method = RequestMethod.GET)
    public void createValidationCodeImage(HttpServletResponse response) throws IOException {
        ValidationCodeUtils code = new ValidationCodeUtils();
        char[] chars = code.randomChar(4);
        RedisUtil.setDefaultTimeOut(StringHelper.toString(getCurrentSessionId()), new String(chars));
        BufferedImage image = code.getImage(chars);
        code.output(image, response.getOutputStream());
    }

    /**
     * 注册账号
     * @param register
     * @param sign
     * @param timestamp
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = produces)
	public String register(UserRegister register,String  sign, Long timestamp) {
        logger.info("===> register, UserRegister:{}",register.toString());
        String validationCode = StringHelper.toString(RedisUtil.get(StringHelper.toString(getCurrentSessionId())));
        if (StringHelper.isEmpty(register.getValidationCode() ) || !register.getValidationCode().equalsIgnoreCase(validationCode)) {
            return InterfaceResult.getErrorResult(ResponseCode.verify_captcha_error).toString();
        }
        InterfaceResult result = UserHelper.checkRegisterParams(register, sign, timestamp);
        if (result.getCode() != 10000) {
            return result.toString();
        }
        try {
            sysUserService.insertOnRegisterUserAccount(register);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return InterfaceResult.getErrorResult().toString();
        }
        return InterfaceResult.getSuccess().toString();
    }

    /**
     * 邮箱链接激活账户
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/activateAccount", method = RequestMethod.GET, produces = produces)
    public String activateAccount(String token) {
        logger.info("====> activateAccount:token={}",token);
        if (StringUtils.isEmpty(token)) {
            return InterfaceResult.getErrorResult(ResponseCode.missing_parameter).toString();
        }
        String encrypt = null;
        try {
            encrypt = new DES().decrypt(token);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return InterfaceResult.getErrorResult(ResponseCode.error.getCode(),e.getMessage()).toString();
        }

        if (StringUtils.isEmpty(encrypt)) {
            return InterfaceResult.getErrorResult(ResponseCode.forbidden_ip).toString();
        }

        String[] split = StringUtils.split(encrypt, ",");
        if (split == null || split.length <= 0) {
            return InterfaceResult.getErrorResult(ResponseCode.forbidden_ip).toString();
        }

        String email = null;
        Long timestamp = null;
        try {
            email = split[0];
            timestamp = Long.valueOf(split[1]);
        } catch (IndexOutOfBoundsException e) {
            logger.error(e.getMessage(),e);
            return InterfaceResult.getErrorResult(ResponseCode.param_format_error).toString();
        }

        if (UserHelper.comparingTimestamps(timestamp) > 30) {
            return InterfaceResult.getErrorResult(ResponseCode.request_timeout).toString();
        }
        try {
            sysUserService.activateAccount(email);
        } catch (RequestErrorException e) {
	        logger.error(e.getErrMsg(),e);
            return InterfaceResult.getErrorResult(e.getErrCode(), e.getErrMsg()).toString();
        }
        return InterfaceResult.getSuccess().toString();
    }

    /**
     * 发送激活链接
     * @param email
     * @param sign
     * @param timestamp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendActivatelink",method = RequestMethod.POST)
    public String sendActivateAccountlink(String email,String sign, Long timestamp) {
        logger.info("===> sendActivatelink,email:{}",email);
        if (StringUtils.isEmpty(email)) {
            return InterfaceResult.getErrorResult(ResponseCode.missing_parameter).toString();
        }
        if (!StringHelper.isEmaile(email)) {
            return InterfaceResult.getErrorResult(ResponseCode.param_format_error.getCode(),"无效的邮箱地址").toString();
        }
        if (UserHelper.comparingTimestamps(timestamp) > 1) {
            return InterfaceResult.getErrorResult(ResponseCode.request_timeout).toString();
        }
        if (!StringUtils.equals(sign, MD5.encryption(timestamp + email))) {
            return InterfaceResult.getErrorResult(ResponseCode.sign_error).toString();
        }

        List<SysUser> sysUsers = sysUserService.selectSysUser(new Assist(Assist.and_eq("email", email)));

        if (CollectionUtils.isEmpty(sysUsers)) {
            return InterfaceResult.getErrorResult(ResponseCode.unknown_account).toString();
        }

        SysUser sysUser = sysUsers.get(0);

        if (sysUser.getStatus() == UserConst.ACCOUNT_STATUS_NORMAL) {
            return  InterfaceResult.getErrorResult(ResponseCode.success.getCode(),"正常账户,不能重复激活!").toString();
        } else if (sysUser.getStatus() == UserConst.ACCOUNT_STATUS_DEL) {
            return InterfaceResult.getErrorResult(ResponseCode.unknown_account).toString();
        } else if (sysUser.getStatus() == UserConst.ACCOUNT_STATUS_DISABLED) {
            return InterfaceResult.getErrorResult(ResponseCode.forbidden_account).toString();
        }

        try {
            StringHelper.sendActivateAccountlink(email);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return InterfaceResult.getErrorResult(ResponseCode.error.getCode(),e.getMessage()).toString();
        }
        return InterfaceResult.getSuccess().toString();
    }

    @RequestMapping("/sendSuccess")
    public String jumpSendEmailSuccessPage(@RequestParam String email, Model model) {
        model.addAttribute("email",email);
        return "sys/sendEmailSuccess";
    }

    @RequestMapping("/icon")
    public String jumpAwesomeIconPage() {
        return "share/awesome_icon";
    }

}
