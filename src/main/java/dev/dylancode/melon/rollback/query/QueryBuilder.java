package dev.dylancode.melon.rollback.query;

import dev.dylancode.melon.rollback.log.MelonAction;
import org.bukkit.Material;

public class QueryBuilder {
    public String error = null;
    public String query;

    public QueryBuilder(String[] queryArgs) {
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
        }
        query = query.substring(0, query.length() - " AND ".length());
    }
}
