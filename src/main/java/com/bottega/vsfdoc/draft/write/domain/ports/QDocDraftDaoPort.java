package com.bottega.vsfdoc.draft.write.domain.ports;

import com.bottega.vsfdoc.shared.identifiers.QDocId;

import java.util.Optional;

public interface QDocDraftDaoPort {
	Optional<QDocDraftRecord> findById(QDocId id);

	void save(QDocDraftRecord record);
}
