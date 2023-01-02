package dev.aangepast.farmly.data;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class FarmData {

    private Location minPos;
    private Location maxPos;
    private Location spawn;
    private List<String> players;
    private OfflinePlayer owner;

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public OfflinePlayer getOwner() {
        return owner;
    }

    public void setOwner(OfflinePlayer owner) {
        this.owner = owner;
    }

    public Location getMinPos() {
        return minPos;
    }

    public void setMinPos(Location minPos) {
        this.minPos = minPos;
    }

    public Location getMaxPos() {
        return maxPos;
    }

    public void setMaxPos(Location maxPos) {
        this.maxPos = maxPos;
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }
}
