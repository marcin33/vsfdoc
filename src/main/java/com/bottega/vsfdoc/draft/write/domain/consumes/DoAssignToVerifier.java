package com.bottega.vsfdoc.draft.write.domain.consumes;

import com.bottega.vsfdoc.shared.command.Command;
import com.bottega.vsfdoc.shared.identifiers.QDocId;
import com.bottega.vsfdoc.shared.identifiers.VerifierId;
import lombok.Data;
import lombok.NonNull;

@Data
public class DoAssignToVerifier implements Command {

	@NonNull
	private final QDocId id;

	@NonNull
	private final VerifierId verifierId;
}
