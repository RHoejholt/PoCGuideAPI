package app.persistence.dao.impl;

import app.entities.Item;

import app.persistence.dao.IDAO;
import app.persistence.dto.ItemDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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
        Item item = new Item(itemDTO);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        }
        return new ItemDTO(item);
    }

    public List<ItemDTO> getItems() {
        List<Item> itemList = new ArrayList<>();
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i", Item.class);
            return ItemDTO.toDTOList(query.getResultList());
        }
    }


    @Override
    public Optional<ItemDTO> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<ItemDTO> findAll() {
        return List.of();
    }

    @Override
    public ItemDTO update(ItemDTO dto) {
        return null;
    }

    @Override
    public void delete(int id) {

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
