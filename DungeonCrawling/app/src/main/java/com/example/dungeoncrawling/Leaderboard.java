public class Leaderboard {
    private static Leaderboard instance;
    private List<ScoreEntry> scores;

    private Leaderboard() {
        scores = new ArrayList<>();
    }

    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public List<ScoreEntry> getScores() {
        // Sort scores in descending order
        Collections.sort(scores, Collections.reverseOrder());
        return scores;
    }

    public void addScore(ScoreEntry score) {
        scores.add(score);
    }
}

public class ScoreEntry implements Comparable<ScoreEntry> {
    private String playerName;
    private int score;
    private Date date;

    public ScoreEntry(String playerName, int score, Date date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        // Compare score in descending order
        return Integer.compare(other.score, this.score);
    }
}
