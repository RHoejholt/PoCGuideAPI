package app.entities;

import lombok.Getter;

public class Champion {

    @Getter
    int championID;
    String championName;
    @Getter
    String description;

    public Champion(int championID, String championName, String description) {
        this.championID = championID;
        this.championName = championName;
        this.description = description;
    }

    public String getName() {
        return championName;
    }

}