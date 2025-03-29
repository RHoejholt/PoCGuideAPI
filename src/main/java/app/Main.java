package app;
import app.config.HibernateConfig;
import app.controllers.VoteController;
import app.entities.Champion;
import app.entities.Item;
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
        ChampionDTO championDTO1 = new ChampionDTO("Fiddlesticks", "scary guy");
        ChampionDTO championDTO2 = new ChampionDTO("Amumu", "sad guy");
        championDAO.save(championDTO1);
        championDAO.save(championDTO2);

        ItemDTO itemDTO1 = new ItemDTO("Trinity Force", "tons of damage", 3.333);
        ItemDTO itemDTO2 = new ItemDTO("Infinity edge", "tons of edging", 5);
        itemDAO.save(itemDTO1);
        itemDAO.save(itemDTO2);


        Champion champion1 = new Champion(championDTO1);
        Champion champion2 = new Champion(championDTO2);
        Item item1 = new Item(itemDTO1);
        Item item2 = new Item(itemDTO2);

        VoteDTO vote1 = new VoteDTO(champion1, item2, 5);
        VoteDTO vote2 = new VoteDTO(champion2, item1, 2);
        VoteDTO vote3 = new VoteDTO(champion1, item1, 3);
        VoteDTO vote4 = new VoteDTO(champion2, item2, 4);
        VoteDTO vote5 = new VoteDTO(champion1, item1, 1);
        VoteDTO vote6 = new VoteDTO(champion1, item2, 5);
        VoteDTO vote7 = new VoteDTO(champion2, item1, 4);
        VoteDTO vote8 = new VoteDTO(champion2, item2, 3);
        VoteDTO vote9 = new VoteDTO(champion1, item1, 2);
        VoteDTO vote10 = new VoteDTO(champion1, item2, 4);
        VoteDTO vote11 = new VoteDTO(champion2, item1, 5);
        VoteDTO vote12 = new VoteDTO(champion2, item2, 1);
        VoteDTO vote13 = new VoteDTO(champion1, item1, 3);
        VoteDTO vote14 = new VoteDTO(champion1, item2, 4);
        VoteDTO vote15 = new VoteDTO(champion2, item1, 2);
        VoteDTO vote16 = new VoteDTO(champion2, item2, 5);
        VoteDTO vote17 = new VoteDTO(champion1, item1, 4);
        VoteDTO vote18 = new VoteDTO(champion1, item2, 1);
        VoteDTO vote19 = new VoteDTO(champion2, item1, 3);
        VoteDTO vote20 = new VoteDTO(champion2, item2, 2);

        voteDAO.save(vote1);
        voteDAO.save(vote2);
        voteDAO.save(vote3);
        voteDAO.save(vote4);
        voteDAO.save(vote5);
        voteDAO.save(vote6);
        voteDAO.save(vote7);
        voteDAO.save(vote8);
        voteDAO.save(vote9);
        voteDAO.save(vote10);
        voteDAO.save(vote11);
        voteDAO.save(vote12);
        voteDAO.save(vote13);
        voteDAO.save(vote14);
        voteDAO.save(vote15);
        voteDAO.save(vote16);
        voteDAO.save(vote17);
        voteDAO.save(vote18);
        voteDAO.save(vote19);
        voteDAO.save(vote20);

    }
}
