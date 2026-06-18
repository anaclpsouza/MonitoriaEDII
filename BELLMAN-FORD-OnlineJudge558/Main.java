import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String primeiraLinha = reader.readLine();

        if (primeiraLinha == null || primeiraLinha.trim().isEmpty()) {
            return;
        }

        int casosTeste = Integer.parseInt(primeiraLinha.trim());

        for (int i = 0; i < casosTeste; i++) {
            String[] linha = reader.readLine().trim().split("\\s+");
            int vertice = Integer.parseInt(linha[0]);
            int aresta = Integer.parseInt(linha[1]);

            List<List<Vizinho>> grafo = new ArrayList<>();

            for (int j = 0; j < vertice; j++) {
                grafo.add(new ArrayList<>());
            }

            for (int j = 0; j < aresta; j++) {
                linha = reader.readLine().trim().split("\\s+");
                int origem = Integer.parseInt(linha[0]);
                int destino = Integer.parseInt(linha[1]);
                int peso = Integer.parseInt(linha[2]);

                grafo.get(origem).add(new Vizinho(destino, peso));
            }

            verifica(grafo.size(), grafo);
        }
    }

    private static void verifica(int n, List<List<Vizinho>> grafo) {
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[0] = 0;

        for (int k = 0; k <= n; k++) {
            boolean altera = false;
            for (int i = 0; i < n; i++) {
                if (dist[i] == Integer.MAX_VALUE)
                    continue;

                for (Vizinho vizinho : grafo.get(i)) {
                    if (dist[vizinho.destino] > dist[i] + vizinho.peso) {
                        dist[vizinho.destino] = dist[i] + vizinho.peso;
                        altera = true;
                    }
                }
            }

            if (!altera)
                break;

            if (n == k && altera) {
                System.out.println("possible");
                return;
            }
        }

        System.out.println("not possible");
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