package app.persistence.dto;
import app.entities.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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
   // @JsonIgnore
    private double averageRating;

    public ItemDTO(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.averageRating = averageRating;
    }

    public ItemDTO(String name, String description){
        this.name= name;
        this.description = description;

    }
/*
    public ItemDTO(int id, String name, String description){
        this.id = id;
        this.name= name;
        this.description = description;

    }
    */


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