package app;
import app.config.HibernateConfig;
import app.persistence.dto.ChampionDTO;
import app.persistence.dao.impl.ChampionDAO;
import app.controllers.ChampionController;
import io.javalin.Javalin;
import jakarta.persistence.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        Javalin app = Javalin.create().start(7000);
        ChampionController championController = new ChampionController();
        ChampionDAO championDAO = ChampionDAO.getInstance(emf);
        populateDataBase(championController, championDAO);
        addRoutes(app, championController);
    }

    private static void addRoutes(Javalin app, ChampionController championController) {
        app.get("/hello", ctx -> ctx.result("Hello World"));

        app.get("/champions", championController::getAllChampions);

        app.get("/champions/{id}", championController::getChampionById);

        /*
        app.post("/champions", ctx -> {
            Champion champion = ctx.bodyAsClass(Champion.class);
            ctx.json(championService.createChampion(champion));
        });

        app.post("/votes", ctx -> {
            VoteDTO voteDTO = ctx.bodyAsClass(VoteDTO.class);
            ctx.json(voteService.submitVote(voteDTO));
        });

 */
    }

    private static void populateDataBase(ChampionController cs, ChampionDAO championDAO) throws IOException, InterruptedException {
        ChampionDTO champion1 = new ChampionDTO("Fiddlesticks", "scary guy");
        ChampionDTO champion2 = new ChampionDTO("Amumu", "sad guy");
        championDAO.save(champion1);
        championDAO.save(champion2);

    }
}
