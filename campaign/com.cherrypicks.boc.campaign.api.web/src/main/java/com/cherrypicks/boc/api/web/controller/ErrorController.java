package com.cherrypicks.boc.api.web.controller;

import java.lang.reflect.GenericSignatureFormatError;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统错误处理类
 * 
 */
@Controller
public class ErrorController {
	private final static Log LOGGER = LogFactory.getLog(ErrorController.class);

	@RequestMapping(value = "/error.do")
	public String error(HttpServletRequest request, ModelMap model) {

		return "error";
	}

	public static void printStackTrace(String url, Throwable e) {
		LOGGER.error("url:" + url);
		LOGGER.error(LOGGER, e);
	}

	public static String getMessage(Throwable e) {
		if (e instanceof DataAccessException) {
			return "操作数据库出错，请稍后重试.";
		}
		if (e instanceof org.springframework.dao.DataAccessException) {
			return "操作数据库出错，请稍后重试.";
		}
		if (e instanceof SQLException) {
			return "操作数据库出错，请稍后重试.";
		}
		if (e instanceof GenericSignatureFormatError) {
			return "更新程序后，还没有重启服务.";
		}

		LOGGER.error(e.getMessage(), e);

		return e.getMessage();
	}

	public static String getJson(Throwable e, ModelMap model) {
		String message = ("{\"errmsg\": \"" + e.getMessage() + "\"}");
		model.addAttribute("message", message);
		return "message";
	}
}
