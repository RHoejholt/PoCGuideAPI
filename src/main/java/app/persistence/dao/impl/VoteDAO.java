package app.persistence.dao.impl;

import app.entities.Champion;
import app.entities.Item;
import app.persistence.dto.VoteDTO;
import app.entities.Vote;
import app.persistence.dao.IDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VoteDAO implements IDAO<VoteDTO> {
    private static VoteDAO instance;
    private static EntityManagerFactory emf;

    private VoteDAO() {}

    public static VoteDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new VoteDAO();
            emf = _emf;
        }
        return instance;
    }

    @Override
    public VoteDTO save(VoteDTO voteDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Fetch managed entities for associations
            Champion champion = em.find(Champion.class, voteDTO.getChampionId());
            Item item = em.find(Item.class, voteDTO.getItemId());
            if (champion == null || item == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Invalid championId or itemId");
            }
            // Create and persist vote
            Vote vote = new Vote();
            vote.setChampion(champion);
            vote.setItem(item);
            vote.setRating(voteDTO.getRating());

            em.persist(vote);
            em.flush();  // force the INSERT now
            em.getTransaction().commit();
            return new VoteDTO(vote);
        }
    }

    /*
    @Override
    public VoteDTO save(VoteDTO voteDTO) {
        Vote vote = new Vote(voteDTO);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(vote);
            em.getTransaction().commit();
        }
        return new VoteDTO(vote);
    }*/

    public List<VoteDTO> getVotes() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Vote> query = em.createQuery("SELECT v FROM Vote v", Vote.class);
            return query.getResultList().stream().map(VoteDTO::new).collect(Collectors.toList());
        }
    }

    @Override
    public Optional<VoteDTO> findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            Vote vote = em.find(Vote.class, id);
            return vote != null ? Optional.of(new VoteDTO(vote)) : Optional.empty();
        }
    }

    @Override
    public List<VoteDTO> findAll() {
        return getVotes();
    }

    @Override
    public VoteDTO update(VoteDTO voteDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Vote vote = em.find(Vote.class, voteDTO.getId());
            if (vote != null) {
                vote.setRating(voteDTO.getRating());
                em.merge(vote);
                em.getTransaction().commit();
                return new VoteDTO(vote);
            }
            em.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Vote vote = em.find(Vote.class, id);
            if (vote != null) {
                em.remove(vote);
            }
            em.getTransaction().commit();
        }
    }

    public double getAverageRatingForItemAndChampion(int itemId, int championId) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Double> query = em.createQuery(
                    "SELECT COALESCE(AVG(v.rating), 0) FROM Vote v WHERE v.item.id = :itemId AND v.champion.id = :championId",
                    Double.class
            );
            query.setParameter("itemId", itemId);
            query.setParameter("championId", championId);
            Double result = query.getSingleResult();

           return result;
        }
    }


}
