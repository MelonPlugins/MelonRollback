package dev.dylancode.melon.rollback.event;

import dev.dylancode.melon.rollback.log.Log;
import dev.dylancode.melon.rollback.log.MelonAction;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.dylancode.melon.rollback.database.DatabaseManager.conn;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        for (Block block : event.blockList()) {
            Log log = new Log(-1,
                    System.currentTimeMillis(),
                    MelonAction.BLOCK_BREAK,
                    block.getType().toString().toLowerCase(),
                    block.getX(), block.getY(), block.getY()
            );
            log.writeToDatabase();
        }
    }
}
