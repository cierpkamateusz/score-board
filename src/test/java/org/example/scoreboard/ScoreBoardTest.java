package org.example.scoreboard;

import org.example.match.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    ScoreBoard scoreBoard;

    @BeforeEach
    void setup() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    void shouldReturnEmptyScoreBoardAfterCreation() {
        assertTrue(scoreBoard.getSummary().isEmpty());
    }

    @Test
    void shouldAddNewMatchesToScoreBoard() {
        //given
        Match match1 = new Match("Team1", "Team2");
        Match match2 = new Match("Team3", "Team4");

        //when
        scoreBoard.startMatch(match1);
        scoreBoard.startMatch(match2);

        //then
        assertEquals(2, scoreBoard.getSummary().size());
    }

    @Test
    void shouldThrowExceptionIfTryToAddMatchThatAlreadyExists() {
        //given
        Match match1 = new Match("Team1", "Team2");
        Match match2 = new Match("Team1", "Team2");

        //when
        scoreBoard.startMatch(match1);

        //then
        assertThrows(IllegalStateException.class, () -> scoreBoard.startMatch(match2));
    }

    @Test
    void shouldReturnSummarySortedByMatchStartTimeDesc() {
        //given
        Match match1 = new Match("Team1", "Team2");
        Match match2 = new Match("Team3", "Team4");

        //when
        scoreBoard.startMatch(match1);
        scoreBoard.startMatch(match2);

        //then
        List<Match> expectedResult = scoreBoard.getSummary();
        assertEquals("Team3 0 - Team4 0", expectedResult.get(0).getScoreString());
        assertEquals("Team1 0 - Team2 0", expectedResult.get(1).getScoreString());
    }

    @Test
    void shouldUpdateMatchScore() {
        //given
        Match match = new Match("Team1", "Team2");

        //when
        scoreBoard.startMatch(match);
        scoreBoard.updateScore(match, 1, 0);

        //then
        Match updatedMatch = scoreBoard.getSummary().getFirst();
        assertAll(() -> {
            assertEquals(1, updatedMatch.getHomeTeamScore());
            assertEquals(0, updatedMatch.getAwayTeamScore());
        });
    }

    @Test
    void shouldThrowExceptionWhenTryToUpdateNotExistingMatch() {
        //given
        Match match = new Match("Team1", "Team2");

        //then
        assertThrows(IllegalStateException.class, () -> scoreBoard.updateScore(match, 1, 0));
    }

    @Test
    void shouldReturnEmptyScoreBoardAfterMatchEnd() {
        //given
        Match match = new Match("Team1", "Team2");

        //when
        scoreBoard.startMatch(match);
        scoreBoard.finishMatch(match);

        //then
        assertTrue(scoreBoard.getSummary().isEmpty());
    }

    @Test
    void shouldReturnSummarySortedByTotalScoreDesc() {
        //given
        Match portugalFrance = new Match("Portugal", "France");
        Match germanyItaly = new Match("Germany", "Italy");
        Match spainPoland = new Match("Spain", "Poland");
        Match argentinaBrazil = new Match("Argentina", "Brazil");

        //when
        scoreBoard.startMatch(portugalFrance);
        scoreBoard.updateScore(portugalFrance, 1, 2);

        scoreBoard.startMatch(germanyItaly);
        scoreBoard.updateScore(germanyItaly, 4, 10);

        scoreBoard.startMatch(spainPoland);
        scoreBoard.updateScore(spainPoland, 3, 0);

        scoreBoard.startMatch(argentinaBrazil);
        scoreBoard.updateScore(argentinaBrazil, 1, 1);

        //then
        List<Match> summary = scoreBoard.getSummary();
        assertAll(() -> {
            assertEquals("Germany 4 - Italy 10", summary.get(0).getScoreString());
            assertEquals("Spain 3 - Poland 0", summary.get(1).getScoreString());
            assertEquals("Portugal 1 - France 2", summary.get(2).getScoreString());
            assertEquals("Argentina 1 - Brazil 1", summary.get(3).getScoreString());
        });
    }
}