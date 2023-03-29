package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.Building;
import dev.aangepast.farmly.managers.buildingManager;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


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
                    if (buildingManager.containsPlayer(player)) {
                        buildingManager.removePlayer(player);
                        player.sendMessage(ChatColor.RED + "Build mode has been disabled.");
                        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
                        player.resetPlayerWeather();
                        return;
                    }
                    buildingManager.addPlayer(player);
                    player.sendMessage(ChatColor.GREEN + "Build mode has been enabled.");
                    player.setPlayerWeather(WeatherType.CLEAR);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, 5));
                    ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
                    String size = itemMeta.getLore().get(6);
                    String newSize = size.replaceAll("x", " ").replaceAll(ChatColor.GREEN + "", "").replaceAll(ChatColor.DARK_GREEN + "", "");
                    String lore = itemMeta.getLore().get(8);
                    String newLore = lore.replaceAll("#", "").replaceAll("§8", "");
                    int ID = Integer.parseInt(newLore);

                    String[] sizes = newSize.split(" ");
                    Building building = new Building();
                    building.setxSize(Integer.parseInt(sizes[1]));
                    building.setySize(Integer.parseInt(sizes[2]));
                    building.setzSize(Integer.parseInt(sizes[3]));
                    building.setId(ID);


                    displayParticles(player, building);
                }
            }
        }
    }

    public void displayParticles(Player player, Building building) {
        String direction = PlayerUtility.getCardinalDirection(player);
        if (direction == null) {
            return;
        }

        Location location = player.getLocation();
        location.setX(player.getLocation().getBlockX());
        location.setY(player.getLocation().getBlockY());
        location.setZ(player.getLocation().getBlockZ());

        if (!player.getInventory().getItemInMainHand().getType().equals(Material.PAPER)) {
            buildingManager.removePlayer(player);
            player.sendMessage(ChatColor.RED + "Build mode has been disabled.");
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.resetPlayerWeather();
            return;
        }

        location.add(0,0.1,0);

        if(!buildingManager.containsPlayer(player)){return;}

        switch (direction) {
            case "N":
                for (int i = 0; i < building.getxSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, -1);
                }
                for (int i = 0; i < building.getzSize(); i++){
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(1, 0, 0);
                }
                for (int i = building.getxSize(); i > 0; i--) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, 1);
                }
                for (int i = 0; i < building.getzSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(-1, 0, 0);
                }
                break;
            case "S":
                for (int i = 0; i < building.getxSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, 1);
                }
                for (int i = 0; i < building.getzSize(); i++){
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(-1, 0, 0);
                }
                for (int i = building.getxSize(); i > 0; i--) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, -1);
                }
                for (int i = 0; i < building.getzSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(1, 0, 0);
                }
                break;
            case "W":
                for (int i = 0; i < building.getxSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(-1, 0, 0);
                }
                for (int i = 0; i < building.getzSize(); i++){
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, -1);
                }
                for (int i = building.getxSize(); i > 0; i--) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(1, 0, 0);
                }
                for (int i = 0; i < building.getzSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, 1);
                }
                break;
            case "E":
                for (int i = 0; i < building.getxSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(1, 0, 0);
                }
                for (int i = 0; i < building.getzSize(); i++){
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, 1);
                }
                for (int i = building.getxSize(); i > 0; i--) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(-1, 0, 0);
                }
                for (int i = 0; i < building.getzSize(); i++) {
                    location.getWorld().spawnParticle(Particle.CRIT, location, 1, 0, 0, 0,0);
                    location.add(0, 0, -1);
                }
                break;
        }
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                displayParticles(player, building);
            }
        }, 5);

    }
}
