import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        boolean[] visitados = new boolean[n];
        Queue<Integer> fila = new LinkedList<>();

        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        visitados[source] = true;
        fila.add(source);

        while (!fila.isEmpty()) {
            int atual = fila.poll();

            if (atual == destination) {
                return true;
            }

           for (int vizinho : adj.get(atual)) {
                if (!visitados[vizinho]) {
                    visitados[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }

        return false;
    }
}
