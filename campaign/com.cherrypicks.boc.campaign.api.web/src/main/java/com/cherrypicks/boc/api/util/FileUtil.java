package com.cherrypicks.boc.api.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang.StringUtils;

public class FileUtil {
		 
	//新建文件
	public static File createFile(String filePath,String fileName){
	    try{
	    	
	    	File file = new File(filePath);
            //建立目录
			if (!file.exists()) {
				
				file.mkdirs();
			}
			//建立文件
			File file1 = new File(filePath+fileName);
			if (!file1.exists()) {
				
				file1.createNewFile();
			}

			return file1;
			
	    }catch (Exception e) {
	    	System.err.println("create File error");
	    	e.printStackTrace();
	      return null;
	    }
	    
	}

	//删除文件
	public static void delFile(String filePath,String fileName){
		
	    try{
	    	File file = new File(filePath+fileName);
	    	file.delete();
	    }catch (Exception e) {
	      System.err.println("delete File error");
	      e.printStackTrace();
	    }
	}
	
	//字符串写入文件
	public static void writeFile(String filePath,String fileName,String news){

	  Writer pw=null;
	  try{
		 
		  File file=createFile(filePath, fileName);
	  
		  pw=new FileWriter(file);
		  String keydd = new String(news.getBytes("UTF-8"),"UTF-8");
		  pw.write(keydd);
		  pw.flush();
	      pw.close();
	  }catch(IOException ex){
		  
		   ex.printStackTrace();
		   
       }finally{
    	   try {
    		   pw.close();
			} catch (Exception e) {
				 e.printStackTrace();
			}
	  }
	 
	}
	
	/*
	 * create csv file
	 */
	public static String createCsv(String filePath,String fileName,String content){
	       
			if(StringUtils.isBlank(filePath)|| StringUtils.isBlank(fileName)){
				return null;
			}
			FileUtil.writeFile(filePath, fileName+".csv", content);
			
			return filePath+fileName;
	}
	 
	public static void main(String aa[]){
		writeFile("E:/data/upload/diyTop/","top20.json","w 我不中文大家好。。。。。。。。。。wwwww");
	}
} 