package app.persistence.dto;

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
    private int championId;
    private int itemId;
    private int rating;

    public VoteDTO(Vote vote) {
        this.id = vote.getId();
        this.championId = vote.getChampionId();
        this.itemId = vote.getItemId();
        this.rating = vote.getRating();
    }

    public static List<VoteDTO> toDTOList(List<Vote> votes) {
        return votes.stream().map(VoteDTO::new).toList();
    }
}
