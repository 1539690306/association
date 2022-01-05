package com.ls.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ls.pojo.MinGanCi;
import com.ls.pojo.Notice;

public class TestFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String article = request.getParameter("article");
		
		String[] words = request.getParameterValues("words");
		int pos=-1;
		for(String word:words) {
			pos = article.indexOf(word);
			if(pos != -1) {
				break;
			}
		}
		if (pos != -1) {
			req.setAttribute("msg", "此段信息中含有敏感词");
			request.getRequestDispatcher("toAddNotice").forward(req, res);
			
			System.out.println("拦截成功#########################");
		} else {
			chain.doFilter(request, response);				
		}		
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
