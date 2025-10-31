package dev.dylancode.melon.rollback.command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

public class CmdMelonrollback implements BasicCommand {

    @Override
    public void execute(@NotNull CommandSourceStack ctx, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            CmdMelonrollbackHelp.execute(ctx, args);
            return;
        }
        if (args[0].equalsIgnoreCase("query")) {
            String[] queryArgs = Arrays.copyOfRange(args, 1, args.length);
            new CmdMelonrollbackQuery(ctx, queryArgs);
        }
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack ctx, String @NotNull [] args) {
        return BasicCommand.super.suggest(ctx, args);
    }

    @Override
    public boolean canUse(CommandSender sender) {
        return sender.hasPermission("melon.rollback");
    }

    @Override
    public @Nullable String permission() {
        return "melon.rollback";
    }
}
