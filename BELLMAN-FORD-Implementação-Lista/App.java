import java.util.List;

class App {

    public static void main(String[] args) {

    }

    public boolean bellmanFord(int n, List<List<Vizinho>> grafo, int origem) {
        int[] distancias = new int[n];
        int[] rota = new int[n];

        for (int i = 0; i < n; i++) {
            distancias[i] = Integer.MAX_VALUE;
            rota[i] = -1;
        }
        distancias[origem] = 0;

        for (int k = 0; k < n; k++) {
            boolean altera = false;

            for (int u = 0; u < n; u++) {

                if (distancias[u] == Integer.MAX_VALUE)
                    continue;

                for (Vizinho vizinho : grafo.get(u)) {
                    int v = vizinho.destino;
                    int peso = vizinho.peso;

                    if (distancias[v] > distancias[u] + peso) {
                        distancias[v] = distancias[u] + peso;
                        rota[v] = u;
                        altera = true;
                    }
                }
            }

            if (!altera) {
                break;
            }

            if (k == n && altera) {
                return true;
            }
        }
        return false;
    }
}

class Vizinho {
    int destino;
    int peso;

    public Vizinho(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}
