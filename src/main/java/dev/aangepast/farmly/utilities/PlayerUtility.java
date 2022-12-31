package dev.aangepast.farmly.utilities;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerUtility {
    private static Map<String, PlayerData> playerData= new HashMap<>();

    public static PlayerData getPlayerData(Player p){
        if(!playerData.containsKey(p.getUniqueId().toString())){
            PlayerData data = new PlayerData();
            playerData.put(p.getUniqueId().toString(), data);
            return data;
        }
         return playerData.get(p.getUniqueId().toString());
    }

    public static void setPlayerData(Player p, PlayerData data) {
        if (data == null) {
            playerData.remove(p.getUniqueId().toString());
        } else {
            playerData.put(p.getUniqueId().toString(), data);
        }
    }

    public static String getFolderPath(Player p, Main plugin){
        return plugin.getDataFolder().getAbsolutePath() + "/player/" + p.getUniqueId();
    }

    public static void deletePlayerData(Player p){
        playerData.remove(p.getUniqueId().toString());
    }

    public static void giveBuilding(Player player, String name, int buildingID, int level, int x, int z, int y){
        ItemStack item = new ItemBuilder(Material.PAPER, 1).setName(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + name).addLoreLine("").addLoreLine(ChatColor.GRAY + "Right-Click to enter build mode.").addLoreLine(ChatColor.GRAY + "Left-Click to build in build mode.").addLoreLine("").addLoreLine(ChatColor.DARK_GREEN + "Building: " + ChatColor.GREEN + name).addLoreLine(ChatColor.DARK_GREEN + "Level: " + ChatColor.GREEN + level).addLoreLine(ChatColor.DARK_GREEN + "Size: " + ChatColor.GREEN + x + "x" + y + "x" + z).addLoreLine("").addLoreLine(ChatColor.DARK_GRAY + "#" + buildingID).toItemStack();
        player.getInventory().addItem(item);
    }

}
