package com.bottega.vsfdoc.draft.write.domain.ports;

import com.bottega.vsfdoc.draft.write.domain.produces.*;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
public class QDocDraftRecord {

	@Id
	private UUID id;
	private UUID ownerId;
	private String type;
	private String content;
	private String number;
	private String state;
	private UUID verifierId;
	@OneToMany
	private List<UUID> departments;
	private String declineNote;

	QDocDraftRecord() {
		// JPA only
	}

	public QDocDraftRecord(UUID id) {
		this.id = id;
	}

	public void apply(DomainEvent event) {
		if (event instanceof QDocWasCreated) {
			this.number = ((QDocWasCreated) event).getQDocNumber();
			this.state = ((QDocWasCreated) event).getState();
			this.ownerId = ((QDocWasCreated) event).getOwnerId().value();
			this.type = ((QDocWasCreated) event).getType();
		} else if (event instanceof QDocContentWasUpdated) {
			this.content = ((QDocContentWasUpdated) event).getContent();
		} else if (event instanceof QDocWasAssignToVerifier) {
			this.verifierId = ((QDocWasAssignToVerifier) event).getVerifierId().value();
		} else if (event instanceof QDocDepartmentsWereSet) {
			this.departments = ((QDocDepartmentsWereSet) event).getDepartmentIds()
					.stream()
					.map(DepartmentId::value)
					.collect(Collectors.toList());
		} else if (event instanceof QDocWasSendToVerification) {
			this.state = ((QDocWasSendToVerification) event).getState();
		} else if (event instanceof QDocWasVerified) {
			this.state = ((QDocWasVerified) event).getState();
		} else if (event instanceof QDocWasDecline) {
			this.state = ((QDocWasDecline) event).getState();
			this.declineNote = ((QDocWasDecline) event).getDeclineNote();
		} else if (event instanceof QDocWasPublished) {
			this.state = ((QDocWasPublished) event).getState();
		} else {
			throw new IllegalStateException("Event not implemented: " + event);
		}
	}

	public Optional<String> getNumber() {
		return Optional.ofNullable(number);
	}

	public Optional<String> getContent() {
		return Optional.ofNullable(content);
	}

	public Optional<String> getState() {
		return Optional.ofNullable(state);
	}

	public Optional<UUID> getVerifierId() {
		return Optional.ofNullable(verifierId);
	}

	public Optional<UUID> getOwnerId() {
		return Optional.ofNullable(ownerId);
	}

	public Optional<String> getType() {
		return Optional.ofNullable(type);
	}
}
