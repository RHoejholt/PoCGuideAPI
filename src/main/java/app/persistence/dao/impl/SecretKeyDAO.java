package app.persistence.dao.impl;

import app.entities.SecretKey;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class SecretKeyDAO {

    private static SecretKeyDAO instance;
    private static EntityManagerFactory emf;

    private SecretKeyDAO() {}

    public static SecretKeyDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new SecretKeyDAO();
            emf = _emf;
        }
        return instance;
    }

    // Get all secret keys
    public List<String> getAllSecretKeys() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<String> query = em.createQuery("SELECT s.secretKey FROM SecretKey s", String.class);
            return query.getResultList();
        }
    }

    public void deleteSecretKey(String secretKey) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<SecretKey> query = em.createQuery("SELECT s FROM SecretKey s WHERE s.secretKey = :secretKey", SecretKey.class);
            query.setParameter("secretKey", secretKey);
            SecretKey key = query.getSingleResult();
            if (key != null) {
                em.remove(key);
            }
            em.getTransaction().commit();
        }
    }

    // Save a secret key
    public void save(SecretKey secretKey) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(secretKey);
            em.getTransaction().commit();
        }
    }
}
