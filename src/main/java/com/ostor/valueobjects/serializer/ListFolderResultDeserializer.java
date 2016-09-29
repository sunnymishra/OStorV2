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
import com.ostor.valueobjects.clientrequest.ListFolderResult;
import com.ostor.valueobjects.clientrequest.Metadata;

public class ListFolderResultDeserializer extends StdDeserializer<ListFolderResult> {
	public ListFolderResultDeserializer() {
		this(null);
	}

	public ListFolderResultDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ListFolderResult deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		JsonNode rootNode = jp.getCodec().readTree(jp);
		Metadata entry;
		/*
		 * String tag = null; tag = readTag(jp); if ("".equals(tag)) { tag =
		 * null; }
		 */
		List<Metadata> entries = new ArrayList<Metadata>();
		JsonNode entriesArrayNode = rootNode.path("entries");
		if (entriesArrayNode.isMissingNode())
			return null;
		if (entriesArrayNode.isArray()) {
			for (JsonNode entryNode : entriesArrayNode) {
				String tag = entryNode.path(".tag").asText();
				if (tag == null || "".equals(tag)) {
					entry = Metadata.deserializeMetadata(entryNode);
					entries.add(entry);
				} else if ("file".equals(tag)) {
					entry = FileMetadata.deserializeFileMetadata(entryNode);
					entries.add(entry);
				}
			}
		}

		// int id = (Integer) ((IntNode) node.get("name")).numberValue();
		String cursor = rootNode.get("cursor").asText();
		Boolean hasMore = (Boolean) ((BooleanNode) rootNode.get("has_more")).booleanValue();

		return new ListFolderResult(entries, cursor, hasMore);
	}

	protected static final String TAG_FIELD = ".tag";


}
