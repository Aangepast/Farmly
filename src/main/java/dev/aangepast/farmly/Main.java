package dev.aangepast.farmly;

import dev.aangepast.farmly.commands.*;
import dev.aangepast.farmly.data.Building;
import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.data.CropType;
import dev.aangepast.farmly.data.chartData;
import dev.aangepast.farmly.listeners.*;
import dev.aangepast.farmly.managers.buildingManager;
import dev.aangepast.farmly.managers.cropManager;
import dev.aangepast.farmly.managers.marketManager;
import dev.aangepast.farmly.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    // TODO FIX PLAYER SAVE DATA? WERKT MOMENTEEL NIET HELEMAAL OFZO
    // TODO Je was bezig met minPos en maxPos van de farm plot van de user in #newSpot

    // TODO on building build werkt nog niet
    // TODO als je een nieuwe spot aanmaakt wordt het nog niet op spelers naam gezet

    // TODO AUTO-SAVE TOEVOEGEN VOOR SPELERS TEGEN CRASH

    public HashMap<String, Entity> currentInteract = new HashMap<>();

    public int currentFarmId;
    public Location loginSpawn;
    public Location nextSpot;
    public Location Spawn;
    public int currentDay;
    public marketManager market;
    public List<chartData> charts;

    public void addFarmID(){
        currentFarmId = currentFarmId + 1;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Load all events and commands
        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new onQuit(this), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryClick(this), this);
        Bukkit.getPluginManager().registerEvents(new onAnimalPlace(this), this);
        Bukkit.getPluginManager().registerEvents(new animalFixer(this), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryClose(this), this);
        Bukkit.getPluginManager().registerEvents(new onBuildingPlace(this), this);
        Bukkit.getPluginManager().registerEvents(new onBuildingBuild(), this);
        Bukkit.getPluginManager().registerEvents(new onItemPickup(), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryClickOutsideHandler(), this);
        Bukkit.getPluginCommand("newprofile").setExecutor(new newProfile(this));
        Bukkit.getPluginCommand("deleteprofile").setExecutor(new deleteProfile(this));
        Bukkit.getPluginCommand("profile").setExecutor(new profileCommand());
        Bukkit.getPluginCommand("setjoinspawn").setExecutor(new setJoinSpawnCommand(this));
        Bukkit.getPluginCommand("newspot").setExecutor(new newSpot(this));
        Bukkit.getPluginCommand("setnewspot").setExecutor(new setNewSpotCommand(this));
        Bukkit.getPluginCommand("getbuilding").setExecutor(new getBuilding());
        Bukkit.getPluginCommand("date").setExecutor(new getDate(this));
        Bukkit.getPluginCommand("farm").setExecutor(new farmCommand(this));
        Bukkit.getPluginCommand("market").setExecutor(new marketCommand(this));
        Bukkit.getPluginCommand("setspawn").setExecutor(new setSpawnCommand(this));
        Bukkit.getPluginCommand("spawn").setExecutor(new spawnCommand(this));
        Bukkit.getPluginCommand("balance").setExecutor(new balanceCommand());
        Bukkit.getPluginCommand("setbalance").setExecutor(new setBalanceCommand());

        // Get farmId
        File file = new File(getDataFolder().getAbsolutePath() + "/server/farmId.yml");
        if(file.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            currentFarmId = config.getInt("farmId");
        } else {
            currentFarmId = 0;
        }

        // Get loginSpawn & spawn
        File file1 = new File(getDataFolder().getAbsolutePath() + "/server/login.yml");
        if(file1.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file1);
            loginSpawn = config.getLocation("location");
            Spawn = config.getLocation("spawn");
        } else {
            Bukkit.getLogger().warning("Login spawn & spawn location is not set!!");
        }

        // Get next spot
        File file2 = new File(getDataFolder().getAbsolutePath() + "/server/spot.yml");
        if(file2.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file2);
            nextSpot = config.getLocation("next");
        } else {
            Bukkit.getLogger().warning("Next spot file is empty. Warning!");
        }

        // Date preparing
        File file3 = new File(getDataFolder().getAbsolutePath() + "/server/date.yml");
        if(file3.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file3);
            currentDay = config.getInt("day");
        } else {
            currentDay = 1;
        }

        // Get buildings from file
        File file4 = new File(getDataFolder().getAbsolutePath() + "/server/buildings.yml");
        if(file4.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file4);
            for (String key : config.getKeys(false)){
                Building building = new Building();
                building.setName(key);
                building.setId(config.getInt(key+".id"));
                building.setxSize(config.getInt(key+".xsize"));
                building.setySize(config.getInt(key+".ysize"));
                building.setzSize(config.getInt(key+".zsize"));
                building.setLevel(config.getInt(key+".level"));
                buildingManager.addBuilding(building);
                getLogger().info("Added a new building called " + building.getName());
            }
        }

        // Set market instance & initialise hem
        marketManager market = new marketManager();
        File file5 = new File(getDataFolder().getAbsolutePath() + "/server/market.yml");
        if(file5.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file5);
            for(String key : config.getKeys(false)){
                CropData crop = new CropData();
                crop.setRawName(key);
                crop.setDisplayName(Utils.translateHexColorCodes("&#", "", ChatColor.translateAlternateColorCodes('&', config.getString(key+".displayname"))));
                crop.setMaterial(Material.getMaterial(config.getString(key+".material")));
                switch(config.getString(key+".cropType")){
                    case "CARROT":
                        crop.setCropType(CropType.CARROT);
                        break;
                    case "POTATO":
                        crop.setCropType(CropType.POTATO);
                        break;
                    case "BERRIES":
                        crop.setCropType(CropType.BERRIES);
                        break;
                    case "WHEAT":
                        crop.setCropType(CropType.WHEAT);
                        break;
                    case "MELON":
                        crop.setCropType(CropType.MELON);
                        break;
                    case "PUMPKIN":
                        crop.setCropType(CropType.PUMPKIN);
                        break;
                    case "BEET":
                        crop.setCropType(CropType.BEETS);
                        break;
                    default:
                        crop.setCropType(CropType.NONE);
                        break;
                }
                crop.setCrowTime(config.getInt(key+".growTime"));
                crop.setDefaultBuyPrice(config.getDouble(key+".defaultBuyPrice"));
                crop.setDefaultSellPrice(config.getDouble(key+".defaultSellPrice"));
                crop.setXp(config.getDouble(key+".XP"));
                crop.setMaxDrops(config.getInt(key+".maxDrops"));
                crop.setMinDrops(config.getInt(key+".minDrops"));
                cropManager.addCrop(crop);
                market.addCropBuyPrice(crop, config.getDouble(key+".currentBuyPrice"));
                market.addCropSellPrice(crop, config.getDouble(key+".currentSellPrice"));
            }
            this.market = market;
            getLogger().info("Loaded market prices & cropData");
        } else {
            getLogger().warning("Market prices & cropData is not setup. Set it up in /server/market.yml");
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Save current farmId
        File file = new File(getDataFolder().getAbsolutePath() + "/server/farmId.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("farmId", currentFarmId);
        saveConfig(config, file);

        // Save current next spot
        File file2 = new File(getDataFolder().getAbsolutePath() + "/server/spot.yml");
        FileConfiguration config2 = YamlConfiguration.loadConfiguration(file2);
        config2.set("next", nextSpot);
        saveConfig(config2, file2);

        // Save date
        File file3 = new File(getDataFolder().getAbsolutePath() + "/server/date.yml");
        FileConfiguration config3 = YamlConfiguration.loadConfiguration(file3);
        config3.set("day", currentDay);
        saveConfig(config3, file3);

        // Save market prices
        File file4 = new File(this.getDataFolder().getAbsolutePath() + "/server/market.yml");
        FileConfiguration config4 = YamlConfiguration.loadConfiguration(file4);
        for(CropData crop : cropManager.getCrops()){
            config4.set(crop.getRawName()+".currentBuyPrice", market.getCropBuyPrice(crop));
            config4.set(crop.getRawName()+".currentSellPrice", market.getCropSellPrice(crop));
            getLogger().info("Saved crop data for " + crop.getRawName());
        }
        saveConfig(config4, file4);
    }

    public void saveConfig(FileConfiguration config, File file){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
