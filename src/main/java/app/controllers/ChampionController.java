package app.controllers;

import java.util.ArrayList;
import java.util.List;

import app.entities.Champion;
import io.javalin.http.Context;


public class ChampionController {
    ArrayList<Champion> championsList = new ArrayList<>();

    public ChampionController() {
        Champion champion1 = new Champion(1, "Fiddlesticks", "scary guy");
        Champion champion2 = new Champion(2, "Amumu", "sad guy");
        championsList.add(champion1);
        championsList.add(champion2);
    }

    public  List<Champion> getAllChampions(){


        return championsList;
    }

    public void getAllChampions2(Context ctx){
    ctx.json(championsList);

    }

    public Champion getChampionById(int id){
        for (Champion champion : championsList) {
            if (champion.getId() == id) {
                return champion;
            }
        }
        return null;
    }
}
