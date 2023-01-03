package dev.aangepast.farmly.utilities;

import org.bukkit.Location;

import java.util.Arrays;

public class FarmUtilities {

    public static boolean isInArea(Location loc, Location minPos, Location maxPos) {

        double[] dim = new double[2];

        dim[0] = minPos.getX();
        dim[1] = maxPos.getX();
        Arrays.sort(dim);
        if (loc.getX() > dim[1] || loc.getX() < dim[0]) {
            return false;
        }

        dim[0] = minPos.getZ();
        dim[1] = maxPos.getZ();
        Arrays.sort(dim);
        if (loc.getZ() > dim[1] || loc.getZ() < dim[0]) {
            return false;
        }

        dim[0] = minPos.getY();
        dim[1] = maxPos.getY();
        Arrays.sort(dim);
        if (loc.getY() > dim[1] || loc.getY() < dim[0]) {
            return false;
        }

        return true;
    }

}
