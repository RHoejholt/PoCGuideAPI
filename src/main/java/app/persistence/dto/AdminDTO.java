package app.persistence.dto;

import app.entities.Admin;
import app.persistence.dao.impl.SecretKeyDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminDTO {
    private static EntityManagerFactory emf;
    private int id;
    private String email;
    private String username;
    private String password;
    private String secretKey;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.secretKey = admin.getSecretKey();
    }

    public boolean validateSecretKey(SecretKeyDAO secretKeyDAO, String secretKeyFromRequest) {
        List<String> secretKeys = secretKeyDAO.getAllSecretKeys();
        if (secretKeys.contains(secretKeyFromRequest)) {
            secretKeyDAO.deleteSecretKey(secretKeyFromRequest);
            return true;
        }
        return false;
    }

    public AdminDTO(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public Admin toAdmin() {
        Admin admin = new Admin(this);
        // You can hash the password before setting it in the Admin entity
        admin.setPasswordWithHashing(this.password);  // Hashing the password before saving
        return admin;
    }

    public List<String> getAllSecretKeys() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<String> query = em.createQuery("SELECT a.secretKey FROM Admin a", String.class);
            return query.getResultList();
        }
    }



}
