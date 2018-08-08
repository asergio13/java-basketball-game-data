package com.dice.basketball.service.impl;

import org.assertj.core.api.Assertions;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.dice.basketball.domain.MatchEvent;
import com.dice.basketball.service.IMatchEventService;

class MatchEventServiceTest {

	private IMatchEventService matchEventService = new MatchEventService();

	static Stream<Arguments> hexadecimalDataProvider() {
		return Stream.of(
				Arguments.of("0x801002", "100000000001000000000010"),
				Arguments.of("0xf81016", "111110000001000000010110"),
				Arguments.of("0x1d8102f", "1110110000001000000101111"));
	}

	@Test
	@ParameterizedTest
	@MethodSource("hexadecimalDataProvider")
	public void shouldConvertHexadecimaltoBinary_whenHexadecimalDatagiven(String hexValue, String expectedBinaryValue)
			throws Exception {
		String resultedBinaryValue = MatchEventService.hexToBin(hexValue.substring(2));
		
		Assertions.assertThat(resultedBinaryValue).isEqualTo(expectedBinaryValue);
	}

	
	static Stream<Arguments> binaryDataProvider() {
		return Stream.of(
				Arguments.of("11110000001000000000010", new MatchEvent(2, 1, 0, 2, Long.valueOf(15))),
				Arguments.of("111100000001000000011111", new MatchEvent(2, 1, 0, 4, Long.valueOf(30)))
				);
	}

	@Test
	@ParameterizedTest
	@MethodSource("binaryDataProvider")
	public void shouldParseBinaryToMatchEvent_whenBinaryDatagiven(String inputValue, MatchEvent expectedMatchEvent) 
			throws Exception {
		MatchEvent resultedMatchEvent = matchEventService.parseBinaryToMatchEvent(inputValue);
		
		Assertions.assertThat(resultedMatchEvent.getPointsScored()).isEqualTo(expectedMatchEvent.getPointsScored());
		Assertions.assertThat(resultedMatchEvent.getTeamWhoScored()).isEqualTo(expectedMatchEvent.getTeamWhoScored());
		Assertions.assertThat(resultedMatchEvent.getElapsedTime()).isEqualTo(expectedMatchEvent.getElapsedTime());
	}

}
