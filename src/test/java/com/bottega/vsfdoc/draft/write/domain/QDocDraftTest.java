package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.QDocDraftRecord;
import com.bottega.vsfdoc.draft.write.domain.produces.*;
import com.bottega.vsfdoc.shared.DomainEvent;
import com.bottega.vsfdoc.shared.identifiers.DepartmentId;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

public class QDocDraftTest {

	private static final QDocId Q_DOC_ID = QDocId.of(UUID.randomUUID());
	private static final String Q_DOC_NUMBER_STR = "DEMO/ISO/123/11/2018/AUDIT";
	private static final QDocNumber Q_DOC_NUMBER = new QDocNumber(Q_DOC_NUMBER_STR);
	private static final OwnerId OWNER_ID = OwnerId.of(UUID.randomUUID());
	private static final List<DepartmentId> DEPARTMENT_IDS = Arrays.asList(DepartmentId.of(UUID.randomUUID()), DepartmentId.of(UUID.randomUUID()));

	private static final QDocWasCreated QDocWasCreated = new QDocWasCreated(Q_DOC_ID, Q_DOC_NUMBER_STR, OWNER_ID, QDocType.BASIC.name(), QDocState.NEW.name());

	@Test
	public void shouldCreateDraft() {
		// given
		QDocDraft qDocDraft = create();

		// when
		DomainEvent event = qDocDraft.create(Q_DOC_NUMBER, OWNER_ID, QDocType.BASIC);

		// then
		then(event).isEqualTo(QDocWasCreated);
	}

	@Test
	public void shouldUpdateContent() {
		// given
		QDocDraft qDocDraft = create(QDocWasCreated);
		String newContent = "new content";
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.updateContent(validator, newContent);

		// then
		then(event).isEqualTo(new QDocContentWasUpdated(Q_DOC_ID, newContent));
	}

	@Test
	public void shouldAssignToVerifier() {
		// given
		QDocDraft qDocDraft = create(QDocWasCreated);
		VerifierId verifierId = VerifierId.of(UUID.randomUUID());
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.assignToVerifier(validator, verifierId);

		// then
		then(event).isEqualTo(new QDocWasAssignToVerifier(Q_DOC_ID, verifierId));
	}

	@Test
	public void shouldSetDepartments() {
		// given
		QDocDraft qDocDraft = create(QDocWasCreated);
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.setDepartments(validator, DEPARTMENT_IDS);

		// then
		then(event).isEqualTo(new QDocDepartmentsWereSet(Q_DOC_ID, DEPARTMENT_IDS));
	}

	@Test
	public void shouldSendToVerification() {
		// given
		QDocDraft qDocDraft = create(
				QDocWasCreated,
				new QDocContentWasUpdated(Q_DOC_ID, "new content"),
				new QDocWasAssignToVerifier(Q_DOC_ID, VerifierId.of(UUID.randomUUID())),
				new QDocDepartmentsWereSet(Q_DOC_ID, DEPARTMENT_IDS));
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.sendToVerification(validator);

		// then
		then(event).isEqualTo(new QDocWasSendToVerification(Q_DOC_ID, QDocState.IN_VERIFICATION.name()));
	}

	@Test
	public void shouldVerify() {
		// given
		QDocDraft qDocDraft = create(
				QDocWasCreated,
				new QDocContentWasUpdated(Q_DOC_ID, "new content"),
				new QDocWasAssignToVerifier(Q_DOC_ID, VerifierId.of(UUID.randomUUID())),
				new QDocDepartmentsWereSet(Q_DOC_ID, DEPARTMENT_IDS),
				new QDocWasSendToVerification(Q_DOC_ID, QDocState.IN_VERIFICATION.name()));
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.verify(validator);

		// then
		then(event).isEqualTo(new QDocWasVerified(Q_DOC_ID, QDocState.VERIFIED.name()));
	}

	@Test
	public void shouldDecline() {
		// given
		QDocDraft qDocDraft = create(
				QDocWasCreated,
				new QDocContentWasUpdated(Q_DOC_ID, "new content"),
				new QDocWasAssignToVerifier(Q_DOC_ID, VerifierId.of(UUID.randomUUID())),
				new QDocDepartmentsWereSet(Q_DOC_ID, DEPARTMENT_IDS),
				new QDocWasSendToVerification(Q_DOC_ID, QDocState.IN_VERIFICATION.name()));
		String declineNote = "decline note";
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.decline(validator, declineNote);

		// then
		then(event).isEqualTo(new QDocWasDecline(Q_DOC_ID, declineNote, QDocState.NEW.name()));
	}

	@Test
	public void shouldPublish() {
		// given
		QDocDraft qDocDraft = create(
				QDocWasCreated,
				new QDocContentWasUpdated(Q_DOC_ID, "new content"),
				new QDocWasAssignToVerifier(Q_DOC_ID, VerifierId.of(UUID.randomUUID())),
				new QDocDepartmentsWereSet(Q_DOC_ID, DEPARTMENT_IDS),
				new QDocWasSendToVerification(Q_DOC_ID, QDocState.IN_VERIFICATION.name()),
				new QDocWasVerified(Q_DOC_ID, QDocState.VERIFIED.name()));
		QDocValidator validator = new QDocValidator(qDocDraft);

		// when
		DomainEvent event = qDocDraft.publish(validator);

		// then
		then(event).isEqualTo(new QDocWasPublished(Q_DOC_ID, QDocState.PUBLISHED.name()));
	}

	private QDocDraft create(DomainEvent... events) {
		QDocDraftRecord record = new QDocDraftRecord(Q_DOC_ID.value());

		Optional.ofNullable(events).map(Stream::of).orElse(Stream.empty()).forEach(record::apply);

		return new QDocDraft(record);
	}

}
