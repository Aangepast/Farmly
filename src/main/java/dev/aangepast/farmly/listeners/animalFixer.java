package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.inventories.animalInventory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class animalFixer implements Listener {

    private Main plugin;

    public animalFixer(Main plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void animalFix(PlayerInteractEntityEvent e){
        if(e.getHand().equals(EquipmentSlot.HAND)){
            if(e.getRightClicked().getType() == EntityType.CHICKEN){
                if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.CHICKEN_SPAWN_EGG){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "You cannot interact with animals while holding a spawn egg.");
                } else if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
                    if(plugin.currentInteract.containsValue(e.getRightClicked())){
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "You cannot interact with this animal while someone else is interacting with it.");
                        e.getPlayer().sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + "If you think this is bugged, please relog to hopefully fix this issue. If not, please contact a staff member.");
                    } else {
                        plugin.currentInteract.put(e.getPlayer().getUniqueId().toString(), e.getRightClicked());
                        new animalInventory(e.getRightClicked(), e.getPlayer(), "Chicken", plugin);
                    }
                } else {
                    e.getPlayer().sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Right-click this chicken with nothing in your hand to interact.");
                }
            }
        }
    }



}
