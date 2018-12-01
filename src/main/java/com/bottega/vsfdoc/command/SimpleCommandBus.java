package com.bottega.vsfdoc.command;

import com.bottega.vsfdoc.shared.command.Command;
import com.bottega.vsfdoc.shared.command.CommandBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
public class SimpleCommandBus implements CommandBus {

	@NonNull
	private final CommandHandlerRegistry registry;

	@Override
	public void dispatch(Command command) {
		requireNonNull(command);

		registry.get(command).handle(command);
	}

}
