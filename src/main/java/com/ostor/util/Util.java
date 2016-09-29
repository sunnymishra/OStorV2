package com.ostor.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonFactory;

public class Util {
    public static final JsonFactory JSON = new JsonFactory();

    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int LONG_FORMAT_LENGTH = DATE_TIME_FORMAT.replace("'", "").length();
    private static final int SHORT_FORMAT_LENGTH = DATE_FORMAT.replace("'", "").length();

    public static String formatTimestamp(Date timestamp) {
        DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        format.setCalendar(new GregorianCalendar(UTC));
        return format.format(timestamp);
    }

    public static Date parseTimestamp(String timestamp) throws ParseException {
        int length = timestamp.length();

        DateFormat format = null;
        if (length == LONG_FORMAT_LENGTH) {
            format = new SimpleDateFormat(DATE_TIME_FORMAT);
        } else if (length == SHORT_FORMAT_LENGTH) {
            format = new SimpleDateFormat(DATE_FORMAT);
        } else {
            throw new ParseException("timestamp has unexpected format: '" + timestamp + "'", 0);
        }

        format.setCalendar(new GregorianCalendar(UTC));
        return format.parse(timestamp);
    }
    public static String getAuthToken(String authToken){
    	if(StringUtils.isEmpty(authToken)){
    		return null;
    	}
    	String BEARER_CAPS = "Bearer ";
    	String BEARER_SMALL = "bearer ";
    	boolean bearerCapsAvailable=false;
    	boolean bearerSmallAvailable=false;
    	
    	if(authToken.startsWith(BEARER_CAPS))
    		bearerCapsAvailable=true;
    	else if(authToken.startsWith(BEARER_SMALL))
    		bearerSmallAvailable=true;
    	if(!bearerCapsAvailable && !bearerSmallAvailable)
    		throw new IllegalArgumentException("Token doesn't begin with \"Bearer\"");
    	if(authToken.length()<BEARER_CAPS.length())
    		throw new IllegalArgumentException("Token length too short");
    	
    	if(bearerCapsAvailable)
    		authToken=authToken.substring(BEARER_CAPS.length());
		else if(bearerSmallAvailable)
			authToken=authToken.substring(BEARER_SMALL.length());
			
    	return authToken;
	}

    public static String processAuthorization(String authToken) {
    	try {
			authToken = Util.getAuthToken(authToken);
		} catch (IllegalArgumentException e) {
			authToken=null;
		}
		return authToken;
	}
}
