import java.util.ArrayList;
import java.util.List;

public class Main {
    static final int INF = 1_000_000;

    public static void main(String[] args) {
        int[][] grafo1 = {
                { 0, INF, INF, INF, 8, 3, INF, INF },
                { INF, 0, 5, 4, INF, 2, INF, INF },
                { INF, 2, 0, INF, INF, 9, INF, INF },
                { INF, 2, INF, 0, INF, INF, INF, 6 },
                { INF, INF, INF, INF, 0, INF, INF, 4 },
                { INF, INF, INF, INF, INF, 0, INF, INF },
                { INF, INF, INF, INF, 7, 1, 0, 3 },
                { INF, INF, INF, INF, INF, INF, INF, 0 }
        };

        int[][] rota1 = floydWarshall(grafo1);
        imprimirCaminho(2, 3, rota1);
    }

    public static int[][] floydWarshall(int[][] grafo) {
        int n = grafo.length;
        int[][] pesos = new int[n][n];
        int[][] rotas = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pesos[i][j] = grafo[i][j];

                if ((i == j) || grafo[i][j] == INF) {
                    rotas[i][j] = -1;
                } else {
                    rotas[i][j] = j;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (pesos[i][j] > pesos[i][k] + pesos[k][j]) {
                        pesos[i][j] = pesos[i][k] + pesos[k][j];
                        rotas[i][j] = rotas[i][k];
                    }
                }
            }
        }

        return rotas;
    }

    public static void imprimirCaminho(int origem, int destino, int[][] rotas) {
        if (rotas[origem][destino] == -1) {
            System.out.println("Não há caminho.");
            return;
        }

        List<Integer> caminho = new ArrayList<>();
        caminho.add(origem);

        int atual = origem;
        while (atual != destino) {
            atual = rotas[atual][destino];
            if (atual == -1) {
                System.out.println("Erro na reconstrução da rota.");
                return;
            }
            caminho.add(atual);
        }

        System.out.println("Caminho mais curto: " + caminho);
    }
}