package dev.dylancode.melon.rollback;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.dylancode.melon.rollback.DatabaseManager.conn;

public class EntityExplodeListener implements Listener {
    private static final String genericInsertSql = "INSERT INTO logs" +
            "(timestamp, action, itemname, x, y, z)" +
            "VALUES (?,?,?,?,?,?)";

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        System.out.println("block explode");
        for (Block block : event.blockList()) {
            try {
                PreparedStatement stmt = conn.prepareStatement(genericInsertSql);
                stmt.setLong(1, System.currentTimeMillis());
                stmt.setInt(2, 1);
                stmt.setString(3, block.getType().toString().toLowerCase());
                stmt.setInt(4, block.getX());
                stmt.setInt(5, block.getY());
                stmt.setInt(6, block.getZ());
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
