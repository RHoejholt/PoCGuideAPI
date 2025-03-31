package app.persistence.dao.impl;

import app.entities.Admin;
import app.persistence.dao.IDAO;
import app.persistence.dto.AdminDTO;
import app.persistence.dto.ChampionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class AdminDAO implements IDAO<AdminDTO> {
    private static AdminDAO instance;
    private static EntityManagerFactory emf;

    private AdminDAO() {}

    public static AdminDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new AdminDAO();
            emf = _emf;
        }
        return instance;
    }

    public Optional<AdminDTO> findByEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Admin> query = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst().map(AdminDTO::new);
        }
    }

    public Optional<String> getPassword(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<String> query = em.createQuery("SELECT a.password FROM Admin a WHERE a.email = :email", String.class);
            query.setParameter("email", email);
            return query.getResultStream().findFirst();
        }
    }

    @Override
    public AdminDTO save(AdminDTO adminDTO) {
        Admin admin = new Admin(adminDTO);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        }
        return new AdminDTO(admin);
    }


    @Override
    public Optional<AdminDTO> findById(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            Admin admin = em.find(Admin.class, id);
            return admin != null ? Optional.of(new AdminDTO(admin)) : Optional.empty();
        }
    }

    @Override
    public List<AdminDTO> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Admin> query = em.createQuery("SELECT a FROM Admin a", Admin.class);
            return query.getResultList().stream().map(AdminDTO::new).toList();
        }
    }

    @Override
    public AdminDTO update(AdminDTO dto) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Admin admin = em.find(Admin.class, dto.getId());
            if (admin != null) {
                admin.setEmail(dto.getEmail());
                admin.setUsername(dto.getUsername());
                em.merge(admin);
                em.getTransaction().commit();
                return new AdminDTO(admin);
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Optional.ofNullable(em.find(Admin.class, id)).ifPresent(em::remove);
            em.getTransaction().commit();
        }
    }
}
