package app;
import app.config.HibernateConfig;
import app.persistence.dto.ChampionDTO;
import app.persistence.dao.impl.ChampionDAO;
import app.entities.Champion;
import app.entities.ErrorMsg;
import app.controllers.ChampionController;
import io.javalin.Javalin;
import jakarta.persistence.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        Javalin app = Javalin.create().start(7000);
        ChampionController cs = new ChampionController();
        ChampionDAO championDAO = new ChampionDAO(emf.createEntityManager());
        /*
        VoteService voteService = new VoteService();

     */

        populateDataBase(cs, championDAO);

        app.get("/hello", ctx -> ctx.result("Hello World"));

        app.get("/champions", cs::getAllChampions2);

        app.get("/champions/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            Champion championmatch = cs.getChampionById(id);
            if (championmatch == null) {
                ctx.status(404);
                ctx.json(new ErrorMsg(404, "No content found for this request"));
                return;
            }
            ctx.json(championmatch);
        });



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
        ChampionDTO champion1 = new ChampionDTO(1, "Fiddlesticks", "scary guy");
        ChampionDTO champion2 = new ChampionDTO(2, "Amumu", "sad guy");
        championDAO.save(champion1);
        championDAO.save(champion2);

    }
}
