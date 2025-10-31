package dev.dylancode.melon.rollback.command;

import dev.dylancode.melon.rollback.database.DatabaseManager;
import dev.dylancode.melon.rollback.log.Log;
import dev.dylancode.melon.rollback.log.MelonAction;
import dev.dylancode.melon.rollback.query.Query;
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
        QueryBuilder queryBuilder = new QueryBuilder(queryArgs);
        if (queryBuilder.error != null) {
            ctx.getSender().sendMessage("&cInvalid query: " + queryBuilder.error);
            return;
        }

        Query query = new Query(queryBuilder.query);
        for (Log log : query.logs) {
            ctx.getSender().sendMessage(log.getSummary());
        }
    }
}
