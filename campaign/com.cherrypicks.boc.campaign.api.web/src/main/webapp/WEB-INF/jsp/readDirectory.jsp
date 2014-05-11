
<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8"%>
 
<%@page import="java.io.*"%>
<%@page import="java.lang.*"%>
<html>
<%
/* 	String path =(String)request.getAttribute("grandpriceFilePath");
    String webPath =(String)request.getAttribute("grandpriceFileUrlPath");

	if(request.getParameter("path")!=null)
	{
	 
	path = path.trim();
	webPath = webPath.trim();
	 
	}
 
	File f = new File(path);
	
	if (!f.exists())
	 
	{
	 
	        out.println(path+" not exists");
	 
	        return;
	 
	}


	File fa[] = f.listFiles();
 
	for(int i=0;i<fa.length;i++)
 
	{
         File fs = fa[i];
 
        if (fs.isDirectory())
 
        {
 
           out.print("[目录]<a href=\""+fs.getAbsolutePath()+"\" class=\"dirlink\" title=\"最后修改时间:"+fs.lastModified()+" \r\n文件大小:"+fs.length()+" bytes"+"\r\n是否可写:"+fs.canWrite()+"\">");

           out.println(""+fs.getName()+"");

           out.print("</a><br>");
 
        }else{
 
            out.print("[文件]<a href=\""+fs.getAbsolutePath()+"\" target=\"_blank\" title=\"最后修改时间:"+fs.lastModified()+" \r\n文件大小:"+fs.length()+"bytes\r\n是否可写:"+fs.canWrite()+"\">");                   
 
            out.println(fs.getName());
 
            out.print("</a><br>");
 
        }
 
}
 */
%>
<h2>success</h2>
</html>