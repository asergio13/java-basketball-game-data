package com.dice.basketball.service.impl;

import java.math.BigInteger;

import com.dice.basketball.domain.BasketBallFullMatch;
import com.dice.basketball.domain.MatchEvent;
import com.dice.basketball.service.IMatchEventService;

public class MatchEventService implements IMatchEventService {

	
	@Override
	public MatchEvent getMatchEventfromData(String hexadecimalStringData) {

		MatchEvent matchEvent = null;
		// Convert hexadecimal to Binary, handle Exception
		if (hexadecimalStringData.startsWith("0x")) {
			try {
				// Convert Raw data
				String binaryData = hexToBin(hexadecimalStringData.substring(2));
				// UPDATE GAME
				String binaryReversed = new StringBuilder(binaryData).reverse().toString();
				matchEvent = parseBinaryToMatchEvent(binaryReversed);

			} catch (NumberFormatException e) {
				// thrown if Data invalid, do nothing
			}
		}
		return matchEvent;
	}
	
	@Override
	public void processMatchEventfromData(String hexadecimalStringData, BasketBallFullMatch matchToUpdate) {

		// Convert hexadecimal to Binary, handle Exception
		if (hexadecimalStringData.startsWith("0x")) {
			try {
				// Convert Raw data
				String binaryData = hexToBin(hexadecimalStringData.substring(2));
				// UPDATE GAME
				addMatchEventoGame(binaryData, matchToUpdate);

			} catch (NumberFormatException e) {
				// thrown if Data invalid, do nothing
			}
		}
	}

	public static String hexToBin(String s) {
		return new BigInteger(s, 16).toString(2);
	}

	private int convertBinaryToIntValue(String binaryString) {
		return Integer.parseInt(binaryString, 2);
	}
	
	@Override
	public MatchEvent parseBinaryToMatchEvent(String binaryStringData) {

		int pointScored = convertBinaryToIntValue(new StringBuilder(binaryStringData.substring(0, 2)).reverse().toString());
		int teamWhoScored = convertBinaryToIntValue(new StringBuilder(binaryStringData.substring(2, 3)).reverse().toString());
		int team2TotalPoints = convertBinaryToIntValue(new StringBuilder(binaryStringData.substring(3, 11)).reverse().toString());
		int team1TotalPoints = convertBinaryToIntValue(new StringBuilder(binaryStringData.substring(11, 19)).reverse().toString());
		// Time
		Long elapsedTime = Long.valueOf(convertBinaryToIntValue(binaryStringData.substring(19)));

		return new MatchEvent(pointScored, teamWhoScored, team1TotalPoints, team2TotalPoints, elapsedTime);
	}
	
	@Override
	public void addMatchEventoGame(String binaryStringData, BasketBallFullMatch matchToUpdate) {
		
		String binaryReversed = new StringBuilder(binaryStringData).reverse().toString();
		
		MatchEvent matchEvent = parseBinaryToMatchEvent(binaryReversed);
		
		if (matchEvent != null) {
			
			// add points to the log to check consistency
			if (matchEvent.getTeamWhoScored() == 0) {
				// if team1 scored add points to team 1
				matchToUpdate.getScoreLog().getTeam1Points().add(matchEvent.getPointsScored());
			} else {
				// if team2 scored add points to team 2
				matchToUpdate.getScoreLog().getTeam2Points().add(matchEvent.getPointsScored());
			}
			
			// Check Consistency of points, if total points matches add event to the game
			checkMatchEventConsistency( matchToUpdate, matchEvent);
		}
	}
	
	public boolean checkMatchEventConsistency(BasketBallFullMatch matchToUpdate, MatchEvent matchEvent) {
		boolean result = false;
		if (matchToUpdate.getTeam1TotalPoints() == matchEvent.getTeam1TotalPoints()) {
			matchToUpdate.getListMatchEvents().add(matchEvent);
			result = true;
		} else {
			// remove bad data from the score log
			matchToUpdate.getScoreLog().getTeam1Points().remove(matchToUpdate.getScoreLog().getTeam1Points().size() - 1);
			matchToUpdate.getScoreLog().getTeam2Points().remove(matchToUpdate.getScoreLog().getTeam2Points().size() - 1);
		}
		return result;
	}
	
	
}
