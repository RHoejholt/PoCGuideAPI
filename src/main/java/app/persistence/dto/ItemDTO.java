package app.persistence.dto;
import app.entities.Champion;
import app.entities.Item;
import app.persistence.dao.impl.ItemDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDTO {
    private int id;
    private String name;
    private String description;
    private double averageRating;

    public ItemDTO(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.averageRating = averageRating;
    }

    public ItemDTO(String name, String description, double averageRating){
        this.name= name;
        this.description = description;
        this.averageRating = averageRating;

    }

    public ItemDTO(Item item, double averageRating) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.averageRating = averageRating;
    }

    public static List<ItemDTO> toDTOList(List<Item> items){
        return items.stream().map(ItemDTO::new).toList();
    }
}