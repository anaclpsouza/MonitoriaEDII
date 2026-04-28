import java.util.Scanner;

public class Main {

    static int di[] = { 1, -1, 0, 0 };
    static int dj[] = { 0, 0, 1, -1 };
    static int m, n;
    static int[][] mapa;
    static boolean[][] visitados;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        mapa = new int[n][m];
        visitados = new boolean[n][m];

        int inicio_i = -1;
        int inicio_j = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = sc.nextInt();
                mapa[i][j] = num;
                if (num == 2) {
                    inicio_i = i;
                    inicio_j = j;
                }
            }
        }

        System.out.println(encontrarSaida(inicio_i, inicio_j));

        sc.close();
    }

    private static int encontrarSaida(int i, int j) {
        if (mapa[i][j] == 3) {
            return 1;
        }

        visitados[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int ni = i + di[k];
            int nj = j + dj[k];

            if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                if (!visitados[ni][nj] && (mapa[ni][nj] == 1 || mapa[ni][nj] == 3)) {
                    int dist = encontrarSaida(ni, nj);

                    if (dist != 0) {
                        return dist + 1;
                    }
                }
            }
        }
        return 0;
    }
}

// Outra forma de encontrar a posição
// // 1. Tentar ir para BAIXO (linha i + 1, mesma coluna j)
// if (i + 1 < n && !visitados[i + 1][j] && (mapa[i + 1][j] == 1 || mapa[i +
// 1][j] == 3)) {
// dist = encontrarSaida(i + 1, j);
// if (dist != 0) return dist + 1;
// }

// // 2. Tentar ir para CIMA (linha i - 1, mesma coluna j)
// if (i - 1 >= 0 && !visitados[i - 1][j] && (mapa[i - 1][j] == 1 || mapa[i -
// 1][j] == 3)) {
// dist = encontrarSaida(i - 1, j);
// if (dist != 0) return dist + 1;
// }

// // 3. Tentar ir para a DIREITA (mesma linha i, coluna j + 1)
// if (j + 1 < m && !visitados[i][j + 1] && (mapa[i][j + 1] == 1 || mapa[i][j +
// 1] == 3)) {
// dist = encontrarSaida(i, j + 1);
// if (dist != 0) return dist + 1;
// }

// // 4. Tentar ir para a ESQUERDA (mesma linha i, coluna j - 1)
// if (j - 1 >= 0 && !visitados[i][j - 1] && (mapa[i][j - 1] == 1 || mapa[i][j -
// 1] == 3)) {
// dist = encontrarSaida(i, j - 1);
// if (dist != 0) return dist + 1;
// }