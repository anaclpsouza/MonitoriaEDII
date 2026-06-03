import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static int V;
    private static boolean[][] grafo;
    private static boolean[] visitado;
    private static boolean temSaida;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null) return;

        int N = Integer.parseInt(line.trim()); 

        for (int caso = 1; caso <= N; caso++) {
            String[] partesVE = reader.readLine().trim().split("\\s+");
            V = Integer.parseInt(partesVE[0]);
            int E = Integer.parseInt(partesVE[1]);

            grafo = new boolean[V][V];
            visitado = new boolean[V];

            for (int i = 0; i < E; i++) {
                String[] partesAresta = reader.readLine().trim().split("\\s+");
                int u = Integer.parseInt(partesAresta[0]);
                int v = Integer.parseInt(partesAresta[1]);
                grafo[u][v] = true; 
            }

            // Imprime o cabeçalho do caso
            System.out.println("Caso " + caso + ":");
            
            for (int i = 0; i < V; i++) {
                if (!visitado[i]) {
                    temSaida = false;
                    sb = new StringBuilder();
                    
                    // Executa a busca a partir da raiz do componente (começa com 2 espaços)
                    dfs(i, 2); 
                    
                    // Se este componente gerou alguma aresta impressa
                    if (temSaida) {
                        System.out.print(sb.toString());
                        System.out.println(); // Força a linha em branco IMEDIATAMENTE após o segmento
                    }
                }
            }
        }
    }

    private static void dfs(int u, int espaços) {
        visitado[u] = true;
        
        // Gera a string de recuo de forma simples
        StringBuilder recuo = new StringBuilder();
        for (int i = 0; i < espaços; i++) {
            recuo.append(" ");
        }

        for (int v = 0; v < V; v++) {
            if (grafo[u][v]) {
                temSaida = true;
                if (!visitado[v]) {
                    sb.append(recuo).append(u).append("-").append(v).append(" pathR(G,").append(v).append(")\n");
                    dfs(v, espaços + 2);
                } else {
                    sb.append(recuo).append(u).append("-").append(v).append("\n");
                }
            }
        }
    }
}
