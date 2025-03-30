package app.persistence.dao.impl;
import app.entities.Item;
import app.persistence.dao.IDAO;
import app.persistence.dto.ItemDTO;
import io.javalin.http.NotFoundResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemDAO implements IDAO<ItemDTO> {
    private static ItemDAO instance;
    private static EntityManagerFactory emf;

    private ItemDAO() {
    }

    public static ItemDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new ItemDAO();
            emf = _emf;
        }
        return instance;
    }

    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        }
        return new ItemDTO(item);
    }

    public List<ItemDTO> getItems() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<ItemDTO> query = em.createQuery(
                    "SELECT new app.persistence.dto.ItemDTO(i.id, i.name, i.description) FROM Item i",
                    ItemDTO.class
            );
            return query.getResultList();
        }
    }




    public List<ItemDTO> getItemsForChampion(VoteDAO voteDAO, int championId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i", Item.class);
            List<Item> itemList = query.getResultList(); // Retrieve all items

            return itemList.stream()
                    .map(i -> new ItemDTO(i, voteDAO.getAverageRatingForItemAndChampion(i.getId(), championId)))
                    .collect(Collectors.toList());
        }
    }



    @Override
    public Optional<ItemDTO> findById(int id) {
        try (EntityManager em = emf.createEntityManager()){
            Item item = em.find(Item.class, id);
            if (item != null){
                return Optional.of(new ItemDTO(item));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<ItemDTO> findAll() {
        return List.of();
    }

    public ItemDTO update(ItemDTO dto) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Item item = em.find(Item.class, dto.getId());

            if (item != null) {
                item.setName(dto.getName());
                item.setDescription(dto.getDescription());

                em.merge(item);
                em.getTransaction().commit();

                return new ItemDTO(item);
            }

            return null;
        }
    }

        @Override
        public void delete(int id) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();

                // Retrieve the existing Item entity from the database
                Item item = em.find(Item.class, id);

                // If the item exists, remove it from the database
                if (item != null) {
                    em.remove(item);
                    em.getTransaction().commit();
                } else {
                    // Handle the case where the item was not found (e.g., throw an exception)
                    throw new NotFoundResponse("Item not found");
                }
            }
        }


/*
    @Override
    public Optional<ChampionDTO> findById(int id) {
        try (EntityManager em = emf.createEntityManager()){
            Champion champion = em.find(Champion.class, id);
            if (champion != null){
                return Optional.of(new ChampionDTO(champion));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<ChampionDTO> findAll() {
        return List.of();
    }

    @Override
    public ChampionDTO update(ChampionDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }


 */
}
