package com.bottega.vsfdoc.shared.identifiers;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DepartmentId {

    @NonNull
    private final UUID value;

    public static DepartmentId of(UUID id) {
        requireNonNull(id, "id is required");

        return new DepartmentId(id);
    }

    public UUID value() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
