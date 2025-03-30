package app.controllers;
import app.config.HibernateConfig;
import app.middleware.AdminAuthMiddleware;
import app.persistence.dao.impl.ItemDAO;
import app.persistence.dto.ItemDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

public class ItemController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ItemDAO itemDAO = ItemDAO.getInstance(emf);

    public void getAllItems(Context ctx) {
        ctx.json(itemDAO.getItems());
    }

    public void createItem(Context ctx) throws Exception {
        new AdminAuthMiddleware().handle(ctx);
        ItemDTO item = ctx.bodyAsClass(ItemDTO.class);
        itemDAO.save(item);
        ctx.status(201).json("Item added");
    }

    public void updateItem(Context ctx) throws Exception {
        new AdminAuthMiddleware().handle(ctx);
        int id = Integer.parseInt(ctx.pathParam("itemId"));
        ItemDTO item = ctx.bodyAsClass(ItemDTO.class);
        item.setId(id);
        itemDAO.update(item);
        ctx.json("Item updated");
    }

    public void deleteItem(Context ctx) throws Exception {
        new AdminAuthMiddleware().handle(ctx);
        int id = Integer.parseInt(ctx.pathParam("itemId"));
        itemDAO.delete(id);
        ctx.json("Item deleted");
    }
}
