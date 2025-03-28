package app.services;

import java.util.ArrayList;
import java.util.List;

import app.entities.Champion;
import lombok.*;


public class ChampionService {
    ArrayList<Champion> championsList = new ArrayList<>();

    public ChampionService() {
        Champion champion1 = new Champion(1, "Fiddlesticks", "scary guy");
        Champion champion2 = new Champion(2, "Amumu", "sad guy");
        championsList.add(champion1);
        championsList.add(champion2);
    }

    public  List<Champion> getAllChampions(){


        return championsList;
    }

    public Champion getChampionById(int id){
        for (Champion champion : championsList) {
            if (champion.getChampionID() == id) {
                return champion;
            }
        }
        return null;
    }
}
