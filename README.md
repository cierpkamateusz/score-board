# Live Football World Cup Scoreboard Library

## Overview

The Live Football World Cup Scoreboard library is a simple in-memory solution to manage ongoing football matches and
their scores. This library provides functionality to:

1. Start a new match.
    - Adds a new match to the scoreboard with an initial score of 0–0.
    - Requires the home team and away team names as input.
2. Update the score of an existing match.
    - specifying the home and away team’s absolute scores
3. Finish a match that is currently in progress.
    - Removes a match from the scoreboard when it has finished.
4. Retrieve a summary of ongoing matches, ordered by total score and start time.
    - Provides a summary of all ongoing matches, ordered by the total score in descending order. If multiple matches
      have the same total score, the most recently started match appears first.

## Usage
#### WARNING This is the simplest solution that works and it is not threadsafe

1. Create an instance of the `ScoreBoard` class:
   ```java
   ScoreBoard scoreBoard = new ScoreBoard();
   ```

2. Start a match:
   ```java
   Match match1 = new Match("Team1", "Team2");
   scoreBoard.startMatch(match1);
   ```

3. Update the score of a match providing home and away team score:
   ```java
   scoreBoard.updateScore(match, 1, 0);
   ```

4. Finish a match:
   ```java
   scoreBoard.finishMatch(match);
   ```

5. Get a summary of ongoing matches:
   ```java
   List<Match> summary = scoreBoard.getSummary();
   for (Match match : summary) {
       System.out.println(match.getScoreString());
   }
   ```
