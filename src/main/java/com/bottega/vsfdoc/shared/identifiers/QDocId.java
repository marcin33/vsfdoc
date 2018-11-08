package com.bottega.vsfdoc.shared.identifiers;

import java.util.UUID;

import lombok.NonNull;
import lombok.Value;

@Value
public class QDocId {

	@NonNull
	private final UUID id;

	public UUID value() {
	    return id;
    }

}
