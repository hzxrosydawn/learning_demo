<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:directive.page import="org.springframework.core.io.Resource"/>
<jsp:directive.page import="org.springframework.web.context.support.ServletContextResource"/>
<jsp:directive.page import="org.springframework.web.util.WebUtils"/>
<%
   /*ServletContextResource以相对于Web应用根路径读取资源文件*/
   Resource res3 = new ServletContextResource(application,"/WEB-INF/classes/conf/file1.txt");
   out.print(res3.getFilename()+"<br/>");
   out.print(WebUtils.getTempDir(application).getAbsolutePath());
%>