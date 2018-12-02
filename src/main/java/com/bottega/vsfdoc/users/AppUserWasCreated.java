package com.bottega.vsfdoc.users;

import com.bottega.vsfdoc.shared.DomainEvent;
import lombok.Value;

import java.util.UUID;

@Value
public class AppUserWasCreated implements DomainEvent {

	private final UUID id;
	private final String name;

}
