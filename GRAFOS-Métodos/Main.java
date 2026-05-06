public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           CRIANDO E TESTANDO UM GRAFO            ");
        System.out.println("==================================================\n");

        GrafoMatriz grafo = new GrafoMatriz(6);

        grafo.adicionarAresta(0, 1, false);
        grafo.adicionarAresta(1, 2, false);
        grafo.adicionarAresta(2, 0, false); 
        grafo.adicionarAresta(2, 3, false); 
        grafo.adicionarAresta(3, 4, false);
        grafo.adicionarAresta(4, 4, false); 

        System.out.println("1. ESTRUTURA INTERNA");
        System.out.println("--------------------------------------------------");
        grafo.imprimirMatriz();
        System.out.println();

        System.out.println("2. PROPRIEDADES DO GRAFO");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-25s : %b\n", "É Direcionado?", grafo.isDirecionado());
        System.out.printf("%-25s : %b (falso devido ao laço no 4)\n", "É Simples?", grafo.isSimples());
        System.out.printf("%-25s : %b\n", "É Completo?", grafo.isCompleto());
        System.out.printf("%-25s : %b\n", "É Regular?", grafo.isRegular());
        System.out.printf("%-25s : %b (falso devido ao 5 isolado)\n", "É Conexo?", grafo.isConexo());
        System.out.printf("%-25s : %b (ciclo 0-1-2-0)\n", "Tem Ciclo?", grafo.temCiclo(false));
        System.out.printf("%-25s : %b (no vértice 4)\n", "Tem Laço?", grafo.temLaco());
        System.out.printf("%-25s : %d (vértice 5)\n", "Vértices Isolados", grafo.contarVerticesIsolados());
        System.out.println();

        // 3. Propriedades Locais (Análise de cada Vértice)
        System.out.println("3. ANÁLISE DOS VÉRTICES");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < 6; i++) {
            System.out.printf("Vértice %d | Grau: %d | Adjacentes: %s\n",
                    i, grafo.getGrau(i), grafo.getAdjacentes(i));
        }

        System.out.println("\n==================================================");
        System.out.println("                   TESTE CONCLUÍDO                ");
        System.out.println("==================================================");
    }
}
