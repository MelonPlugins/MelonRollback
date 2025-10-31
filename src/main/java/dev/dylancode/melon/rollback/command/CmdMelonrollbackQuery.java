package dev.dylancode.melon.rollback.command;

import dev.dylancode.melon.rollback.database.DatabaseManager;
import dev.dylancode.melon.rollback.log.Log;
import dev.dylancode.melon.rollback.log.MelonAction;
import dev.dylancode.melon.rollback.query.QueryBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class CmdMelonrollbackQuery {
    public CmdMelonrollbackQuery(@NotNull CommandSourceStack ctx, String[] queryArgs) {
        ResultSet rs;
        ArrayList<Log> logs = new ArrayList<>();
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
