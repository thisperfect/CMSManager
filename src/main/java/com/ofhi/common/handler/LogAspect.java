package com.ofhi.common.handler;

import com.alibaba.fastjson.JSON;
import com.ofhi.common.annotation.Log;
import com.ofhi.common.util.RequestUtil;
import com.ofhi.common.util.StringHelper;
import com.ofhi.common.util.key.SnowflakeIdWorker;
import com.ofhi.modules.cms.sys.entity.pojo.SysLog;
import com.ofhi.modules.cms.sys.service.SysLogService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志记录AOP实现
 */
@Aspect
public class LogAspect {

	private static Logger _log = LoggerFactory.getLogger(LogAspect.class);

	// 开始时间
	private long startTime = 0L;
	// 结束时间
	private long endTime = 0L;

	@Autowired
	private SysLogService sysLogService;

	//Controller层切点
	@Pointcut("@annotation(com.ofhi.common.annotation.Log)")
	public  void controllerAspect() {}


	@Before("execution(* *..controller..*.*(..))")
	public void doBeforeInServiceLayer(JoinPoint joinPoint) {
		_log.debug("doBeforeInServiceLayer");
		startTime = System.currentTimeMillis();
	}

	@After("execution(* *..controller..*.*(..))")
	public void doAfterInServiceLayer(JoinPoint joinPoint) {
		_log.debug("doAfterInServiceLayer");
	}

	@Around("execution(* *..controller..*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		// 获取request
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		HttpServletRequest request = servletRequestAttributes.getRequest();

		SysLog syslog = new SysLog();
		// 从注解中获取操作名称、获取响应结果
		Object result = pjp.proceed();
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		if (method.isAnnotationPresent(ApiOperation.class)) {
			ApiOperation log = method.getAnnotation(ApiOperation.class);
			syslog.setDescription(log.value());
		}
		if (method.isAnnotationPresent(RequiresPermissions.class)) {
			RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
			String[] permissions = requiresPermissions.value();
			if (permissions.length > 0) {
				syslog.setPermissions(permissions[0]);
			}
		}
		endTime = System.currentTimeMillis();
		_log.debug("doAround >>> result={},耗时：{} mm", result, endTime - startTime);

		syslog.setBasePath(RequestUtil.getBasePath(request));
		syslog.setIp(RequestUtil.getIpAddr(request));
		syslog.setMethod(request.getMethod());
		if (request.getMethod().equalsIgnoreCase("GET")) {
			syslog.setParameter(request.getQueryString());
		} else {
			syslog.setParameter(ObjectUtils.toString(request.getParameterMap()));
		}
		syslog.setId(new SnowflakeIdWorker(1,11).nextId());
		syslog.setResult(JSON.toJSONString(result));
		syslog.setSpendTime((endTime - startTime));
		syslog.setStartTime(startTime);
		syslog.setUri(request.getRequestURI());
		syslog.setUrl(ObjectUtils.toString(request.getRequestURL()));
		syslog.setUserAgent(request.getHeader("User-Agent"));
		syslog.setUsername(ObjectUtils.toString(SecurityUtils.getSubject().getPrincipal()));
		sysLogService.insertSysLog(syslog);
		return result;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 *
	 * @param joinPoint 切点
	 * @return 方法描述
	 * @throws Exception
	 */
	private SysLog getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {
		SysLog sysLog = new SysLog();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					Log annotation = method.getAnnotation(Log.class);
					sysLog.setModule(annotation.module());
					String de = annotation.description();
					if(StringHelper.isEmpty(de))de="未填写注释";
					sysLog.setDescription(de);
					break;
				}
			}
		}
		return sysLog;
	}

}
