package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.*;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import lombok.Getter;

import java.util.List;

import static java.util.Objects.requireNonNull;

@Getter
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

	DomainEvent updateContent(QDocValidator validator, String content) {
		validator.state(QDocState.NEW)
				.notBlankContent(content)
				.validate();

		return new QDocContentWasUpdated(qDocId, content);
	}

	DomainEvent assignToVerifier(QDocValidator validator, VerifierId verifierId) {
		validator.state(QDocState.NEW)
				.ownerIsNotVerifier(verifierId)
				.validate();

		return new QDocWasAssignToVerifier(qDocId, verifierId);
	}

	DomainEvent setDepartments(QDocValidator validator, List<DepartmentId> departmentIds) {
		validator.state(QDocState.NEW)
				.departmentIdsNotEmpty(departmentIds)
				.validate();

		return new QDocDepartmentsWereSet(qDocId, departmentIds);
	}

	DomainEvent sendToVerification(QDocValidator validator) {
		validator.state(QDocState.NEW)
				.notBlankContent(content)
				.departmentsNotEmpty()
				.verifierNotEmpty()
				.validate();

		return new QDocWasSendToVerification(qDocId, QDocState.IN_VERIFICATION.name());
	}

	DomainEvent verify(QDocValidator validator) {
		validator.state(QDocState.IN_VERIFICATION)
				.validate();

		return new QDocWasVerified(qDocId, QDocState.VERIFIED.name());
	}

	DomainEvent decline(QDocValidator validator, String declineNote) {
		validator.state(QDocState.IN_VERIFICATION)
				.notBlankDeclineNote(declineNote)
				.validate();

		return new QDocWasDecline(qDocId, declineNote, QDocState.NEW.name());
	}

	DomainEvent publish(QDocValidator validator) {
		validator.state(QDocState.VERIFIED)
				.validate();

		return new QDocWasPublished(qDocId, QDocState.PUBLISHED.name());
	}
}
