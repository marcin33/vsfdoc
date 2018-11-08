package com.bottega.vsfdoc.draft.read;

import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QDocDraftReader {


	private QDocDraftReadModelDao dao;


	QDocDraftDetailsReadModel findDetails(QDocId id) {
		QDocDraftDetailsReadModel one = dao.getOne(id);

		return one;
	}
}
