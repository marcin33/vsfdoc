package com.bottega.vsfdoc.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {

	@Id
	private UUID uuid;

	private String name;

	@ElementCollection
	private List<String> roles;

}
