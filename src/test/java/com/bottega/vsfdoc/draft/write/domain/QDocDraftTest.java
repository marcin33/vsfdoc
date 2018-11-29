package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocContentWasUpdated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasAssignToVerifier;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import org.junit.Test;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

public class QDocDraftTest {

	private static final QDocId Q_DOC_ID = QDocId.of(UUID.randomUUID());
	private static final String Q_DOC_NUMBER_STR = "DEMO/ISO/123/11/2018/AUDIT";
	private static final QDocNumber Q_DOC_NUMBER = new QDocNumber(Q_DOC_NUMBER_STR);
	private static final OwnerId OWNER_ID = OwnerId.of(UUID.randomUUID());

	private static final QDocWasCreated Q_DOC_WAS_CREATED = new QDocWasCreated(Q_DOC_ID, Q_DOC_NUMBER_STR, OWNER_ID, QDocType.BASIC.name(), QDocState.NEW.name());

	@Test
	public void shouldCreateDraft() {
		// given
		QDocDraft qDocDraft = create();

		// when
		DomainEvent event = qDocDraft.create(Q_DOC_NUMBER, OWNER_ID, QDocType.BASIC);

		// then
		then(event).isEqualTo(Q_DOC_WAS_CREATED);
	}

	@Test
	public void shouldUpdateContent() {
		// given
		QDocDraft qDocDraft = create(Q_DOC_WAS_CREATED);
		String newContent = "new content";

		// when
		DomainEvent event = qDocDraft.updateContent(newContent);

		// then
		then(event).isEqualTo(new QDocContentWasUpdated(Q_DOC_ID, newContent));
	}

	@Test
	public void shouldAssignToVerifier() {
		// given
		QDocDraft qDocDraft = create(Q_DOC_WAS_CREATED);
		VerifierId verifierId = VerifierId.of(UUID.randomUUID());

		// when
		DomainEvent event = qDocDraft.assignToVerifier(verifierId);

		// then
		then(event).isEqualTo(new QDocWasAssignToVerifier(Q_DOC_ID, verifierId));
	}

	//@Test
	public void shouldSendToVerification() {
		// given
		QDocDraft qDocDraft = create(
				Q_DOC_WAS_CREATED,
				new QDocContentWasUpdated(Q_DOC_ID, "new content"),
				new QDocWasAssignToVerifier(Q_DOC_ID, VerifierId.of(UUID.randomUUID())));

		// when
		DomainEvent event = qDocDraft.sendToVerification();

		// then
		then(event).isEqualTo(new QDocWasSendToVerification(Q_DOC_ID));
	}

	private QDocDraft create(DomainEvent... events) {
		QDocDraftRecord record = new QDocDraftRecord(Q_DOC_ID.value());

		Optional.ofNullable(events).map(Stream::of).orElse(Stream.empty()).forEach(record::apply);

		return new QDocDraft(record);
	}


}
