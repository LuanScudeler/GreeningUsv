package com.greeningu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.greeningu.bean.Usuario;
import com.greeningu.util.HibernateUtil;

public class UsuarioDAO {
	private Session sessao;
	private Transaction transacao;

	public void salvar(Usuario usuario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(usuario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a usuario. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção da classe UsuarioDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Usuario usuario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(usuario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a usuario. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização  da classe UsuarioDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Usuario usuario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(usuario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a usuario. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão  da classe UsuarioDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public Usuario buscaUsuario(Integer id) {
		Usuario usuario = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Usuario.class);
			filtro.add(Restrictions.eq("id", id));
			usuario = (Usuario) filtro.uniqueResult();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de busca  da classe UsuarioDAO. Mensagem: " + e.getMessage());
			}
		}
		return usuario;
	}

	public List<Usuario> listar() {
		List<Usuario> permissoes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Usuario.class);
			permissoes = filtro.list();
			this.transacao.commit();
		} catch (Throwable e) {
			if (this.transacao.isActive()) {
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de listagem  da classe UsuarioDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissoes;
	}
}
