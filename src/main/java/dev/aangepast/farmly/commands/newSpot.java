package dev.aangepast.farmly.commands;

import com.fastasyncworldedit.core.FaweAPI;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.math.transform.Transform;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.PlayerData;
import dev.aangepast.farmly.utilities.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class newSpot implements CommandExecutor {

    private Main plugin;

    public newSpot(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        player.sendMessage(ChatColor.GREEN + "Generating new farm, please be patient...");

        Location spot = nextGridLocation(plugin.nextSpot);

        player.sendMessage(ChatColor.DARK_GRAY + "Found available spot!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999999, 5));

        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/server/Farmv1.schem");

        player.sendMessage(ChatColor.DARK_GRAY + "Preparing farm...");

        PlayerData data = PlayerUtility.getPlayerData(player);

        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                player.sendMessage(ChatColor.DARK_GRAY + "Generating farm, please wait...");
                if (paste(file, spot)) {
                    Location minPos = spot.add(32, 0, 31);
                    Location maxPos = spot.add(-101,20,-101);
                    data.setMaxPos(maxPos);
                    data.setMinPos(minPos);
                    player.teleport(spot.add(0.5,1,0.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.sendMessage(ChatColor.GRAY + "Operation completed, have fun!");
                    player.sendMessage(ChatColor.GREEN + "Welcome to " + ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "Farmly" + ChatColor.GREEN + "!");
                } else {
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.sendMessage(ChatColor.DARK_GRAY + "Could not generate farm");
                }
            }
        }, 30);
        return true;
    }

    private Location nextGridLocation(final Location lastFarm) {
        int x = lastFarm.getBlockX();
        int z = lastFarm.getBlockZ();
        int d = 180;
        if (x < z) {
            if (-1 * x < z) {
                lastFarm.setX(lastFarm.getX() + d);
                return lastFarm;
            }
            lastFarm.setZ(lastFarm.getZ() + d);
            return lastFarm;
        }
        if (x > z) {
            if (-1 * x >= z) {
                lastFarm.setX(lastFarm.getX() - d);
                return lastFarm;
            }
            lastFarm.setZ(lastFarm.getZ() - d);
            return lastFarm;
        }
        if (x <= 0) {
            lastFarm.setZ(lastFarm.getZ() + d);
            return lastFarm;
        }
        lastFarm.setZ(lastFarm.getZ() - d);
        return lastFarm;
    }

    public boolean paste(File file, Location location) {
        try {
            ClipboardFormat format = ClipboardFormats.findByFile(file);
            ClipboardReader reader = format.getReader(Files.newInputStream(file.toPath()));
            Clipboard clipboard = reader.read();
            try (EditSession editSession = com.sk89q.worldedit.WorldEdit.getInstance().getEditSessionFactory().getEditSession(FaweAPI.getWorld(location.getWorld().getName()), -1)) {
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                        .copyEntities(true)
                        .ignoreAirBlocks(true)
                        .build();
                Operations.complete(operation);
            }
        } catch (Exception e) {
            plugin.getLogger().warning("Failed to paste farm with FAWE");
            return false;
        }
        return true;
    }
}
