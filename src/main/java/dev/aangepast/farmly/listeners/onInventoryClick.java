package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.ItemBuilder;
import dev.aangepast.farmly.utilities.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
                PlayerData data = PlayerUtility.getPlayerData((Player) e.getWhoClicked());
                e.getWhoClicked().teleport(data.getSpawn(), PlayerTeleportEvent.TeleportCause.PLUGIN);
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
        }

    }



}
