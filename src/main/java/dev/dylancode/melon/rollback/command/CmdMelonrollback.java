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
            ResultSet rs;
            ArrayList<Log> logs = new ArrayList<>();
            String[] queryArgs = Arrays.copyOfRange(args, 1, args.length);
            QueryBuilder queryBuilder = new QueryBuilder(queryArgs);
            if (queryBuilder.error != null) {
                ctx.getSender().sendMessage(queryBuilder.error);
                return;
            }
            String query = queryBuilder.query;
            try {
                Statement stmt = DatabaseManager.conn.createStatement();
                System.out.println(query);
                rs = stmt.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                while (rs.next()) {
                    System.out.println(rs.getRow());
                    int id = rs.getInt("id");
                    long timestamp = rs.getLong("timestamp");
                    int actionId = rs.getInt("action");
                    String itemname = rs.getString("itemname");
                    int x = rs.getInt("x");
                    int y = rs.getInt("y");
                    int z = rs.getInt("z");

                    MelonAction action = MelonAction.fromId(actionId);
                    logs.add(new Log(id, timestamp, action, itemname, x, y, z));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (Log log : logs) {
                ctx.getSender().sendMessage(log.getSummary());
            }
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
