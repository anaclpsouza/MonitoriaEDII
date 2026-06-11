import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int t = sc.nextInt(); 
        
        while (t-- > 0) {
            int v = sc.nextInt();
            int e = sc.nextInt();
            
            List<List<int[]>> grafo = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                grafo.add(new ArrayList<>());
            }
            
            for (int i = 0; i < e; i++) {
                int de = sc.nextInt();
                int para = sc.nextInt();
                int peso = sc.nextInt();
                grafo.get(de).add(new int[]{para, peso});
            }
            
            int origem = sc.nextInt();
            int destino = sc.nextInt();
            
            int[] dist = new int[v + 1];
            boolean[] visitado = new boolean[v + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[origem] = 0;
            
            for (int i = 1; i <= v; i++) {
                int u = -1;
                int menorDist = Integer.MAX_VALUE;
                for (int j = 1; j <= v; j++) {
                    if (!visitado[j] && dist[j] < menorDist) {
                        menorDist = dist[j];
                        u = j;
                    }
                }

                if (u == -1 || u == destino) break;
                
                visitado[u] = true;

                for (int[] vizinho : grafo.get(u)) {
                    int nxt = vizinho[0];
                    int peso = vizinho[1];
                    if (!visitado[nxt] && dist[u] + peso < dist[nxt]) {
                        dist[nxt] = dist[u] + peso;
                    }
                }
            }
            
            if (dist[destino] == Integer.MAX_VALUE) {
                System.out.println("NO");
            } else {
                System.out.println(dist[destino]);
            }
        }
        sc.close();
    }
}