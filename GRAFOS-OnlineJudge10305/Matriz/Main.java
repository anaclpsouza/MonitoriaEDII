package Matriz;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n == 0 && m == 0) {
                break;
            }

            int[][] grafo = new int[n][n];
            int[] grauEntrada = new int[n];

            for (int i = 0; i < m; i++) {
                int j = sc.nextInt() - 1;
                int k = sc.nextInt() - 1;
                
                grafo[j][k] = 1;
                grauEntrada[k]++;
            }

            ordenacao(grafo, grauEntrada, n);
        }

        sc.close();
    }

    public static void ordenacao(int[][] grafo, int[] grauEntrada, int n) {
        Queue<Integer> fila = new LinkedList<>();
        ArrayList<Integer> ordenacao = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (grauEntrada[i] == 0) {
                fila.add(i);
            }
        }

        while (!fila.isEmpty()) {
            int u = fila.remove();
            ordenacao.add(u);

            for (int i = 0; i < n; i++) {
                if (grafo[u][i] == 1) {
                    grafo[u][i] = 0;
                    grauEntrada[i]--;

                    if (grauEntrada[i] == 0) {
                        fila.add(i);
                    }
                }
            }
        }

        for (int i = 0; i < ordenacao.size(); i++) {
            System.out.print((ordenacao.get(i) + 1 + " "));
        }
        System.out.println();
    }
}