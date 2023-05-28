public enum ForumsEnum {
    GAME_BUSINESS(3),
    FORUM_BUSINESS(2),

    GAME_DISCUSSION(4),
    WORLDS(8),
    WORLD_CREATION(7),
    QUESTIONS_AND_ANSWERS(18),
    CREWS(9),
    BOTS_AND_PROGRAMMING(19),

    GAME_SUGGESTIONS(5),
    GRAPHICS_SUGGESTIONS(20),
    CAMPAIGN_SUGGESTIONS(24),
    BUG_REPORTS(12),
    FORUM_DISCUSSION(11),

    OFF_TOPIC_DISCUSSION(13),
    DEBATES(26),
    CREATIVE(14),
    FORUM_GAMES(17),

    COMPOST_BIN(15),

    ;

    final int id;

    ForumsEnum(int id) {
        this.id = id;
    }
}
