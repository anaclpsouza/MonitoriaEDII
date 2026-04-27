import java.util.ArrayList;
import java.util.List;

public class Trie {
    TrieNo raiz = new TrieNo();

    public void adicionar(String chave) {
        TrieNo atual = raiz;

        for (char c : chave.toCharArray()) {
            int i = c - 'a';

            if (atual.filhos[i] == null) {
                atual.filhos[i] = new TrieNo();
            }

            atual = atual.filhos[i];
        }

        atual.isChave = true;
    }

    public void excluir(String chave) {
        excluir(raiz, chave, 0);
    }

    private static boolean excluir(TrieNo atual, String chave, int index) {

        if (atual == null) {
            return false;
        }

        if (index == chave.length()) {
            atual.isChave = false;
            return isVazio(atual);
        }

        int i = chave.charAt(index) - 'a';

        if (excluir(atual.filhos[i], chave, index + 1)) {
            atual.filhos[i] = null;

            return !atual.isChave && isVazio(atual);
        }

        return false;
    }

    private static boolean isVazio(TrieNo atual) {
        return atual.filhos == null;
    }

    public boolean possui(String chave) {
        return possui(raiz, chave, 0);
    }

    private static boolean possui(TrieNo atual, String chave, int index) {
        if (atual == null) {
            return false;
        }

        if (index == chave.length()) {
            return atual.isChave;
        }

        int i = chave.charAt(index) - 'a';
        return possui(atual.filhos[i], chave, index + 1);
    }

    public void print() {
        print(raiz, "");
    }

    private static void print(TrieNo atual, String chave) {
        if (atual == null) {
            return;
        }

        if (atual.isChave) {
            System.out.println(chave);
        }

        for (int i = 0; i < 26; i++) {
            if (atual.filhos[i] != null) {
                char letra = (char) (i + 'a');
                print(atual.filhos[i], chave+letra);
            }
        }
    }

    public List<String> buscarPorPrefixo(String prefixo) {
        List<String> resultados = new ArrayList<>();
        TrieNo atual = raiz;

        for (char c : prefixo.toCharArray()) {
            int i = c - 'a';

            if (atual.filhos[i] == null) {
                return resultados;
            }

            atual = atual.filhos[i];
        }

        coletar(atual, prefixo, resultados);
        return resultados;
    }

    private void    coletar(TrieNo atual, String palavraAtual, List<String> resultados) {
        if (atual == null) {
            return;
        }

        if (atual.isChave) {
            resultados.add(palavraAtual);
        }

        for (int i = 0; i < 26; i++) {
            if (atual.filhos[i] != null) {
                char letra = (char) (i +  'a');
                coletar(atual.filhos[i], palavraAtual + letra, resultados);
            }
        }
    }
}
