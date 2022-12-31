package dev.aangepast.farmly.inventories;

import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class profileInventory {

    public profileInventory(Player player){

        Inventory inv = Bukkit.createInventory(player, 27, ChatColor.DARK_GRAY + "Select a profile to continue");

        PlayerData data = PlayerUtility.getPlayerData(player);

        if(data != null){
            int levelOverall = data.getLevel() + data.getLevelCrafting() + data.getLevelFarming() + data.getLevelForgaging() + data.getLevelLifestock();
            ItemStack is = new ItemBuilder(Material.EMERALD_BLOCK, 1).setName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Load main profile").addLoreLine("").addLoreLine(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "FARM STATISTICS:").addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.GREEN + "Cash: $" + data.getCash()).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.DARK_PURPLE + "Overall level: " + levelOverall).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.DARK_AQUA + "Player level: " + data.getLevel()).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.GREEN + "Farming level: " + data.getLevelFarming()).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.GOLD + "Crafting level: " + data.getLevelCrafting()).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.DARK_GREEN + "Foraging level: " + data.getLevelForgaging()).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.YELLOW + "Lifestock level: " + data.getLevelLifestock()).addLoreLine(ChatColor.DARK_GRAY + "▪ " + ChatColor.LIGHT_PURPLE + "Skill points: " + data.getSkillPoints()).addLoreLine("").addLoreLine(ChatColor.DARK_GRAY + "FarmID #" + data.getFarmId()).toItemStack();
            inv.setItem(13, is);
        } else {
            ItemStack is = new ItemBuilder(Material.REDSTONE_BLOCK, 1).setName(ChatColor.DARK_RED + "No player data available").addLoreLine("").addLoreLine(ChatColor.WHITE + "This can happen due to several things:").addLoreLine(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "First time you've joined").addLoreLine(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Failed to get player data").addLoreLine(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "You've just created a new profile").addLoreLine("").addLoreLine(ChatColor.WHITE + "Most of the time you can fix").addLoreLine(ChatColor.WHITE + "this issue by reconnecting.").toItemStack();
            inv.setItem(13, is);
        }

        player.playSound(player.getLocation(), "block.note_block.pling", 1,1);
        player.openInventory(inv);



    }

}
