package com.bottega.vsfdoc.command;

import com.bottega.vsfdoc.shared.command.Command;

@FunctionalInterface
public interface CommandHandler<C extends Command> {
	void handle(C command);
}
