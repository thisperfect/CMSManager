package com.ofhi.common.util;

import com.ofhi.common.response.ResponseCode;
import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.common.security.DES;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper extends StringUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    private static final Logger logger = LoggerFactory.getLogger(StringHelper.class);

	private StringHelper() {}


	public static String toString(Object object) {
	    return object == null ? "" : object.toString();
    }

    public static int passInteger(Object object) {
        try {
            return object == null ? 0 : Integer.valueOf(object.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double passDouble(Object object) {
        try {
            return object == null ? 0 : Double.valueOf(object.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static long passLong(Object object) {
        try {
            return object == null ? 0L : Long.valueOf(object.toString());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 驼峰转下划线,效率比上面高
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 捕捉异常详细异常栈
     *
     * @param throwable
     * @return
     */
    public static String exceptionDetail(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        return  writer.toString();
    }

    /**
     * 正则表达式校验邮箱
     *
     * @param email
     *            待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    public static boolean isEmaile(String email) {
        String rule_email = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        return matcher(rule_email, email);
    }

    private static boolean matcher(String regex, String checkStr) {
        // 正则表达式的模式
        Pattern p = Pattern.compile(regex);
        // 正则表达式的匹配器
        Matcher m = p.matcher(checkStr);
        // 进行正则匹配
        return m.matches();
    }

    /**
     * 生成用户使用邮箱祖册时候发送的激活链接邮箱页面代码
     *
     * @param activationURL
     *            激活动态链接
     * @return
     */
    public static String createEmailRegisterHtml(String activationURL) {

        StringBuffer html = new StringBuffer();
        html.append("  <div style='background: #f6f8f9; padding:50px;font-family:Tahoma,Helvetica,'microsoftyahei','Hiragino Sans GB',Simsun,sans-serif;'> ");
        html.append("   <div style='margin: 0 auto; text-align: left;padding: 38px 50px; width: 560px; font-size: 14px;color: #606060; background: #fff; border-radius:2px; font-family:Tahoma,Helvetica,'microsoftyahei','Hiragino Sans GB',Simsun,sans-serif;box-shadow: 0 0 4px rgba(0,0,0,0.2);'> ");
        html.append("    <table style='margin: 0 auto;text-align: left; font-size: 14px; color: #606060;background: #fff; font-family: inherit;font-family:Tahoma,Helvetica,'microsoftyahei','Hiragino Sans GB',Simsun,sans-serif;' width='560' cellspacing='0' cellpadding='0' border='0'> ");
        html.append("     <tbody>");
        html.append("    ");
        html.append("      <tr> ");
        html.append("       <td colspan='2' style='font-size:18px; padding: 30px 0 18px;'>尊敬的用户：</td> ");
        html.append("      </tr> ");
        html.append("      <tr> ");
        html.append("       <td colspan='2' style='line-height: 1.8;'> ");
        html.append("        <div>");
        html.append("         欢迎使用");
        String companyName = ProjectUtils.getProperty("company.name");
        logger.debug("====> companyName:{}", companyName);
        html.append(companyName);
        html.append("的数据服务！");
        html.append("        </div> ");
        html.append("        <div>");
        html.append("         请点击以下的链接验证您的邮箱，验证成功后就可以使用我们为您提供的所有服务了。");
        html.append("        </div> </td> ");
        html.append("      </tr> ");
        html.append("      <tr> ");
        html.append("       <td colspan='2' style='font-size:12px; line-height: 20px; padding-top: 14px;padding-bottom: 25px; color: #909090;'> ");
        html.append("        <div>");
        html.append("         <a href='");
        html.append(activationURL);
        html.append("' style='color: #03c5ff; text-decoration:underline;' target='_blank'>");
        html.append(activationURL);
        html.append("    </a>    </div> ");
        html.append("        <div style='padding-top:4px;'>");
        html.append("         (如果不能打开页面，请复制该地址到浏览器打开)");
        html.append("        </div> </td> ");
        html.append("      </tr> ");
        html.append("      <tr> ");
        html.append("       <td colspan='2' style='text-align:right; line-height: 1.8; padding-bottom: 18px;'> ");
        html.append("        <div>");
        html.append(companyName);
        html.append("         技术团队");
        html.append("        </div> ");
        html.append("        <div style='color:#909090;'>");
        html.append("         <span style='border-bottom: 1px dashed rgb(204, 204, 204); position: relative;' t='5' times=''>");
        html.append(DateUtil.getCurrentTime("yyyy年MM月dd"));
        html.append("</span>");
        html.append("        </div> </td> ");
        html.append("      </tr> ");
        html.append("   ");
        html.append("      <tr> ");
        html.append("       <td colspan='2' style='padding-top: 20px; border-top: 1px solid #e7e7e7; line-height: 1.8; font-size: 12px; color:#909090;'> ");
        html.append("        <div>温馨提示：</div> ");
        html.append("        <div>");
        html.append("         1. 我们官方网址为：");
        String companyURL = ProjectUtils.getProperty("company.url");
        html.append("         <a href='");
        html.append(companyURL);
        html.append("' target='_blank'>");
        html.append(companyURL);
        html.append("</a>，请注意网址，防止钓鱼。");
        html.append("        </div> ");
        html.append("        <div>2. 本邮件为系统自动发出，请勿回复。</div>");
        html.append("		<div>3. 本链接有效时长为半个小时。</div> ");
        html.append("		</td> ");
        html.append("      </tr> ");
        html.append("     </tbody>");
        html.append("    </table> ");
        html.append("   </div> ");
		/*html.append("   <div style='padding: 30px 0; text-align:center; font-size: 14px; color: #909090;font-family:inherit;'>");
		html.append("    感谢您使用聚合数据服务，有任何问题您都可以登录 ");
		html.append("    <a style='color: #03c5ff; text-decoration:underline;' href='https://www.juhe.cn/contact' target='_blank'>https://www.juhe.cn/contact</a> 与我们进行联系");
		html.append("   </div> ");*/
        html.append("  </div>");
        return html.toString();
    }

    /**
     *  发送邮箱账户激活链接
     * @param email
     */
    public static void  sendActivateAccountlink(String email) throws Exception {
        if (StringUtils.isEmpty(email)) {
            throw new RequestErrorException(ResponseCode.missing_parameter.getCode(), "邮箱地址不能为空");
        }
        StringBuffer sb = new StringBuffer(ProjectUtils.getProperty("company.url"));
        sb.append("/system/activateAccount.shtml?");
        Long timestamp = System.currentTimeMillis();
        sb.append("token=");
        sb.append(new DES().encrypt(email + "," + timestamp));
        try {
            MailUtils.doSendHtmlEmail("激活账户邮件", createEmailRegisterHtml(sb.toString()), email);
        } catch (Exception e) {
            throw new RequestErrorException( "发送邮件激活链接失败",e);
        }
    }

}
