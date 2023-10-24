package br.com.tgid.service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.tgid.model.Usuario;

public class LoginService {

	public Usuario realizarLogin(List<Usuario> usuarios, Scanner sc) {
		try {
			System.out.println("Entre com seu usuário e senha:");
			System.out.print("Usuário: ");
			String username = sc.next();
			System.out.print("Senha: ");
			String senha = sc.next();

			List<Usuario> usuariosSearch = usuarios.stream().filter(x -> x.getUsername().equals(username))
					.collect(Collectors.toList());

			if (usuariosSearch.size() > 0) {
				Usuario usuarioLogado = usuariosSearch.get(0);
				if ((usuarioLogado.getSenha().equals(senha))) {
					return usuarioLogado;
				} else {
					System.out.println("Senha incorreta");
					return null;
				}
			} else {
				System.out.println("Usuário não encontrado");
				return null;
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro ao realizar o login: " + e.getMessage());
			return null;
		}
	}

}
