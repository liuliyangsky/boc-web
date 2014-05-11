<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.File" %>
<%@ page import="com.cherrypicks.boc.utils.PropertiesUtil" %>

<%
String fileName = request.getParameter("v");
String path = PropertiesUtil.getProperty("image.path");

FileInputStream fis = null;
ServletOutputStream sos = null;

try {
    File file = new File(path + fileName);
    System.out.println("path:" + path + " fileName:" + fileName);
    if (!file.exists()) return;
	fis = new FileInputStream(path + fileName);
	sos = response.getOutputStream();
	int size = fis.available();
	byte buf[] = new byte[size];
	int len = 0;
	while ((len = fis.read(buf)) != -1) {
	    sos.write(buf, 0, len);
	}
	sos.flush();
	out.clear();
	out = pageContext.pushBody();
} catch (Exception err) {
} finally {
	if (fis != null) {
		fis.close();
	}
	if (sos != null) {
		sos.close();
	}
}

%>