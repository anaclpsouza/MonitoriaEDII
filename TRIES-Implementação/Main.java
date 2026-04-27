import java.util.List;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.adicionar("abobora");
        trie.adicionar("abacadabra");
        trie.adicionar("mochila");
        trie.adicionar("mocha");
        trie.adicionar("bolo");
        trie.adicionar("bola");

        trie.print();

        System.out.println("========== busca por prefixo 1 ============");

        List<String> resultados = trie.buscarPorPrefixo("bol");

        for (String s : resultados) {
            System.out.println(s);
        }
        trie.excluir("bola");
        System.out.println("========== busca por prefixo pós remoção ============");
        List<String> resultados2 = trie.buscarPorPrefixo("bol");
        for (String s : resultados2) {
            System.out.println(s);
        }
        System.out.println("========== print ============");
        trie.print();
        System.out.println("========== print ============");
        System.out.println(trie.possui("abobora"));
        System.out.println(trie.possui("teste"));
        // trie.possui("1243");
    }
}
