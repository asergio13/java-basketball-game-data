package com.dice.app;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import com.dice.basketball.domain.BasketBallFullMatch;
import com.dice.basketball.domain.MatchEvent;
import com.dice.basketball.resource.MatchEventResource;

public class BasketBallApplication {

	public static void main(String[] args) {
		System.out.println("==== BasketBall Game --- start");
		MatchEventResource matchEventResource = new MatchEventResource();

		String fileName = "/src/main/resources/sample1.txt";
		String filePath = new File("").getAbsolutePath();
		filePath = filePath.concat(fileName);

		try {
			BasketBallFullMatch basketBallFullMatch = matchEventResource.getAllMatchEvents(filePath);
			List<MatchEvent> listMatchEvents = matchEventResource.getLastNumberOfMatchEvents(filePath, 5);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.out.println("==== BasketBall Game --- end");
		System.exit(0);
	}

}
