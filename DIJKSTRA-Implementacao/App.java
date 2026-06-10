import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        int[][] matrizPesosNaoD = {
                { 0, 1, 0, 2, 0, 0, 0, 0 },
                { 1, 0, 0, 3, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 6, 7, 9, 0 },
                { 2, 3, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 6, 0, 0, 0, 0, 3 },
                { 0, 0, 7, 1, 0, 0, 7, 9 },
                { 0, 0, 9, 0, 0, 7, 0, 0 },
                { 0, 0, 0, 0, 3, 9, 0, 0 }
        };

        int[][] matrizPesosDirecionado = {
                { 0, 0, 5, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 5, 5, 0, 0 },
                { 0, 4, 0, 0, 0, 7, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1 },
                { 0, 7, 0, 0, 0, 0, 2, 2 },
                { 0, 0, 1, 0, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 2, 0 }
        };

        int[] rota = Dijkstra(matrizPesosDirecionado);
        System.out.println("Grafo direcionado: ");
        imprimirCaminho(rota, 0, 7);

        System.out.println("\n =================================");

        int[] rota2 = Dijkstra(matrizPesosNaoD);
        System.out.println("Grafo não direcionado: ");
        imprimirCaminho(rota2, 0, 7);
    }

    public static int[] Dijkstra(int[][] matrizPesos) {
        int n = matrizPesos.length;
        int count = n;
        long[] dist = new long[n];
        int[] rota = new int[n];
        dist[0] = 0;
        rota[0] = -1;

        for (int i = 1; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            rota[i] = -1;
        }

        boolean[] vizitados = new boolean[n];

        while (count > 0) {
            count--;
            long min = Integer.MAX_VALUE;
            int v = -1;
            for (int i = 0; i < n; i++) {
                if (!vizitados[i] && dist[i] != Integer.MAX_VALUE && dist[i] < min) {
                    min = dist[i];
                    v = i;
                }
            }

            if (v == -1)
                break;

            vizitados[v] = true;
            for (int u = 0; u < n; u++) {
                if (matrizPesos[v][u] > 0 && !vizitados[u]) {
                    if (dist[v] + matrizPesos[v][u] < dist[u]) {
                        dist[u] = dist[v] + matrizPesos[v][u];
                        rota[u] = v;
                    }
                }
            }
        }

        return rota;
    }

    public static void imprimirCaminho(int[] rota, int origem, int destino) {

        if (rota[destino] == -1 && destino != origem) {
            System.out.println("Não existe caminho de " + origem + " até " + destino);
            return;
        }

        List<Integer> caminho = new ArrayList<>();
        int atual = destino;

        while (atual != -1) {
            caminho.add(atual);
            atual = rota[atual]; 
        }

        Collections.reverse(caminho);

        if (caminho.get(0) != origem) {
            System.out.println("Não existe caminho direto de " + origem + " até " + destino);
            return;
        }

        System.out.print("Caminho de " + origem + " até " + destino + ": ");
        for (int i = 0; i < caminho.size(); i++) {
            if (i == caminho.size() - 1) {
                System.out.println(caminho.get(i));
            } else {
                System.out.print(caminho.get(i) + " -> ");
            }
        }
    }
}