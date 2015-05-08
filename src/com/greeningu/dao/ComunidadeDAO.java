package com.greeningu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.greeningu.bean.Comunidade;
import com.greeningu.util.HibernateUtil;

public class ComunidadeDAO {
	private Session sessao;
	private Transaction transacao;

	public void salvar(Comunidade comunidade) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(comunidade);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a comunidade. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção da classe ComunidadeDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Comunidade comunidade) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(comunidade);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a comunidade. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização  da classe ComunidadeDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Comunidade comunidade) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(comunidade);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a comunidade. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão  da classe ComunidadeDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public Comunidade buscaComunidade(Integer id) {
		Comunidade comunidade = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Comunidade.class);
			filtro.add(Restrictions.eq("id", id));
			comunidade = (Comunidade) filtro.uniqueResult();
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
				System.out.println("Erro ao fechar operação de busca  da classe ComunidadeDAO. Mensagem: " + e.getMessage());
			}
		}
		return comunidade;
	}

	public List<Comunidade> listar() {
		List<Comunidade> permissoes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Comunidade.class);
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
				System.out.println("Erro ao fechar operação de listagem  da classe ComunidadeDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissoes;
	}
	
	/*public static void main(String[] args) {
		List<Comunidade> p = new ArrayList<Comunidade>();
		ComunidadeDAO pd = new ComunidadeDAO();
		
		pd.salvar(new Comunidade(0,"Comum"));
		
		p = pd.listar();
		
		for (Comunidade pe : p) {
			System.out.println(pe.getId() + " - "+pe.getTipo());
		}
		System.out.println(pd.buscaComunidade(3).getTipo());
	}*/
	
	
}

