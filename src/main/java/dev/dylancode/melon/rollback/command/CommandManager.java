package dev.dylancode.melon.rollback.command;

import dev.dylancode.melon.rollback.melonrollback.MelonRollback;

public class CommandManager {
    public CommandManager(MelonRollback plugin) {
        plugin.registerCommand("melonrollback", new CmdMelonrollback());
    }
}
