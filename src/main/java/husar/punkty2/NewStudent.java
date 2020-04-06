package husar.punkty2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewStudent {
    @JsonProperty("name") final String name;
    @JsonProperty("number") final String number;
    @JsonProperty("grupa") final String grupa;

    @JsonCreator
    //public NewStudent(@JsonProperty("name") String name, @JsonProperty("number") String number, @JsonProperty("grupa") String grupa)
    public NewStudent(String name, String number, String grupa) {
        this.name = name;
        this.number = number;
        this.grupa = grupa;
    }
}
