import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Lista[] grafo;
    static boolean[] visitados;
    static ArrayList<Integer> componentes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numCasos = sc.nextInt();

        for (int i = 1; i <= numCasos; i++) {
            int v = sc.nextInt();
            int e = sc.nextInt();

            grafo = new Lista[v];
            for (int j = 0; j < v; j++) {
                grafo[j] = new Lista();
            }

            visitados = new boolean[v];

            for (int j = 0; j < e; j++) {
                char origem = sc.next().charAt(0);
                char destino = sc.next().charAt(0);

                int u = origem - 'a';
                int w = destino - 'a';

                grafo[u].add(w);
                grafo[w].add(u);
            }

            System.out.println("Case #" + i + ":");
            int numComponentes = 0;

            for (int j = 0; j < v; j++) {
                if (!visitados[j]) {
                    componentes = new ArrayList<>();

                    dfs(j);

                    Collections.sort(componentes);

                    for (int vertice : componentes) {
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

    private static void dfs(int j) {
        visitados[j] = true;
        componentes.add(j);

        No atual = grafo[j].cabeca;

        while (atual != null) {

            if (!visitados[atual.no]) {
                dfs(atual.no);
            }

            atual = atual.proximo;
        }
    }
}

class No {
    int no;
    No proximo;

    public No(int num) {
        this.no = num;
        this.proximo = null;
    }

    public No() {
        this.no = 0;
        this.proximo = null;
    }
}

class Lista {
    No cabeca = new No();
    No cauda = cabeca;

    public void add(int num) {
        No no = new No(num);

        cauda.proximo = no;
        cauda = no;
    }

    public int get(int i) {
        No atual = cabeca;
        int count = 0;

        while (atual.proximo != null) {
            if (count == i) {
                return atual.no;
            }
            atual = atual.proximo;
            count++;
        }
        return 0;
    }
}