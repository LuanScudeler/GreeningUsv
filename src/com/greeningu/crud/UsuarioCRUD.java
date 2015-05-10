package com.greeningu.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.greeningu.bean.Usuario;
import com.greeningu.bean.UsuarioLogin;
import com.greeningu.conexao.Conexao;

public class UsuarioCRUD {
	public void salvar(Usuario usuario){
		Connection conexao = Conexao.geraConexao();
		PreparedStatement ps = null;
		
		String insert = "insert into usuario(nome, sobrenome, email, login, senha, sexo, pontuacao)"
				+ " values(?,?,?,?,?,?,?)";
		System.out.println(insert);
		try {
			ps = conexao.prepareStatement(insert);
			
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSobrenome());
			ps.setString(3, usuario.getEmail());
			ps.setString(4, usuario.getLogin());
			ps.setString(5, usuario.getSenha());
			ps.setString(6, usuario.getSexo());
			ps.setInt(7, 0);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar usuario." + e.getMessage());
		}finally{
			try {
				ps.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção.: " + e.getMessage());
			}
			
		}
	}
	
	public void atualizarPontuacao(Usuario usuario, int pontos){
		Connection conexao = Conexao.geraConexao();
		
		PreparedStatement ps = null;
		
		String update = "update usuario set pontuacao = pontuacao + ? where id = ?";
		
		try {
			ps = conexao.prepareStatement(update);
			
			ps.setInt(1, pontos);
			ps.setInt(2, usuario.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar pontuacao. " + e.getMessage());
		}finally{
			try{
				ps.close();
				conexao.close();
			}catch(Throwable e){
				System.out.println("Falha ao fechar atualização dos pontos.: " + e.getMessage());
			}
		}
	}
	
	public UsuarioLogin buscaLogin(String login){
		Connection conexao = Conexao.geraConexao();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		UsuarioLogin usuarioLogin = null;
		
		String select = "select * from usuario where login = ?";
		
		try {
			ps = conexao.prepareStatement(select);
			ps.setString(1, login);
			resultSet = ps.executeQuery();
			
			if(resultSet.next()){
				usuarioLogin = new UsuarioLogin();
				
				usuarioLogin.setId(resultSet.getInt("id"));
				usuarioLogin.setLogin(resultSet.getString("login"));
				usuarioLogin.setSenha(resultSet.getString("senha"));
				
				System.out.println(usuarioLogin.getLogin());
			}
			
		} catch (SQLException e) {
			System.out.println("Falha ao buscar login. : " + e.getMessage());
		}finally{
			try {
				ps.close();
				resultSet.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Falha ao fechar busca de login.: " + e.getMessage());
			}
		}
		return usuarioLogin;
	}
}
