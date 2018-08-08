package com.dice.basketball.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScoreLog implements Serializable {
	
	private static final long serialVersionUID = 6289027405690535463L;

	private List<Integer> team1Points;
	private List<Integer> team2Points;

	public List<Integer> getTeam1Points() {
		if (team1Points == null) {
			team1Points = new ArrayList<>();
		}
		return team1Points;
	}

	public void setTeam1Points(List<Integer> team1Points) {
		this.team1Points = team1Points;
	}

	public List<Integer> getTeam2Points() {
		if (team2Points == null) {
			team2Points = new ArrayList<>();
		}
		return team2Points;
	}

	public void setTeam2Points(List<Integer> team2Points) {
		this.team2Points = team2Points;
	}
	
}
