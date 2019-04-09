package com.common.syslog;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.dao.SyslogMapper;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统日志切面
 * 
 * @author zhuzhe
 * @date 2018/6/4 9:27
 * @email 1529949535@qq.com
 */
@Aspect // 使用@Aspect注解声明一个切面
@Component
@Slf4j
public class SysLogAspect {

	@Autowired
	private SysLogService sysLogService;

	private static final Logger logger = Logger.getLogger(SysLogAspect.class);

	/**
	 * Dao
	 */
	@Autowired
	private SyslogMapper syslogMapper;

	/**
	 * 这里我们使用注解的形式 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method 切点表达式:
	 * execution(...)
	 */
	@Pointcut("@annotation(com.common.syslog.SysLog)")
	public void logPointCut() {
	}

	/**
	 * 环绕通知 @Around ， 当然也可以使用 @Before (前置通知) @After (后置通知)
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object result = point.proceed();
		long time = System.currentTimeMillis() - beginTime;
		try {
			saveLog(point, time);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 保存日志
	 * 
	 * @param joinPoint
	 * @param time
	 */
	private void saveLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogBO sysLogBO = new SysLogBO();
		sysLogBO.setExeuTime(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sysLogBO.setCreateDate(dateFormat.format(new Date()));
		SysLog sysLog = method.getAnnotation(SysLog.class);
		if (sysLog != null) {
			// 注解上的描述
			sysLogBO.setRemark(sysLog.value());
		}
		// 请求的 类名、方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLogBO.setClassName(className);
		sysLogBO.setMethodName(methodName);
		// 请求的参数
		Object[] args = joinPoint.getArgs();
		try {
			List<String> list = new ArrayList<String>();
			for (Object o : args) {
				list.add(new Gson().toJson(o));
			}
			sysLogBO.setParams(list.toString() + "fsdafasfsaf24244513512" + 8980);
		} catch (Exception e) {
			save(sysLogBO, "fail");
		}
		save(sysLogBO, "succeed");
	}

	public boolean save(SysLogBO sysLogBO, String flag) {
		// 这里就不做具体实现了
		logger.info(sysLogBO.getParams());
		log.info(sysLogBO.getParams());

		String remart;
		if (flag.equals("succeed")) {
			remart = "用户XX执行了: " + sysLogBO.getRemark() + " 操作成功！";
		} else {
			remart = "用户XX执行了: " + sysLogBO.getRemark() + " 操作失败！";
		}

		sysLogBO.setRemark(remart);
		syslogMapper.insert(sysLogBO);

		return true;
	}

}
