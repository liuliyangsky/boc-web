<%@page import="com.cherrypicks.boc.common.util.CodeStatus,com.cherrypicks.boc.api.web.view.ResultVO,com.cherrypicks.boc.common.util.Json,org.apache.commons.lang.math.NumberUtils"%>
<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%
	String viewName = com.cherrypicks.boc.api.web.controller.WebHandlerExceptionResolver.getErrorInfo(request,response,exception);

Object errorCode = request.getAttribute(com.cherrypicks.boc.api.web.controller.WebHandlerExceptionResolver.ERROR_CODE_PARAMETER_NAME);
Object errorMessage = request.getAttribute(com.cherrypicks.boc.api.web.controller.WebHandlerExceptionResolver.ERROR_MESSAGE_PARAMETER_NAME);

ResultVO<Object> result = new ResultVO<Object>();
result.setErrorCode(NumberUtils.toInt(String.valueOf(errorCode), CodeStatus.UNKNOWN));
result.setErrorMessage(String.valueOf(errorMessage));
result.setResult(null);

request.setAttribute("message", Json.toJson(result));
%>
<%@include file="message.json.jsp" %>
