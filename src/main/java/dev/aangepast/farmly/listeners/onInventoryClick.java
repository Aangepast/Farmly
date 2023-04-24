package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.inventories.farmSettingsInventory;
import dev.aangepast.farmly.managers.cropManager;
import dev.aangepast.farmly.managers.marketManager;
import dev.aangepast.farmly.managers.teleportManager;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class onInventoryClick implements Listener {

    private Material eggItem;
    private String name;

    private Main plugin;

    public onInventoryClick(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        if(e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Select a profile to continue")){
            e.setCancelled(true);

            if(e.getRawSlot() == 13){
                e.getWhoClicked().closeInventory();
                FarmData farm = PlayerUtility.getFarmData((Player) e.getWhoClicked());
                e.getWhoClicked().teleport(farm.getSpawn(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                e.getWhoClicked().sendMessage(ChatColor.GRAY + "Traveling...");
                e.getWhoClicked().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 3, 5));
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), "block.stone_pressure_plate.click_on", 1, 1);
            }


        } else if (e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Animal: ")){
            e.setCancelled(true);

            if(e.getRawSlot() == 15) {
                // check of plugin.currentInteract de player bevat, zoja krijg daarvan de entity en kill het daarmee
                if(plugin.currentInteract.containsKey(e.getWhoClicked().getUniqueId().toString())){
                    Entity entity = plugin.currentInteract.get(e.getWhoClicked().getUniqueId().toString());
                    entity.remove();

                    switch(entity.getType()){
                        case CHICKEN:
                            eggItem = Material.CHICKEN_SPAWN_EGG;
                            name = "Chicken";
                        case COW:
                            eggItem = Material.COW_SPAWN_EGG;
                            name = "Cow";
                        case PIG:
                            eggItem = Material.PIG_SPAWN_EGG;
                            name = "Pig";
                    }

                    ItemStack item = new ItemBuilder(eggItem, 1).setName(ChatColor.WHITE + name).addLoreLine(ChatColor.GRAY + "Right-click on an unused hay bale").addLoreLine(ChatColor.GRAY + "to spawn this " + name + ".").toItemStack();

                    Player player = (Player) e.getWhoClicked();
                    player.playSound(player.getLocation(), "player.item.pickup",1,1);
                    e.getWhoClicked().sendMessage(ChatColor.GREEN + "You have picked up the animal.");
                    e.getWhoClicked().getInventory().addItem(item);
                }
                e.getWhoClicked().closeInventory();
            }
        } else if (e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Farm menu")) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            player.playSound(player.getLocation(), "block.stone_pressure_plate.click_off",1,1);
            switch(e.getRawSlot()){
                case 4:
                    if(teleportManager.containsPlayer(player)){player.sendMessage(ChatColor.RED + "You already have a teleport commencing!");player.closeInventory();return;}
                    FarmData farm = PlayerUtility.getFarmData(player);
                    Location spawn = new Location(farm.getSpawn().getWorld(), farm.getSpawn().getBlockX(), farm.getSpawn().getBlockY(), farm.getSpawn().getBlockZ());
                    Chunk chunk = spawn.getChunk();
                    chunk.load();
                    player.sendMessage(ChatColor.GRAY + "Preparing your farm chunks, teleporting you in 3 seconds...");
                    player.closeInventory();
                    teleportManager.addPlayer(player);
                    Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.teleport(spawn, PlayerTeleportEvent.TeleportCause.COMMAND);
                            player.sendMessage(ChatColor.GRAY + "You have been teleported to your farm!");
                            teleportManager.removePlayer(player);
                            player.playSound(player.getLocation(), "entity.experience_orb.pickup",1,1);
                        }
                    },20*3);
                    break;
                case 6:
                    farmSettingsInventory.openInventory(player);
                    break;
            }
        } else if (e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Farm settings")){
            Player player = (Player) e.getWhoClicked();
            //switch
        } else if (e.getView().getTitle().contains(ChatColor.DARK_GRAY + "Trading: ")){
            Player player = (Player) e.getWhoClicked();
            e.setCancelled(true);
            marketManager manager = plugin.market;
            switch(e.getRawSlot()){
                case 11:
                    String[] invName = e.getView().getTitle().split("Trading: ");
                    if(e.getClick() == ClickType.RIGHT){
                        manager.buyCrop(player, plugin.market, cropManager.getCrop(invName[1]), 16, plugin);
                    } else if (e.getClick()==ClickType.LEFT) {
                        manager.buyCrop(player, plugin.market, cropManager.getCrop(invName[1]), 1, plugin);
                    }
                    break;
            }
        }

    }



}
