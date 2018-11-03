package com.bottega.vsfdoc.draft.write.domain.ports;

import com.bottega.vsfdoc.shared.QDocId;

import java.util.Optional;

public interface QDocDraftDaoPort {
	Optional<QDocDraftRecord> findById(QDocId id);

	void save(QDocDraftRecord record);
}
