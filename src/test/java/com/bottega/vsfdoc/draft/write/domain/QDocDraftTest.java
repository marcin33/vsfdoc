package com.bottega.vsfdoc.draft.write.domain;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.UUID;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasCreated;
import com.bottega.vsfdoc.draft.write.domain.produces.QDocWasSendToVerification;
import com.bottega.vsfdoc.shared.DomainEvent;
import org.junit.Test;

public class QDocDraftTest {

    @Test
	public void shouldCreateDraft() {
		// given
		UUID id = UUID.randomUUID();
		QDocDraftRecord record = new QDocDraftRecord(id);
		QDocDraft qDocDraft = new QDocDraft(record);
		QDocNumber qDocNumber = new QDocNumber("DEMO/ISO/123/11/2018/AUDIT");

		// when
		DomainEvent event = qDocDraft.create(qDocNumber);

		// then
		then(event).isEqualTo(new QDocWasCreated("DEMO/ISO/123/11/2018/AUDIT"));
	}

	@Test
	public void shouldPublishDraft() {
		// given
		UUID id = UUID.randomUUID();
		QDocDraftRecord record = new QDocDraftRecord(id);
		record.apply(new QDocWasCreated("DEMO/ISO/123/11/2018/AUDIT"));
		record.apply(new QDocWasSendToVerification());
		QDocDraft qDocDraft = new QDocDraft(record);
		QDocNumber qDocNumber = new QDocNumber("DEMO/ISO/123/11/2018/AUDIT");

		// when
		DomainEvent event = qDocDraft.create(qDocNumber);

		// then
		then(event).isEqualTo(new QDocWasCreated("DEMO/ISO/123/11/2018/AUDIT"));
	}

}
