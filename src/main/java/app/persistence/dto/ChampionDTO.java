package app.persistence.dto;
import app.entities.Champion;
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
public class ChampionDTO {

    public ChampionDTO(Champion champion){
        this.id = champion.getId();
        this.name = champion.getName();
        this.description = champion.getDescription();
    }

    public ChampionDTO(String name, String description){
        this.name= name;
        this.description = description;

    }

    private int id;
    private String name;
    private String description;

    public static List<ChampionDTO> toDTOList(List<Champion> poems){
        return poems.stream().map(ChampionDTO::new).toList();
    }
}