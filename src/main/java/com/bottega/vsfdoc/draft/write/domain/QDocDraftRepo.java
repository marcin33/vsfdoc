package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftDaoPort;
import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class QDocDraftRepo {

	private final QDocDraftDaoPort dao;
	private final ApplicationEventPublisher publisher;

	@Transactional
	void apply(QDocId id, Function<QDocDraft, DomainEvent> consumer) {
		requireNonNull(id, "id is required");
		requireNonNull(consumer, "consumer is required");

		QDocDraftRecord record = dao.findById(id.value()).orElse(new QDocDraftRecord(id.value()));

		QDocDraft qDocDraft = new QDocDraft(record);

		DomainEvent event = consumer.apply(qDocDraft);
		record.apply(event);
		publisher.publishEvent(event);

		dao.save(record);
	}
}
