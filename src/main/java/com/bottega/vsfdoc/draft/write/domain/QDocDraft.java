package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.*;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

class QDocDraft {

	private final QDocId qDocId;
	private final OwnerId ownerId;
	private final QDocState state;
	private final String content;
	private final VerifierId verifierId;
	private final Departments departments;

	QDocDraft(QDocDraftRecord record) {
		this.qDocId = QDocId.of(record.getId());
		this.ownerId = record.getOwnerId()
				.map(OwnerId::of)
				.orElse(null);
		this.content = record.getContent()
				.orElse(null);
		this.state = record.getState()
				.map(QDocState::valueOf)
				.orElse(null);
		this.verifierId = record.getVerifierId()
				.map(VerifierId::of)
				.orElse(null);
		this.departments = new Departments(record.getDepartments());
	}

	DomainEvent create(QDocNumber number, OwnerId ownerId, QDocType type) {
		requireNonNull(number, "number is required");
		requireNonNull(ownerId, "ownerId is required");
		requireNonNull(type, "type is required");

		return new QDocWasCreated(qDocId, number.getValue(), ownerId, type.name(), QDocState.NEW.name());
	}

	DomainEvent updateContent(String content) {
		if (state.isNew() && isNotBlank(content)) {
			return new QDocContentWasUpdated(qDocId, content);
		}
		throw new IllegalStateException("Cannot update content");
	}

	DomainEvent assignToVerifier(VerifierId verifierId) {
		if (state.isNew() && !ownerId.isSame(verifierId)) {
			return new QDocWasAssignToVerifier(qDocId, verifierId);
		}
		throw new IllegalStateException("Cannot assign to verifier");
	}

	DomainEvent setDepartments(List<DepartmentId> departmentIds) {
		requireNonNull(departmentIds, "departments are required");

		if (!departmentIds.isEmpty() && state.isNew()) {
			return new QDocDepartmentsWereSet(qDocId, departmentIds);
		}
		throw new IllegalStateException("Cannot set departments");
	}

	DomainEvent sendToVerification() {
		if (state.isNew() && verifierId != null && isNotBlank(content)
				&& departments.isNotEmpty()) {
			return new QDocWasSendToVerification(qDocId, QDocState.IN_VERIFICATION.name());
		}
		throw new IllegalStateException("Cannot send to verification");
	}

	DomainEvent verify() {
		if (state.isInVerification()) {
			return new QDocWasVerified(qDocId, QDocState.VERIFIED.name());
		}
		throw new IllegalStateException("Cannot verify");
	}

	DomainEvent decline(String declineNote) {
		if (state.isInVerification() && isNotBlank(declineNote)) {
			return new QDocWasDecline(qDocId, declineNote, QDocState.NEW.name());
		}
		throw new IllegalStateException("Cannot decline");
	}

	DomainEvent publish() {
		if (state.isVerified()) {
			return new QDocWasPublished(qDocId, QDocState.PUBLISHED.name());
		}
		throw new IllegalStateException("Cannot publish");
	}
}
