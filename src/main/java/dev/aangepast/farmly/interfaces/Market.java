package dev.aangepast.farmly.interfaces;

import dev.aangepast.farmly.data.CropData;
import dev.aangepast.farmly.managers.marketManager;
import org.bukkit.entity.Player;

public interface Market {

    // Testing purposes only, might use in real time plugin
    boolean buyCrop(Player player, marketManager manager, CropData cropData, int amount);
    boolean sellCrop(Player player, marketManager manager, CropData cropData, int amount);

}
