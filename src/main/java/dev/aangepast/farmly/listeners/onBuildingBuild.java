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
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

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
        location.setX(player.getLocation().getBlockX());
        location.setY(player.getLocation().getBlockY());
        location.setZ(player.getLocation().getBlockZ());
        final Location pos1;
        Location location2;

        switch (direction) {
            case "N":
                location.add(0,0,-1);
                location.getBlock().setType(Material.DIAMOND_BLOCK);
                location2 = location;
                pos1 = location;
                location2.add(building.getzSize(),building.getySize(),-building.getxSize());
                location2.getBlock().setType(Material.GOLD_BLOCK);
                break;
            case "S":
                location.add(-1,0,0);
                location.getBlock().setType(Material.DIAMOND_BLOCK);
                location2 = location;
                pos1 = location;
                location2.add(-building.getzSize(),building.getySize(),building.getxSize());
                location2.getBlock().setType(Material.GOLD_BLOCK);
                break;
            case "W":
                location.add(-1,0,-1);
                location.getBlock().setType(Material.DIAMOND_BLOCK);
                location2 = location;
                pos1 = location;
                location2.add(-building.getxSize(),building.getySize(),-building.getzSize());
                location2.getBlock().setType(Material.GOLD_BLOCK);
                break;
            case "E":
                location.getBlock().setType(Material.DIAMOND_BLOCK);
                location2 = location;
                pos1 = location;
                location2.add(building.getxSize(),building.getySize(),building.getzSize());
                location2.getBlock().setType(Material.GOLD_BLOCK);
                break;
            default:
                player.sendMessage(ChatColor.RED + "Something went wrong while trying to build your building.");
                player.sendMessage(ChatColor.GRAY + "Report this: CardinalDirection: " + direction + " - ERROR_NO_DIRECTION - " + building.getName());
                break;
        }

        player.sendMessage(ChatColor.GREEN + "Building placed!");
    }

    public void buildFail(Player player, Location location){
        player.sendMessage(ChatColor.RED + "You cannot place a building here!");
        location.getBlock().setType(Material.GOLD_BLOCK);
        player.playSound(player.getLocation(), "entity.villager.no",1,1);
    }


}
