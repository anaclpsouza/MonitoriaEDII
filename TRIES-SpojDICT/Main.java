import java.io.*;
import java.util.*;

class TrieNo {
    TrieNo[] filhos = new TrieNo[26]; 
    boolean isChave = false;
}

class Trie {
    TrieNo raiz = new TrieNo(); 

    public void inserir(String palavra) {
        TrieNo atual = raiz;
        for (char c : palavra.toCharArray()) {
            int idx = c - 'a';
            if (atual.filhos[idx] == null) {
                atual.filhos[idx] = new TrieNo();
            }
            atual = atual.filhos[idx];
        }
        atual.isChave = true; 
    }

    public void buscarPrefixos(String prefixo, PrintWriter out) {
        TrieNo atual = raiz;
        for (char c : prefixo.toCharArray()) {
            int idx = c - 'a';
            if (atual.filhos[idx] == null) {
                out.println("No match.");
                return;
            }
            atual = atual.filhos[idx];
        }

        boolean encontrou = false;
        // Proper prefix: começamos pelos filhos do nó do prefixo
        for (int i = 0; i < 26; i++) {
            if (atual.filhos[i] != null) {
                encontrou = true;
                // Usamos um array de char como buffer para evitar criar muitas Strings
                char[] buffer = new char[40]; 
                System.arraycopy(prefixo.toCharArray(), 0, buffer, 0, prefixo.length());
                dfs(atual.filhos[i], prefixo.length(), (char)(i + 'a'), buffer, out);
            }
        }

        if (!encontrou) out.println("No match.");
    }

    // DFS otimizada usando buffer de caracteres [cite: 263-265]
    private void dfs(TrieNo no, int nivel, char c, char[] buffer, PrintWriter out) {
        buffer[nivel] = c;
        if (no.isChave) {
            out.println(new String(buffer, 0, nivel + 1));
        }

        for (int i = 0; i < 26; i++) {
            if (no.filhos[i] != null) {
                dfs(no.filhos[i], nivel + 1, (char)(i + 'a'), buffer, out);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st;

        String linha = br.readLine();
        if (linha == null) return;
        
        int n = Integer.parseInt(linha.trim());
        Trie trie = new Trie();
        for (int i = 0; i < n; i++) {
            trie.inserir(br.readLine().trim());
        }

        int k = Integer.parseInt(br.readLine().trim());
        for (int i = 1; i <= k; i++) {
            String prefixo = br.readLine().trim();
            out.println("Case #" + i + ":");
            trie.buscarPrefixos(prefixo, out);
        }
        
        out.flush(); 
        out.close();
    }
}