/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova;

import java.util.List;

/**
 *
 * @author angeloluz
 */
public class Boteco {

    private List<Produto> produtos;
    private double caixa;

    public Boteco() {
        produtos = ManipulaArquivo.lerDados();
        caixa = ManipulaArquivo.lerValorCaixa();
    }

    public void init() {
        int var = 0;
        while (var != 9) {
            System.out.println("+===== Botequis do Mussum ======+");
            System.out.println("|1. Adicionar novo produto      |");
            System.out.println("|2. Pesquisar produto           |");
            System.out.println("|3. Realizar venda              |");
            System.out.println("|4. Listar produtos             |");
            System.out.println("|5. Verificar caixa             |");
            System.out.println("|9. Sair                        |");
            System.out.println("+===============================+");
            var = Util.lerInt("Escolha uma opção: ", "");
            switch (var) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    String nome = Util.lerString("Digite o nome do produto: ");
                    listarProdutosPorNome(nome);
                    break;
                case 3:
                    System.out.println("===== Cadastro de Venda ======");
                    listarProdutosCadastros();

                    while (true) {
                        int cod = Util.lerInt("Digite o código do produto: ", "Código inválido\n");
                        if (cod != -1) {
                            Produto p = produtos.get(cod - 1);
                            System.out.println(p);
                            int quantidade = Util.lerInt("Digite a quantidade: ", "");
                            if (quantidade == -1 || !p.baixarDoEstoque(quantidade)) {
                                System.out.println("Produto ou quantidade indisponível");
                            } else {
                                System.out.printf("%s - R$ %.2f x %d = %.2f\n", p.getNome(), p.getValorDeVenda(), quantidade,
                                        (quantidade * p.getValorDeVenda()));
                                caixa += quantidade * p.getValorDeVenda();
                                ManipulaArquivo.salvarValorCaixa(caixa);
                            }
                        }
                        String cont = Util.lerString("Tecle 'F' para finalizar ou <Enter> para continuar vendendo");
                        if (cont.equalsIgnoreCase("F")) {
                            break;
                        }
                    }
                    break;
                case 4:
                    listarProdutosCadastros();
                    break;
                case 5:
                    System.out.println("Valor em caixa: " + caixa);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void adicionarProduto() {
        Produto p = new Produto();
        Util.load("Buscando produtos já cadastrados", 3000);
        listarProdutosCadastros();
        System.out.println("<<Atenção>> Este cadastro é para novos produtos");
        System.out.println("===== Cadastro de Produto =====");
        String marca = Util.lerString("Marca........: ");
        String nome = Util.lerString("Nome.........: ");
        int volume = Util.lerInt("Volume(ml)...: ", "Volume inválido\nCadastro cancelado\n");
        if (volume == -1) return;
        int quantidade = Util.lerInt("Quantidade...: ", "Quantidade inválida\nCadastro cancelado\n");
        if (quantidade == -1) return;
        if (isCadastrado(nome, marca, volume)) {
            System.out.println("Produto já está cadastrado no sistema");
            System.out.println("Cadastro cancelado");
        } else {
            double valorDeCompra = Util.lerDouble("Valor de compra.:", "Valor inválido para compra\nCadastro cancelado\n");
            if (valorDeCompra == -1) return;
            double valorDeVenda = Util.lerDouble(String.format("Valor de venda (Sugerido: %.2f): ", valorDeCompra * 1.30), "Valor inválido para venda\nCadastro cancelado\n");
            if (valorDeVenda == -1) return;
            Util.load("Verificando valor em caixa", 4000);
            if (quantidade * valorDeCompra > caixa) {
                System.out.println("Compra cancelada por falta de recurso financeiro");
            } else {
                caixa -= quantidade * valorDeCompra;
                p.cadastrar(marca, nome, volume, valorDeCompra, valorDeVenda, quantidade);
                ManipulaArquivo.salvarDados(p);
                ManipulaArquivo.salvarValorCaixa(caixa);
                System.out.println("Compra registrada");
                produtos.add(p);
            }
        }
    }

    private void listarProdutosPorNome(String nome) {

        int codigo = 1;
        int reg = 0;
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                System.out.println("<<" + codigo + ">> " + produto);
                reg++;
            }
            codigo++;
        }
        if (produtos.isEmpty() || reg == 0) {
            System.out.println("Ainda não há produtos cadastrados com esse nome");
        }
    }

    private void listarProdutosCadastros() {
        if (produtos.isEmpty()) {
            System.out.println("Ainda não há produtos cadastrados");
        }
        int contador = 1;
        for (Produto p : produtos) {
            System.out.printf("[%d] - %s %s - %dml\n", contador, p.getMarca(), p.getNome(),
                    p.getVolume());
            contador++;
        }
    }

    private boolean isCadastrado(String nome, String marca, int volume) {
        for (Produto produto : produtos) {
            if ((nome.equals(produto.getNome()))
                    && marca.equals(produto.getMarca())
                    && volume == produto.getVolume()) {
                return true;
            }
        }
        return false;
    }

    private Produto getProdutoCadastrado(String nome, String marca, int volume) {
        for (Produto produto : produtos) {
            if ((nome.equals(produto.getNome()))
                    && marca.equals(produto.getMarca())
                    && volume == produto.getVolume()) {
                return produto;
            }
        }
        return null;
    }

}
