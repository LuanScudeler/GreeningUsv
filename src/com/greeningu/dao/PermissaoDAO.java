package com.greeningu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.greeningu.bean.Permissao;
import com.greeningu.util.HibernateUtil;

public class PermissaoDAO {
	private Session sessao;
	private Transaction transacao;

	public void salvar(Permissao permissao) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(permissao);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a permissao. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção da classe PermissaoDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Permissao permissao) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(permissao);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a permissao. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização  da classe PermissaoDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Permissao permissao) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(permissao);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a permissao. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão  da classe PermissaoDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public Permissao buscaPermissao(Integer id) {
		Permissao permissao = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Permissao.class);
			filtro.add(Restrictions.eq("permissao", id));
			permissao = (Permissao) filtro.uniqueResult();
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
				System.out.println("Erro ao fechar operação de busca  da classe PermissaoDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissao;
	}

	public List<Permissao> listar() {
		List<Permissao> permissoes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Permissao.class);
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
				System.out.println("Erro ao fechar operação de listagem  da classe PermissaoDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissoes;
	}
	
	public static void main(String[] args) {
		List<Permissao> p = new ArrayList<Permissao>();
		PermissaoDAO pd = new PermissaoDAO();
		
		//pd.salvar(new Permissao(0,"Teste"));
		
		p = pd.listar();
		
		for (Permissao pe : p) {
			System.out.println(pe.getId() + " - "+pe.getTipo());
		}
	}
	
	
}
