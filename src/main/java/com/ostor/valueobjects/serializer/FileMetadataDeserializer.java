package com.ostor.valueobjects.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ostor.valueobjects.clientrequest.FileMetadata;

public class FileMetadataDeserializer extends StdDeserializer<FileMetadata> {
	/**
	 */
	private static final long serialVersionUID = 1L;

	public FileMetadataDeserializer() {
		this(null);
	}

	public FileMetadataDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public FileMetadata deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode rootNode = jp.getCodec().readTree(jp);
		if (rootNode.isMissingNode())
			return null;
		FileMetadata fileMetadata = FileMetadata.deserializeFileMetadata(rootNode);

		return fileMetadata;
	}

}
