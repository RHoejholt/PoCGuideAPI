package app.persistence.dao.impl;

import app.persistence.dto.ChampionDTO;

import app.entities.Champion;
import app.persistence.dao.IDAO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ChampionDAO implements IDAO<ChampionDTO> {
    private static ChampionDAO instance;
    private static EntityManagerFactory emf;

    private ChampionDAO() {
    }

    public static ChampionDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new ChampionDAO();
            emf = _emf;
        }
        return instance;
    }

    @Override
    public ChampionDTO save(ChampionDTO championDTO) {
        Champion champion = new Champion(championDTO);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(champion);
            em.getTransaction().commit();
        }
        return new ChampionDTO(champion);
    }

    public List<ChampionDTO> getChampions() {
        List<Champion> championList = new ArrayList<>();
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Champion> query = em.createQuery("SELECT p FROM Champion p", Champion.class);
            return ChampionDTO.toDTOList(query.getResultList());
        }
    }

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
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Champion champion = em.find(Champion.class, dto.getId());
            if (champion != null) {
                champion.setName(dto.getName());
                champion.setDescription(dto.getDescription());
                em.merge(champion);
                em.getTransaction().commit();
                return new ChampionDTO(champion);
            }
            return null;
        }
    }


    @Override
    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Champion champion = em.find(Champion.class, id);
            if (champion != null) {
                em.remove(champion); // Remove the Champion entity from the database
                em.getTransaction().commit();
            }
        }
    }
}