package org.vanillacraft.melonrollback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
                    "action INTEGER," +
                    "itemname TEXT)");
        }
    }
}
