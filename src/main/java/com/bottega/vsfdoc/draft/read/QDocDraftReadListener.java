package com.bottega.vsfdoc.draft.read;

import com.bottega.vsfdoc.users.AppUserWasCreated;
import com.bottega.vsfdoc.users.AppUserWasUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class QDocDraftReadListener {

	private final JdbcOperations jdbc;

	@EventListener
	public void listenOn(AppUserWasCreated event) {
//		log.info("get event: {}", event);

//		jdbc.update("insert into user_read_model (id, name) value (?,?)", event.getId(), event.getName());
	}

	@EventListener
	public void listenOn(AppUserWasUpdated event) {

//		jdbc.update("update user_read_model set name = ? where id = ? ", event.getName(), event.getId());
	}

}
