package app;

//import app.entities.Champion;
import app.entities.Champion;
import app.entities.ErrorMsg;
import app.services.ChampionService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        ChampionService championService = new ChampionService();
        /*
        VoteService voteService = new VoteService();

     */

        app.get("/hello", ctx -> ctx.result("Hello World"));

        app.get("/champions", ctx -> {
            ctx.json(championService.getAllChampions());
        });

        app.get("/champions/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));

            Champion championmatch = championService.getChampionById(id);
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
}
