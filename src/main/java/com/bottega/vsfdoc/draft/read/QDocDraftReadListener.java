package com.bottega.vsfdoc.draft.read;

import com.bottega.vsfdoc.users.UserWasCreated;
import com.bottega.vsfdoc.users.UserWasUpdated;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcOperations;

@AllArgsConstructor
public class QDocDraftReadListener {

	private JdbcOperations jdbc;

	@EventListener
	public void listenOn(UserWasCreated event) {

		jdbc.update("insert into user_read_model (id, name) value (?,?)", event.getId(), event.getName());
	}

	@EventListener
	public void listenOn(UserWasUpdated event) {

		jdbc.update("update user_read_model set name = ? where id = ? ", event.getName(), event.getId());
	}

}
