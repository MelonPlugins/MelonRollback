package dev.dylancode.melon.rollback.command;

import dev.dylancode.melon.rollback.log.Log;
import dev.dylancode.melon.rollback.query.Query;
import dev.dylancode.melon.rollback.query.QueryBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

public class CmdMelonrollbackQuery {
    public CmdMelonrollbackQuery(@NotNull CommandSourceStack ctx, String[] queryArgs) {
        QueryBuilder queryBuilder = new QueryBuilder(queryArgs, ctx.getLocation());
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
