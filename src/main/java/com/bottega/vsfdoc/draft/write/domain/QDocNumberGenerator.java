package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.draft.write.domain.ports.CurrentUserPort;
import com.bottega.vsfdoc.draft.write.domain.ports.SequencePort;
import com.bottega.vsfdoc.draft.write.domain.ports.SystemConfigPort;
import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;

@AllArgsConstructor
class QDocNumberGenerator {

	private final SequencePort sequencePort;
	private final SystemConfigPort configPort;
	private final CurrentUserPort userPort;


	QDocNumber generate() {
		return new QDocNumber(configPort.getSystemType(),configPort.isDemo(),userPort.isAuditor(), LocalDate.now(Clock.systemDefaultZone()),sequencePort.next());
	}
}
