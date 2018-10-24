package com.bottega.vsfdoc.draft.write.domain;

import com.bottega.vsfdoc.shared.QDocId;

public interface QDocDraftRepo {
	QDocDraft load(QDocId qDocId);

	void save(QDocDraft draft);
}
