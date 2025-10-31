package dev.dylancode.melon.rollback;

import io.papermc.paper.command.brigadier.CommandSourceStack;

public class CmdMelonrollbackHelp {
    public static void execute(CommandSourceStack ctx, String[] args) {
         ctx.getSender().sendMessage("Melon Help");
    }
}
