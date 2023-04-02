package dev.aangepast.farmly.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class teleportManager {

    private static List<Player> teleportCommencing = new ArrayList<>();

    public static void addPlayer(Player player){
        teleportCommencing.add(player);
    }

    public static boolean containsPlayer(Player player){
        return teleportCommencing.contains(player);
    }

    public static void removePlayer(Player player){
        teleportCommencing.remove(player);
    }

}
