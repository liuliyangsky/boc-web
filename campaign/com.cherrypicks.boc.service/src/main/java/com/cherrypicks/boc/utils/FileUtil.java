package com.cherrypicks.boc.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static void save(String folderPath, String fileName, InputStream is)
			throws IOException {
		if (folderPath == null || fileName == null || is == null)
			throw new IllegalArgumentException("Parameter must full fill.");
		// check the folder exists, if not to create one
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		if (folderPath.lastIndexOf("/") != folderPath.length() - 1)
			folderPath += "/";
		
		String path = folderPath + fileName;
		FileOutputStream fos = new FileOutputStream(new File(path));
		try {
			byte[] buf = new byte[1024];
			int p = 0;
			while ((p = is.read(buf)) != -1) {
				fos.write(buf, 0, p);
				fos.flush();
			}
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException err) {
			}
			try {
				if (fos != null)
					fos.flush();
			} catch (IOException err) {
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException err) {
			}
		}
	}

	public static boolean delete(String filePath, String fileName) {
		if (filePath.lastIndexOf("/") != filePath.length() - 1)
			filePath += "/";
		
		File file = new File(filePath + fileName);
		if (file.exists() && file.isFile())
			return file.delete();
		return false;
	}

	public static boolean deleteDirectory(String folderPath) {
		if (folderPath.lastIndexOf("/") != folderPath.length() - 1)
			folderPath += "/";
		
		File folder = new File(folderPath);
	    if (folder.exists() && folder.isDirectory()) {
	        String[] children = folder.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDirectory(folderPath + children[i]);
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return folder.delete(); // The directory is empty now and can be deleted.
	}
	
	public static File get(String folderPath, String fileName) {
		if (folderPath.lastIndexOf("/") != folderPath.length() - 1)
			folderPath += "/";
		
		File file = new File(folderPath + fileName);
		if (file.exists() && file.isFile())
			return file;
		return null;
	}

	@SuppressWarnings("resource")
	public static String readLine(String folderPath, String fileName) {
		File file = get(folderPath, fileName);
		if (file == null)
			return null;
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(fileReader);
			String line = bufReader.readLine();
			return line;
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@SuppressWarnings("resource")
	public static String read(String folderPath, String fileName) throws IOException {
		File file = new File(folderPath + fileName);
		if (file.exists() && file.isFile()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			int i;
			while ((i = reader.read()) != -1) {
				sb.append((char) i);
			}
			return sb.toString();
		}
		return null;
	}

	public static void write(String folderPath, String fileName, String content, boolean append) {
		if (StringUtils.isEmpty(folderPath) || StringUtils.isEmpty(fileName) || StringUtils.isEmpty(content))
			throw new IllegalArgumentException("Parameter must full fill.");

		if (folderPath.lastIndexOf("/") != folderPath.length() - 1)
			folderPath += "/";
		
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		try {
			FileWriter fileWriter = new FileWriter(new File(folderPath + fileName), append);
			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
			bufWriter.write(content);
			bufWriter.flush();
			bufWriter.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void copyToDirectory(String source, String targetDirectory)
			throws FileNotFoundException, IOException {
		File sourceFile = new File(source);
		if (sourceFile.exists() == false)
			return;

		if (sourceFile.isFile()) {
			copyFileToDirectory(sourceFile, targetDirectory);
		} else if (sourceFile.isDirectory()) {
			copyDirectoryToDirectory(sourceFile, targetDirectory);
		}
	}

	private static void copyFileToDirectory(File file, String targetDirectory)
			throws FileNotFoundException, IOException {
		save(targetDirectory, file.getName(), new FileInputStream(file));
	}

	private static void copyDirectoryToDirectory(File directory,
			String targetDirectory) throws FileNotFoundException, IOException {
		File[] fileArr = directory.listFiles();
		for (File file : fileArr) {
			if (file.isFile()) {
				copyFileToDirectory(file, targetDirectory);
			} else if (file.isDirectory()) {
				copyDirectoryToDirectory(file, targetDirectory);
			}
		}
	}

}
