import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // Vetor de cores: 
        // 0 significa não visitado/não colorido
        // 1 significa cor A
        // -1 significa cor B
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                colors[i] = 1; 

                while (!queue.isEmpty()) {
                    int curr = queue.poll();

                    for (int neighbor : graph[curr]) {
                        if (colors[neighbor] == 0) {
                            colors[neighbor] = -colors[curr];
                            queue.add(neighbor);
                        } 
                        else if (colors[neighbor] == colors[curr]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == 0) {
                if (!dfs(graph, colors, neighbor, -color)) {
                    return false;
                }
            } else if (colors[neighbor] == colors[node]) {
                return false;
            }
        }

        return true;
    }
}