package app.entities;

import app.persistence.dto.ChampionDTO;
import app.persistence.dto.ItemDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String description;
    double AverageRating;

    public Item(ItemDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getName();
        this.description = itemDTO.getDescription();
        this.AverageRating = itemDTO.getAverageRating();
    }

}