
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String username=request.getParameter("username");
	String password=request.getParameter("password"	);
	
	if("admin".equals(username)){
		if("admin".equals(password)){
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}else{
			request.setAttribute("msg","密码错误！");
			request.getRequestDispatcher("login.jsp").forward(request,response);
			
		}
	}else{
		request.setAttribute("msg","该用户不存在！");
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

%>