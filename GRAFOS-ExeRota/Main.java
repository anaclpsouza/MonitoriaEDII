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
    int tamanho = 0;

    public void addLast(int num) {
        No no = new No(num);
        cauda.proximo = no;
        cauda = no;
        tamanho++;
    }

    public void addFirst(int num) {
        No no = new No(num);
        no.proximo = cabeca.proximo;
        cabeca.proximo = no;
        if (cauda == cabeca) {
            cauda = no;
        }
        tamanho++;
    }

    public int get(int i) {
        No atual = cabeca.proximo;
        int count = 0;

        while (atual != null) {
            if (count == i) {
                return atual.no;
            }
            atual = atual.proximo;
            count++;
        }
        return -1;
    }

    public int getFirst() {
        return cabeca.proximo != null ? cabeca.proximo.no : -1;
    }

    public int getLast() {
        return cauda.no;
    }

    public int size() {
        return tamanho;
    }
}


public class Main {
    public static void main(String[] args) {
        // Índice: 0:MNU, 1:BH, 2:SP, 3:RJ, 4:FL, 5:VT, 6:CU, 7:PO
        String[] cidades = { "MNU", "BH", "SP", "RJ", "VT", "FL", "CU", "PO" };
        int totalCidades = cidades.length;

        int[][] distancias = {
                { 0, 283, 748, 430, 234, 1449, 1155, 1884 },
                { 283, 0, 584, 441, 515, 1344, 1208, 1809 },
                { 748, 584, 0, 446, 938, 709, 419, 1144 },
                { 430, 441, 446, 0, 517, 1136, 842, 1571 },
                { 234, 515, 938, 517, 0, 1638, 1345, 2073 },
                { 1449, 1344, 709, 1136, 1638, 0, 306, 463 },
                { 1155, 1208, 419, 842, 1345, 306, 0, 741 },
                { 1884, 1809, 1144, 1571, 2073, 463, 741, 0 }
        };

        boolean[] visitadas = new boolean[totalCidades];
        Lista rota = new Lista();

        rota.addLast(0);
        visitadas[0] = true;

        // Construção da Rota Gulosa
        while (rota.size() < totalCidades) {
            int extEsquerda = rota.getFirst();
            int extDireita = rota.getLast();

            int menorDist = Integer.MAX_VALUE;
            int melhorCidade = -1;
            boolean inserirNaEsquerda = false;

            for (int cidade = 0; cidade < totalCidades; cidade++) {
                if (!visitadas[cidade]) {
                    if (distancias[extDireita][cidade] < menorDist) {
                        menorDist = distancias[extDireita][cidade];
                        melhorCidade = cidade;
                        inserirNaEsquerda = false;
                    }
                    if (distancias[cidade][extEsquerda] < menorDist) {
                        menorDist = distancias[cidade][extEsquerda];
                        melhorCidade = cidade;
                        inserirNaEsquerda = true;
                    }
                }
            }

            if (inserirNaEsquerda) {
                rota.addFirst(melhorCidade);
            } else {
                rota.addLast(melhorCidade);
            }

            visitadas[melhorCidade] = true;
        }

        // Cálculo da distância
        int distanciaTotal = 0;
        for (int i = 0; i < rota.size() - 1; i++) {
            distanciaTotal += distancias[rota.get(i)][rota.get(i + 1)];
        }

        // Soma a volta para fechar o ciclo
        distanciaTotal += distancias[rota.getLast()][rota.getFirst()];

        // 1. Descobrir o índice onde MNU (0) foi parar na lista
        int idxMnu = -1;
        for (int i = 0; i < rota.size(); i++) {
            if (rota.get(i) == 0) {
                idxMnu = i;
                break;
            }
        }

        // 2. Imprimir de forma circular começando por idxMnu
        StringBuilder rotaStr = new StringBuilder();
        for (int i = 0; i < rota.size(); i++) {
            int idxReal = (idxMnu + i) % rota.size();
            rotaStr.append(cidades[rota.get(idxReal)]).append(" -> ");
        }
        
        // Fecha anexando o primeiro novamente (MNU)
        rotaStr.append(cidades[0]);

        System.out.println("\n[RESULTADO GULOSO]");
        System.out.println("Rota a ser seguida: " + rotaStr.toString());
        System.out.println("Quilometragem percorrida: " + distanciaTotal + " km");
    }
}