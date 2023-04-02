package dev.aangepast.farmly;

import dev.aangepast.farmly.commands.*;
import dev.aangepast.farmly.data.Building;
import dev.aangepast.farmly.listeners.*;
import dev.aangepast.farmly.managers.buildingManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    // TODO FIX PLAYER SAVE DATA? WERKT MOMENTEEL NIET HELEMAAL OFZO
    // TODO Je was bezig met minPos en maxPos van de farm plot van de user in #newSpot

    // TODO on building build werkt nog niet
    // TODO als je een nieuwe spot aanmakt wordt het nog niet op spelers naam gezet

    public HashMap<String, Entity> currentInteract = new HashMap<>();

    public int currentFarmId;
    public Location loginSpawn;
    public Location nextSpot;
    public int currentDay;

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
        Bukkit.getPluginCommand("newprofile").setExecutor(new newProfile(this));
        Bukkit.getPluginCommand("deleteprofile").setExecutor(new deleteProfile(this));
        Bukkit.getPluginCommand("profile").setExecutor(new profileCommand());
        Bukkit.getPluginCommand("setjoinspawn").setExecutor(new setJoinSpawnCommand(this));
        Bukkit.getPluginCommand("newspot").setExecutor(new newSpot(this));
        Bukkit.getPluginCommand("setnewspot").setExecutor(new setNewSpotCommand(this));
        Bukkit.getPluginCommand("getbuilding").setExecutor(new getBuilding());
        Bukkit.getPluginCommand("date").setExecutor(new getDate(this));
        Bukkit.getPluginCommand("farm").setExecutor(new farmCommand(this));

        // Get farmId
        File file = new File(getDataFolder().getAbsolutePath() + "/server/farmId.yml");
        if(file.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            currentFarmId = config.getInt("farmId");
        } else {
            currentFarmId = 0;
        }

        // Get loginSpawn
        File file1 = new File(getDataFolder().getAbsolutePath() + "/server/login.yml");
        if(file1.exists()){
            FileConfiguration config = YamlConfiguration.loadConfiguration(file1);
            loginSpawn = config.getLocation("location");
        } else {
            Bukkit.getLogger().warning("Login spawn location is not set!!");
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
    }

    public void saveConfig(FileConfiguration config, File file){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
