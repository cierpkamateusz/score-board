package org.example.match;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTest {

    Match match;
    private static final String homeTeamName = "Team1";
    private static final String awayTeamName = "Team2";

    @BeforeEach
    void setupMatch() {
        match = new Match(homeTeamName, awayTeamName);
    }

    @Test
    void shouldCorrectlySetTeamNames() {
        assertEquals(homeTeamName, match.getHomeTeamName());
        assertEquals(awayTeamName, match.getAwayTeamName());
    }

    @Test
    void shouldReturnStartingScoreEqualZero() {
        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());
    }

    @Test
    void shouldSetMatchScoreCorrectlyWhilePassingPairOfScores() {
        //when
        match.setScore(1, 2);

        //then
        assertEquals(1, match.getHomeTeamScore());
        assertEquals(2, match.getAwayTeamScore());
    }

    @Test
    void shouldThrowExceptionIfPassedNegativeScore() {
        assertThrows(IllegalStateException.class, () -> match.setScore(-1, 2));
    }

    @Test
    void shouldReturnCorrectScoreString() {
        String expectedString = homeTeamName + " 0 - " + awayTeamName + " 0";
        assertEquals(expectedString, match.getScoreString());
    }

    @ParameterizedTest
    @MethodSource("prepareIncorrectTeamNames")
    void shouldThrowExceptionIfPassedIncorrectTeamName(String homeTeamName, String awayTeamName) {
        assertThrows(IllegalStateException.class, () -> new Match(homeTeamName, awayTeamName));
    }

    private static Stream<Arguments> prepareIncorrectTeamNames() {
        return Stream.of(
                Arguments.of(null, awayTeamName),
                Arguments.of("", awayTeamName),
                Arguments.of(" ", awayTeamName),
                Arguments.of(homeTeamName, null),
                Arguments.of(homeTeamName, ""),
                Arguments.of(homeTeamName, " "),
                Arguments.of(homeTeamName, homeTeamName)
        );
    }
}