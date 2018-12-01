package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.command.Command;
import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Data
public class DoSetDepartments implements Command {

    @NonNull
    private final QDocId id;

    @NonNull
    private final List<DepartmentId> departmentIds;

}
