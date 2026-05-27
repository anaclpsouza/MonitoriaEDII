class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visitados = new boolean[n];
        int numProvincias = 0;

        // Passa por cada cidade do grafo
        for (int i = 0; i < n; i++) {
            // Se a cidade ainda não foi explorada, encontramos uma nova província (componente conexo)
            if (!visitados[i]) {
                numProvincias++;
                // Dispara a busca para marcar todas as cidades conectadas a ela
                dfs(isConnected, visitados, i);
            }
        }

        return numProvincias;
    }

    private void dfs(int[][] isConnected, boolean[] visitados, int atual) {
        // Marca a cidade atual como visitada
        visitados[atual] = true;

        // Varre a linha da matriz para encontrar os vizinhos (cidades conectadas)
        for (int vizinho = 0; vizinho < isConnected.length; vizinho++) {
            // Se há conexão direta E o vizinho ainda não foi visitado
            if (isConnected[atual][vizinho] == 1 && !visitados[vizinho]) {
                dfs(isConnected, visitados, vizinho);
            }
        }
    }
}