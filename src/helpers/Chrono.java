package helpers;

public class Chrono {

    private static long startTime;
    private static long startPause;
    private static long totalPause;

    public Chrono() {
        reinitialization();
    }

    public void reinitialization() {
        startTime  = 0;
        startPause = 0;
        totalPause = 0;
    }

    public void start() {
        reinitialization();
        startTime = System.currentTimeMillis();
    }

    public void resume() {
        if (startTime == 0 || startPause == 0) {
            /* Has not be started */
            return;
        }

        totalPause += System.currentTimeMillis() - startPause;
    }

    public void pause() {
        if (startTime == 0) {
            /* Has not be started */
            return;
        }

        startPause = System.currentTimeMillis();
    }

    public void stop() {
        reinitialization();
    }

    public long timeEllapsed() {
        return System.currentTimeMillis() - startTime - totalPause;
    }

    @Override
    public String toString() {
        long realTime = timeEllapsed();

        int minutes      = (int) ((realTime / 1000) / 60);
        int seconds      = (int) ((realTime / 1000) % 60);
        int milliseconds = (int) (realTime % 1000);

        return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
    }

}
