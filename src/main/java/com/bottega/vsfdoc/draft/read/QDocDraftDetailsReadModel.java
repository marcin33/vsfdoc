package com.bottega.vsfdoc.draft.read;

import com.bottega.vsfdoc.draft.write.domain.QDocState;
import com.bottega.vsfdoc.shared.QDocId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "qdoc_draft")
public class QDocDraftDetailsReadModel {

	@Id
	private QDocId qDocId;
	private QDocState state;
	private String content;
	private String number;
	private String author;
	private String verificator;
	private LocalDate creationDate;
	private List<DepartmentReadModel> departments;

	private class DepartmentReadModel {
		UUID id;
		String name;
	}
}
