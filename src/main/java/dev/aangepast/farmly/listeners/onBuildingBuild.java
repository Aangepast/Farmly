package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.data.Building;
import dev.aangepast.farmly.managers.buildingManager;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class onBuildingBuild implements Listener {

    @EventHandler
    public void leftClick(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)){
            Player player = e.getPlayer();
            if(buildingManager.containsPlayer(player)){
                e.setCancelled(true);
                ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
                String size = itemMeta.getLore().get(6);
                String newSize = size.replaceAll("x", " ").replaceAll(ChatColor.GREEN + "", "").replaceAll(ChatColor.DARK_GREEN + "", "");
                String lore = itemMeta.getLore().get(8);
                String newLore = lore.replaceAll("#", "").replaceAll("§8", "");
                int ID = Integer.parseInt(newLore);

                Building building = buildingManager.getBuildingByID(ID);
                if(building == null){player.sendMessage(ChatColor.RED + "The building that you're trying to place does not exist.");return;}
                buildBuilding(building,player);
            }
        }
    }
    public void buildBuilding(Building building, Player player){

        String direction = PlayerUtility.getCardinalDirection(player);
        if (direction == null) {
            return;
        }

        Location location = player.getLocation();
        Location pos2 = location;
        location.setX(player.getLocation().getBlockX());
        location.setY(player.getLocation().getBlockY());
        location.setZ(player.getLocation().getBlockZ());

        switch (direction) {
            case "N":
                pos2.add(building.getzSize(),building.getySize(),-building.getxSize());
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

        if(!checkForBlocks(location, pos2)){

        }
        player.sendMessage(ChatColor.GREEN + "Building placed!");
    }

    public void buildFail(Player player, Location location){
        player.sendMessage(ChatColor.RED + "You cannot place a building here!");
        location.getBlock().setType(Material.GOLD_BLOCK);
        player.playSound(player.getLocation(), "entity.villager.no",1,1);
    }

    public boolean checkForBlocks(Location pos1, Location pos2){

        int yPos1 = pos1.getBlockY();
        int yPos2 = pos2.getBlockY();
        int yTimes = yPos2 - yPos1;

        int zPos1 = pos1.getBlockZ();
        int zPos2 = pos1.getBlockZ();
        int zTimes = zPos2 - zPos1;

        int xPos1 = pos1.getBlockX();
        int xPos2 = pos1.getBlockX();
        int xTimes = xPos2 - xPos1;

        for (int i = 0; i < yTimes; i++){
            pos1.add(0,1,0);
            if(noMaterial(pos1)){
                pos1.getBlock().setType(Material.BEDROCK);
                return false;
            }
            pos1.getBlock().setType(Material.GOLD_BLOCK);
            for(int z = 0; z < zTimes ; z++) {
                pos1.add(0, 0, 1);
                if (!noMaterial(pos1)){
                    pos1.getBlock().setType(Material.BEDROCK);
                    return false;
                }
                pos1.getBlock().setType(Material.GOLD_BLOCK);
                for(int x = 0; x < xTimes ; x++){
                    pos1.add(1,0,0);
                    if(!noMaterial(pos1)){
                        pos1.getBlock().setType(Material.BEDROCK);
                        return false;
                    }
                    pos1.getBlock().setType(Material.GOLD_BLOCK);
                }
            }
        }
        return true;
    }

    public boolean noMaterial(Location location) {
        if (!location.getBlock().getType().equals(Material.AIR)) {
            location.getBlock().setType(Material.BEDROCK);
            return false;
        }
        return true;
    }

}
