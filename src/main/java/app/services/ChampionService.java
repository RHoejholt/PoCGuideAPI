package app.services;

import java.util.ArrayList;
import java.util.List;

import app.entities.Champion;
import lombok.*;

@Data
public class ChampionService {


    public  List<Champion> getAllChampions(){
        ArrayList<Champion> championsList = new ArrayList<>();
        Champion champion1 = new Champion(1, "Fiddlesticks", "scary guy");
        championsList.add(champion1);
        return championsList;
    }
}
