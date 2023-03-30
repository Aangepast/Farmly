package dev.aangepast.farmly.managers;

import dev.aangepast.farmly.data.Building;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class buildingManager {


    private static List<Player> players = new ArrayList<>();
    private static List<Building> buildings = new ArrayList<>();

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Player> players) {
        buildingManager.players = players;
    }

    public static void addPlayer(Player player){
        players.add(player);
    }

    public static void removePlayer(Player player){
        players.remove(player);
    }

    public static boolean containsPlayer(Player player){
        return players.contains(player);
    }

    public static void addBuilding(Building building){
        buildings.add(building);
    }

    public static void removeBuilding(Building building){
        buildings.remove(building);
    }

    public static Building getBuildingByName(String name){
        for(Building building : buildings){
            if(building.getName().equalsIgnoreCase(name)){
                return building;
            }
        }
        return null;
    }

    public static Building getBuildingByID(int id){
        for(Building building : buildings){
            if(building.getId() == id){
                return building;
            }
        }
        return null;
    }
}
