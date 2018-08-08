package com.dice.basketball.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasketBallFullMatch implements Serializable{
	
	private static final long serialVersionUID = 6321607523122302760L;
	
	private List<MatchEvent> listMatchEvents;
	private ScoreLog scoreLog;
	private int team1TotalPoints;
	private int team2TotalPoints;
	
	
	public List<MatchEvent> getListMatchEvents() {
		if (listMatchEvents == null) {
			listMatchEvents = new ArrayList<>();
		}
		return listMatchEvents;
	}

	public void setListMatchEvents(List<MatchEvent> listMatchEvents) {
		this.listMatchEvents = listMatchEvents;
	}

	public ScoreLog getScoreLog() {
		return scoreLog;
	}

	public void setScoreLog(ScoreLog scoreLog) {
		this.scoreLog = scoreLog;
	}
	
	public int getTeam1TotalPoints() {
		this.team1TotalPoints = 0;
		for(Integer score: scoreLog.getTeam1Points()) {
			this.team1TotalPoints += score;
		}
		return this.team1TotalPoints;
	}

	public int getTeam2TotalPoints() {
		this.team2TotalPoints = 0;
		for(Integer score: scoreLog.getTeam2Points()) {
			this.team2TotalPoints += score;
		}
		return this.team2TotalPoints;
	}
	
}
