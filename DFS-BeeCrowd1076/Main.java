import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        if (line == null) return;
        
        int T = Integer.parseInt(line.trim()); 

        while (T > 0) {
            T--;
            int inicio = Integer.parseInt(reader.readLine().trim());
            
            // Divide a linha usando qualquer quantidade de espaços em branco como separador
            String[] partesVA = reader.readLine().trim().split("\\s+");
            int V = Integer.parseInt(partesVA[0]); 
            int A = Integer.parseInt(partesVA[1]); 
            
            boolean[][] matrizAdjacencia = new boolean[V][V];
            int arestasUnicas = 0;

            for (int i = 0; i < A; i++) {
                String[] partesAresta = reader.readLine().trim().split("\\s+");
                int u = Integer.parseInt(partesAresta[0]);
                int v = Integer.parseInt(partesAresta[1]);
                
                if (!matrizAdjacencia[u][v]) {
                    matrizAdjacencia[u][v] = true;
                    matrizAdjacencia[v][u] = true;
                    arestasUnicas++;
                }
            }
            System.out.println(arestasUnicas * 2);
        }
    }
}