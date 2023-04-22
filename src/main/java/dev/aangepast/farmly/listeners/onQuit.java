package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.FarmUtilities;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;

public class onQuit implements Listener {

    private Main plugin;

    public onQuit(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        e.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
        e.getPlayer().resetPlayerWeather();
        PlayerData data = PlayerUtility.getPlayerData(e.getPlayer());
        File file = new File(PlayerUtility.getFolderPath(e.getPlayer(), plugin) + "/data.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        config.set("stats.farmId", data.getFarmId());
        config.set("stats.cash", data.getCash());
        config.set("stats.group", data.getGroup());
        config.set("stats.groupId", data.getGroupId());
        config.set("stats.level", data.getLevel());
        config.set("stats.hasaccesstrademarket", data.isHasAccessTradeMarket());
        config.set("stats.levelcrafting", data.getLevelCrafting());
        config.set("stats.levelforgaging", data.getLevelForgaging());
        config.set("stats.levellifestock", data.getLevelLifestock());
        config.set("stats.levelfarming", data.getLevelFarming());
        config.set("stats.skillpoints", data.getSkillPoints());
        config.set("stats.skillxp", data.getSkillXp());
        config.set("stats.xp", data.getXp());

        if(PlayerUtility.getFarmData(e.getPlayer()).getOwner() != null){
            FarmData farmData = PlayerUtility.getFarmData(e.getPlayer());
            File farm = new File(PlayerUtility.getFarmFolderPath(e.getPlayer(), plugin) + "/farm.yml");
            FileConfiguration farmConfig = YamlConfiguration.loadConfiguration(farm);

            farmConfig.set("farm.minPos", farmData.getMinPos());
            farmConfig.set("farm.maxPos", farmData.getMaxPos());
            farmConfig.set("farm.owner", farmData.getOwner());
            farmConfig.set("farm.spawn", farmData.getSpawn());
            farmConfig.set("farm.players", farmData.getPlayers());

            try {
                farmConfig.save(farm);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        try {
            config.save(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        plugin.currentInteract.remove(e.getPlayer().getUniqueId().toString());

        PlayerUtility.setPlayerData(e.getPlayer(), null);
        PlayerUtility.setFarmData(e.getPlayer(), null);

    }

}
