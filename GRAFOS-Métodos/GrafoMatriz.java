import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GrafoMatriz {
    private int[][] matriz;
    private int numVertices;

    public GrafoMatriz(int numVertices) {
        this.numVertices = numVertices;
        this.matriz = new int[numVertices][numVertices];
    }

    // Método auxiliar para adicionar aresta (só para testar)
    public void adicionarAresta(int origem, int destino, boolean direcionado) {
        matriz[origem][destino] = 1;
        if (!direcionado) {
            matriz[destino][origem] = 1;
        }
    }

    // 1. Retornar os vértices adjacentes ao vértice i
    public List<Integer> getAdjacentes(int i) {
        List<Integer> adjacentes = new ArrayList<>();
        for (int j = 0; j < numVertices; j++) {
            // Verifica se há aresta saindo de i para j ou chegando em i vindo de j
            if (matriz[i][j] > 0 || matriz[j][i] > 0) {
                // Evita adicionar o mesmo vértice duplicado em grafos direcionados
                if (!adjacentes.contains(j)) {
                    adjacentes.add(j);
                }
            }
        }
        return adjacentes;
    }

    // 2. Retornar o grau de saída de um vértice i
    public int getGrauSaida(int i) {
        int grauSaida = 0;
        for (int j = 0; j < numVertices; j++) {
            grauSaida += matriz[i][j];
        }
        return grauSaida;
    }

    // 3. Retornar o grau de entrada de um vértice i
    public int getGrauEntrada(int i) {
        int grauEntrada = 0;
        for (int j = 0; j < numVertices; j++) {
            grauEntrada += matriz[j][i];
        }
        return grauEntrada;
    }

    // 4. Retorna o grau de um vértice i (Total)
    public int getGrau(int i) {
        if (isDirecionado()) {
            return getGrauEntrada(i) + getGrauSaida(i);
        } else {
            // Em grafos não direcionados, laços (self-loops) contam duas vezes para o grau
            return getGrauSaida(i) + matriz[i][i];
        }
    }

    // 5. Retornar se um grafo é ou não direcionado
    public boolean isDirecionado() {
        // Um grafo é não-direcionado se sua matriz de adjacência for estritamente
        // simétrica
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return true; // Encontrou assimetria, logo é direcionado
                }
            }
        }
        return false;
    }

    // 6. Retornar se um grafo é completo
    public boolean isCompleto() {
        // Um grafo completo tem arestas entre todos os pares distintos de vértices
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j && matriz[i][j] == 0) {
                    return false; // Falta uma aresta entre i e j
                }
            }
        }
        return true;
    }

    // 7. Retornar se um grafo é regular
    public boolean isRegular() {
        if (numVertices == 0)
            return true;

        int grauPadrao = getGrau(0); // Pega o grau do primeiro vértice como referência

        for (int i = 1; i < numVertices; i++) {
            if (getGrau(i) != grauPadrao) {
                return false; // Encontrou um vértice com grau diferente
            }
        }
        return true;
    }

    // 8. Retornar o número de vértices isolados em um grafo
    public int contarVerticesIsolados() {
        int isolados = 0;
        for (int i = 0; i < numVertices; i++) {
            // Um vértice é isolado se o seu grau total for 0
            if (getGrau(i) == 0) {
                isolados++;
            }
        }
        return isolados;
    }

    // 9. Retornar se um grafo é simples
    public boolean isSimples() {
        // Um grafo simples não possui laços (self-loops) nem arestas múltiplas
        
        // Verifica laços (diagonal principal deve ser 0)
        if (temLaco()) return false;

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                // Verifica arestas múltiplas (valor maior que 1)
                if (matriz[i][j] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // 10. Retornar se o grafo possui algum ciclo
    public boolean temCiclo(boolean isDire) {
        boolean[] visitados = new boolean[numVertices];

        if (isDire) {
            boolean[] pilhaRecurso = new boolean[numVertices];
            for (int i = 0; i < numVertices; i++) {
                if (!visitados[i] && temCicloDirecionadoAux(i, visitados, pilhaRecurso)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < numVertices; i++) {
                // Passamos -1 como pai inicial, pois o primeiro vértice não tem pai
                if (!visitados[i] && temCicloNaoDirecionadoAux(i, visitados, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Auxiliar DFS para grafos Direcionados
    private boolean temCicloDirecionadoAux(int v, boolean[] visitados, boolean[] pilhaRecurso) {
        if (pilhaRecurso[v])
            return true; // Encontrou um vértice no caminho atual (Ciclo!)
        if (visitados[v])
            return false; // Já processado e limpo em outra iteração

        visitados[v] = true;
        pilhaRecurso[v] = true;

        for (int i = 0; i < numVertices; i++) {
            if (matriz[v][i] > 0) { // Se há aresta de saída
                if (temCicloDirecionadoAux(i, visitados, pilhaRecurso)) {
                    return true;
                }
            }
        }
        pilhaRecurso[v] = false; // Tira da pilha de recursão ao retroceder
        return false;
    }

    // Auxiliar DFS para grafos Não Direcionados
    private boolean temCicloNaoDirecionadoAux(int v, boolean[] visitados, int pai) {
        visitados[v] = true;

        for (int i = 0; i < numVertices; i++) {
            if (matriz[v][i] > 0) { // Se são adjacentes
                if (!visitados[i]) {
                    if (temCicloNaoDirecionadoAux(i, visitados, v)) {
                        return true;
                    }
                } else if (i != pai) {
                    // Se o vizinho já foi visitado e NÃO é o vértice de onde viemos (pai), é um
                    // ciclo
                    return true;
                }
            }
        }
        return false;
    }


    // 11. Verificar se o grafo é conexo (fortemente focado em não direcionados para
    // simplificar)
    public boolean isConexo() {
        if (numVertices == 0)
            return true;

        boolean[] visitados = new boolean[numVertices];
        Queue<Integer> fila = new LinkedList<>();

        // Começa a busca pelo vértice 0
        fila.add(0);
        visitados[0] = true;
        int contagemVisitados = 1;

        while (!fila.isEmpty()) {
            int atual = fila.poll();
            for (int i = 0; i < numVertices; i++) {
                // Em grafos não direcionados, olha a linha. Em direcionados precisaria ser mais
                // complexo.
                if ((matriz[atual][i] > 0 || matriz[i][atual] > 0) && !visitados[i]) {
                    visitados[i] = true;
                    fila.add(i);
                    contagemVisitados++;
                }
            }
        }
        // Se a busca a partir do vértice 0 alcançou todos, ele é conexo
        return contagemVisitados == numVertices;
    }

    // 12. Retorna se o grafo possui laços
    public boolean temLaco() {
        for (int i = 0; i < numVertices; i++) {
            // Verifica a diagonal principal
            if (matriz[i][i] > 0) {
                return true; // Encontrou um laço no vértice i
            }
        }
        return false; // Percorreu tudo e não achou nenhum laço
    }

    // 13. Imprimir a Matriz 
    public void imprimirMatriz() {
        System.out.println("Matriz de Adjacências:");
        System.out.print("   ");
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < numVertices; j++) {
                System.out.printf("[%d]", matriz[i][j]);
            }
            System.out.println();
        }
    }
}