package app.entities;

import app.persistence.dto.ChampionDTO;
import app.persistence.dto.VoteDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "votes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int championId;
    int itemId;
    int rating;

    public Vote(VoteDTO voteDTO) {
        this.id = voteDTO.getId();
        this.championId = voteDTO.getChampionId();
        this.itemId = voteDTO.getItemId();
        this.rating = voteDTO.getRating();
    }

}