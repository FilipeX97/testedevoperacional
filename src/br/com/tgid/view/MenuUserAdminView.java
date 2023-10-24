package br.com.tgid.view;

import java.util.List;
import java.util.Scanner;

import br.com.tgid.model.Cliente;
import br.com.tgid.model.Empresa;
import br.com.tgid.model.Usuario;

public class MenuUserAdminView {

	public void exibirMenuAdmin(Usuario usuarioLogado, List<Empresa> empresas, List<Cliente> clientes, Scanner sc) {
		try {
			while (true) {
				int escolha = 0;
				boolean entradaValida = false;
				
				while (!entradaValida) {
		            try {
		            	System.out.println("1 - Visualizar informações das Empresas");
						System.out.println("2 - Visualizar informações dos Clientes");
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
					visualizarEmpresas(empresas);
					break;
				case 2:
					visualizarClientes(clientes);
					break;
				case 0:
					return;
				default:
					System.out.println("Opção inválida. Tente novamente.");
				}
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro ao exibir o menu administrativo: " + e.getMessage());
			System.out.println("Finalizando Sistema");
		}
	}

	private static void visualizarEmpresas(List<Empresa> empresas) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("INFORMAÇÕES DAS EMPRESAS");
		empresas.forEach(empresa -> {
			System.out.println("************************************************************");
			System.out.println("ID: " + empresa.getId());
			System.out.println("Nome: " + empresa.getNome());
			System.out.println("CNPJ: " + empresa.getCnpj());
			System.out.println("Taxa: " + empresa.getTaxa());
			System.out.println("Saldo: " + empresa.getSaldo());
		});
	}

	private static void visualizarClientes(List<Cliente> clientes) {
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("INFORMAÇÕES DOS CLIENTES");
		clientes.forEach(cliente -> {
			System.out.println("************************************************************");
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("Username: " + cliente.getUsername());
			System.out.println("Idade: " + cliente.getIdade());
		});
	}

}
