package com.xhz.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xhz.entity.SmbmsUser;

/**
 * 拦截器
 * @author Administrator
 *
 */
public class SmbmsUserInterceptor extends HandlerInterceptorAdapter{

	/**
	 * false:拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//拿到用户请求地址
		String url = request.getRequestURI();
		//截取最后一个"/"
		String substring = url.substring(url.lastIndexOf("/")+1);
		SmbmsUser user = (SmbmsUser) request.getSession().getAttribute("smbmsUser");
		if (user==null && !substring.equals("login.do")) {
			response.sendRedirect(request.getContextPath()+"/error.jsp");
			return false;
		}
		return true;
	}
	
}
