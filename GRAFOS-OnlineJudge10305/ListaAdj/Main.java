package ListaAdj;

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

            ArrayList<ArrayList<Integer>> grafo = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                grafo.add(new ArrayList<>());
            }

            int[] grau = new int[n];

            for (int i = 0; i < m; i++) {
                int j = sc.nextInt() - 1;
                int k = sc.nextInt() - 1;

                grafo.get(j).add(k);
                grau[k]++;
            }

            ordenacao(grafo, grau);
        }

        sc.close();
    }

    public static void ordenacao(ArrayList<ArrayList<Integer>> grafo, int[] grau) {
        Queue<Integer> fila = new LinkedList<>();
        ArrayList<Integer> ordem = new ArrayList<>();

        for (int i = 0; i < grafo.size(); i++) {
            if (grau[i] == 0) {
                fila.add(i);
            }
        }

        while (!fila.isEmpty()) {
            int u = fila.remove();
            ordem.add(u);

            for (int v : grafo.get(u)) {
                grau[v]--;

                if (grau[v] == 0) {
                    fila.add(v);
                }
            }
        }

        for (int i = 0; i < ordem.size(); i++) {
            System.out.print((ordem.get(i) + 1) + " ");

        }
        System.out.println();
    }
}
