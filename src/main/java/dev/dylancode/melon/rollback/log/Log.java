package dev.dylancode.melon.rollback.log;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dev.dylancode.melon.rollback.database.DatabaseManager.conn;

public class Log {
    int id;
    long timestamp;
    MelonAction action;
    String itemname;
    int x;
    int y;
    int z;

    public Log(int id, long timestamp, MelonAction action, String itemname, int x, int y, int z) {
        this.id = id;
        this.timestamp = timestamp;
        this.action = action;
        this.itemname = itemname;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getSummary() {
        return "#" + id + " " + action.toString().toLowerCase() + " (" + itemname + ")";
    }

    private static final String genericInsertSql = "INSERT INTO logs" +
            "(timestamp, action, itemname, x, y, z)" +
            "VALUES (?,?,?,?,?,?)";

    public void writeToDatabase() {
        try {
            PreparedStatement stmt = conn.prepareStatement(genericInsertSql);
            stmt.setLong(1, timestamp);
            stmt.setInt(2, action.id());
            stmt.setString(3, itemname);
            stmt.setInt(4, x);
            stmt.setInt(5, y);
            stmt.setInt(6, z);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
