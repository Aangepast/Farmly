package dev.aangepast.farmly.inventories;

import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class farmCommandInventory {

    public static void openInventory(Player player){

        Inventory inv = Bukkit.createInventory(player, InventoryType.DISPENSER, ChatColor.DARK_GRAY + "Farm menu");

        ItemStack comingSoon = new ItemBuilder(Material.IRON_BARS, 1).setName(ChatColor.RED + ChatColor.BOLD.toString() + "???").addLoreLine(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Hmm, who knows what this is going to be.").toItemStack();
        ItemStack darkGreenPane = new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE, 1).setName(ChatColor.RESET + " ").toItemStack();
        ItemStack limeGreenPane = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE,1).setName(ChatColor.RESET + " ").toItemStack();
        ItemStack teleportButton = new ItemBuilder(Material.OAK_DOOR, 1).setName(Utils.translateHexColorCodes("&#", "", "&#00cc44Teleport")).addLoreLine(ChatColor.BOLD + Utils.translateHexColorCodes("&#", "", "&#00cc44| ") + ChatColor.RESET + Utils.translateHexColorCodes("&#", "", "&#00ff55Click to teleport to your farm home.")).toItemStack();
        ItemStack settingsButton = new ItemBuilder(Material.COMPARATOR,1).setName(Utils.translateHexColorCodes("&#", "", "&#ff1a1aSettings")).addLoreLine(ChatColor.BOLD + Utils.translateHexColorCodes("&#", "", "&#ff1a1a| ") + ChatColor.RESET + Utils.translateHexColorCodes("&#", "", "&#cc0000Click to open the settings menu.")).toItemStack();
        ItemStack inviteFriends = new ItemBuilder(Material.LEVER, 1).setName(Utils.translateHexColorCodes("&#", "", "&#00b8e6Invite player")).addLoreLine(ChatColor.BOLD + Utils.translateHexColorCodes("&#", "", "&#00b8e6| ") + ChatColor.RESET + Utils.translateHexColorCodes("&#", "", "&#008fb3Click to invite a friend to your farm.")).toItemStack();

        inv.setItem(0,comingSoon);
        inv.setItem(1,darkGreenPane);
        inv.setItem(7,darkGreenPane);
        inv.setItem(3,limeGreenPane);
        inv.setItem(5,limeGreenPane);
        inv.setItem(4,teleportButton);
        inv.setItem(6,settingsButton);
        inv.setItem(8,inviteFriends);

        player.playSound(player.getLocation(), "block.chest.open", 1,2);

        player.openInventory(inv);

    }


}
