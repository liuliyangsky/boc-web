package com.cherrypicks.boc.api.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cherrypicks.boc.common.util.PropertiesUtil;
import com.rosaloves.bitlyj.Bitly;
import com.rosaloves.bitlyj.Bitly.Provider;
import com.rosaloves.bitlyj.ShortenedUrl;

public class BitlyUrlUtil {
	private static Log logger = LogFactory.getLog(BitlyUrlUtil.class);
	
	private static final String PROJECT_ENVIRONMENT = PropertiesUtil.getProperty("PROJECT.ENVIRONMENT");
	
	private static final String PROJECT_ENVIRONMENT_DEV = "DEV";
	
	private static final String PROJECT_ENVIRONMENT_PRO = "PRO";
	
	private static final String PROD_BITLY_USER = PropertiesUtil.getProperty("PROD.BITLY.USER");
	private static final String PROD_BITLY_API_KEY = PropertiesUtil.getProperty("PROD.BITLY.API.KEY");
	
	private static final String STAGING_BITLY_USER = PropertiesUtil.getProperty("STAGING.BITLY.USER");;
	private static final String STAGING_BITLY_API_KEY = PropertiesUtil.getProperty("STAGING.BITLY.API.KEY");
	
	private static Provider bitly;
	
	static{
		if(PROJECT_ENVIRONMENT.equals(PROJECT_ENVIRONMENT_DEV)){
			bitly = Bitly.as(STAGING_BITLY_USER, STAGING_BITLY_API_KEY);
		}
		
		if(PROJECT_ENVIRONMENT.equals(PROJECT_ENVIRONMENT_PRO)){
			bitly = Bitly.as(PROD_BITLY_USER, PROD_BITLY_API_KEY);
		}
		
	}
	
	public static String LongToShrtURL(String longUrl){
		String shortenedUrlString = null;
		logger.info("Long URL : "+ longUrl);
		//System.out.println( "Long URL : "+ longUrl);
		
		try{
			ShortenedUrl shortenedUrl = bitly.call(Bitly.shorten(longUrl));
			shortenedUrlString = shortenedUrl.getShortUrl();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
      
        logger.info("Shortened URL : "+ shortenedUrlString);
        //System.out.println("Shortened URL : "+ shortenedUrlString);
        return shortenedUrlString;
	}
	
	public static void main(String[] args) {
		String longUrl = "http://www.watsons.com.cn/系统分类/特惠/c/homepage_special_offer?sort=456";
		BitlyUrlUtil.LongToShrtURL(longUrl);
	}
}
