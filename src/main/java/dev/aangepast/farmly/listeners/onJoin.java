package dev.aangepast.farmly.listeners;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.FarmData;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.inventories.profileInventory;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.io.File;
import java.util.List;

public class onJoin implements Listener {

    private Main plugin;

    public onJoin(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){
        e.getPlayer().teleport(plugin.loginSpawn, PlayerTeleportEvent.TeleportCause.PLUGIN);
        PlayerData data = new PlayerData();
        FarmData farm = new FarmData();
        File f = new File(PlayerUtility.getFolderPath(e.getPlayer(), plugin) + "/data.yml");
        File farms = new File(PlayerUtility.getFarmFolderPath(e.getPlayer(), plugin) + "/farm.yml");

        if(f.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(f);
            data.setCash(config.getDouble("stats.cash"));
            data.setFarmId(config.getInt("stats.farmId"));
            data.setGroup(config.getString("stats.group"));
            data.setGroupId(config.getInt("stats.groupId"));
            data.setLevel(config.getInt("stats.level"));
            data.setHasAccessTradeMarket(config.getBoolean("stats.hasaccesstrademarket"));
            data.setLevelCrafting(config.getInt("stats.levelcrafting"));
            data.setLevelFarming(config.getInt("stats.levelfarming"));
            data.setLevelForgaging(config.getInt("stats.levelforgaging"));
            data.setLevelLifestock(config.getInt("stats.levellifestock"));
            data.setSkillPoints(config.getInt("stats.skillpoints"));
            data.setSkillXp(config.getDouble("stats.skillxp"));
            data.setXp(config.getDouble("stats.xp"));
            FileConfiguration configFarm = YamlConfiguration.loadConfiguration(farms);

            if(configFarm.getOfflinePlayer("farm.owner") != null) {
                // Farm data
                farm.setMinPos(configFarm.getLocation("farm.minPos"));
                farm.setMaxPos(configFarm.getLocation("farm.maxPos"));
                farm.setPlayers((List<String>) configFarm.getList("farm.players"));
                farm.setOwner(configFarm.getOfflinePlayer("farm.owner"));
                farm.setSpawn(configFarm.getLocation("farm.spawn"));
                PlayerUtility.setFarmData(e.getPlayer(), farm);
                e.getPlayer().sendMessage(ChatColor.GRAY + "Loaded farm data.");
            } else {
                e.getPlayer().sendMessage(ChatColor.GREEN + "Create a new farm by using command /start");
            }

            e.getPlayer().sendMessage(ChatColor.GRAY + "Player data loaded.");
        } else {
            // create new player profile
            e.getPlayer().sendMessage(ChatColor.GREEN + "Creating new profile...");
            data.setXp(0.0);
            data.setSkillXp(0.0);
            data.setSkillPoints(0);
            data.setLevelLifestock(1);
            data.setLevelFarming(1);
            data.setLevelForgaging(1);
            data.setLevelCrafting(1);
            data.setFarmId(plugin.currentFarmId++);
            data.setHasAccessTradeMarket(false);
            data.setLevel(0);
            data.setCash(100.0);
            plugin.addFarmID();
        }

        PlayerUtility.setPlayerData(e.getPlayer(), data);

        new profileInventory(e.getPlayer());

    }
}
