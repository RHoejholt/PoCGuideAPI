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

        ItemDTO itemDTO9;
        List<ItemDTO> items = List.of(

        new ItemDTO("Arcane Comet", "Round Start: Create a Fleeting 6 Falling Comet in hand."),


        new ItemDTO("Archangel's Staff", "Round Start: Refill your spell mana."),


        new ItemDTO("Armordillo Shell", "+0|+1 and Tough"),


        new ItemDTO("Banshee's Veil", "Spellshield"),


        new ItemDTO("Big Guns", "Power: Your spells and skills deal 1 extra damage."),


        new ItemDTO("Black Shield", "Power: When you summon an ally, grant it SpellShield."),


        new ItemDTO("Caulfield's Warhammer", "+2|+1"),


        new ItemDTO("Cease and Desist", "3 Impact. Play: If I have 10+ Power, give me Barrier and start a free attack Challenging the strongest enemy."),


        new ItemDTO("Chosen by the Stars", "Fated, Augment, Empowered 10: Overwhelm, Regeneration, and Challenger."),



        new ItemDTO("Chemtech Duplicator", "When you play a spell, if you have 6+ mana gems, copy it with the same targets."),


        new ItemDTO("Condenser", "Support: Create a 1 cost 1|1 copy of my supported ally in hand."),


        new ItemDTO("Corrupted Star Fragment", "Support: Kill my supported ally and grant me its keyword and stats."),


       // new ItemDTO("Cosmic Blessing", "+1|+1. Quest: Win an Adventure. Reward: Gain 5x the normal Champion XP."),


       // new ItemDTO("Cosmic Pearl", "+1 Starting Mana. Quest: Win an Adventure. Reward: Gain 5x the normal Champion XP."),


        new ItemDTO("The Curator's Gatebreaker", "-1|-0. Play: I strike the enemy Nexus."),


        new ItemDTO("Defense Spending", "Power: Your Nexus has Tough. I am Tech."),


        new ItemDTO("Death's Foil", "+1|+0. While I'm attacking, I can't take damage or die."),


        new ItemDTO("Deadly Harpoon", "+2|+0 and Challenger. Power: Game Start: Shuffle two 5 Deaths From Below into your deck and grant 5 Deaths From Below a random epic item."),


        new ItemDTO("The Deceiver's Crest", "When I level up, create a copy of my champion spell in hand. It costs 0 this round. It will become a regular champion card once the champion leaves the board."),
        new ItemDTO("Disciple of Shadows", "I cost 1 less for each ally in play. Play: Deal 3 to all other allies."),
                new ItemDTO("Cruel Experiments", "Power: When you play an ally with 2+ Health, deal 1 to it and grant it Overwhelm and Impact."),
                new ItemDTO("Dreadway Chase Gun", "When I'm summoned, create a Dreadway in hand."),
                new ItemDTO("Dreams of Yordles", "Game Start: Create 3 random Yordles in hand."),
                new ItemDTO("Echoing Spirit", "Game Start: Create 7 copies of me in deck. Champion Spells cost 1 less."),
                new ItemDTO("Essence Theft", "When you Recall another unit, grant me +1|+0. Power: Game Start: Create a Homecoming in hand and grant it Double Time Watch."),
                new ItemDTO("Everfrost", "When I'm summoned, Stun the strongest enemy."),
                new ItemDTO("Fear-Cleaving Axe", "Spirit Attack: Boost all units."),
                new ItemDTO("Found Fortune", "Play: Draw the highest cost card that costs less than my base cost, grant it an Epic item, and reduce its cost to 0."),
                new ItemDTO("Frozen Tomb", "Game Start: If I'm in deck or hand, summon a Stormcarved Spire with Countdown 4: Summon me."),
                new ItemDTO("Full Build", "Challenger, Tough, and Overwhelm."),
                new ItemDTO("Galeforce", "When I'm summoned, Rally."),
                new ItemDTO("Golden Spatula", "When you summon an ally, grant it a random keyword."),
              //  new ItemDTO("Greater Cosmic Blessing", "+2|+2. Quest: Win an Adventure. Reward: Gain 10x the normal Champion XP."),
               // new ItemDTO("Greater Cosmic Pearl", "+1 Starting Mana. Quest: Win an Adventure. Reward: Gain 10x the normal Champion XP."),
                new ItemDTO("Greenglade Shadeleaf", "When I'm summoned, give me Elusive this round."),
                new ItemDTO("Guardian Angel", "Start adventures with +1 Revive."),
                new ItemDTO("Guardian's Orb", "When I'm summoned, grant me Barrier."),
                new ItemDTO("Guardian's Trinket", "When I'm summoned, grant me SpellShield."),
                new ItemDTO("Guinsoo's Rageblade", "When I attack, grant me +1|+0."),
                new ItemDTO("Heart of Gold", "When I'm summoned, grant me +1|+1 for each 200 gold you have."),
                new ItemDTO("Hextech Rifle", "When I'm summoned, deal 1 to all enemies."),
                new ItemDTO("Hidden Tome", "When I'm summoned, draw 1."),
                new ItemDTO("Hymn of Valor", "When I'm summoned, grant me +1|+1 for each ally."),
                new ItemDTO("Icon of Valhir", "When I'm summoned, grant me Regeneration."),
                new ItemDTO("Jaurim's Fist", "+1|+2."),
                new ItemDTO("Jayce's Hextech Battery", "When I'm summoned, refill your spell mana."),
                new ItemDTO("Laurent Bladerack", "When I'm summoned, grant me Challenger."),
                new ItemDTO("Living Weapon", "When I'm summoned, grant me Quick Attack."),
                new ItemDTO("Loaded Dice", "When I'm summoned, create a random card in hand."),
                new ItemDTO("Lost Chapter", "When I'm summoned, refill your spell mana."),
                new ItemDTO("Luden's Tempest", "Your spells and skills deal 1 extra damage."),
                new ItemDTO("Luminous Orb", "When I'm summoned, grant me SpellShield."),
                new ItemDTO("Lux's Incandescent Baton", "When I'm summoned, grant me Barrier."),
                new ItemDTO("Norra's Portal Accelerator", "When I'm summoned, create a random 7+ cost unit in hand."),
                new ItemDTO("Oath of the Guardians", "When I'm summoned, grant me Tough."),
                new ItemDTO("Packed Powder", "When I'm summoned, deal 1 to all enemies."),
                new ItemDTO("Portal Pals", "When I'm summoned, create 2 random 7+ cost units from your regions and reduce their cost by my base cost."),
              //  new ItemDTO("Quest for Wisdom", "When I'm summoned, draw 1 and give it Fleeting."),
                new ItemDTO("Ravenous Hydra", "When I'm summoned, deal 1 to all enemies."),
                new ItemDTO("Riptide Battery", "When I'm summoned, deal 1 to all enemies."),
                new ItemDTO("Scales of Judgment", "Power: When you play a champion, it strikes the weakest enemy. If it's Nasus, it also strikes the strongest enemy."),
                new ItemDTO("Shield of Daybreak", "Power: Allies with Daybreak have Tough. When an ally activates Daybreak, grant allies +0|+1."),
                new ItemDTO("Starforged Gauntlets", "Power: Game Start: If I'm in deck or hand and Titanic, +1 Starting Mana. You can find Level 2 Champions when you Invoke or Manifest."),
                new ItemDTO("The Beast Within", "When I'm summoned, grant me Overwhelm."),
                new ItemDTO("The Grand Duelist's Blade", "Challenger."),
                new ItemDTO("The Grand General's Counterplan", "When an enemy champion is summoned, draw me if I'm not in hand and reduce my cost by that champion's cost."),
                new ItemDTO("The Gravedigger's Spade", "Round Start: Draw 1 and give it Fleeting."),
                new ItemDTO("The Scourge's Stash", "Plunder: I cost 2 less."),
                new ItemDTO("The Starchild's Staff", "Game Start: Heal your Nexus 5."),
                new ItemDTO("The Troll King's Crusher", "Overwhelm."),
                new ItemDTO("Treasures of the Deep", "Deep and I am a Sea Monster. When I'm summoned, if you aren't Deep, Toss equal to my cost."),
                new ItemDTO("Warmog's Armor", "Regeneration."),
                new ItemDTO("Crownguard Inheritance", "When I level up, Rally."),

        new ItemDTO("Soul Spear", "+1|+0 and Fearsome."),

        new ItemDTO("Stalker's Blade", "When I'm summoned, I strike the weakest enemy."),

        new ItemDTO("Star Gem", "Allied champions have +1|+1 and cost 2 less."),

        new ItemDTO("Stormrazor", "Quick Attack."),

        new ItemDTO("Succubus's Brand", "+1|+1 and when I kill a unit, summon a random Husk."),

        new ItemDTO("The Berserker's Buckle", "When I survive damage, grant me +2|+2."),

        new ItemDTO("The Bounty Hunter's Renown", "I have +1|+1 for every 200 Gold you have."),

        new ItemDTO("The Card Master's Gambit", "+1|+1 and when you win an encounter without taking any Nexus damage, earn 1 Reroll."),

        new ItemDTO("The Chameleons Necklace", "Game Start: Create 2 copies of me in your deck."),

        new ItemDTO("The Collector", "+1|+1 and when I kill an enemy, you earn 50 Gold. (Max 2 kills per encounter)"),

        new ItemDTO("The Loose Cannon's Payload", "When I'm summoned, discard your hand then create that many 2|2 Pow-Pows in hand."),

        new ItemDTO("Transmogulator", "Support: Transform my supported ally into a random follower that costs 3 more."),

        new ItemDTO("Troll King's Crown", "Allies have Overwhelm."),

        new ItemDTO("True Ice Flail", "When I'm summoned, give enemies Vulnerable this round."),

        new ItemDTO("Turret Plating", "Your Nexus has Tough."),

        new ItemDTO("Utmost Despair", "Play: Grant enemies Gloom."),

        new ItemDTO("Voidborne Carapace", "Evolve: When ANY unit dies, grant me its keywords."),

        new ItemDTO("Wicked Harvest", "Play: Deal 3 to all other units."),

        new ItemDTO("Wriggle's Lantern", "Round End: Grant me Impact twice if I'm in hand (max Impact 10)."),
                new ItemDTO("Z-Drive Prototype", "Start adventures with +2 Rerolls."));

        for (ItemDTO item : items) {
            itemDAO.save(item);
        }


    }
}
