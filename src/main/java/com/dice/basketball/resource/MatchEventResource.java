package com.dice.basketball.resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dice.basketball.domain.BasketBallFullMatch;
import com.dice.basketball.domain.MatchEvent;
import com.dice.basketball.domain.ScoreLog;
import com.dice.basketball.service.IMatchEventService;
import com.dice.basketball.service.impl.MatchEventService;

public class MatchEventResource {

	private IMatchEventService matchEventService;

	public MatchEvent getLastMatchEvent() {
		return null;

	}

	public List<MatchEvent> getLastNumberOfMatchEvents(String fileName, int numberEvents) {

		List<String> listMatchEvent = null;

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			// convert Stream to collection
			listMatchEvent = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// reverse order of the collection of Events
		Collections.reverse(listMatchEvent);
		
		// Init return Object
		BasketBallFullMatch basketBallFullMatch = new BasketBallFullMatch();
		basketBallFullMatch.setScoreLog(new ScoreLog());
		
		if(listMatchEvent != null && !listMatchEvent.isEmpty()) {
			// PROCESS RAW DATA
			System.out.println("###### Last Events #####");
			for(int i = 0; i <= numberEvents && i < listMatchEvent.size(); i++) {
				MatchEvent me = getMatchEventService().getMatchEventfromData(listMatchEvent.get(i));
				basketBallFullMatch.getListMatchEvents().add(me);
				printMatchEvent(me);
			}
			System.out.println("########################");
		}
		return basketBallFullMatch.getListMatchEvents();
	}

	public BasketBallFullMatch getAllMatchEvents(String fileName) throws URISyntaxException {

		List<String> listMatchEvent = null;

		// read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			// convert Stream to collection
			listMatchEvent = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (listMatchEvent != null && !listMatchEvent.isEmpty()) {
			listMatchEvent.forEach(System.out::println);
		}
		
		// Init return Object
		BasketBallFullMatch basketBallFullMatch = new BasketBallFullMatch();
		basketBallFullMatch.setScoreLog(new ScoreLog());
		
		// PROCESS RAW DATA
		for (String line : listMatchEvent) {
			// process match Event from Data
			getMatchEventService().processMatchEventfromData(line, basketBallFullMatch);
		}
		printBasketBallMatchStatus(basketBallFullMatch);

		return basketBallFullMatch;
	}

	private void printMatchEvent(MatchEvent matchEvent) {
		if (matchEvent != null) {
			System.out.println("Points scored: " + matchEvent.getPointsScored());
			System.out.println("Team scored: " + matchEvent.getTeamWhoScored());
			System.out.println("Elapsed time: " + matchEvent.getElapsedTime());
			System.out.println("=======");
		}
	}
	
	private void printBasketBallMatchStatus(BasketBallFullMatch basketBallFullMatch) {
		System.out.println("========= Print Match Data - Start ==========");
		if (basketBallFullMatch != null) {
			for (MatchEvent m: basketBallFullMatch.getListMatchEvents()) {
				printMatchEvent(m);
			}
			System.out.println("Total Points Team1: " + basketBallFullMatch.getTeam1TotalPoints());
			System.out.println("Total Points Team2: " + basketBallFullMatch.getTeam2TotalPoints());
		}
		System.out.println("========= Print Match Data - End  ==========");
	}

	public void setMatchEventService(IMatchEventService matchEventService) {
		this.matchEventService = matchEventService;
	}

	public IMatchEventService getMatchEventService() {
		if (matchEventService == null) {
			matchEventService = new MatchEventService();
		}
		return matchEventService;
	}

}