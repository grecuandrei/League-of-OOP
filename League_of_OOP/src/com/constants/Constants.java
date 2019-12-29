package constants;

public final class Constants {
    private Constants() { }
    // GAME
    public static final int ANGEL_PARTS = 3;
    // HERO
    public static final int BASE_XP_LEVEL_UP = 250;
    public static final int MULTIPLIER_LEVEL_UP = 50;
    public static final int MULTIPLIER_XP_WINNER = 40;
    public static final int BASE_XP_XP_WINNER = 200;
    public static final float PRECISION = 1000f;
    // ROGUE
    public static final int INITIAL_HP_ROGUE = 600;
    public static final int LEVEL_HP_MULTIPLIER_ROGUE = 40;
    public static final float BASE_DMG_BACKSTAB_ROGUE = 200;
    public static final int BASE_MULTIPLIER_BACKSTAB = 20;
    public static final float HITS_BACKSTAB = 3;
    public static final float CRIT_DMG = 1.5f;
    public static final float LAND_MODIFIER_ROGUE = 1.15f;
    public static final int BASE_DMG_PARALYSIS_ROGUE = 40;
    public static final int BASE_MULTIPLIER_PARALYSIS = 10;
    public static final float BACKSTAB_MULTIPLIER_ROGUE = 1.2f;
    public static final float PARALYSIS_MULTIPLIER_ROGUE = 0.9f;
    public static final float BACKSTAB_MULTIPLIER_KNIGHT = 0.9f;
    public static final float PARALYSIS_MULTIPLIER_KNIGHT = 0.8f;
    public static final float BACKSTAB_MULTIPLIER_PYRO = 1.25f;
    public static final float PARALYSIS_MULTIPLIER_PYRO = 1.2f;
    public static final float BACKSTAB_MULTIPLIER_WIZARD = 1.25f;
    public static final float PARALYSIS_MULTIPLIER_WIZARD = 1.25f;
    public static final int MAX_ROUNDS = 6;
    public static final int MIN_ROUNDS = 3;
    public static final int R_STRAT_LOW = 7;
    public static final int R_STRAT_HIGH = 5;
    // WIZARD
    public static final int INITIAL_HP_WIZARD = 400;
    public static final int LEVEL_HP_MULTIPLIER_WIZARD = 30;
    public static final float BASE_PERCENT_DRAIN_WIZARD = 0.2f;
    public static final float BASE_MULTIPLIER_DRAIN = 0.05f;
    public static final double BASE_PERCENT_DEFLECT = 0.35;
    public static final double BASE_MULTIPLIER_DEFLECT = 0.02;
    public static final double MAX_PERCENTAGE = 0.7;
    public static final float LAND_MODIFIER_WIZARD = 1.1f;
    public static final float DRAIN_MULTIPLIER_ROGUE = 0.8f;
    public static final float DEFLECT_MULTIPLIER_ROGUE = 1.2f;
    public static final float DRAIN_MULTIPLIER_KNIGHT = 1.2f;
    public static final float DEFLECT_MULTIPLIER_KNIGHT = 1.4f;
    public static final float DRAIN_MULTIPLIER_PYRO = 0.9f;
    public static final float DEFLECT_MULTIPLIER_PYRO = 1.3f;
    public static final float DRAIN_MULTIPLIER_WIZARD = 1.05f;
    public static final float PERCENTAGE_BASE_HP = 0.3f;
    public static final int W_STRAT_LOW = 4;
    public static final int W_STRAT_HIGH = 2;
    // KNIGHT
    public static final int INITIAL_HP_KNIGHT = 900;
    public static final int LEVEL_HP_MULTIPLIER_KNIGHT = 80;
    public static final float BASE_DMG_EXECUTE_KNIGHT = 200;
    public static final int BASE_MULTIPLIER_EXECUTE = 30;
    public static final float HP_PERCENT = 0.2f;
    public static final float LEVEL_PERCENT = 0.01f;
    public static final float HP_LIMIT = 0.4f;
    public static final float LAND_MODIFIER_KNIGHT = 1.15f;
    public static final float BASE_DMG_SLAM = 100;
    public static final int BASE_MULTIPLIER_SLAM = 40;
    public static final float EXECUTE_MULTIPLIER_ROGUE = 1.15f;
    public static final float SLAM_MULTIPLIER_ROGUE = 0.8f;
    public static final float SLAM_MULTIPLIER_KNIGHT = 1.2f;
    public static final float EXECUTE_MULTIPLIER_PYRO = 1.1f;
    public static final float SLAM_MULTIPLIER_PYRO = 0.9f;
    public static final float EXECUTE_MULTIPLIER_WIZARD = 0.8f;
    public static final float SLAM_MULTIPLIER_WIZARD = 1.05f;
    public static final int K_STRAT_LOW = 3;
    public static final int K_STRAT_HIGH = 2;
    // PYROMANCER
    public static final int INITIAL_HP_PYRO = 500;
    public static final int LEVEL_HP_MULTIPLIER_PYRO = 50;
    public static final float BASE_DMG_FIREBLAST = 350;
    public static final int BASE_MULTIPLIER_FIREBLAST = 50;
    public static final float LAND_MODIFIER_PYRO = 1.25f;
    public static final float BASE_DMG_IGNITE = 150;
    public static final int BASE_MULTIPLIER_IGNITE = 20;
    public static final float FIREBLAST_MULTIPLIER_ROGUE = 0.8f;
    public static final float IGNITE_MULTIPLIER_ROGUE = 0.8f;
    public static final float FIREBLAST_MULTIPLIER_KNIGHT = 1.2f;
    public static final float IGNITE_MULTIPLIER_KNIGHT = 1.2f;
    public static final float FIREBLAST_MULTIPLIER_PYRO = 0.9f;
    public static final float IGNITE_MULTIPLIER_PYRO = 0.9f;
    public static final float FIREBLAST_MULTIPLIER_WIZARD = 1.05f;
    public static final float IGNITE_MULTIPLIER_WIZARD = 1.05f;
    public static final int IGNITE_OVT_DMG = 50;
    public static final int IGNITE_OVT_MULTIPLIER = 30;
    public static final int IGNITE_ROUNDS = 2;
    public static final int P_STRAT_LOW = 4;
    public static final int P_STRAT_HIGH = 3;
    // DAMAGEANGEL
    public static final float DMGA_KNIGHT_MOD = 0.15f;
    public static final float DMGA_PYRO_MOD = 0.2f;
    public static final float DMGA_ROGUE_MOD = 0.3f;
    public static final float DMGA_WIZARD_MOD = 0.4f;
    // DARKANGEL
    public static final int DARKA_KNIGHT = 40;
    public static final int DARKA_PYRO = 30;
    public static final int DARKA_ROGUE = 10;
    public static final int DARKA_WIZARD = 20;
    // DRACULA
    public static final float DRAC_KNIGHT_MOD = 0.2f;
    public static final float DRAC_PYRO_MOD = 0.3f;
    public static final float DRAC_ROGUE_MOD = 0.1f;
    public static final float DRAC_WIZARD_MOD = 0.4f;
    public static final int DRAC_KNIGHT = 60;
    public static final int DRAC_PYRO = 40;
    public static final int DRAC_ROGUE = 35;
    public static final int DRAC_WIZARD = 20;
    // GOODBOY
    public static final float GB_KNIGHT_MOD = 0.4f;
    public static final float GB_PYRO_MOD = 0.5f;
    public static final float GB_ROGUE_MOD = 0.4f;
    public static final float GB_WIZARD_MOD = 0.3f;
    public static final int GB_KNIGHT = 20;
    public static final int GB_PYRO = 30;
    public static final int GB_ROGUE = 40;
    public static final int GB_WIZARD = 50;
    // LEVELUPANGEL
    public static final float LVL_KNIGHT_MOD = 0.1f;
    public static final float LVL_PYRO_MOD = 0.2f;
    public static final float LVL_ROGUE_MOD = 0.15f;
    public static final float LVL_WIZARD_MOD = 0.25f;
    // LIFEGIVER
    public static final int LG_KNIGHT = 100;
    public static final int LG_PYRO = 80;
    public static final int LG_ROGUE = 90;
    public static final int LG_WIZARD = 120;
    // SMALANGEL
    public static final float SA_KNIGHT_MOD = 0.1f;
    public static final float SA_PYRO_MOD = 0.15f;
    public static final float SA_ROGUE_MOD = 0.05f;
    public static final float SA_WIZARD_MOD = 0.1f;
    public static final int SA_KNIGHT = 10;
    public static final int SA_PYRO = 15;
    public static final int SA_ROGUE = 20;
    public static final int SA_WIZARD = 25;
    // SPAWNER
    public static final int SPAWNER_KNIGHT = 200;
    public static final int SPAWNER_PYRO = 150;
    public static final int SPAWNER_ROGUE = 180;
    public static final int SPAWNER_WIZARD = 120;
    // XPANGEL
    public static final int XP_KNIGHT = 45;
    public static final int XP_PYRO = 50;
    public static final int XP_ROGUE = 40;
    public static final int XP_WIZARD = 60;
    // STRATEGIES CONSTANTS
    public static final float MOD_04 = 0.4f;
    public static final float MOD_05 = 0.5f;
    public static final float MOD_01 = 0.1f;
    public static final float MOD_025 = 0.25f;
    public static final float MOD_02 = 0.2f;
    public static final float MOD_07 = 0.7f;
    public static final float MOD_03 = 0.3f;
    public static final float MOD_06 = 0.6f;
}
