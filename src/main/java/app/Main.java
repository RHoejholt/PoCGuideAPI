package app;
import app.config.HibernateConfig;
import app.controllers.AdminController;
import app.controllers.VoteController;
import app.entities.Champion;
import app.entities.Item;
import app.persistence.Populator;
import app.persistence.dao.impl.ItemDAO;
import app.persistence.dao.impl.VoteDAO;
import app.persistence.dto.ChampionDTO;
import app.persistence.dao.impl.ChampionDAO;
import app.controllers.ChampionController;
import app.controllers.ItemController;
import app.persistence.dto.ItemDTO;
import app.persistence.dto.VoteDTO;
import io.javalin.Javalin;
import jakarta.persistence.*;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        Javalin app = Javalin.create().start(7000);
        app.before(ctx -> {
            ctx.header("Access-Control-Allow-Origin", "http://localhost:5173");
            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            ctx.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
            ctx.header("Access-Control-Allow-Credentials", "true");
        });
        app.options("/*", ctx -> {
            ctx.status(204); // No Content
        });



        ChampionController championController = new ChampionController();
        ChampionDAO championDAO = ChampionDAO.getInstance(emf);
        ItemController itemController = new ItemController();
        ItemDAO itemDAO = ItemDAO.getInstance(emf);
        VoteController voteController = new VoteController();
        VoteDAO voteDAO = VoteDAO.getInstance(emf);
        AdminController adminController = new AdminController();



        Populator.populateDataBase(championDAO, itemDAO, voteDAO);
        addRoutes(app, championController, itemController, voteController, adminController);
    }

    private static void addRoutes(Javalin app, ChampionController championController, ItemController itemController, VoteController voteController, AdminController adminController) {
        app.get("/hello", ctx -> ctx.result("Hello World"));

        app.get("/champions", championController::getAllChampions);

        app.get("/champions/{id}", championController::getChampionAndItemsById);

        app.get("/items", itemController::getAllItems);

        app.post("/votes", voteController::submitVote);

        app.post("/admin/signup", adminController::signUp);

        app.post("/admin/login", adminController::login);

        //Protected Admin Endpoints

        app.post("/champions", championController::createChampion);

        app.put("/champions/{championId}", championController::updateChampion);

        app.delete("/champions/{championId}", championController::deleteChampion);

        app.post("/items", itemController::createItem);

        app.put("/items/{itemId}", itemController::updateItem);

        app.delete("/items/{itemId}", itemController::deleteItem);
    }
}
