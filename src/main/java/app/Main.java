package app;
import app.config.HibernateConfig;
import app.controllers.AdminController;
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

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        Javalin app = Javalin.create().start(7000);

        ChampionController championController = new ChampionController();
        ChampionDAO championDAO = ChampionDAO.getInstance(emf);
        ItemController itemController = new ItemController();
        ItemDAO itemDAO = ItemDAO.getInstance(emf);
        VoteController voteController = new VoteController();
        VoteDAO voteDAO = VoteDAO.getInstance(emf);
        AdminController adminController = new AdminController();



        populateDataBase(championDAO, itemDAO, voteDAO);
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

    private static void populateDataBase(ChampionDAO championDAO, ItemDAO itemDAO, VoteDAO voteDAO) {
        ChampionDTO championDTO1 = new ChampionDTO("Fiddlesticks", "scary guy");
        ChampionDTO championDTO2 = new ChampionDTO("Amumu", "sad guy");
        championDAO.save(championDTO1);
        championDAO.save(championDTO2);

        ItemDTO itemDTO1 = new ItemDTO("Trinity Force", "tons of damage");
        ItemDTO itemDTO2 = new ItemDTO("Infinity edge", "tons of infinity");
        itemDAO.save(itemDTO1);
        itemDAO.save(itemDTO2);


        Champion champion1 = championDAO.findById(1)
                .map(Champion::new)
                .orElseThrow(() -> new RuntimeException("Champion not found"));

        Champion champion2 = championDAO.findById(2)
                .map(Champion::new)
                .orElseThrow(() -> new RuntimeException("Champion not found"));

        Item item1 = itemDAO.findById(1)
                .map(Item::new)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Item item2 = itemDAO.findById(2)
                .map(Item::new)
                .orElseThrow(() -> new RuntimeException("Item not found"));

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

        saveVotes(voteDAO, vote1, vote2, vote3, vote4, vote5, vote6, vote7, vote8, vote9, vote10);
        saveVotes(voteDAO, vote11, vote12, vote13, vote14, vote15, vote16, vote17, vote18, vote19, vote20);

    }

    private static void saveVotes(VoteDAO voteDAO, VoteDTO vote1, VoteDTO vote2, VoteDTO vote3, VoteDTO vote4, VoteDTO vote5, VoteDTO vote6, VoteDTO vote7, VoteDTO vote8, VoteDTO vote9, VoteDTO vote10) {
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
    }
}
