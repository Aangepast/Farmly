package dev.aangepast.farmly.inventories;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.utilities.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class animalInventory {

    private int farmId;
    private int dateSpawned;
    private String owner;

    public animalInventory(Entity entity, Player player, String name, Main plugin){

        Inventory inv = Bukkit.createInventory(player, 27, ChatColor.DARK_GRAY + "Animal: " + name);

        NamespacedKey keyOwner = new NamespacedKey(plugin, "owner");
        NamespacedKey keyFarmId = new NamespacedKey(plugin, "farmId");
        NamespacedKey keySpawned = new NamespacedKey(plugin, "spawnDate");

        if(entity.getPersistentDataContainer().has(keyFarmId, PersistentDataType.INTEGER)){
            farmId = entity.getPersistentDataContainer().get(keyFarmId, PersistentDataType.INTEGER);
        } else {
            farmId = -1;
        }

        if(entity.getPersistentDataContainer().has(keySpawned, PersistentDataType.INTEGER)){
            dateSpawned = entity.getPersistentDataContainer().get(keySpawned, PersistentDataType.INTEGER);
        } else {
            dateSpawned = -1;
        }

        if(entity.getPersistentDataContainer().has(keyOwner, PersistentDataType.STRING)){
            owner = entity.getPersistentDataContainer().get(keyOwner, PersistentDataType.STRING);
            owner = String.valueOf(Bukkit.getOfflinePlayer(UUID.fromString(owner)).getName());
        } else {
            owner = "Not found";
        }

        ItemStack grassBlock = new ItemBuilder(Material.GRASS_BLOCK, 1).setName(ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Information").addLoreLine(ChatColor.GRAY + "This animal belongs to farm ID " + ChatColor.WHITE + "#" + farmId).addLoreLine(ChatColor.GRAY + "Animal: " + ChatColor.WHITE + entity.getType()).addLoreLine(ChatColor.GRAY + "Placed days ago: " + ChatColor.WHITE + dateSpawned).addLoreLine(ChatColor.GRAY + "Owner: " + ChatColor.WHITE + owner).toItemStack();
        ItemStack bucket = new ItemBuilder(Material.BUCKET, 1).setName(ChatColor.RED + ChatColor.BOLD.toString() + "Pickup").addLoreLine(ChatColor.GRAY + "Pickup this "+ name +" will despawn").addLoreLine(ChatColor.GRAY + "it, and give you a new "+ name +" egg.").addLoreLine("").addLoreLine(ChatColor.YELLOW + ChatColor.UNDERLINE.toString() + "Click to pickup this " + name).toItemStack();
        ItemStack seeds = new ItemBuilder(Material.WHEAT_SEEDS,1).setName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Feed").addLoreLine(ChatColor.GRAY + "When you feed this " + name + ", it will produce goods.").addLoreLine("").addLoreLine(ChatColor.GRAY + "Status: " + ChatColor.WHITE + "0").addLoreLine("").addLoreLine(ChatColor.YELLOW + ChatColor.UNDERLINE.toString() + "Click to feed this " + name).toItemStack();

        inv.setItem(11, seeds);
        inv.setItem(15, bucket);
        inv.setItem(13, grassBlock);

        player.playSound(player.getLocation(), "block.stone_button.off",1,1);

        player.openInventory(inv);

    }


}