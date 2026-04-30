import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    
    static int[][] matrizGrafo;
    static boolean[] visitados;
    static ArrayList<Integer> componenteAtual;
    static int v; // Declarado globalmente para o DFS saber o tamanho da matriz

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int numCasos = sc.nextInt();

        for (int caso = 1; caso <= numCasos; caso++) {
            v = sc.nextInt(); 
            int e = sc.nextInt(); 

            // Inicializando a Matriz de Adjacências (v linhas por v colunas)
            matrizGrafo = new int[v][v];
            visitados = new boolean[v];

            for (int i = 0; i < e; i++) {
                char origem = sc.next().charAt(0);
                char destino = sc.next().charAt(0);

                int u = origem - 'a';
                int w = destino - 'a';

                // ida e volta
                matrizGrafo[u][w] = 1;
                matrizGrafo[w][u] = 1;
            }

            System.out.println("Case #" + caso + ":");
            int numComponentes = 0;

            for (int i = 0; i < v; i++) {
                if (!visitados[i]) {
                    componenteAtual = new ArrayList<>();
                    
                    dfs(i);

                    Collections.sort(componenteAtual);
                    
                    for (int vertice : componenteAtual) {
                        System.out.print((char) (vertice + 'a') + ",");
                    }
                    System.out.println(); 
                    
                    numComponentes++;
                }
            }
            System.out.println(numComponentes + " connected components\n");
        }
        
        sc.close();
    }

    private static void dfs(int no) {
        visitados[no] = true;
        componenteAtual.add(no); 

        // Na matriz, somos OBRIGADOS a olhar para todas as colunas (de 0 a v-1)
        for (int vizinho = 0; vizinho < v; vizinho++) {
            // Só entra na recursão se existir uma aresta (true) E se ainda não foi visitado
            if (matrizGrafo[no][vizinho] == 1 && !visitados[vizinho]) {
                dfs(vizinho);
            }
        }
    }
}
