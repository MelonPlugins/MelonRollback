package dev.dylancode.melon.rollback;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.dylancode.melon.rollback.DatabaseManager.conn;

public class BlockBreakListener implements Listener {
    private static final String genericInsertSql = "INSERT INTO logs" +
            "(timestamp, action, itemname, x, y, z)" +
            "VALUES (?,?,?,?,?,?)";

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        try {
            PreparedStatement stmt = conn.prepareStatement(genericInsertSql);
            stmt.setLong(1, System.currentTimeMillis());
            stmt.setInt(2, 1);
            stmt.setString(3, event.getBlock().getType().toString().toLowerCase());
            stmt.setInt(4, event.getBlock().getX());
            stmt.setInt(5, event.getBlock().getY());
            stmt.setInt(6, event.getBlock().getZ());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
