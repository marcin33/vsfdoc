package com.bottega.vsfdoc.shared.command;

public interface CommandBus {

	void dispatch(Command command);
}
