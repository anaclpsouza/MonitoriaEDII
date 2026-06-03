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
        int N = Integer.parseInt(partesNM[0]); 
        int M = Integer.parseInt(partesNM[1]); 

        ArrayList<ArrayList<Integer>> grafo = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            grafo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] partesAresta = reader.readLine().trim().split("\\s+");
            int u = Integer.parseInt(partesAresta[0]);
            int v = Integer.parseInt(partesAresta[1]);

            grafo.get(u).add(v);
            grafo.get(v).add(u);
        }

        boolean[] visitado = new boolean[N + 1];
        int totalFamilias = 0;

        for (int i = 1; i <= N; i++) {
            if (!visitado[i]) {
                totalFamilias++;
                Queue<Integer> fila = new LinkedList<>();
                fila.add(i);
                visitado[i] = true;

                while (!fila.isEmpty()) {
                    int atual = fila.poll();
                    for (int parente : grafo.get(atual)) {
                        if (!visitado[parente]) {
                            visitado[parente] = true;
                            fila.add(parente); 
                        }
                    }
                }
            }
        }

        System.out.println(totalFamilias);
    }
}
