package com.dice.basketball.resource;

import java.io.File;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.dice.basketball.domain.BasketBallFullMatch;
import com.dice.basketball.domain.MatchEvent;
import com.dice.basketball.service.IMatchEventService;
import com.dice.basketball.service.impl.MatchEventService;

public class MatchEventResourceTest {

	
	private MatchEventResource matchEventResource = new MatchEventResource();

	private IMatchEventService matchEventService = new MatchEventService();

	@BeforeEach
	public void init() {
		matchEventResource.setMatchEventService(matchEventService);
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void shouldParseMatchDataFile_givenValidFile() throws Exception {
		String fileName = "/src/main/resources/sample1.txt";
		String filePath = new File("").getAbsolutePath();
		filePath = filePath.concat(fileName);

		BasketBallFullMatch basketBallFullMatch = matchEventResource.getAllMatchEvents(filePath);

		Assertions.assertThat(basketBallFullMatch.getListMatchEvents()).isNotNull();
		Assertions.assertThat(basketBallFullMatch.getListMatchEvents()).isNotEmpty();

	}
	
	@Test
	void shouldGetLastNumberOfMatchEvents_givenValidFile() throws Exception {
		String fileName = "/src/main/resources/sample1.txt";
		String filePath = new File("").getAbsolutePath();
		filePath = filePath.concat(fileName);

		List<MatchEvent> listMatchEvents = matchEventResource.getLastNumberOfMatchEvents(filePath, 5);

		Assertions.assertThat(listMatchEvents).isNotNull();
		Assertions.assertThat(listMatchEvents).isNotEmpty();
	}

}
