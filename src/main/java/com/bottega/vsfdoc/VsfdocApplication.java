package com.bottega.vsfdoc;

import com.bottega.vsfdoc.users.AppUser;
import com.bottega.vsfdoc.users.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class VsfdocApplication {

	public static void main(String[] args) {
		SpringApplication.run(VsfdocApplication.class, args);
	}

	@Slf4j
	@Component
	private static class UsersInitializer implements CommandLineRunner {

		private static final List<String> USERS = Arrays.asList("q1", "q2", "a1");

		@Autowired
		private AppUserService appUserService;

		@Override
		public void run(String... args) throws Exception {
			USERS.forEach(username -> {
				AppUser appUser = new AppUser(UUID.randomUUID(), username, Collections.emptyList());
				log.info("creating user: {}", appUser);
				appUserService.save(appUser);
			});
		}
	}

}
