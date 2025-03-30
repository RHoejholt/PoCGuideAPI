package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "secret_keys")
public class SecretKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Getter
    @Column(nullable = false, unique = true)
    private String secretKey;

    public SecretKey() {}

    public SecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}
