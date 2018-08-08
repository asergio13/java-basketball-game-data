package com.dice.basketball.service;

import com.dice.basketball.domain.BasketBallFullMatch;
import com.dice.basketball.domain.MatchEvent;

public interface IMatchEventService {

	MatchEvent parseBinaryToMatchEvent(String binaryStringData);
	
	void addMatchEventoGame(String binaryStringData, BasketBallFullMatch matchToUpdate);

	void processMatchEventfromData(String hexadecimalStringData, BasketBallFullMatch matchToUpdate);

	MatchEvent getMatchEventfromData(String hexadecimalStringData);
}
