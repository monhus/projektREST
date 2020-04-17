package husar.punkty2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Score {
    public final int score;
    public final String comment;

    @JsonCreator
    public Score(@JsonProperty("score") int score, @JsonProperty("comment") String comment) {
        this.score = score;
        this.comment = comment;
    }
}
