package dev.dylancode.melon.rollback;

import java.sql.*;

public class DatabaseManager {
    public static Connection conn;

    public static void connect(MelonRollback plugin) throws SQLException {
        plugin.getDataFolder().mkdirs();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        conn = DriverManager.getConnection("jdbc:sqlite:plugins/MelonRollback/melonrollback.db");

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS logs" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "timestamp LONG," +
                    "action INTEGER," +
                    "itemname TEXT," +
                    "x INTEGER," +
                    "y INTEGER," +
                    "z INTEGER)");
        }
    }
}
