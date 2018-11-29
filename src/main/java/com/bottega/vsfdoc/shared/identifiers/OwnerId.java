package com.bottega.vsfdoc.shared.identifiers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OwnerId {

	@NonNull
	private final UUID value;

	public static OwnerId of(UUID id) {
		requireNonNull(id, "id is required");

		return new OwnerId(id);
	}

	public UUID value() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	public boolean isSame(VerifierId verifierId) {
		return verifierId != null && value.equals(verifierId.value());
	}
}
