import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Vizinho {
    int destino;
    double probabilidade;

    public Vizinho(int destino, double probabilidade) {
        this.destino = destino;
        this.probabilidade = probabilidade;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String linha;

        while ((linha = br.readLine()) != null) {

            String[] partes = linha.trim().split(" ");
            
            int n = Integer.parseInt(partes[0]);
            
            if (n == 0) break; 

            int m = Integer.parseInt(partes[1]);

            List<List<Vizinho>> grafo = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                grafo.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                String[] partesAresta = br.readLine().trim().split(" ");
                
                int u = Integer.parseInt(partesAresta[0]);
                int v = Integer.parseInt(partesAresta[1]);
                double p = Double.parseDouble(partesAresta[2]) / 100.0; 

                grafo.get(u).add(new Vizinho(v, p));
                grafo.get(v).add(new Vizinho(u, p));
            }

            // --- BELLMAN-FORD ---
            double[] prob = new double[n + 1];
            prob[1] = 1.0; // 100% de chance na origem

            for (int k = 0; k < n - 1; k++) {
                boolean altera = false;

                for (int u = 1; u <= n; u++) {
                    if (prob[u] == 0.0) continue;

                    for (Vizinho vizinho : grafo.get(u)) {
                        int v = vizinho.destino;
                        double peso = vizinho.probabilidade;

                        if (prob[v] < prob[u] * peso) {
                            prob[v] = prob[u] * peso;
                            altera = true;
                        }
                    }
                }

                if (!altera) {
                    break;
                }
            }

            System.out.printf("%.6f percent\n", prob[n] * 100.0);
        }
    }
}