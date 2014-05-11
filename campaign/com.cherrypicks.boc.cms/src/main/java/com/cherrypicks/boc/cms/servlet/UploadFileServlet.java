package com.cherrypicks.boc.cms.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cherrypicks.boc.utils.FileUtil;
import com.cherrypicks.boc.utils.PropertiesUtil;
import com.cherrypicks.boc.utils.StringUtil;

public class UploadFileServlet extends HttpServlet {

	private static final long serialVersionUID = 6588342323300244283L;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String UPLOAD_TEMP_PATH = PropertiesUtil.getProperty("temp.path");
	
	private Long maxSize = null;
	
	//private ISequenceService sequenceService;
	
	private void initSerivce() {
		//ServletContext application = getServletContext(); 
        //WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application); 
       /* if (sequenceService == null) {
        	sequenceService = (ISequenceService) ctx.getBean("sequenceService");
        }*/
	}
	
	
	@SuppressWarnings({ "unchecked" })
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Begin: FileUploadServlet.doPost......");
		
		if (request == null || request.getContentType() == null  || !request.getContentType().startsWith(ServletFileUpload.MULTIPART_FORM_DATA)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
		
		logger.debug("init service");
		initSerivce();
		
        // Create a temp folder 
		HttpSession session = request.getSession();
		String realPath = session.getServletContext().getRealPath("/");
        File tempFile = new File(realPath + UPLOAD_TEMP_PATH);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);
        factory.setRepository(tempFile);
        
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileItemFactory(factory);
        upload.setSizeMax(getUploadFileMaxLength());
        upload.setHeaderEncoding("UTF-8"); 
        
        
        String callbackFunctionName = request.getParameter("callbackFunctionName");
        callbackFunctionName = callbackFunctionName == null || "".equals(callbackFunctionName) ? "unkonw" : callbackFunctionName.trim();
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("<script>");
        
        BufferedInputStream bis = null;
        String fileName = null;
        String fileExtension = "";
        String fileNamePrefix = null;
		String fileNameSuffix = null;
		String fileNameAliasCN = null;
		String fileNameAliasEN = null;
		

		String folderPath = null;
		FileItem fileItem = null;
				
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				if ("fileType".equals(item.getFieldName())) {
					String fileType = item.getString();
					if ("image".equals(fileType)){
						folderPath = PropertiesUtil.getProperty("image.path");
					}else if ("document".equals(fileType)){
						folderPath = PropertiesUtil.getProperty("document.path");
					}else if ("video".equals(fileType)){
						folderPath = PropertiesUtil.getProperty("video.path");
					}
				} else if ("file".equals(item.getFieldName())) {
					fileItem = item;
					
					// get fileName
					fileName = item.getName();
					if (!StringUtils.isEmpty(fileName)) {
						int pointIndex = fileName.lastIndexOf(".");
						if (pointIndex != -1) {
							fileExtension = fileName.substring(pointIndex);
						}
					}
				} else if ("fileNamePrefix".equals(item.getFieldName())) {
					fileNamePrefix = item.getString();
				} else if ("fileNameSuffix".equals(item.getFieldName())) {
					fileNameSuffix = item.getString();
				} else if ("fileNameAliasCN".equals(item.getFieldName())){
					fileNameAliasCN = item.getString();
				} else  if ("fileNameAliasEN".equals(item.getFieldName())){
					fileNameAliasEN = item.getString();
				}
			}
			
			// create file name
			if (StringUtil.isNotEmpty(fileNamePrefix) && StringUtil.isNotEmpty(fileNameSuffix)) {
				Long fileNameVersion = Long.valueOf(fileNamePrefix);
				fileName = String.format("%1$d%2$s%3$s", fileNameVersion, fileNameSuffix, fileExtension.toLowerCase());
			} else if (StringUtil.isNotEmpty(fileNameSuffix)) {
				//Sequence sequence = sequenceService.updateSequence(Sequence.LANDING_PAGE_IMAGE, UserLoginHelper.getLoginUserName());
				//Long version = sequence.getVersion();
				//fileName = String.format("%1$d%2$s%3$s", version, fileNameSuffix, fileExtension.toLowerCase());
			} else if (StringUtil.isNotEmpty(fileNameAliasCN)){
				fileName =fileNameAliasCN + fileExtension.toLowerCase();
				logger.debug("fileNameAliasCN:  "+fileNameAliasCN);
			} else if (StringUtil.isNotEmpty(fileNameAliasEN)){
				fileName =fileNameAliasEN + fileExtension.toLowerCase();
				logger.debug("fileNameAliasEN:  "+fileNameAliasEN);
			}else {
				fileName = UUID.randomUUID().toString() + fileExtension.toLowerCase();
			}
			bis = new BufferedInputStream(fileItem.getInputStream());
			
			FileUtil.save(folderPath, fileName, bis);
			
			out.write("window.parent." + callbackFunctionName + "(\"0\", \"" + fileName + "\");");
			out.write("</script>");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
            String exceptionMessage;
            if (e instanceof SizeLimitExceededException) {
                exceptionMessage = "The file was rejected because its size exceeds the configured maximum(" + PropertiesUtil.getProperty("uploadFile.maxSize") + ")!";
            } else {
                exceptionMessage = (e.getCause() == null) ? e.getMessage() : e.getCause().getMessage();
            }
            
            out.write("var exceptionMessage = \"" + replaceAllQuotationMark(exceptionMessage) + "\";");
            out.write("try {");
            out.write("window.parent." + callbackFunctionName + "(\"-1\", exceptionMessage);");
            out.write("} catch (ex) {");
            out.write("window.alert(exceptionMessage);");
            out.write("}");
            out.write("</script>");
		} finally {
			if (bis != null) {
				try {
					bis.close();
					bis = null;
				} catch (IOException e) {
					logger.warn(e.getMessage(), e);
				}
			}
			
			out.flush();
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("Not supported method: GET.");
		out.flush();
		out.close();
	}
	
	private long getUploadFileMaxLength() {
        if (maxSize != null) {
            return maxSize.longValue();
        }
        String str = PropertiesUtil.getProperty("uploadFile.maxSize");
        String unit = str.substring(str.length() - 1, str.length());
        String length = str.substring(0, str.length() - 1);
        long size;
        try {
            size = Long.parseLong(length);
        } catch (NumberFormatException ex) {
            maxSize = -1L;
            return maxSize.longValue();
        }
        if ("G".equalsIgnoreCase(unit)) {
            maxSize = new Long(size * 1024l * 1024l * 1024l);
            return maxSize.longValue();
        } else if ("M".equalsIgnoreCase(unit)) {
            maxSize = new Long(size * 1024l * 1024l);
            return maxSize.longValue();
        } else if ("K".equalsIgnoreCase(unit)) {
            maxSize = new Long(size * 1024l);
            return maxSize.longValue();
        } else {
            //unlimit
            maxSize = -1L;
            return maxSize.longValue();
        }
    }

	private String replaceAllQuotationMark(String str) {
        return str.replaceAll("\"", " ").replaceAll("'", " ");
    }

	
}
