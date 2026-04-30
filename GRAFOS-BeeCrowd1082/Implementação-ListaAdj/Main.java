import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    
    // Lista de Adjacências para representar o grafo
    static ArrayList<ArrayList<Integer>> grafo;
    static boolean[] visitados;
    static ArrayList<Integer> componenteAtual;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numCasos = sc.nextInt();

        for (int caso = 1; caso <= numCasos; caso++) {
            int v = sc.nextInt(); // Número de vértices
            int e = sc.nextInt(); // Número de arestas

            // Inicializando a Lista de Adjacências
            grafo = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                grafo.add(new ArrayList<>());
            }
            visitados = new boolean[v];

            // Lendo as arestas
            for (int i = 0; i < e; i++) {
                char origem = sc.next().charAt(0);
                char destino = sc.next().charAt(0);

                // transformando letra minúscula em índice (0 a 25)
                int u = origem - 'a';
                int w = destino - 'a';

                // Como é um Grafo Não Direcionado, a conexão vai e volta
                grafo.get(u).add(w);
                grafo.get(w).add(u);
            }

            System.out.println("Case #" + caso + ":");
            int numComponentes = 0;

            // Varrendo todos os vértices possíveis do grafo
            for (int i = 0; i < v; i++) {
                // Se acharmos um vértice não visitado, descobrimos um novo componente
                if (!visitados[i]) {
                    componenteAtual = new ArrayList<>();
                    
                    // Dispara a DFS para "infectar" todos os vértices conectados a ele
                    dfs(i);

                    // O Beecrowd exige que os vértices do componente sejam impressos em ordem alfabética
                    Collections.sort(componenteAtual);
                    
                    for (int vertice : componenteAtual) {
                        // Transformando o índice de volta em letra
                        System.out.print((char) (vertice + 'a') + ",");
                    }
                    System.out.println(); // Quebra a linha ao terminar de imprimir o componente
                    
                    numComponentes++;
                }
            }
            // Imprime o total de componentes e uma quebra de linha extra exigida pelo Beecrowd
            System.out.println(numComponentes + " connected components\n");
        }
        
        sc.close();
    }

    // Função de Busca em Profundidade (DFS)
    private static void dfs(int no) {
        visitados[no] = true;
        componenteAtual.add(no); // Guarda o vértice atual na lista deste componente

        // Olha para todos os vizinhos conectados ao nó atual na Lista de Adjacências
        for (int vizinho : grafo.get(no)) {
            if (!visitados[vizinho]) {
                dfs(vizinho); // Se o vizinho não foi visitado, entra nele
            }
        }
    }
}