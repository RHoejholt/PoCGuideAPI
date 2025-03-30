package app.controllers;
import java.util.*;
import app.config.HibernateConfig;
import app.entities.ErrorMsg;
import app.persistence.dao.impl.ChampionDAO;
import app.persistence.dao.impl.ItemDAO;
import app.persistence.dao.impl.VoteDAO;
import app.persistence.dto.ChampionDTO;
import app.persistence.dto.ItemDTO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

public class ChampionController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    ChampionDAO championDAO = ChampionDAO.getInstance(emf);
    ItemDAO itemDAO = ItemDAO.getInstance(emf);
    VoteDAO voteDAO = VoteDAO.getInstance(emf);

    public void getAllChampions(Context ctx) {
        ctx.json(championDAO.getChampions());
    }

    public void getChampionById(Context ctx) {
        Optional<ChampionDTO> match = championDAO.findById(Integer.parseInt(ctx.pathParam("id")));
        if (match.isPresent()) {
            ctx.json(match.get());
            return;
        } else {
            ctx.status(404);
            ctx.json(new ErrorMsg(404, "No content found for this request"));
        }
    }

    public void getChampionAndItemsById(Context ctx) {
        int championId = Integer.parseInt(ctx.pathParam("id"));
        Optional<ChampionDTO> match = championDAO.findById(championId);
        if (match.isEmpty()) {
            ctx.status(404);
            ctx.json(new ErrorMsg(404, "No content found for this request"));
            return;
        }

        ChampionDTO champion = match.get();

        List<ItemDTO> items = itemDAO.getItemsForChampion(voteDAO, championId);
        List<Map<String, Object>> itemsWithRatings = new ArrayList<>();
        for (ItemDTO item : items) {
            double averageRating = voteDAO.getAverageRatingForItemAndChampion(championId, item.getId());

            Map<String, Object> itemData = new LinkedHashMap<>();
            itemData.put("id", item.getId());
            itemData.put("name", item.getName());
            itemData.put("description", item.getDescription());
            itemData.put("averageRating", averageRating);

            itemsWithRatings.add(itemData);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("champion", champion);
        response.put("items", itemsWithRatings);

        ctx.json(response);

    }


}


