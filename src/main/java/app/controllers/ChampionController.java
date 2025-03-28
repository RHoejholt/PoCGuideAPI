package app.controllers;

import java.util.ArrayList;

import app.config.HibernateConfig;
import app.entities.Champion;
import app.entities.ErrorMsg;
import app.persistence.dao.impl.ChampionDAO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;


public class ChampionController {
    ArrayList<Champion> championsList = new ArrayList<>();
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ChampionDAO championDAO = ChampionDAO.getInstance(emf);

    public ChampionController() {
        Champion champion1 = new Champion(1, "Fiddlesticks", "scary guy");
        Champion champion2 = new Champion(2, "Amumu", "sad guy");
        championsList.add(champion1);
        championsList.add(champion2);
    }

    public void getAllChampions(Context ctx) {
    ctx.json(championDAO.getChampions());
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
