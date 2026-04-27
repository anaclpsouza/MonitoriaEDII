import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        Trie trie = new Trie();

        for (int i = 0; i < n; ++i) {

            if (!sc.hasNextLine()) {
                break;
            }

            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) {
                i--;
                continue;
            }

            String[] partes = linha.split("\\s+", 2);
            String comando = partes[0];
            String chave = partes.length > 1 ? partes[1].trim() : "";

            if (comando.equals("ADD")) {
                trie.adicionar(chave);
            }

            if (comando.equals("GET")) {
                if (trie.buscaExata(chave)) {
                    System.out.println("Alvo localizado: " + chave + ". Iniciando purgo.");
                } else {
                    String prefixo = trie.encontrar(chave);

                    if (prefixo.isEmpty()) {
                        System.out.println("Nenhum registro correspondente.");
                    } else {
                        List<String> resultados = trie.buscarPorPrefixo(prefixo);
                        System.out.println("Alvo nao exato. Sugerindo suspeitos com inicio '" + prefixo + "':");
                        for (String nome : resultados) {
                            System.out.println(nome);
                        }
                    }
                }
            }
        }
        sc.close();
    }
}

class TrieNo {
    TrieNo[] filhos = new TrieNo[54];
    boolean isChave = false;
}

class Trie {
    TrieNo raiz = new TrieNo();

    private int charToIndex(char c) {
        if (c == ' ')
            return 0;
        if (c == '-')
            return 1;
        if (c >= 'A' && c <= 'Z')
            return c - 'A' + 2;
        if (c >= 'a' && c <= 'z')
            return c - 'a' + 28;
        return -1;
    }

    private char indexToChar(int i) {
        if (i == 0)
            return ' ';
        if (i == 1)
            return '-';
        if (i >= 2 && i <= 27)
            return (char) ('A' + (i - 2));
        if (i >= 28 && i <= 53)
            return (char) ('a' + (i - 28));
        return '\0';
    }

    public void adicionar(String chave) {
        TrieNo atual = raiz;

        for (char c : chave.toCharArray()) {
            int i = charToIndex(c);
            if (i == -1) {
                return;
            }

            if (atual.filhos[i] == null) {
                atual.filhos[i] = new TrieNo();
            }

            atual = atual.filhos[i];
        }

        atual.isChave = true;
    }

    public boolean buscaExata(String chave) {
        TrieNo atual = raiz;
        for (char c : chave.toCharArray()) {
            int i = charToIndex(c);
            if (i == -1 || atual.filhos[i] == null) {
                return false;
            }
            atual = atual.filhos[i];
        }
        return atual.isChave;
    }

    public String encontrar(String chave) {
        String retorno = "";
        TrieNo atual = raiz;
        for (char c : chave.toCharArray()) {
            int i = charToIndex(c);
            if (i == -1 || atual.filhos[i] == null) {
                return retorno;
            }
            retorno += c;
            atual = atual.filhos[i];
        }
        return retorno;
    }

    public List<String> buscarPorPrefixo(String prefixo) {
        List<String> resultados = new ArrayList<>();
        TrieNo atual = raiz;

        for (char c : prefixo.toCharArray()) {
            int i = charToIndex(c);
            if (i == -1) {
                return resultados;
            }

            if (atual.filhos[i] == null) {
                return resultados;
            }

            atual = atual.filhos[i];
        }

        coletar(atual, prefixo, resultados);
        return resultados;
    }

    private void coletar(TrieNo atual, String palavraAtual, List<String> resultados) {
        if (atual == null) {
            return;
        }

        if (atual.isChave) {
            resultados.add(palavraAtual);
        }

        for (int i = 0; i < 54; i++) {
            if (atual.filhos[i] != null) {
                char letra = indexToChar(i);
                coletar(atual.filhos[i], palavraAtual + letra, resultados);
            }
        }
    }
}
