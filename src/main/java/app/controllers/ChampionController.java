package app.controllers;

import java.util.ArrayList;

import app.entities.Champion;
import app.entities.ErrorMsg;
import io.javalin.http.Context;


public class ChampionController {
    ArrayList<Champion> championsList = new ArrayList<>();

    public ChampionController() {
        Champion champion1 = new Champion(1, "Fiddlesticks", "scary guy");
        Champion champion2 = new Champion(2, "Amumu", "sad guy");
        championsList.add(champion1);
        championsList.add(champion2);
    }

    public void getAllChampions(Context ctx) {
        ctx.json(championsList);
    }

    public void getChampionById(Context ctx) {

        int id = Integer.parseInt(ctx.pathParam("id"));
        for (Champion championSearch : championsList) {
            if (championSearch.getId() == id) {
                ctx.json(championSearch);
                return;
            }
        }

        ctx.status(404);
        ctx.json(new ErrorMsg(404, "No content found for this request"));
    }
}
