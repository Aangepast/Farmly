package dev.aangepast.farmly.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class buildingManager {


    private static List<Player> players = new ArrayList<>();

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
}
