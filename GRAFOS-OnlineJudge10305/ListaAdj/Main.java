package ListaAdj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n == 0 && m == 0) {
                break;
            }

            Lista[] grafo = new Lista[n];
            for (int i = 0; i < n; i++) {
                grafo[i] = new Lista();
            }

            int[] grau = new int[n];

            for (int i = 0; i < m; i++) {
                int j = sc.nextInt() - 1;
                int k = sc.nextInt() - 1;

                grafo[j].add(k);
                grau[k]++;
            }

            ordenacao(grafo, grau);
        }

        sc.close();
    }

    public static void ordenacao(Lista[] grafo, int[] grau) {
        Queue<Integer> fila = new LinkedList<>();
        ArrayList<Integer> ordem = new ArrayList<>();

        for (int i = 0; i < grafo.length; i++) {
            if (grau[i] == 0) {
                fila.add(i);
            }
        }

        while (!fila.isEmpty()) {
            int u = fila.remove();
            ordem.add(u);

            No atual = grafo[u].cabeca.proximo;

            while (atual != null) {
                int v = atual.no;
                grau[v]--;

                if (grau[v] == 0) {
                    fila.add(v);
                }

                atual = atual.proximo;
            }
        }

        for (int i = 0; i < ordem.size(); i++) {
            System.out.print((ordem.get(i) + 1) + " ");

        }

        System.out.println();
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
}