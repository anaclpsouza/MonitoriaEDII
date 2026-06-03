    package BFS-BeeCrowd2429;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.Queue;

    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();
            if (line == null) return;

            int N = Integer.parseInt(line.trim());

            // Inicializa a lista de adjacência para o grafo direcionado
            ArrayList<ArrayList<Integer>> grafo = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                grafo.add(new ArrayList<>());
            }

            // Vetor para monitorar o grau de entrada de cada cidade
            int[] grauEntrada = new int[N + 1];

            // Leitura das N rodovias
            for (int i = 0; i < N; i++) {
                String[] partes = reader.readLine().trim().split("\\s+");
                int u = Integer.parseInt(partes[0]);
                int v = Integer.parseInt(partes[1]);

                grafo.get(u).add(v); // Rodovia de mão única: U -> V
                grauEntrada[v]++;    // V recebe uma rodovia vinda de U
            }

            // Primeiramente, valida se existe alguma cidade sem rodovia chegando
            boolean estruturaValida = true;
            for (int i = 1; i <= N; i++) {
                if (grauEntrada[i] != 1) {
                    estruturaValida = false;
                    break;
                }
            }

            // Se a contagem de graus falhou, nem precisamos rodar a busca
            if (!estruturaValida) {
                System.out.println("N");
                return;
            }

            // --- Execução da Busca em Largura (BFS) ---
            boolean[] visitado = new boolean[N + 1];
            Queue<Integer> fila = new LinkedList<>();

            // Começa a busca a partir da cidade 1
            fila.add(1);
            visitado[1] = true;
            int cidadesVisitadas = 1;

            while (!fila.isEmpty()) {
                int atual = fila.poll();

                // Varre os vizinhos direcionados da cidade atual
                for (int vizinho : grafo.get(atual)) {
                    if (!visitado[vizinho]) {
                        visitado[vizinho] = true;
                        cidadesVisitadas++;
                        fila.add(vizinho);
                    }
                }
            }

            // Se a BFS conseguiu tocar todas as N cidades, o grafo é fortemente conexo
            if (cidadesVisitadas == N) {
                System.out.println("S");
            } else {
                System.out.println("N");
            }
        }
    }