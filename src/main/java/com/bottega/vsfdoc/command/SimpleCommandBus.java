package com.bottega.vsfdoc.command;

import com.bottega.vsfdoc.shared.command.Command;
import com.bottega.vsfdoc.shared.command.CommandBus;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNull;

@Component
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
