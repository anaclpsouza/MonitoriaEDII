import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        int caso = 1;

        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;

            HashMap<String, Integer> moedas = new HashMap<>();
            for (int i = 0; i < n; i++) {
                moedas.put(sc.next(), i);
            }

            double[][] tabela = new double[n][n];
            for (int i = 0; i < n; i++) {
                tabela[i][i] = 1.0;
            }

            int m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                String de = sc.next();
                double taxa = sc.nextDouble();
                String para = sc.next();
                
                tabela[moedas.get(de)][moedas.get(para)] = taxa;
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (tabela[i][k] * tabela[k][j] > tabela[i][j]) {
                            tabela[i][j] = tabela[i][k] * tabela[k][j];
                        }
                    }
                }
            }

            boolean existeArbitragem = false;
            for (int i = 0; i < n; i++) {
                if (tabela[i][i] > 1.0) {
                    existeArbitragem = true;
                    break;
                }
            }

            if (existeArbitragem) {
                System.out.println("Case " + caso + ": Yes");
            } else {
                System.out.println("Case " + caso + ": No");
            }
            caso++;
        }
        sc.close();
    }
}