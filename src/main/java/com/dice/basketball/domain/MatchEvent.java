package com.dice.basketball.domain;

import java.io.Serializable;

public class MatchEvent implements Serializable {

	private static final long serialVersionUID = -2456310243123614198L;

	private int pointsScored;
	private int teamWhoScored;
	private int team1TotalPoints;
	private int team2TotalPoints;
	private Long elapsedTime;

	public int getPointsScored() {
		return pointsScored;
	}

	public void setPointsScored(int pointsScored) {
		this.pointsScored = pointsScored;
	}

	public int getTeamWhoScored() {
		return teamWhoScored;
	}

	public void setTeamWhoScored(int teamWhoScored) {
		this.teamWhoScored = teamWhoScored;
	}

	public Long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public int getTeam1TotalPoints() {
		return team1TotalPoints;
	}

	public void setTeam1TotalPoints(int team1TotalPoints) {
		this.team1TotalPoints = team1TotalPoints;
	}

	public int getTeam2TotalPoints() {
		return team2TotalPoints;
	}

	public void setTeam2TotalPoints(int team2TotalPoints) {
		this.team2TotalPoints = team2TotalPoints;
	}

	public MatchEvent(int pointsScored, int teamWhoScored, int team1TotalPoints, int team2TotalPoints,  Long elapsedTime) {
		super();
		this.pointsScored = pointsScored;
		this.teamWhoScored = teamWhoScored;
		this.team1TotalPoints = team1TotalPoints;
		this.team2TotalPoints = team2TotalPoints;
		this.elapsedTime = elapsedTime;
	}

}
