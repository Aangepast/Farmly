package dev.aangepast.farmly.utilities;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.managers.cropManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerUtility {
    private static Map<String, PlayerData> playerData= new HashMap<>();
    private static Map<String, FarmData> farmData = new HashMap<>();

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

    public static FarmData getFarmData(Player p){
        if(!farmData.containsKey(p.getUniqueId().toString())){
            FarmData farm = new FarmData();
            farmData.put(p.getUniqueId().toString(), farm);
            return farm;
        }
        return farmData.get(p.getUniqueId().toString());
    }

    public static void setFarmData(Player p, FarmData farm){
        if(farm == null){
            farmData.remove(p.getUniqueId().toString());
        } else {
            farmData.put(p.getUniqueId().toString(), farm);
        }
    }

    public static String getFolderPath(Player p, Main plugin){
        return plugin.getDataFolder().getAbsolutePath() + "/player/" + p.getUniqueId();
    }

    public static String getFarmFolderPath(Player p, Main plugin){
        return plugin.getDataFolder().getAbsolutePath() + "/farms/" + p.getUniqueId();
    }

    public static void deletePlayerData(Player p){
        playerData.remove(p.getUniqueId().toString());
    }

    public static void giveBuilding(Player player, String name, int buildingID, int level, int x, int z, int y){
        ItemStack item = new ItemBuilder(Material.PAPER, 1).setName(ChatColor.DARK_AQUA + ChatColor.BOLD.toString() + name).addLoreLine("").addLoreLine(ChatColor.GRAY + "Right-Click to enter build mode.").addLoreLine(ChatColor.GRAY + "Left-Click to build in build mode.").addLoreLine("").addLoreLine(ChatColor.DARK_GREEN + "Building: " + ChatColor.GREEN + name).addLoreLine(ChatColor.DARK_GREEN + "Level: " + ChatColor.GREEN + level).addLoreLine(ChatColor.DARK_GREEN + "Size: " + ChatColor.GREEN + x + "x" + y + "x" + z).addLoreLine("").addLoreLine(ChatColor.DARK_GRAY + "#" + buildingID).toItemStack();
        player.getInventory().addItem(item);
    }

    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90.0F) % 360.0F;
        if (rotation < 0.0D) {
            rotation += 360.0D;
        }
        if ((0.0D <= rotation) && (rotation < 45D)) {
            return "W";
        }
        if ((45D <= rotation) && (rotation < 135D)) {
            return "N";
        }
        if ((135D <= rotation) && (rotation < 225D)) {
            return "E";
        }
        if ((225D <= rotation) && (rotation < 315D)) {
            return "S";
        }
        if ((315 <= rotation) && (rotation < 360.0D)) {
            return "N";
        }
        return null;
    }

    public static void breakBlock(Block block, Player player, ItemStack usedTool){
        block.breakNaturally(usedTool);
        CropData cropData = cropManager.getCrop(block.getType());
        String[] StringXP = String.valueOf(cropData.getXp()).split("\\.");
        int firstDecimal = Integer.parseInt(StringXP[0]);
        int secondDecimal = Integer.parseInt(StringXP[1]);
        PlayerData playerData = PlayerUtility.getPlayerData(player);
        playerData.get
        String msg = Utils.translateHexColorCodes("&#", "", "&#10fb83+ &#1bfb81" + firstDecimal + "&#26fb7e.&#31fb7c" + secondDecimal + " &#3cfb7aX&#47fc77P &#52fc75(&#5dfc73F&#68fc70a&#73fc6er&#7efc6cm&#89fc6ai&#94fc67n&#9ffc65g &#aafc63L&#b5fd60e&#c0fd5ev&#cbfd5ce&#d6fd59l&#e1fd57)");
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
    }

}
