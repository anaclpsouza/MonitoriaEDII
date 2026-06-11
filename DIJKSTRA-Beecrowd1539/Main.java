import java.util.*;

public class Main {
    static class Antena {
        int x, y, r;

        Antena(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0)
                break;

            Antena[] antenas = new Antena[n + 1];
            for (int i = 1; i <= n; i++) {
                antenas[i] = new Antena(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }

            double[][] grafo = new double[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                Arrays.fill(grafo[i], -1.0);
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j)
                        continue;

                    long dx = antenas[i].x - antenas[j].x;
                    long dy = antenas[i].y - antenas[j].y;
                    long distAoQuadrado = dx * dx + dy * dy;
                    long raioAoQuadrado = (long) antenas[i].r * antenas[i].r;

                    if (distAoQuadrado <= raioAoQuadrado) {
                        grafo[i][j] = Math.sqrt(distAoQuadrado);
                    }
                }

                int c = sc.nextInt();
                for (int q = 0; q < c; q++) {
                    int origem = sc.nextInt();
                    int destino = sc.nextInt();

                    int resultado = dijkstra(grafo, n, origem, destino);
                    System.out.println(resultado);
                }
            }
            sc.close();
        }
    }

    private static int dijkstra(double[][] grafo, int n, int origem, int destino) {
        double[] dist = new double[n + 1];
        boolean[] visitado = new boolean[n + 1];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[origem] = 0.0;

        for (int i = 1; i <= n; i++) {
            int u = -1;
            double menorDist = Double.MAX_VALUE;

            for (int j = 1; j <= n; j++) {
                if (!visitado[j] && dist[j] < menorDist) {
                    menorDist = dist[j];
                    u = j;
                }
            }

            if (u == -1 || u == destino)
                break;
            visitado[u] = true;

            for (int v = 1; v <= n; v++) {
                if (grafo[u][v] != -1.0 && !visitado[v]) {
                    if (dist[u] + grafo[u][v] < dist[v]) {
                        dist[v] = dist[u] + grafo[u][v];
                    }
                }
            }
        }

        return dist[destino] == Double.MAX_VALUE ? -1 : (int) dist[destino];
    }
}