package app.persistence.dto;

import app.entities.Champion;
import app.entities.Item;
import app.entities.Vote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteDTO {
    private int id;
    private Champion champion;
    private Item item;
    private int rating;

    public VoteDTO(Vote vote) {
        this.id = vote.getId();
        this.champion = vote.getChampion();
        this.item = vote.getItem();
        this.rating = vote.getRating();
    }

    public VoteDTO(Champion champion, Item item, int rating) {
        this.champion = champion;
        this.item = item;
        this.rating = rating;
    }

    public static List<VoteDTO> toDTOList(List<Vote> votes) {
        return votes.stream().map(VoteDTO::new).toList();
    }
}
