import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> grafo;
    static int[] id, menorNo, compId;
    static boolean[] naPilha;
    static Stack<Integer> pilha;
    static int count, compCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            line = line.trim();
            if (line.isEmpty()) continue;
            
            N = Integer.parseInt(line);
            if (N == 0) break; // Condição de parada do enunciado

            grafo = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                grafo.add(new ArrayList<>());
            }

            // Leitura da matriz binária
            for (int i = 0; i < N; i++) {
                String linha = br.readLine().trim().replace(" ", ""); 
                for (int j = 0; j < N; j++) {
                    if (linha.charAt(j) == '1') {
                        grafo.get(i).add(j); 
                    }
                }
            }

            // Executando o Algoritmo de Tarjan
            tarjan();

            // Calculando o grau de entrada de cada SCC
            int[] grauEntradaComp = new int[compCount];
            for (int u = 0; u < N; u++) {
                for (int v : grafo.get(u)) {
                    // Se a aresta vai para um SCC diferente, incrementa o grau de entrada dele
                    if (compId[u] != compId[v]) {
                        grauEntradaComp[compId[v]]++;
                    }
                }
            }

            // Conta quantos SCCs têm grau de entrada zero
            int minPessoas = 0;
            for (int i = 0; i < compCount; i++) {
                if (grauEntradaComp[i] == 0) {
                    minPessoas++;
                }
            }

            System.out.println(minPessoas);
        }
    }

    private static void tarjan() {
        id = new int[N];
        menorNo = new int[N];
        compId = new int[N];
        naPilha = new boolean[N];
        pilha = new Stack<>();
        Arrays.fill(id, -1); // -1 indica que o vértice não foi visitado
        
        count = 0;
        compCount = 0;

        for (int i = 0; i < N; i++) {
            if (id[i] == -1) {
                tarjanDFS(i);
            }
        }
    }

    private static void tarjanDFS(int u) {
        id[u] = menorNo[u] = count++;
        pilha.push(u);
        naPilha[u] = true;

        for (int v : grafo.get(u)) {
            if (id[v] == -1) { // Vizinho não visitado
                tarjanDFS(v);
                menorNo[u] = Math.min(menorNo[u], menorNo[v]);
            } else if (naPilha[v]) { // Vizinho está na pilha (faz parte do ciclo atual)
                menorNo[u] = Math.min(menorNo[u], id[v]);
            }
        }

        // Se u é uma raiz de SCC, desempilha todos os componentes dele
        if (id[u] == menorNo[u]) {
            while (true) {
                int node = pilha.pop();
                naPilha[node] = false;
                compId[node] = compCount; // Atribui o ID do componente ao vértice
                if (node == u) break;
            }
            compCount++;
        }
    }
}