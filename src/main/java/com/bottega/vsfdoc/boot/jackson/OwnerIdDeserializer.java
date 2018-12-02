package com.bottega.vsfdoc.boot.jackson;

import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.UUID;

@JsonComponent
public class OwnerIdDeserializer extends JsonDeserializer<OwnerId> {

	@Override
	public OwnerId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		return OwnerId.of(UUID.fromString(jsonParser.getValueAsString()));
	}
}
