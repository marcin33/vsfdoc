package com.bottega.vsfdoc.shared.identifiers;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class DepartmentId {

    @NonNull
    private final UUID id;

}
