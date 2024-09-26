package org.example.scoreboard;

import org.example.match.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ScoreBoard {

    private final HashMap<String, Match> matches = new HashMap<>();

    public void startMatch(Match match) {
        String matchKey = createMatchKey(match);
        if (matches.containsKey(matchKey)) {
            throw new IllegalStateException("Match already exists");
        }
        match.startMatch();
        matches.put(matchKey, match);
    }

    public void finishMatch(Match matchToFinish) {
        matches.remove(createMatchKey(matchToFinish));
    }

    public List<Match> getSummary() {
        List<Match> summary = new ArrayList<>(matches.values());
        summary.sort(Comparator.comparing(Match::getTotalScore)
                .thenComparing(Match::getStartTime).reversed());
        return summary;
    }

    public void updateScore(Match matchToUpdate, int homeTeamScore, int awayTeamScore) {
        Match match = matches.get(createMatchKey(matchToUpdate));
        if (match == null) {
            throw new IllegalStateException("Match not found");
        }
        match.setScore(homeTeamScore, awayTeamScore);
    }

    private String createMatchKey(Match match) {
        return match.getHomeTeamName() + ":" + match.getAwayTeamName();
    }
}
