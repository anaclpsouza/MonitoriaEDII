import java.util.ArrayList;
import java.util.List;

public class App {

    /**
     * Origem -> Destino
     */
    public static List<Integer> obterFechoDireto(int[][] matriz, int verticeAlvo) {
        int n = matriz.length;
        boolean[] visitados = new boolean[n];
        int idx = verticeAlvo - 1; 

        // Dispara a DFS clássica a partir do vértice alvo
        dfsDireto(matriz, idx, visitados);

        // Monta a lista de resultados (excluindo o próprio vértice alvo)
        List<Integer> fechoDireto = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visitados[i] && i != idx) {
                fechoDireto.add(i + 1);
            }
        }
        return fechoDireto;
    }

    /**
     * Destino <- Origem
     */
    public static List<Integer> obterFechoIndireto(int[][] matriz, int verticeAlvo) {
        int n = matriz.length;
        boolean[] visitados = new boolean[n];
        int idx = verticeAlvo - 1;

        // Dispara a DFS modificada que caminha "na contramão"
        dfsIndireto(matriz, idx, visitados);

        List<Integer> fechoIndireto = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (visitados[i] && i != idx) {
                fechoIndireto.add(i + 1);
            }
        }
        return fechoIndireto;
    }

    // DFS que caminha no sentido normal do arco: linha fixa (atual), varia coluna (i)
    private static void dfsDireto(int[][] matriz, int atual, boolean[] visitados) {
        visitados[atual] = true;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[atual][i] == 1 && !visitados[i]) {
                dfsDireto(matriz, i, visitados);
            }
        }
    }

    // DFS que caminha no sentido inverso do arco: varia linha (i), coluna fixa (atual)
    private static void dfsIndireto(int[][] matriz, int atual, boolean[] visitados) {
        visitados[atual] = true;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][atual] == 1 && !visitados[i]) {
                dfsIndireto(matriz, i, visitados);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] grafo = new int[n][n];

        // Configurando arcos (Base 1 para facilitar a leitura)
        grafo[0][1] = 1; // 1 -> 2
        grafo[0][4] = 1; // 1 -> 5
        grafo[1][3] = 1; // 2 -> 4
        grafo[3][2] = 1; // 4 -> 3
        grafo[3][4] = 1; // 4 -> 5
        grafo[2][0] = 1; // 3 -> 1
        grafo[2][4] = 1; // 3 -> 5

        int alvo = 2;
        System.out.println("Analise para o Vértice " + alvo + ":");
        System.out.println("Fecho Transitivo Direto (Sucessores):   " + obterFechoDireto(grafo, alvo));
        System.out.println("Fecho Transitivo Indireto (Antecessores): " + obterFechoIndireto(grafo, alvo));
    }
}