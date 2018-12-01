package com.bottega.vsfdoc.command;

import com.bottega.vsfdoc.shared.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandHandlerRegistry {

	private final Map<Class<?>, CommandHandler> handlers = new HashMap<>();

	public <C extends Command> void register(CommandHandler<C> handler, Class<C> clazz) {
		handlers.put(clazz, handler);
	}

	@SuppressWarnings("unchecked")
	public <C extends Command> CommandHandler<C> get(Command command) {
		return Optional.ofNullable(handlers.get(command.getClass()))
				.orElseThrow(() -> new RuntimeException("Can NOT find handler for command: " + command.getClass().getSimpleName()));
	}
}
