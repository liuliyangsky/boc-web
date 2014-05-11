package com.cherrypicks.boc.common.jms;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;


public class JsonMessageConverter implements MessageConverter {

	private final Log log = LogFactory.getLog(JsonMessageConverter.class);
	
	private MessageConverter converter = new SimpleMessageConverter();
	private ObjectMapper objectMapper = new ObjectMapper();
	private static String COMPRESSEDFIELD = "compressed";
	private static String COMPRESSEDFLAG = "1";
	private boolean compression;
	private Class<?> sendType;
	private Class<?> receiveType;
	

	public JsonMessageConverter(Class<?> sendType, Class<?> receiveType) {
		if (sendType == null) {
			throw new IllegalArgumentException("sendType should not be null.");
		} else if (receiveType == null) {
			throw new IllegalArgumentException("receiveType should not be null.");
		} else {
			this.sendType = sendType;
			this.receiveType = receiveType;
		}
	}


	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		//System.out.println("Received Message: " + message);
		log.debug(message);
		
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date.toString() + "JsonMessageConverter Received fromMessage.");
		Object obj = converter.fromMessage(message);
		try {
			if (obj instanceof byte[]) {
				if (COMPRESSEDFLAG.equals(message.getStringProperty(COMPRESSEDFIELD))) {
					ByteArrayInputStream bais = new ByteArrayInputStream(((byte[])obj));
					GZIPInputStream dis = new GZIPInputStream(bais);
					return objectMapper.readValue(dis, HashMap.class);
				} else {
					obj = new String((byte[])obj);
				}
			} else if (obj instanceof String) {
				obj = objectMapper.readValue((String)obj, receiveType);
			}
		} catch (Exception e) {
			String msg = "Fail to convert " + receiveType.getName() + " to Json";
			throw new MessageConversionException(msg, e);
		}	
		return obj;
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		try {
			//System.out.println("Converting from " + sendType.getName() + " to JMS: " + object);
			log.debug(object);
			
			Date date = new Date(System.currentTimeMillis());
			System.out.println(date.toString() + "JsonMessageConverter Converting toMessage.");
			if (compression) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				GZIPOutputStream gos = new GZIPOutputStream(baos);
				objectMapper.writeValue(gos, object);
				Message m = converter.toMessage(baos.toByteArray(), session);
				m.setStringProperty(COMPRESSEDFIELD, COMPRESSEDFLAG);
				return m;
			} else {					
				String jsonString = objectMapper.writeValueAsString(object);
				return converter.toMessage(jsonString, session);
			}
		} catch (Exception e) {
			String msg = "Fail to convert " + sendType.getName() + " to Json";
			throw new MessageConversionException(msg, e);
		}
	}

	public boolean isCompression() {
		return compression;
	}

	public void setCompression(boolean compression) {
		this.compression = compression;
	}	
	
}
