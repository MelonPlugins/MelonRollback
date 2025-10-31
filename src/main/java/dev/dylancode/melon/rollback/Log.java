package dev.dylancode.melon.rollback;

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
}
