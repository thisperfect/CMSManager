package com.ofhi.modules.cms.sys.service.impl;

import com.ofhi.common.Constant;
import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.common.response.ResponseCode;
import com.ofhi.common.util.StringHelper;
import com.ofhi.modules.cms.sys.dao.SysUserDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysUser;
import com.ofhi.modules.cms.sys.entity.vo.UserRegister;
import com.ofhi.common.Assist;
import com.ofhi.common.security.MD5;
import com.ofhi.common.user.UserConst;
import com.ofhi.modules.cms.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    private Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser selectSysUser(SysUser sysUser) {
        return sysUserDao.selectOne(sysUser);
    }

    @Override
    public List<SysUser> selectSysUser(Assist assist) {
        return sysUserDao.selectSysUser(assist);
    }

    @Override
    public SysUser selectSysUserById(Long id) {
        return sysUserDao.selectSysUserById(id);
    }

    @Override
    public int insertSysUser(SysUser value) {
        return sysUserDao.insertSysUser(value);
    }

    @Override
    public int insertNonEmptySysUser(SysUser value) {
        return sysUserDao.insertNonEmptySysUser(value);
    }

    @Override
    public int deleteSysUserById(Long id) {
        return sysUserDao.deleteSysUserById(id);
    }

    @Override
    public int deleteSysUser(Assist assist) {
        return sysUserDao.deleteSysUser(assist);
    }

    @Override
    public int updateSysUserById(SysUser enti) {
        return sysUserDao.updateSysUserById(enti);
    }

    @Override
    public int updateSysUser(SysUser value, Assist assist) {
        return sysUserDao.updateSysUser(value, assist);
    }

    @Override
    public int updateNonEmptySysUserById(SysUser enti) {
        return sysUserDao.updateNonEmptySysUserById(enti);
    }

    @Override
    public int updateNonEmptySysUser(SysUser value, Assist assist) {
        return sysUserDao.updateNonEmptySysUser(value, assist);
    }

    @Override
    public void checkUsernameAndEmailExists(String username, String email) throws RequestErrorException {
        SysUser sysUser = SysUser.createDefaultObject();
        sysUser.setLoginName(username);
        sysUser.setEmail(email);
        sysUser = sysUserDao.selectOne(sysUser);
        if (null != sysUser) {
            String msg = "";
            if (UserConst.ACCOUNT_STATUS_DISABLED.equals(sysUser.getStatus())) {
                msg += "账号被禁用，不能注册!";
            }
            if (StringUtils.equals(username, sysUser.getLoginName())) {
                msg += "登入名已存在!;";
            }
            if (StringUtils.equals(email, sysUser.getEmail())) {
                msg += "当前电子邮箱已被其它账户绑定!;";
            }
            if (StringUtils.isNotEmpty(msg)) {
                throw new RequestErrorException(ResponseCode.login_name_already_exist.getCode(), msg);
            }
        }
    }

    @Override
    public SysUser insertOnRegisterUserAccount(UserRegister register) throws Exception {

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(register, sysUser, new String[]{"password", "repeatPassword", "validationCode"});

        sysUser.setLoginName(register.getUsername());
        if (StringUtils.isEmpty(sysUser.getName())) {
            sysUser.setName(register.getUsername());
        }
        sysUser.setPassword(MD5.createPassword(MD5.encryption(register.getPassword())));
        sysUser.setStatus(UserConst.ACCOUNT_STATUS_ACTIVATE);
        sysUser.setIsFinal(Constant.IS_FINAL_YES);
        Date date = new Date();
        sysUser.setCreateTime(date);
        sysUser.setUpdateTime(date);
        sysUser.setSex(UserConst.MAN);
        int id = sysUserDao.insertSelective(sysUser);
        logger.debug("===> inster result:{}",id);
        if (id <= 0) {
            throw new Exception("注册时候插入数据异常!");
        }

        //注册成功发送激活链接
        StringHelper.sendActivateAccountlink(register.getEmail());
        return sysUser;
    }

    @Override
    public void activateAccount(String email) throws RequestErrorException {

        SysUser sysUser = SysUser.createDefaultObject();
        sysUser.setEmail(email);
        sysUser = sysUserDao.selectOne(sysUser);

        if (sysUser.getStatus() == UserConst.ACCOUNT_STATUS_NORMAL) {
            throw new RequestErrorException(ResponseCode.success);
        } else if (sysUser.getStatus() == UserConst.ACCOUNT_STATUS_DEL) {
            throw new RequestErrorException(ResponseCode.unknown_account);
        } else if (sysUser.getStatus() == UserConst.ACCOUNT_STATUS_DISABLED) {
            throw  new RequestErrorException(ResponseCode.forbidden_account);
        }

        SysUser user = new SysUser();
        user.setId(sysUser.getId());
        user.setStatus(UserConst.ACCOUNT_STATUS_NORMAL);
        user.setUpdateTime(new Date());
        sysUserDao.updateByPrimaryKeySelective(user);
    }
}