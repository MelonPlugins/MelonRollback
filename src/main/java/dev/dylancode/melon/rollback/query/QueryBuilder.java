package dev.dylancode.melon.rollback.query;

import dev.dylancode.melon.rollback.log.MelonAction;
import org.bukkit.Location;
import org.bukkit.Material;

public class QueryBuilder {
    public String error = null;
    public String query;

    public QueryBuilder(String[] queryArgs, Location playerLocation) {
        query = "SELECT * FROM logs WHERE ";
        for (String queryArg : queryArgs) {
            if (queryArg.startsWith("a:")) {
                String actionName = queryArg.substring("a:".length());
                MelonAction action;
                try {
                    action = MelonAction.valueOf(actionName.toUpperCase());
                } catch (IllegalArgumentException e) {
                    error = "Unknown action: " + actionName;
                    return;
                }
                int actionId = action.id();

                query += "action = " + actionId + " AND ";
            }
            if (queryArg.startsWith("b:")) {
                String materialString = queryArg.substring("b:".length()).toLowerCase();
                Material material = Material.getMaterial(materialString.toUpperCase());
                if (material == null) {
                    error = "Unknown material for item: " + materialString;
                    return;
                }
                query += "itemname = '" + queryArg.substring("i:".length()).toLowerCase() + "' AND ";
            }
            if (queryArg.startsWith("r:")) {
                String radiusString = queryArg.substring("r:".length()).toLowerCase();
                int radius = Integer.parseInt(radiusString);
                int minX = playerLocation.blockX() - radius;
                int minY = playerLocation.blockY() - radius;
                int minZ = playerLocation.blockZ() - radius;
                int maxX = playerLocation.blockX() + radius;
                int maxY = playerLocation.blockY() + radius;
                int maxZ = playerLocation.blockZ() + radius;
                query += "x >= " +  minX + " AND " + "x <= " + maxX + " AND ";
                query += "y >= " +  minY + " AND " + "y <= " + maxY + " AND ";
                query += "z >= " +  minZ + " AND " + "z <= " + maxZ + " AND ";
                System.out.println(query);
            }
        }
        query = query.substring(0, query.length() - " AND ".length());
    }
}
