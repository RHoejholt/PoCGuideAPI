package app.controllers;

import java.util.ArrayList;
import java.util.Optional;

import app.config.HibernateConfig;
import app.entities.Champion;
import app.entities.ErrorMsg;
import app.persistence.dao.impl.ChampionDAO;
import app.persistence.dto.ChampionDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;


public class ChampionController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ChampionDAO championDAO = ChampionDAO.getInstance(emf);

    public void getAllChampions(Context ctx) {
        ctx.json(championDAO.getChampions());
    }

    public void getChampionById(Context ctx) {
        Optional<ChampionDTO> match = championDAO.findById(Integer.parseInt(ctx.pathParam("id")));
        if (match.isPresent()) {
            ctx.json(match.get());
            return;
        } else {
            ctx.status(404);
            ctx.json(new ErrorMsg(404, "No content found for this request"));
        }
    }
}
