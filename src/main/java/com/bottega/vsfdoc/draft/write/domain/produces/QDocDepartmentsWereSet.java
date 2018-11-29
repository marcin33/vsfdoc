package com.bottega.vsfdoc.draft.write.domain.produces;

import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class QDocDepartmentsWereSet implements DomainEvent {

	private final QDocId qDocId;
	private final List<DepartmentId> departmentIds;

	@Override
	public UUID getId() {
		return qDocId.value();
	}

}
