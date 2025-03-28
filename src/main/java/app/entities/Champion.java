package app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "champions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Champion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String description;

}