package br.com.tgid.controller;

import java.util.List;
import java.util.Scanner;

import br.com.tgid.model.Cliente;
import br.com.tgid.model.Empresa;
import br.com.tgid.model.Produto;
import br.com.tgid.model.Usuario;
import br.com.tgid.model.Venda;
import br.com.tgid.service.LoginService;
import br.com.tgid.view.MenuUserAdminView;
import br.com.tgid.view.MenuUserClienteView;
import br.com.tgid.view.MenuUserEmpresaView;

public class MainController {

	public static void executar(List<Usuario> usuarios, List<Cliente> clientes, List<Empresa> empresas,
			List<Produto> produtos, List<Produto> carrinho, List<Venda> vendas) {
		try (Scanner sc = new Scanner(System.in)) {
			LoginService login = new LoginService();
			Usuario usuarioLogado = login.realizarLogin(usuarios, sc);

			if (usuarioLogado != null) {
				System.out.println("Escolha uma opção para iniciar");
				if (usuarioLogado.IsEmpresa()) {
					MenuUserEmpresaView menuUserEmpresaView = new MenuUserEmpresaView();
					menuUserEmpresaView.exibirMenu(usuarioLogado.getEmpresa(), produtos, vendas, sc);
				} else if (usuarioLogado.IsCliente()) {
					MenuUserClienteView menuUserClienteView = new MenuUserClienteView();
					menuUserClienteView.exibirMenu(usuarioLogado, clientes, empresas, produtos, carrinho, vendas, sc);
				} else if (usuarioLogado.IsAdmin()) {
					MenuUserAdminView menuUserAdminView = new MenuUserAdminView();
					menuUserAdminView.exibirMenuAdmin(usuarioLogado, empresas, clientes, sc);
				}
			}
		}
	}

}
