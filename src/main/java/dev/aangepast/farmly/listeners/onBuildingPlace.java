package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;


public class onBuildingPlace extends Thread implements Listener {

    private Main plugin;

    public onBuildingPlace(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getHand().equals(EquipmentSlot.HAND)) {
                Player player = e.getPlayer();

                if (player.getInventory().getItemInMainHand().getItemMeta() == null) {
                    return;
                }

                if (player.getInventory().getItemInMainHand().getType().equals(Material.PAPER) && player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.GRAY + "Right-Click to enter build mode.")) {
                    ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
                    String size = itemMeta.getLore().get(6);
                    String newSize = size.replaceAll("x", " ").replaceAll(ChatColor.GREEN + "", "").replaceAll(ChatColor.DARK_GREEN + "", "");
                    String[] fixedSize = newSize.split(" ");
                    String lore = itemMeta.getLore().get(8);
                    String newLore = lore.replaceAll("#", "").replaceAll("§8", "");
                    int ID = Integer.parseInt(newLore);
                    player.sendMessage("Facing: " + PlayerUtility.getCardinalDirection(e.getPlayer()));

                    Location location = e.getPlayer().getLocation();
                    String direction = PlayerUtility.getCardinalDirection(e.getPlayer());

                    if(direction == null){return;}


                    while (player.getInventory().getItemInMainHand().getType() == Material.PAPER) {

                        switch(direction){
                            case "N":
                                for(double i=0.1;i<10;i+=0.5){
                                    location.getWorld().spawnParticle(Particle.CRIT, location, 5, 0,0,0);
                                    location.add(0,0,-i);
                                }
                                break;
                            case "S":
                                for(double i=0.1;i<10;i+=0.5){
                                    location.getWorld().spawnParticle(Particle.CRIT, location, 5, 0,0,0);
                                    location.add(0,0,i);
                                }
                                break;
                            case "W":
                                for(double i=0.1;i<10;i+=0.5){
                                    location.getWorld().spawnParticle(Particle.CRIT, location, 5, 0,0,0);
                                    location.add(-i,0,0);
                                }
                                break;
                            case "E":
                                for(double i=0.1;i<10;i+=0.5){
                                    location.getWorld().spawnParticle(Particle.CRIT, location, 5, 0,0,0);
                                    location.add(i,0,0);
                                }
                                break;
                        }
                        try {
                            sleep(250);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }

                    }
                }
            }
        }
    }
}
