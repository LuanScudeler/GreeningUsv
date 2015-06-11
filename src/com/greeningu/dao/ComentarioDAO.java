package com.greeningu.dao;

import java.util.List;
import java.util.Map;

import com.greeningu.bean.Comentario;
import com.greeningu.log.Log;

public class ComentarioDAO extends Dao implements CRUD{

	private static final String NOME_CLASSE = "ComentarioDAO";
	
	
	public int salvarComentario(Comentario comentario) {

		int status = 0;

		abrirConexao();

		String insert = "insert into comentario (id_postagem, texto) values (?, ?)";
		
		String insert2 = "insert into usuario_comentario (data_comentario, id_usuario, id_comentario) values (sysdate(), ?, LAST_INSERT_ID())";
		
		try {

			preparedStatement = conexao.prepareStatement(insert);
			preparedStatement.setInt(1, comentario.getIdPostagem());
			preparedStatement.setString(2, comentario.getTexto());

			status = preparedStatement.executeUpdate();
			
			System.out.println("1 - STATUS: " +  status);
			
			preparedStatement = conexao.prepareStatement(insert2);
			preparedStatement.setInt(1, comentario.getIdUsuario());

			status = preparedStatement.executeUpdate();
			
			System.out.println("2 - STATUS: " +  status);

			Log.sucesso(NOME_CLASSE, METODO_SALVAR);

		} catch (Exception e) {

			Log.erro(NOME_CLASSE, METODO_SALVAR, e);

		}finally{

			fecharConexao();
			
		}
		return status;
	}

	@Override
	public Object buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int salvar(Object objeto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int salvar(Object objeto, Map<String, Object> campos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int atualizar(Object objeto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int atualizar(Object objeto, Map<String, Object> campos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int excluir(Object objeto) {
		// TODO Auto-generated method stub
		return 0;
	}
}
