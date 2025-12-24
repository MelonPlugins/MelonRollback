package dev.dylancode.melon.rollback.log;

public enum MelonAction {
    NONE,
    BLOCK_BREAK,
    BLOCK_PLACE;

    public int id() {
        return switch (this) {
            case NONE -> 0;
            case BLOCK_BREAK -> 1;
            case BLOCK_PLACE -> 2;
        };
    }

    public static MelonAction fromId(int id) {
        return switch (id) {
            case 1 -> BLOCK_BREAK;
            case 2 -> BLOCK_PLACE;
            default -> NONE;
        };
    }

    public String pastVerb() {
        return switch (this) {
            case NONE -> "did nothing to";
            case BLOCK_BREAK -> "broke";
            case BLOCK_PLACE -> "placed";
        };
    }
}
