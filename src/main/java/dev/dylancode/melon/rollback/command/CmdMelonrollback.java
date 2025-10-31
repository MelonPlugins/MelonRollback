package dev.dylancode.melon.rollback.command;

import dev.dylancode.melon.rollback.database.DatabaseManager;
import dev.dylancode.melon.rollback.log.Log;
import dev.dylancode.melon.rollback.log.MelonAction;
import dev.dylancode.melon.rollback.query.QueryBuilder;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack ctx, @NotNull String[] args) {
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
