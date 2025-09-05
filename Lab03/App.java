import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    static List<Produto> pesquisar(List<Produto> produtos, String valor, CriterioBusca criterio) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p: produtos) {
            if (criterio.testar(p, valor)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    static void mostrarLista(String titulo, List<Produto> lista) {
        System.out.println("\n" + titulo);
        if (lista.isEmpty()) {
            System.out.println("(nenhum produto encontrado)");
        } else {
            for (Produto p : lista) {
                System.out.println(p);
            }
        }
    }

    static void menuPrincipal(List<Produto> produtos) {
        boolean sair = false;

        while (!sair) {
System.out.println("\n(1) - Listar produtos");
            System.out.println("(2) - Pesquisar descrição");
            System.out.println("(3) - Pesquisar marca");
            System.out.println("(4) - Pesquisar pelo preço máximo");
            System.out.println("(5) - Pesquisar pelo preço mínimo");
            System.out.println("(0) - Sair");

            System.out.print("Escolha uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "0":
                    sair = true;
                    break;

                case "1":
                    mostrarLista("Lista de produtos:", produtos);
                    break;

                case "2":
                    System.out.print("Termo a pesquisar (descrição): ");
                    String termo = sc.nextLine();
                    mostrarLista("Resultado da pesquisa:", 
                        pesquisar(produtos, termo, new CriterioDescricao()));
                    break;

                case "3":
                    System.out.print("Marca a pesquisar: ");
                    String marca = sc.nextLine();
                    mostrarLista("Resultado da pesquisa:", 
                        pesquisar(produtos, marca, new CriterioMarca()));
                    break;

                case "4":
                    System.out.print("Preço máximo: ");
                    String precoMax = sc.nextLine();
                    mostrarLista("Resultado da pesquisa:", 
                        pesquisar(produtos, precoMax, new CriterioPrecoMaximo()));
                    break;

                case "5":
                    System.out.print("Preço mínimo: ");
                    String precoMin = sc.nextLine();
                    mostrarLista("Resultado da pesquisa:", 
                        pesquisar(produtos, precoMin, new CriterioPrecoMinimo()));
                    break;

                default:
System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        List<String> linhas;
        Path arquivo = Paths.get("produtos.txt");
        List<Produto> produtos = new ArrayList<>();

        try {
            linhas = Files.readAllLines(arquivo);
            for (String linha : linhas) {
                if (linha.trim().isEmpty()) continue;
                String[] c = linha.split(";");
                Produto p = new Produto(c[0], Double.parseDouble(c[1].replace(",", ".")), c[2]);
                produtos.add(p);
            }
            menuPrincipal(produtos);
        } catch (IOException e) {
            System.out.println("Erro ao carregar os produtos (verifique se 'produtos.txt' existe na raiz).");
        } catch (Exception e) {
            System.out.println("Erro ao processar os produtos. Verifique o formato do arquivo (descricao;preco;marca).");
        }
    }
}