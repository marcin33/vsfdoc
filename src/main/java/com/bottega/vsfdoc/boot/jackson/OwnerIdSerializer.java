package com.bottega.vsfdoc.boot.jackson;

import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class OwnerIdSerializer extends JsonSerializer<OwnerId> {

	@Override
	public void serialize(OwnerId ownerId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		if (ownerId != null) {
			jsonGenerator.writeString(ownerId.value().toString());
		}
	}
}
