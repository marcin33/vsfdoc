package com.bottega.vsfdoc.draft.write.domain.ports;

import com.bottega.vsfdoc.draft.write.domain.QDocContentWasUpdated;
import com.bottega.vsfdoc.draft.write.domain.QDocState;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class QDocDraftRecord {


	@Id
	private UUID id;
	private String content;
	private String number;
	private String state;
	@OneToMany
	private List<UUID> departments;

	public QDocDraftRecord() {
	}

	public QDocDraftRecord(UUID id) {
		this.id = id;
	}

	public void apply(DomainEvent event) {
		if(event instanceof QDocWasCreated) {
			this.number = ((QDocWasCreated) event).getQDocNumber();
		} else if (event instanceof QDocContentWasUpdated) {
			this.content = ((QDocContentWasUpdated) event).getContent();
		} else if (event instanceof QDocWasSendToVerification) {
			this.state = QDocState.IN_VERIFICATION.name();
		} else {
			throw new RuntimeException("no such event");
		}
	}
}
