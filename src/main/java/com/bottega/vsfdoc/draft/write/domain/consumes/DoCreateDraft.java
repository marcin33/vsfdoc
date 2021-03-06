package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.command.Command;
import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import lombok.Data;
import lombok.NonNull;

@Data
public class DoCreateDraft implements Command {

	@NonNull
	private final QDocId id;

	@NonNull
	private final String qDocType;

	@NonNull
	private final OwnerId ownerId;

}
