package org.example.match;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Objects;

public class Match {

    private final String homeTeamName;

    private int homeTeamScore;

    private final String awayTeamName;

    private int awayTeamScore;

    private LocalDateTime startTime;

    public Match(String homeTeamName, String awayTeamName) throws IllegalStateException {
        if(!isTeamNameValid(homeTeamName) || !isTeamNameValid(awayTeamName)) {
            throw new IllegalStateException("Incorrect team name");
        }
        if(homeTeamName.equals(awayTeamName)) {
            throw new IllegalStateException("Team names are equal");
        }
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public String getHomeTeamName() {
        return this.homeTeamName;
    }

    public String getAwayTeamName() {
        return this.awayTeamName;
    }

    public int getHomeTeamScore() {
        return this.homeTeamScore;
    }

    public int getAwayTeamScore() {
        return this.awayTeamScore;
    }

    public String getScoreString() {
        return MessageFormat.format("{0} {1} - {2} {3}", homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);

    }

    public void setScore(int homeTeamScore, int awayTeamScore) {
        if(homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalStateException("Score should not be negative");
        }

        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    private boolean isTeamNameValid(String teamName) {
        return teamName != null && !(teamName.isEmpty() || teamName.trim().isEmpty());
    }

    public int getTotalScore() {
        return this.homeTeamScore + awayTeamScore;
    }

    public void startMatch() {
        this.startTime = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeTeamScore == match.homeTeamScore && awayTeamScore == match.awayTeamScore && Objects.equals(homeTeamName, match.homeTeamName) && Objects.equals(awayTeamName, match.awayTeamName) && Objects.equals(startTime, match.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeamName, homeTeamScore, awayTeamName, awayTeamScore, startTime);
    }
}
