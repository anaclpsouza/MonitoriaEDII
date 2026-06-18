import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Aresta {
    int destino, peso;
    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

public class Main {

    static final int INF = 999999999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<List<Aresta>> grafo = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                grafo.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                grafo.get(u).add(new Aresta(v, w));
                grafo.get(v).add(new Aresta(u, w));
            }

            int q = sc.nextInt();
            
            for (int i = 0; i < q; i++) {
                int origem = sc.nextInt();
                int destino = sc.nextInt();
                int k = sc.nextInt();

                int[] dist = new int[n + 1];
                boolean[] visitado = new boolean[n + 1];
                Arrays.fill(dist, INF);
                dist[origem] = 0;

                for (int j = 1; j <= n; j++) {
                    int u = -1;
                    int menorDist = INF;
                    for (int v = 1; v <= n; v++) {
                        if (!visitado[v] && dist[v] < menorDist) {
                            menorDist = dist[v];
                            u = v;
                        }
                    }

                    if (u == -1) break;

                    visitado[u] = true;

                    if (u == destino) break;

                    if (u > k && u != origem) continue;

                    // 3. Relaxamento das arestas
                    for (Aresta aresta : grafo.get(u)) {
                        int v = aresta.destino;
                        int peso = aresta.peso;

                        if (dist[u] + peso < dist[v]) {
                            dist[v] = dist[u] + peso;
                        }
                    }
                }

                if (dist[destino] == INF) {
                    System.out.println("-1");
                } else {
                    System.out.println(dist[destino]);
                }
            }
        }
        sc.close();
    }
}