package com.bottega.vsfdoc.draft.read;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QDocDraftReadModelDao /*extends JpaRepository<QDocDraftDetailsReadModel, QDocId>*/ {

	QDocDraftDetailsReadModel getOne(QDocId id);

}
