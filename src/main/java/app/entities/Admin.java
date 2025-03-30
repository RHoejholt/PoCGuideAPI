package app.entities;

import app.persistence.dto.AdminDTO;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String secretKey;

    public Admin(AdminDTO adminDTO) {
        this.id = adminDTO.getId();
        this.email = adminDTO.getEmail();
        this.username = adminDTO.getUsername();
        this.password = adminDTO.getPassword();
        this.secretKey = adminDTO.getSecretKey();
    }

    public void setPasswordWithHashing(String plainPassword) {
        this.password = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword) {
        return BCrypt.checkpw(plainPassword, this.password);
    }
}
