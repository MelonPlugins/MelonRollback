package dev.dylancode.melon.rollback;

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

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new EntityExplodeListener(), this);

        registerCommand("melonrollback", new CmdMelonrollback());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
