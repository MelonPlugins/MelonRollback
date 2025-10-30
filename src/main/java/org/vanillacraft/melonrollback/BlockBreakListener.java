package org.vanillacraft.melonrollback;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.vanillacraft.melonrollback.DatabaseManager.conn;

public class BlockBreakListener implements Listener {
    private String genericInsertSql = "INSERT INTO logs" +
            "(action, itemname)" +
            "VALUES (?,?)";

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        try {
            PreparedStatement stmt = conn.prepareStatement(genericInsertSql);
            stmt.setInt(1, 1);
            stmt.setString(2, event.getBlock().getType().toString());
            int row =  stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
