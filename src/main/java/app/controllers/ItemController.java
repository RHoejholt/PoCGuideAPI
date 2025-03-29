package app.controllers;

import java.util.ArrayList;
import java.util.Optional;

import app.config.HibernateConfig;
import app.entities.Champion;
import app.entities.ErrorMsg;
import app.persistence.dao.impl.ChampionDAO;
import app.persistence.dao.impl.ItemDAO;
import app.persistence.dto.ChampionDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;


public class ItemController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ItemDAO itemDAO = ItemDAO.getInstance(emf);

    public void getAllItems(Context ctx) {
        ctx.json(itemDAO.getItems());
    }
}
