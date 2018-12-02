package com.bottega.vsfdoc.draft.write.infra.rest.dto;

import com.bottega.vsfdoc.shared.identifiers.OwnerId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateQDocDraftDto {

	@NotNull
	private OwnerId ownerId;

	@NotNull
	private String type;

}
