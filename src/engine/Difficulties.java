package engine;

public enum Difficulties {

    EASY,
    MEDIUM,
    HARD;

    public static Difficulties difficultyOfInt(int i) {
        switch (i) {
            case 0:
                return EASY;
            case 1:
                return MEDIUM;
            case 2:
                return HARD;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int intOfDifficulty(Difficulties d) {
        switch (d) {
            case EASY:
                return 0;
            case MEDIUM:
                return 1;
            case HARD:
                return 2;
            default:
                throw new IllegalArgumentException();
        }
    }

}
