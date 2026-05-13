import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int findShortestCycle(int n, int[][] edges) {

        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[1]).add(edge[0]);
            adj.get(edge[0]).add(edge[1]);
        }

        int menorCiclo = -1;

        for (int i = 0; i < n; i++) {
            int[] dist = new int[n];

            for (int j = 0; j < dist.length; j++) {
                dist[j] = -1;
            }

            int[] pai = new int[n];

            for (int j = 0; j < pai.length; j++) {
                pai[j] = -1;
            }

            Queue<Integer> fila = new LinkedList<>();

            while (!fila.isEmpty()) {
                int atual = fila.poll();

                for (int vizinho : adj.get(atual)) {
                    if (dist[vizinho] == -1) {
                        dist[vizinho] = dist[atual] + 1;
                        pai[vizinho] = atual; // de onde eu vim
                        fila.add(vizinho);
                    } else if (vizinho != pai[atual]) { // se já foi visitado e não é de onde eu vim
                        int tamCicloAtual = dist[atual] + dist[vizinho] + 1;
                        if (menorCiclo > tamCicloAtual) {
                            menorCiclo = tamCicloAtual;
                        }
                    }
                }
            }
        }

        // Se menorCiclo não mudou, significa que o grafo não tem ciclos
        return menorCiclo == Integer.MAX_VALUE ? -1 : menorCiclo;
    }
}