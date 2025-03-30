package app.controllers;
import app.config.HibernateConfig;
import app.persistence.dao.impl.ItemDAO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

public class ItemController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ItemDAO itemDAO = ItemDAO.getInstance(emf);

    public void getAllItems(Context ctx) {
        ctx.json(itemDAO.getItems());
    }
}
