import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grafo = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(grafo[i], Integer.MAX_VALUE);
            grafo[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            if (w < grafo[u][v]) {
                grafo[u][v] = w;
                grafo[v][u] = w;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grafo[i][k] != Integer.MAX_VALUE && grafo[k][j] != Integer.MAX_VALUE) {
                        if (grafo[i][j] > grafo[i][k] + grafo[k][j]) {
                            grafo[i][j] = grafo[i][k] + grafo[k][j];
                        }
                    }
                }
            }
        }

        int menorDistanciaMaxima = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int maiorDistanciaDestaCidade = 0;
            for (int j = 0; j < n; j++) {
                if (grafo[i][j] > maiorDistanciaDestaCidade) {
                    maiorDistanciaDestaCidade = grafo[i][j];
                }
            }

            if (maiorDistanciaDestaCidade < menorDistanciaMaxima) {
                menorDistanciaMaxima = maiorDistanciaDestaCidade;
            }
        }

        System.out.println(menorDistanciaMaxima);
        sc.close();
    }
}
