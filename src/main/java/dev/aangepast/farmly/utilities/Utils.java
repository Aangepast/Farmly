package dev.aangepast.farmly.utilities;

import dev.aangepast.farmly.Main;
import dev.aangepast.farmly.data.CropData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;

public class Utils {

    public static String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    public static double calculateMarketPrice(int amount, CropData cropData, Main plugin, boolean buy){
        double actualPrice = 0;
        if(buy){
            double tempPrice = plugin.market.getCropBuyPrice(cropData);
            for(int i = 0;i<amount;i++){
                tempPrice += 0.01;
                actualPrice += tempPrice;
            }
        } else {
            double tempPrice = plugin.market.getCropSellPrice(cropData);
            for(int i = 0;i<amount;i++){
                tempPrice -= 0.0001;
                actualPrice += tempPrice;
            }
        }
        return actualPrice;
    }

}
