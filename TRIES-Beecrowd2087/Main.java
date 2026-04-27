import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }

            Tries trie = new Tries();
            boolean ruim = false;

            for (int i = 0; i < n; i++) {
                String chave = sc.next();
                if (!ruim) {
                    if (!trie.incluir(chave)) {
                        ruim = true;
                    }
                }
            }

            System.out.println(ruim ? "Conjunto Ruim" : "Conjunto Bom");

        }
        sc.close();
    }
}

class Tries {
    TrieNo raiz = new TrieNo();

    public boolean incluir(String chave) {
        TrieNo atual = raiz;

        for (char c : chave.toCharArray()) {
            if (atual.isChave) {
                return false;
            }

            int i = c - 'a';
            if (atual.filho[i] == null) {
                atual.filho[i] = new TrieNo();
            }
            atual = atual.filho[i];
        }

        if (atual.isChave) {
            return false;
        }

        if (temFilho(atual)) {
            return false;
        }

        atual.isChave = true;
        return true;
    }

    private boolean temFilho(TrieNo no) {
        for (int i = 0; i < 26; i++) {
            if (no.filho[i] != null) {
                return true;
            }
        }

        return false;
    }

}

class TrieNo {
    TrieNo[] filho = new TrieNo[26];
    boolean isChave = false;
}