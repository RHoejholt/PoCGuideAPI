package app.entities;
import app.persistence.dto.ChampionDTO;
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

    public Champion(ChampionDTO championDTO) {
        this.id = championDTO.getId();
        this.name = championDTO.getName();
        this.description = championDTO.getDescription();
    }

}