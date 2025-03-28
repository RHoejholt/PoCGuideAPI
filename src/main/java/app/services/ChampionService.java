package app.services;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
public class ChampionService {


    public  List<String> getAllChampions(){
        ArrayList<String> championsList = new ArrayList<>();
        championsList.add("Champion 1");
        championsList.add("Champion 2");
        return championsList;
    }
}
