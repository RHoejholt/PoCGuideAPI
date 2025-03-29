package app;
import app.config.HibernateConfig;
import app.controllers.VoteController;
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
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        Javalin app = Javalin.create().start(7000);
        ChampionController championController = new ChampionController();
        ChampionDAO championDAO = ChampionDAO.getInstance(emf);
        ItemController itemController = new ItemController();
        ItemDAO itemDAO = ItemDAO.getInstance(emf);
        VoteController voteController = new VoteController();
        VoteDAO voteDAO = VoteDAO.getInstance(emf);
        populateDataBase(championController, championDAO, itemDAO, voteDAO);
        addRoutes(app, championController, itemController, voteController);
    }

    private static void addRoutes(Javalin app, ChampionController championController, ItemController itemController, VoteController voteController) {
        app.get("/hello", ctx -> ctx.result("Hello World"));

        app.get("/champions", championController::getAllChampions);

        app.get("/champions/{id}", championController::getChampionById);

        app.get("/items", itemController::getAllItems);

        app.post("/votes", voteController::submitVote);

    }

    private static void populateDataBase(ChampionController cs, ChampionDAO championDAO, ItemDAO itemDAO, VoteDAO voteDAO) throws IOException, InterruptedException {
        ChampionDTO champion1 = new ChampionDTO("Fiddlesticks", "scary guy");
        ChampionDTO champion2 = new ChampionDTO("Amumu", "sad guy");
        championDAO.save(champion1);
        championDAO.save(champion2);

        ItemDTO item1 = new ItemDTO("Trinity Force", "tons of damage", 3.333);
        ItemDTO item2 = new ItemDTO("Infinity edge", "tons of edging", 5);
        itemDAO.save(item1);
        itemDAO.save(item2);

        VoteDTO vote1 = new VoteDTO(1, 2, 5);
        VoteDTO vote2 = new VoteDTO(2, 1, 2);
        voteDAO.save(vote1);
        voteDAO.save(vote2);
    }
}
