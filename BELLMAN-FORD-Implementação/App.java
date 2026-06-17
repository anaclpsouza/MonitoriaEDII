public class App {

    public static void main(String[] args) {
        
    }

    public void bellmanFord(int[][] grafo) {
        int n = grafo.length;
        int[] distancias = new int[n];
        int[] rota = new int[n];

        for (int i = 1; i < n; i++) {
            if (grafo[0][i] != 0) {
                rota[i] = 0;
                distancias[i] = grafo[0][i];
            } else {
                rota[i] = -1;
                distancias[i] = Integer.MAX_VALUE;
            }
        }

        for (int k = 0; k < n - 1; k++) {
            boolean altera = false;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grafo[j][i] != 0) {
                        if (distancias[j] != Integer.MAX_VALUE && distancias[i] > distancias[j] + grafo[j][i]) {
                            distancias[i] = distancias[j] + grafo[j][i];
                            rota[i] = j;
                            altera = true;
                        }
                    }
                }
            }

            if (!altera) {
                k = n;
            }
        }
    }
}