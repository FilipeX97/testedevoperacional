package br.com.tgid.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.tgid.model.Cliente;
import br.com.tgid.model.Empresa;
import br.com.tgid.model.Produto;
import br.com.tgid.model.Usuario;
import br.com.tgid.model.Venda;
import br.com.tgid.service.VendaService;

public class MenuUserClienteView {

	public void exibirMenu(Usuario usuarioLogado, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas, Scanner sc) {
		try {
			while (true) {
				int escolha = 0;
				boolean entradaValida = false;
				
				while (!entradaValida) {
		            try {
		            	System.out.println("1 - Realizar Compras");
						System.out.println("2 - Ver Compras");
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
					realizarCompras(usuarioLogado, empresas, produtos, carrinho, vendas, clientes);
					break;
				case 2:
					verCompras(usuarioLogado, vendas);
					break;
				case 0:
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro ao exibir o menu do cliente: " + e.getMessage());
			System.out.println("Finalizando Sistema");
		}
	}

	private static void realizarCompras(Usuario usuarioLogado, List<Empresa> empresas, List<Produto> produtos,
			List<Produto> carrinho, List<Venda> vendas, List<Cliente> clientes) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Para realizar uma compra, escolha a empresa onde deseja comprar: ");
		empresas.forEach(x -> {
			System.out.println(x.getId() + " - " + x.getNome());
		});
		int escolhaEmpresa = sc.nextInt();
		int escolhaProduto = -1;
		do {
			System.out.println("Escolha os seus produtos: ");
			produtos.forEach(x -> {
				if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
					System.out.println(x.getId() + " - " + x.getNome());
				}
			});
			System.out.println("0 - Finalizar compra");
			escolhaProduto = sc.nextInt();
			for (Produto produtoSearch : produtos) {
				if (produtoSearch.getId().equals(escolhaProduto))
					carrinho.add(produtoSearch);
			}
		} while (escolhaProduto != 0);
		System.out.println("************************************************************");
		System.out.println("Resumo da compra: ");
		carrinho.forEach(x -> {
			if (x.getEmpresa().getId().equals(escolhaEmpresa)) {
				System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
			}
		});
		Empresa empresaEscolhida = empresas.stream().filter(x -> x.getId() == escolhaEmpresa)
				.collect(Collectors.toList()).get(0);
		Cliente clienteLogado = clientes.stream().filter(x -> x.getUsername().equals(usuarioLogado.getUsername()))
				.collect(Collectors.toList()).get(0);
		Venda venda = VendaService.criarVenda(carrinho, empresaEscolhida, clienteLogado, vendas);
		System.out.println("Total: R$" + venda.getValor());
		System.out.println("************************************************************");
		carrinho.clear();
		sc.close();
	}

	private static void verCompras(Usuario usuarioLogado, List<Venda> vendas) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("COMPRAS EFETUADAS");
		vendas.forEach(venda -> {
			if (venda.getCliente().getUsername().equals(usuarioLogado.getUsername())) {
				System.out.println("************************************************************");
				System.out.println("Compra de código: " + venda.getCódigo() + " na empresa "
						+ venda.getEmpresa().getNome() + ": ");
				venda.getItens().forEach(x -> {
					System.out.println(x.getId() + " - " + x.getNome() + "    R$" + x.getPreco());
				});
				System.out.println("Total: R$" + venda.getValor());
				System.out.println("************************************************************");
			}
		});
	}

}
