package versaoalternativa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static int V;
    private static boolean[][] grafo;
    private static boolean[] visitado;
    private static int movimentos;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null) return;
        
        int T = Integer.parseInt(line.trim()); 

        while (T > 0) {
            T--;
            int inicio = Integer.parseInt(reader.readLine().trim());
            
            String[] partesVA = reader.readLine().trim().split("\\s+");
            V = Integer.parseInt(partesVA[0]); 
            int A = Integer.parseInt(partesVA[1]); 
            
            // 1. Etapa de Construção: Inicializa as estruturas do grafo
            grafo = new boolean[V][V];
            visitado = new boolean[V];
            movimentos = 0;

            // Alimenta a matriz de adjacência (Grafo não direcionado)
            for (int i = 0; i < A; i++) {
                String[] partesAresta = reader.readLine().trim().split("\\s+");
                int u = Integer.parseInt(partesAresta[0]);
                int v = Integer.parseInt(partesAresta[1]);
                
                grafo[u][v] = true;
                grafo[v][u] = true;
            }

            // 2. Etapa de Processamento: Dispara a busca a partir do vértice inicial
            dfs(inicio);

            // Imprime o total acumulado de passos (Idas + Voltas)
            System.out.println(movimentos);
        }
    }

    // Algoritmo clássico de Busca em Profundidade (DFS)
    private static void dfs(int u) {
        // Marca o vértice atual como visitado logo na entrada
        visitado[u] = true;

        // Varre todos os possíveis vizinhos do vértice u
        for (int v = 0; v < V; v++) {
            // Se existe uma aresta entre u e v
            if (grafo[u][v]) {
                // E se o vizinho v ainda não foi explorado nesta busca
                if (!visitado[v]) {
                    movimentos++; // Movimento de IDA para o vizinho v
                    
                    dfs(v);       // Chamada recursiva para explorar a vizinhança de v
                    
                    movimentos++; // Movimento de VOLTA (Backtracking) ao retornar para u
                }
            }
        }
    }
}
