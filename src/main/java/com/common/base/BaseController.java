package com.common.base;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.util.MapperUtil;

public class BaseController {

	private String processSucessCode = "success";
	private String processErrorCode = "error";

	protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

	// @Autowired
	// protected SystemConstant systemConstant;

	protected HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	protected HttpServletResponse getHttpServletResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	protected HashMap<String, Object> getGeneralMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = getHttpServletRequest();
		Map<String, String[]> patameters = request.getParameterMap();
		for (String key : patameters.keySet()) {
			map.put(key, request.getParameter(key));
		}
		return map;
	}

	protected String Action(Action func) {
		try {
			SystemExcute.ExcuteServiceException(func);
			return respHandresult(new HandleResult(processSucessCode, null, "处理成功"));
		} catch (ServiceException se) {
			se.printStackTrace();
			logger.info(se.getMessage());
			if (se.getCodeMsgList() != null && se.getCodeMsgList().size() > 0)
				return respHandresult(new HandleResult(processErrorCode, se.getCodeMsgList()));
			return respHandresult(new HandleResult<String>(se.getCode(), null, se.getMessage()));
		} catch (SQLException se) {
			se.printStackTrace();
			logger.error(se.getMessage());
			return respHandresult(new HandleResult(processErrorCode, null, "数据脚本错误"));
		} catch (NullPointerException se) {
			se.printStackTrace();
			logger.error(se.getMessage());
			return respHandresult(new HandleResult(processErrorCode, null, "空引用异常"));
		} catch (Exception se) {
			logger.error(se.getMessage());
			return respHandresult(new HandleResult(processErrorCode, null, se.getMessage()));
		}
	}

	protected <T> String Func(Callable<T> func) {
		try {
			T temp = SystemExcute.ExcuteServiceException(func);
			return respHandresult(new HandleResult<T>(processSucessCode, temp));
		} catch (ServiceException se) {
			logger.info(se.getMessage());
			if (se.getCodeMsgList() != null && se.getCodeMsgList().size() > 0)
				return respHandresult(new HandleResult(processErrorCode, se.getCodeMsgList()));
			return respHandresult(new HandleResult<String>(se.getCode(), null, se.getMessage()));
		} catch (SQLException se) {
			logger.error(se.getMessage());
			return respHandresult(new HandleResult(processErrorCode, null, "数据脚本错误"));
		} catch (NullPointerException se) {
			se.printStackTrace();
			logger.error(se.getMessage());
			return respHandresult(new HandleResult(processErrorCode, null, "空引用异常"));
		} catch (Exception se) {
			logger.error(se.getMessage());
			se.printStackTrace();
			return respHandresult(new HandleResult(processErrorCode, null, se.getMessage()));
		}
	}

	// private void ExcuteServiceException(Action func) throws ServiceException,
	// Exception {
	// func.run();
	// }
	//
	// private <T> T ExcuteServiceException(Callable<T> func) throws
	// ServiceException, Exception {
	// return func.call();
	// }
	//
	public static String respHandresult(HandleResult hr) {
		return JSONObject.toJSONString(hr, SerializerFeature.WriteMapNullValue);
	}
	//
	// /**
	// * 获取cookie认证信息
	// *
	// * @return
	// */
	// protected Cookie getAuthCookie() {
	// return CookieUtils.getCookie(systemConstant.getCookiename(),
	// getHttpServletRequest());
	// }
	//
	// /**
	// * 获取当前登录用户信息
	// *
	// * @return
	// * @throws IOException
	// */
	// protected LoginSucessUserInfo getCurrentUserInfo() throws IOException {
	//
	// Claims claims = getClaims();
	// LoginSucessUserInfo userinfo = new LoginSucessUserInfo();
	// userinfo.setMobilenumber(claims.get("mobilenumber").toString());
	// userinfo.setUsername(claims.get("username").toString());
	// userinfo.setUsertype(
	// Integer.valueOf(claims.get("usertype") == null ? "-1" :
	// claims.get("usertype").toString()));
	// userinfo.setUserid(Long.valueOf(claims.get("userid") == null ? "0" :
	// claims.get("userid").toString()));
	// userinfo.setCompanyid(Long.valueOf(claims.get("companyid") == null ? "0"
	// : claims.get("companyid").toString()));
	// userinfo.setSystemid(claims.get("companyid") == null ? null :
	// claims.get("systemid").toString());
	// userinfo.setClaims(claims);
	// return userinfo;
	// }
	//
	// /**
	// * 获取当前登录用户token
	// *
	// * @return
	// * @throws IOException
	// */
	// protected Claims getClaims() throws IOException {
	// Cookie authorizationCookie = getAuthCookie();
	//
	// if (authorizationCookie == null) {
	// RedicertLogin();
	// return null;
	// }
	// String token = authorizationCookie.getValue();
	// Claims claims = JwtUtils.getClaimsFromToken(token,
	// systemConstant.getSystemsecretkey());
	//
	// if (claims == null) {
	// CookieUtils.removeCookie(getHttpServletRequest(),
	// getHttpServletResponse(), systemConstant.getCookiename());
	// RedicertLogin();
	// return null;
	// }
	//
	// return claims;
	//
	// }
	//
	// /**
	// * 跳转到登录页面
	// *
	// * @throws IOException
	// */
	// protected void RedicertLogin() throws IOException {
	// getHttpServletResponse().sendRedirect(systemConstant.getLoginurl());
	// return;
	// }
	//
	// /**
	// * 带上制定参数跳转到登录页面
	// *
	// * @param params
	// * @throws IOException
	// */
	// protected void RedicertLoginWithParams(Map<String, String> params) throws
	// IOException {
	// String loginUrl = systemConstant.getLoginurl();
	//
	// if (params == null || params.size() < 1) {
	// getHttpServletResponse().sendRedirect(loginUrl);
	// return;
	// }
	// int i = 1;
	// for (Map.Entry<String, String> entry : params.entrySet()) {
	// String key = entry.getKey();
	// String value = entry.getValue().toString();
	// loginUrl += (i++ == 1 ? "?" : "&") + key + "=" + value;
	// }
	// getHttpServletResponse().sendRedirect(loginUrl);
	// }
}
