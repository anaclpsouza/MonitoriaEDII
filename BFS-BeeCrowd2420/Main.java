package BFS-BeeCrowd2420;

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

        String[] partesNM = line.trim().split("\\s+");
        int N = Integer.parseInt(partesNM[0]); // Número de pessoas
        int M = Integer.parseInt(partesNM[1]); // Número de relações

        // Inicializa a lista de adjacência para o grafo não direcionado
        ArrayList<ArrayList<Integer>> grafo = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            grafo.add(new ArrayList<>());
        }

        // Leitura das M relações de parentesco
        for (int i = 0; i < M; i++) {
            String[] partesAresta = reader.readLine().trim().split("\\s+");
            int u = Integer.parseInt(partesAresta[0]);
            int v = Integer.parseInt(partesAresta[1]);

            // Grafo não direcionado (relação mútua)
            grafo.get(u).add(v);
            grafo.get(v).add(u);
        }

        boolean[] visitado = new boolean[N + 1];
        int totalFamilias = 0;

        // Laço principal para varrer todas as pessoas de 1 a N
        for (int i = 1; i <= N; i++) {
            // Se encontramos alguém que não foi visitado, achamos uma nova família!
            if (!visitado[i]) {
                totalFamilias++;
                
                // --- Execução da BFS para marcar toda a família de 'i' ---
                Queue<Integer> fila = new LinkedList<>();
                fila.add(i);
                visitado[i] = true;

                while (!fila.isEmpty()) {
                    int atual = fila.poll();

                    // Varre todos os parentes diretos da pessoa atual
                    for (int parente : grafo.get(atual)) {
                        if (!visitado[parente]) {
                            visitado[parente] = true;
                            fila.add(parente); // Adiciona na fila para buscar os parentes dele
                        }
                    }
                }
                // --- Fim da BFS para esta família ---
            }
        }

        // Imprime a quantidade de famílias independentes encontradas
        System.out.println(totalFamilias);
    }
} 
