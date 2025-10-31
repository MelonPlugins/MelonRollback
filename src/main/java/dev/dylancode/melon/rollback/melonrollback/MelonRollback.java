package dev.dylancode.melon.rollback.melonrollback;

import dev.dylancode.melon.rollback.command.CommandManager;
import dev.dylancode.melon.rollback.database.DatabaseManager;
import dev.dylancode.melon.rollback.event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class MelonRollback extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            DatabaseManager.connect(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        new EventManager(this);
        new CommandManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
