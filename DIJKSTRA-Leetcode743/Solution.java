import java.util.ArrayList;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            
            graph.get(u).add(v); // vizinho
            graph.get(u).add(w); // peso 
        }

        int count = n;
        long[] dist = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[k] = 0; 

        boolean[] visitados = new boolean[n + 1];

        while (count > 0) {
            count--;
            long min = Integer.MAX_VALUE;
            int v = -1;
            
            for (int i = 1; i <= n; i++) {
                if (!visitados[i] && dist[i] != Integer.MAX_VALUE && dist[i] < min) {
                    min = dist[i];
                    v = i;
                }
            }

            if (v == -1) {
                break;
            }

            visitados[v] = true;
            
            ArrayList<Integer> vizinhosEPesos = graph.get(v);
            

            for (int i = 0; i < vizinhosEPesos.size(); i += 2) {
                int u = vizinhosEPesos.get(i);      
                int peso = vizinhosEPesos.get(i + 1); 
                
                if (!visitados[u]) {
                    if (dist[v] + peso < dist[u]) {
                        dist[u] = dist[v] + peso;
                    }
                }
            }
        }

        long maxDelay = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxDelay = Math.max(maxDelay, dist[i]);
        }

        return (int) maxDelay;
    }
}