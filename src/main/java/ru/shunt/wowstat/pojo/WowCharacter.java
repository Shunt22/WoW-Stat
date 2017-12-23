package ru.shunt.wowstat.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WowCharacter {

	private String realmSlug;
	private String name;
	private long raceId;
	private long specId;
	private long rating;
}
