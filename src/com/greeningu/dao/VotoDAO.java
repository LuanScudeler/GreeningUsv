package com.greeningu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.greeningu.bean.Voto;
import com.greeningu.util.HibernateUtil;

public class VotoDAO {
	private Session sessao;
	private Transaction transacao;

	public void salvar(Voto voto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(voto);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a voto. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção da classe VotoDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Voto voto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(voto);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a voto. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização  da classe VotoDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Voto voto) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(voto);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a voto. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão  da classe VotoDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public Voto buscaVoto(Integer id) {
		Voto voto = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Voto.class);
			filtro.add(Restrictions.eq("id", id));
			voto = (Voto) filtro.uniqueResult();
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
				System.out.println("Erro ao fechar operação de busca  da classe VotoDAO. Mensagem: " + e.getMessage());
			}
		}
		return voto;
	}

	public List<Voto> listar() {
		List<Voto> permissoes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Voto.class);
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
				System.out.println("Erro ao fechar operação de listagem  da classe VotoDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissoes;
	}
	
	/*public static void main(String[] args) {
		List<Voto> p = new ArrayList<Voto>();
		VotoDAO pd = new VotoDAO();
		
		pd.salvar(new Voto(0,"Comum"));
		
		p = pd.listar();
		
		for (Voto pe : p) {
			System.out.println(pe.getId() + " - "+pe.getTipo());
		}
		System.out.println(pd.buscaVoto(3).getTipo());
	}*/
	
	
}
