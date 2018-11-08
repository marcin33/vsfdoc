package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftDaoPort;
import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;
import java.util.function.Function;

class QDocDraftRepo {

	private QDocDraftDaoPort dao;
	private ApplicationEventPublisher publisher;

	@Transactional
	void apply(QDocId id, Function<QDocDraft, DomainEvent> consumer) {

		QDocDraftRecord record = dao.findById(id).orElse(new QDocDraftRecord(id.value()));

		QDocDraft qDocDraft = new QDocDraft(record);

		DomainEvent event = consumer.apply(qDocDraft);
		record.apply(event);
		publisher.publishEvent(event);

		dao.save(record);
	}
}
