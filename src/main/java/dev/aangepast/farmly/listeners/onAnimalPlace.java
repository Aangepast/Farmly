package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class onAnimalPlace implements Listener {

    private Main plugin;

    public onAnimalPlace(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onCreatureSpawn(PlayerInteractEvent e) {

        if (e.getMaterial() == Material.CHICKEN_SPAWN_EGG && e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (e.getClickedBlock().getType() == Material.HAY_BLOCK) {

                if (e.getClickedBlock().getRelative(BlockFace.DOWN).getType() == Material.YELLOW_CONCRETE) {

                    e.setCancelled(true);

                    Chicken chicken = (Chicken) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5,1,0.5), EntityType.CHICKEN);

                    NamespacedKey keyOwner = new NamespacedKey(plugin, "owner");
                    NamespacedKey keyFarmId = new NamespacedKey(plugin, "farmId");
                    NamespacedKey keySpawned = new NamespacedKey(plugin, "spawnDate");

                    chicken.getPersistentDataContainer().set(keyOwner, PersistentDataType.STRING, e.getPlayer().getUniqueId().toString());
                    chicken.getPersistentDataContainer().set(keyFarmId, PersistentDataType.INTEGER, PlayerUtility.getPlayerData(e.getPlayer()).getFarmId());
                    chicken.getPersistentDataContainer().set(keySpawned, PersistentDataType.INTEGER, plugin.currentDay);

                    ItemStack item = new ItemBuilder(Material.CHICKEN_SPAWN_EGG, 1).setName(ChatColor.WHITE + "Chicken").addLoreLine(ChatColor.GRAY + "Right-click on an unused hay bale").addLoreLine(ChatColor.GRAY + "to spawn this Chicken.").toItemStack();

                    e.getPlayer().getInventory().removeItem(item);
                    Block block = e.getClickedBlock().getRelative(BlockFace.DOWN);
                    block.setType(Material.YELLOW_WOOL);

                } else if (e.getClickedBlock().getRelative(BlockFace.DOWN).getType() == Material.YELLOW_WOOL) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "There is already an animal living here, try another hay bale.");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), "entity.villager.no", 1, 1);

                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "Hmm, it seems like this animal doesn't fit here...");
                    e.getPlayer().playSound(e.getPlayer().getLocation(), "entity.villager.no", 1, 1);
                }

            } else {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "Animals can only be spawned on hay bales.");
                e.getPlayer().playSound(e.getPlayer().getLocation(), "entity.villager.no", 1, 1);
            }

        } else if (e.getMaterial() == Material.CHICKEN_SPAWN_EGG){
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "Try to place it on an hay bale.");
        }

    }

}
