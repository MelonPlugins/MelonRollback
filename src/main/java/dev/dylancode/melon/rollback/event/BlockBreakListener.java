package dev.dylancode.melon.rollback.event;

import dev.dylancode.melon.rollback.log.Log;
import dev.dylancode.melon.rollback.log.MelonAction;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private static final String genericInsertSql = "INSERT INTO logs" +
            "(timestamp, action, itemname, x, y, z)" +
            "VALUES (?,?,?,?,?,?)";

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Log log = new Log(-1,
                System.currentTimeMillis(),
                MelonAction.BLOCK_BREAK,
                block.getType().toString().toLowerCase(),
                block.getX(), block.getY(), block.getY()
        );
        log.writeToDatabase();
    }
}
