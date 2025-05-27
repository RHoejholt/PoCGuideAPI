package app.persistence;

import app.persistence.dao.impl.ChampionDAO;
import app.persistence.dao.impl.ItemDAO;
import app.persistence.dao.impl.VoteDAO;
import app.persistence.dto.ChampionDTO;
import app.persistence.dto.ItemDTO;

import java.util.List;

public class Populator {

    public static void populateDataBase(ChampionDAO championDAO, ItemDAO itemDAO, VoteDAO voteDAO) {
        ChampionDTO championDTO9;
        List<ChampionDTO> champions = List.of( //chatgpt assisted data population
                new ChampionDTO("Ahri", "Quick Attack. Attack: Recall another ally to grant me +1|+0."),
                new ChampionDTO("Annie", "Play: Deal 2 to an enemy. When you've played 6+ fast spells, level up."),
                new ChampionDTO("Bard", "Chime: When you draw a follower, grant it +1|+1."),
                new ChampionDTO("Caitlyn", "Quick Attack. Attack: Plant 2 Flashbomb Traps randomly in the top 10 cards of the enemy deck."),
                new ChampionDTO("Darius", "Overwhelm. Level Up: When the enemy Nexus has 10 or less health."),
                new ChampionDTO("Diana", "Nightfall: Challenger. Grant me +2|+0 this round."),
                new ChampionDTO("Ekko", "Quick Attack. Play: Predict. Attack: Create a Time Trick in hand."),
                new ChampionDTO("Elder Dragon", "Overwhelm. When you summon a Dragon, grant it +1|+1."),
                new ChampionDTO("Elise", "Fearsome. Round Start: Summon a Spiderling."),
                new ChampionDTO("Fiddlesticks",  "Game Start: Plant me as a  Nightmare deep in the enemy deck. When I'm summoned, grant enemies 2  Gloom."),
                new ChampionDTO("Garen", "Regeneration. Attack: Ready your attack."),
                new ChampionDTO("Gnar", "Quick Attack. When you damage the enemy Nexus, transform into Mega Gnar."),
                new ChampionDTO("Illaoi", "Attack: Spawn 1. If your Tentacle has 3+ Power, give me Overwhelm."),
                //   new ChampionDTO("Irelia", "Attack: Blade Dance 1."),
                new ChampionDTO("Jack", "Overwhelm. When you play a card, grant me +1|+0."),
                new ChampionDTO("Janna", "When you play a spell, grant your allies +1|+0 this round."),
                new ChampionDTO("Jayce", "Quick Attack. Play: Choose a spell. When you cast a spell, gain its keyword."),
                new ChampionDTO("Jhin", "Quick Attack. When you cast a spell, deal 1 to all enemies."),
                new ChampionDTO("Jinx", "Quick Attack. I see your hand is empty."),
                new ChampionDTO("Kayle", "When you play a spell, grant me +1|+0."),
                new ChampionDTO("Kindred", "Quick Attack. Round Start: Mark the weakest enemy."),
                new ChampionDTO("LeBlanc", "Quick Attack. When you deal 15+ damage, level up."),
                new ChampionDTO("Lee Sin", "When you cast a spell, give Lee Sin Challenger this round. If you cast another, give Lee Sin Barrier."),
                new ChampionDTO("Leona", "Daybreak: Stun the strongest enemy."),
                //   new ChampionDTO("Lulu", "Support: Grant my supported ally +1|+1 this round."),
                new ChampionDTO("Lux", "Barrier. When you cast a spell, deal 2 to all enemies."),
                new ChampionDTO("Lux Illuminated", "Support: Grant my supported ally Spirit and give me Barrier this round."),
                //   new ChampionDTO("Malphite", "Overwhelm. When you summon a landmark, grant me +1|+1."),
                new ChampionDTO("Master Yi", "Quick Attack. Round Start: Reduce the cost of the most expensive spell in your hand by 1."),
                new ChampionDTO("Miss Fortune", "When allies attack, deal 1 to all battling enemies and the enemy Nexus."),
                new ChampionDTO("Mordekaiser", "Overwhelm. When you kill an enemy, summon a copy of it."),
                new ChampionDTO("Nami", "When you cast a spell, grant +1|0 to the weakest other ally that isn't Immobile."),
                new ChampionDTO("Nasus", "Fearsome. When you slay a unit, grant me +1|+1."),
                new ChampionDTO("Nilah", "Quick Attack. When you draw a card, grant me +1|+0."),
                new ChampionDTO("Nidalee", "Quick Attack. When you summon an ally, grant it +1|+0."),
                new ChampionDTO("Ornn", "Tough. When you summon an ally, grant it +1|+1."),
                //   new ChampionDTO("Pantheon", "Overwhelm. When you target allies, grant them +1|+1."),
                new ChampionDTO("Pyke", "When you kill an enemy with an attack, grant me +1|+1."),
                //   new ChampionDTO("Ryze", "When you play a spell, draw 1."),
                new ChampionDTO("Sett", "When you play a spell, grant me +1|+0. When I strike, draw 1."),
                //   new ChampionDTO("Soraka", "Support: Heal my supported ally for 4."),
                new ChampionDTO("Tahm Kench", "Support: Give me +1|+1 this round. When I kill a unit, Obliterate it."),
                //   new ChampionDTO("Taric", "Support: Grant my supported ally Tough this round."),
                new ChampionDTO("Teemo", "Nexus Strike: Plant 5 Poison Puffcaps on random cards in the enemy deck."),
                new ChampionDTO("The Poro King", "Support: Grant my supported ally +1|+1."),
                new ChampionDTO("Thresh", "Fearsome. When an enemy dies, grant me +1|+1."),
                //   new ChampionDTO("Twisted Fate", "Play: Pick a card to play: Blue Card, Red Card, or Gold Card."),
                new ChampionDTO("Veigar", "Play: Deal 1 to the enemy Nexus. When you cast a spell, deal 1 to the enemy Nexus."),
                new ChampionDTO("Vi", "When you play a card, grant me +1|0. Once I've struck for 10+ damage, level up."),
                new ChampionDTO("Viktor", "Augment. Round Start: Create a Hex Core Upgrade in hand."),
                new ChampionDTO("Yasuo", "Quick Attack. When you Stun or Recall an enemy, Yasuo deals 2 to it.")
                //   new ChampionDTO("Zed", "Quick Attack. Attack: Summon an attacking Living Shadow with Zed’s stats."),
                //   new ChampionDTO("Zoe", "Elusive. Nexus Strike: Create a Supercool Starchart in hand."),

        );

        for (ChampionDTO champion : champions) {
            championDAO.save(champion);
        }

        ItemDTO itemDTO1 = new ItemDTO("Banshee's Veil", "Spellshield");
        ItemDTO itemDTO2 = new ItemDTO("Armordillo Shell", "+0/+1 and Tough");
        itemDAO.save(itemDTO1);
        itemDAO.save(itemDTO2);

    }
}
