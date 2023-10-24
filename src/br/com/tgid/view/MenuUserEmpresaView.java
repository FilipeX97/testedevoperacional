package br.com.tgid.view;

import java.util.List;
import java.util.Scanner;

import br.com.tgid.model.Empresa;
import br.com.tgid.model.Produto;
import br.com.tgid.model.Venda;

public class MenuUserEmpresaView {

	public void exibirMenu(Empresa empresa, List<Produto> produtos, List<Venda> vendas, Scanner sc) {
		try {
			while (true) {
				int escolha = 0;
				boolean entradaValida = false;
				
				while (!entradaValida) {
		            try {
		            	System.out.println("1 - Listar vendas");
						System.out.println("2 - Ver produtos");
						System.out.println("0 - Deslogar");
						System.out.print("Escolha uma opção: ");
						
						escolha = Integer.parseInt(sc.next());
						entradaValida = true;
		            } catch (NumberFormatException e) {
		                System.out.println("Entrada inválida. Certifique-se de digitar um número inteiro.");
		            }
		        }

				switch (escolha) {
				case 1:
					listarVendas(empresa, vendas);
					break;
				case 2:
					listarProdutos(empresa, produtos);
					break;
				case 0:
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro ao exibir o menu da empresa: " + e.getMessage());
			System.out.println("Finalizando Sistema");
		}
	}

	private static void listarVendas(Empresa empresa, List<Venda> vendas) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("VENDAS EFETUADAS");
		vendas.stream().filter(venda -> venda.getEmpresa().getId().equals(empresa.getId())).forEach(venda -> {
			System.out.println("************************************************************");
			System.out
					.println("Venda de código: " + venda.getCódigo() + " no CPF " + venda.getCliente().getCpf() + ": ");
			venda.getItens().forEach(item -> {
				System.out.println(item.getId() + " - " + item.getNome() + "    R$" + item.getPreco());
			});
			System.out.println("Total Venda: R$" + venda.getValor());
			System.out.println("Total Taxa a ser paga: R$" + venda.getComissaoSistema());
			System.out.println("Total Líquido para a empresa: " + (venda.getValor() - venda.getComissaoSistema()));
			System.out.println("************************************************************");
		});
		System.out.println("Saldo da Empresa: " + empresa.getSaldo());
		System.out.println("************************************************************");
	}

	private static void listarProdutos(Empresa empresa, List<Produto> produtos) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("MEUS PRODUTOS");
		produtos.stream().filter(produto -> produto.getEmpresa().getId().equals(empresa.getId())).forEach(produto -> {
			System.out.println("************************************************************");
			System.out.println("Código: " + produto.getId());
			System.out.println("Produto: " + produto.getNome());
			System.out.println("Quantidade em estoque: " + produto.getQuantidade());
			System.out.println("Valor: R$" + produto.getPreco());
			System.out.println("************************************************************");
		});
		System.out.println("Saldo da Empresa: " + empresa.getSaldo());
		System.out.println("************************************************************");
	}

}
