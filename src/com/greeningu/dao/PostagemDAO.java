package com.greeningu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.greeningu.bean.Postagem;
import com.greeningu.util.HibernateUtil;

public class PostagemDAO {
	private Session sessao;
	private Transaction transacao;

	public void salvar(Postagem postagem) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(postagem);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a postagem. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção da classe PostagemDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Postagem postagem) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(postagem);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a postagem. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização  da classe PostagemDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Postagem postagem) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(postagem);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a postagem. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão  da classe PostagemDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public Postagem buscaPostagem(Integer id) {
		Postagem postagem = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Postagem.class);
			filtro.add(Restrictions.eq("id", id));
			postagem = (Postagem) filtro.uniqueResult();
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
				System.out.println("Erro ao fechar operação de busca  da classe PostagemDAO. Mensagem: " + e.getMessage());
			}
		}
		return postagem;
	}

	public List<Postagem> listar() {
		List<Postagem> permissoes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Postagem.class);
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
				System.out.println("Erro ao fechar operação de listagem  da classe PostagemDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissoes;
	}
	
	/*public static void main(String[] args) {
		List<Postagem> p = new ArrayList<Postagem>();
		PostagemDAO pd = new PostagemDAO();
		
		pd.salvar(new Postagem(0,"Comum"));
		
		p = pd.listar();
		
		for (Postagem pe : p) {
			System.out.println(pe.getId() + " - "+pe.getTipo());
		}
		System.out.println(pd.buscaPostagem(3).getTipo());
	}*/
	
	
}

