package com.common.oplog;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.common.syslog.SysLogAspect;
import com.common.util.DateUtils;
import com.demo.dao.LogMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 一只会飞的猪
 * @desc 日志录入aop实现类
 * @time 2018/8/28
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
	@Autowired
	private LogMapper logMapper;

	private static final Logger logger = Logger.getLogger(LogAspect.class);

	// 这里定义下切点的位置,也就是刚才我们自定义的注解.
	@Pointcut("@annotation(com.common.oplog.LogAnnotation)")
	public void mypointcut() {
	}

	/**
	 * 环绕通知 @Around ， 当然也可以使用 @Before (前置通知) @After (后置通知)
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("mypointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = point.proceed();
		long time = System.currentTimeMillis() - beginTime;
		try {
			logger.info("xxxxxxxxxx" + time);
			log.info("xxxxxxxxxx00000000000" + time);
		} catch (Exception e) {
		}
		return result;
	}

	// 消息通知 @AfterReturning,在切点方法运行之后触发returning 为目标函数返回值
	@AfterReturning(returning = "result", value = "mypointcut()")
	public void addlog(JoinPoint joinPoint, Object result) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 获取切入点所在的方法
		Method method = signature.getMethod();

		String operatetype = ""; // 定义操作方式
		String operatecontent = ""; // 定义操作内容

		// 获取注解中的操作方式
		if (method != null && !"".equals(method)) {
			// 获取自定义注解操作
			LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
			// 获取用户操作方式
			operatetype = logAnnotation.operateType();
			// 获取用户操作内容
			operatecontent = logAnnotation.operateContent();
		}

		// 获取请求的类名
		String classname = joinPoint.getTarget().getClass().getName();
		// 获取请求的方法名
		String methodname = classname + "." + method.getName();
		// 获取请求方式
		String Method = request.getMethod();
		// 获取请求url
		String URL = request.getRequestURI().toString();
		// 获取请求的ip地址
		String IP = request.getRemoteAddr();
		// 获取userid
		String userid = request.getParameter("userid");
		// 获取子系统id
		String subsystemid = request.getParameter("subsystemid");
		// 生成uuid
		String uuid = UUID.randomUUID().toString();
		// 获取请求的参数
		String argsname[] = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
		Map<String, Object> parammap = new HashMap<>();
		if (argsname.length > 0) {
			parammap = getParam(joinPoint, argsname, methodname);
		}
		String detail = JSON.toJSONString(parammap);
		// 获取操作状态
		// Map<String, Object> statusmap = new HashMap<>();
		String status = "";
		// statusmap = (Map<String, Object>) result;
		// Integer code = (Integer) statusmap.get("code");
		if (null == result) {
			status = "失败";
		} else {
			status = "成功";
		}

		// 日志实体类封装
		LogEntity logEntity = new LogEntity();
		logEntity.setUserid(userid);
		logEntity.setUuid(uuid);
		logEntity.setIp(IP);
		logEntity.setStatus(status);
		logEntity.setOperatetype(operatetype);
		logEntity.setOperatetime(DateUtils.dateToStrLong(new Date()));
		logEntity.setOperatecontent(operatecontent);
		logEntity.setDetail(detail);
		logEntity.setSubsystemid(subsystemid);

		logMapper.addLogInfo(logEntity);

		System.out.println("aop+++++++++++++++++++++切面++++++++++++++++++++");
		System.out.println("用户操作方式:----------" + operatetype);
		System.out.println("用户操作内容:----------" + operatecontent);
		System.out.println("请求方式:-------------" + Method);
		System.out.println("请求地址url:----------" + IP + URL);
		System.out.println("请求ip地址:------------" + IP);
		System.out.println("请求参数:------------" + request.getParameterNames().toString());
		System.out.println("请求参数:============" + request.getQueryString());
		System.out.println("请求方法名:============" + methodname);
		System.out.println("请求userid:============" + userid);
		System.out.println("返回参数:============" + detail);
		System.out.println("返回结果状态:============" + status);
		System.out.println("返回结果:============" + logEntity.toString());

	}

	// 处理参数格式,并返回需要的参数
	public static Map<String, Object> getParam(JoinPoint joinPoint, String argsname[], String methodname) {
		Map<String, Object> detailmap = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> mapCODE = new HashMap<>();
		// 获取参数值
		Object args[] = joinPoint.getArgs();
		// 获取参数名
		argsname = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
		String paramsString = "";
		for (int i = 0; i < argsname.length; i++) {
			if (!argsname[i].equals("model")) {
				map.put(argsname[i], args[i]);
			}
		}
		detailmap.put("method", methodname);
		detailmap.put("params", map);
		System.out.println("detail:=====" + detailmap.toString());
		return detailmap;
	}
}
