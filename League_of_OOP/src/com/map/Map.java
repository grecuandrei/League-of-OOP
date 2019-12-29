package map;
// Implementation of pattern Singleton for the map
public final class Map {
    private char[][] map;
    private static int i = 0, j = 0, n, m;
    private static Map instance = null;
    private Map(final int nn, final int mm) {
        Map.n = nn;
        Map.m = mm;
        map = new char[nn][mm];
    }
    public static Map getInstance(final int nn, final int mm) {
        if (instance == null) {
            instance = new Map(nn, mm);
        }
        return instance;
    }
    // add the terrain block
    public void addTerrain(final char s) {
        if (i < n && j < m) {
            map[i][j] = s;
            ++j;
        } else if (j == n) {
            ++i;
            j = 0;
            map[i][j] = s;
            ++j;
        }
    }
    // get the terrain in the block
    public char getTerrain(final int ii, final int jj) {
        return map[ii][jj];
    }
}
