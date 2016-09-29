package com.ostor.valueobjects.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.ostor.valueobjects.clientrequest.FileMetadata;
import com.ostor.valueobjects.clientrequest.FolderMetadata;
import com.ostor.valueobjects.clientrequest.ListFolderResult;
import com.ostor.valueobjects.clientrequest.Metadata;

public class FolderMetadataDeserializer extends StdDeserializer<FolderMetadata> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FolderMetadataDeserializer() {
		this(null);
	}

	public FolderMetadataDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public FolderMetadata deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode rootNode = jp.getCodec().readTree(jp);
		if (rootNode.isMissingNode())
			return null;
		FolderMetadata folderMetadata = FolderMetadata.deserializeFolderMetadata(rootNode);

		return folderMetadata;
	}

}
