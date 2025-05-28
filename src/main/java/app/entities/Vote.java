package app.entities;
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

    @Setter
    @ManyToOne
    @JoinColumn(name = "champion_id", nullable = false)
    private Champion champion;

    @Setter
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
    int rating;

    public Vote(VoteDTO voteDTO) {
        this.id = voteDTO.getId();
        this.rating = voteDTO.getRating();
    }
}