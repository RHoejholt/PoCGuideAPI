package app.persistence.dao.impl;

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
        Vote vote = new Vote(voteDTO);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(vote);
            em.getTransaction().commit();
        }
        return new VoteDTO(vote);
    }

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
                vote.setRating(voteDTO.getRating()); // Correct field name
                em.merge(vote);
                em.getTransaction().commit();
                return new VoteDTO(vote);
            }
            em.getTransaction().rollback();
            return null;
        } catch (Exception e) {
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
}
