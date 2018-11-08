package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class DoSetDepartments {

    @NonNull
    private final QDocId qDocId;

    @NonNull
    private final List<DepartmentId> departmentIds;

}
