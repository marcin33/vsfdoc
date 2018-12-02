package com.bottega.vsfdoc.users;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class AppUserService {

	private final ApplicationEventPublisher eventPublisher;
	private final AppUserRepository repository;

	@Transactional
	public void save(AppUser appUser) {
		repository.save(appUser);

		eventPublisher.publishEvent(new AppUserWasCreated(appUser.getUuid(), appUser.getName()));
	}

}
