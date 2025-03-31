package app.persistence.dto;
import app.entities.Admin;
import app.persistence.dao.impl.SecretKeyDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityManagerFactory;
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

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.email = admin.getEmail();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
    }

    public boolean validateSecretKey(SecretKeyDAO secretKeyDAO, String secretKeyFromRequest) {
        List<String> secretKeys = secretKeyDAO.getAllSecretKeys();
        if (secretKeys.contains(secretKeyFromRequest)) {
            secretKeyDAO.deleteSecretKey(secretKeyFromRequest);
            return true;
        }
        return false;
    }
}
