import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String linha = br.readLine();
            if (linha == null) break;
            
            String[] partes = linha.trim().split("\\s+");
            if (partes.length < 1 || partes[0].isEmpty()) continue;
            
            int n = Integer.parseInt(partes[0]);
            if (n == 0) break;
            
            int m = Integer.parseInt(partes[1]);
            
            double[][] pesos = new double[n + 1][n + 1];
            
            for (int i = 1; i <= n; i++) {
                pesos[i][i] = 1.0;
            }
            
            for (int i = 0; i < m; i++) {
                String[] aresta = br.readLine().trim().split("\\s+");
                int u = Integer.parseInt(aresta[0]);
                int v = Integer.parseInt(aresta[1]);
                double p = Double.parseDouble(aresta[2]) / 100.0; 
                
                pesos[u][v] = p;
                pesos[v][u] = p;
            }
            
            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (pesos[i][k] * pesos[k][j] > pesos[i][j]) {
                            pesos[i][j] = pesos[i][k] * pesos[k][j];
                        }
                    }
                }
            }
            
            double resultadoPorcentagem = pesos[1][n] * 100.0;
            
            System.out.printf("%.6f percent\n", resultadoPorcentagem);
        }
    }
}