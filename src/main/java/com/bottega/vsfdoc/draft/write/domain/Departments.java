package com.bottega.vsfdoc.draft.write.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import lombok.Getter;

@Getter
class Departments {

	private final List<DepartmentId> departmentIds = new ArrayList<>();

	Departments(List<UUID> departments) {
		Optional.ofNullable(departments)
			.ifPresent(departmentUuids -> departmentUuids
				.forEach(uuid -> this.departmentIds.add(DepartmentId.of(uuid))));
	}

	boolean isNotEmpty() {
		return !departmentIds.isEmpty();
	}
}
