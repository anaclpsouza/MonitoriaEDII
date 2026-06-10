import java.util.ArrayList;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // 1. Inicializar o grafo exatamente como ArrayList<ArrayList<Integer>>
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // Preencher o grafo: a lista de cada nó terá o padrão [vizinho1, peso1, vizinho2, peso2...]
        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];
            
            graph.get(u).add(v); // Salva o vizinho
            graph.get(u).add(w); // Salva o peso logo em seguida
        }

        // 2. Configurações iniciais do Dijkstra
        int count = n;
        long[] dist = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[k] = 0; // Começa no nó k

        boolean[] visitados = new boolean[n + 1];

        // 3. Loop de busca linear (Sem Fila de Prioridade)
        while (count > 0) {
            count--;
            long min = Integer.MAX_VALUE;
            int v = -1;
            
            // Busca o nó não visitado com menor distância
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
            
            // 4. Relaxamento adaptado para a lista sequencial [vizinho, peso, vizinho, peso...]
            ArrayList<Integer> vizinhosEPesos = graph.get(v);
            
            // O passo do loop pula de 2 em 2
            for (int i = 0; i < vizinhosEPesos.size(); i += 2) {
                int u = vizinhosEPesos.get(i);       // Índice par é o Vizinho
                int peso = vizinhosEPesos.get(i + 1); // Índice ímpar é o Peso
                
                if (!visitados[u]) {
                    if (dist[v] + peso < dist[u]) {
                        dist[u] = dist[v] + peso;
                    }
                }
            }
        }

        // 5. Verificar o resultado final
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