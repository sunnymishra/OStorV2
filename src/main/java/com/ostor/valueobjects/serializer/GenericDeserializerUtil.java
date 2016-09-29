package com.ostor.valueobjects.serializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ostor.util.Util;

public class GenericDeserializerUtil {
	public static String getNullableText(JsonNode entryNode, String nodeName) {
		String nodeValue = null;
		JsonNode nullableNode = entryNode.path(nodeName);
		if(!nullableNode.isMissingNode())
			nodeValue = nullableNode.asText();
		return nodeValue;
	}

	public static Date deserializeTimestamp(String dateString) throws IOException, JsonParseException {
		if(dateString==null)
			return null;
		try {
			return Util.parseTimestamp(dateString);
		} catch (ParseException ex) {
			throw new JsonParseException("Malformed timestamp: '" + dateString + "'", null, ex);
		}
	}
}
