package app.controllers;
import app.config.HibernateConfig;
import app.persistence.dao.impl.VoteDAO;
import app.persistence.dto.VoteDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

public class VoteController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    VoteDAO voteDAO = VoteDAO.getInstance(emf);


    public void submitVote(Context ctx) {
        VoteDTO voteDTO = ctx.bodyAsClass(VoteDTO.class);
        VoteDTO savedVote = voteDAO.save(voteDTO);
        ctx.json(savedVote);
    }

}
