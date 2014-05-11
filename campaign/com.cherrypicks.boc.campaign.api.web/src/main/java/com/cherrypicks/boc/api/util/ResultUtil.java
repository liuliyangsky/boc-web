package com.cherrypicks.boc.api.util;

import com.cherrypicks.boc.api.web.view.ResultVO;
import com.cherrypicks.boc.common.util.Json;

public class ResultUtil {

	public static String getResultJson(Object object) {
		ResultVO<Object> resultVo = new ResultVO<Object>();
		resultVo.setResult(object);
		return Json.toJson(resultVo);
	}

	public static String getResultJson(int errorCode, String errorMessage)  {
		ResultVO<Object> resultVo = new ResultVO<Object>();
		
		resultVo.setErrorCode(errorCode);
		resultVo.setErrorMessage(errorMessage);
		
		return Json.toJson(resultVo);
    }
	
	public static String getResultJson(Object object,int errorCode, String errorMessage) {
		ResultVO<Object> resultVo = new ResultVO<Object>();
		resultVo.setResult(object);
		resultVo.setErrorCode(errorCode);
		resultVo.setErrorMessage(errorMessage);
		return Json.toJson(resultVo);
	}

}
