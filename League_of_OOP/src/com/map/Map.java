package map;


public class Map {
    
    private char[][] map;
    private static int i = 0, j = 0, n, m;
    
    private static Map instance = null;
    
    private Map(int n, int m) {
        Map.n = n;
        Map.m = m;
        map = new char[n][m];
    }

    public static Map getInstance(int n, int m){
        if(instance == null) {
            instance = new Map(n ,m);
        }
        return instance;
    }
    
    public void addTerrain(char S) {
        if (i < n && j < m) {
            map[i][j] = S;
            j++;
        } else if (j == n) {
            i++;
            j = 0;
            map[i][j] = S;
            j++;
        }
    }
    
    public char getTerrain(int i, int j) {
        return map[i][j];
    }
    
    public void printMap() {
        for (int i = 0; i<n;i++){
            for (int j = 0; j< m;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
