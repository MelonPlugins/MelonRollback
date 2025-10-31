package dev.dylancode.melon.rollback.event;

import dev.dylancode.melon.rollback.melonrollback.MelonRollback;

public class EventManager {
    public EventManager(MelonRollback plugin) {
        plugin.getServer().getPluginManager().registerEvents(new BlockBreakListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new EntityExplodeListener(), plugin);
    }
}
