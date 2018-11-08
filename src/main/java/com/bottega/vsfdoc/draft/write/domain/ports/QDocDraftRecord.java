package com.bottega.vsfdoc.draft.write.domain.ports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.bottega.vsfdoc.draft.write.domain.QDocState;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocContentWasUpdated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;

import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class QDocDraftRecord {

	@Id
	private UUID id;
	private String content;
	private String number;
	private String state;
	private UUID verifierId;
	@OneToMany
	private List<UUID> departments;

	QDocDraftRecord() {
		// JPA only
	}

	public QDocDraftRecord(UUID id) {
		this.id = id;
	}

	public void apply(DomainEvent event) {
		if (event instanceof QDocWasCreated) {
			this.number = ((QDocWasCreated) event).getQDocNumber();
		}
		else if (event instanceof QDocContentWasUpdated) {
			this.content = ((QDocContentWasUpdated) event).getContent();
		}
		else if (event instanceof QDocWasSendToVerification) {
			this.state = QDocState.IN_VERIFICATION.name();
		}
		else {
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
}
