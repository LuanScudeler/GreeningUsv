package com.greeningu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.greeningu.bean.Comentario;
import com.greeningu.util.HibernateUtil;

public class ComentarioDAO {
	private Session sessao;
	private Transaction transacao;

	public void salvar(Comentario comentario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(comentario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível inserir a comentario. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de inserção da classe ComentarioDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void atualizar(Comentario comentario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(comentario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível alterar a comentario. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de atualização  da classe ComentarioDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public void excluir(Comentario comentario) {
		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(comentario);
			this.transacao.commit();
		} catch (HibernateException e) {
			System.out.println("Não foi possível excluir a comentario. Erro: " + e.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operação de exclusão  da classe ComentarioDAO. Mensagem: " + e.getMessage());
			}
		}
	}

	public Comentario buscaComentario(Integer id) {
		Comentario comentario = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Comentario.class);
			filtro.add(Restrictions.eq("id", id));
			comentario = (Comentario) filtro.uniqueResult();
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
				System.out.println("Erro ao fechar operação de busca  da classe ComentarioDAO. Mensagem: " + e.getMessage());
			}
		}
		return comentario;
	}

	public List<Comentario> listar() {
		List<Comentario> permissoes = null;

		try {
			this.sessao = HibernateUtil.getSessionFactory().getCurrentSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Comentario.class);
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
				System.out.println("Erro ao fechar operação de listagem  da classe ComentarioDAO. Mensagem: " + e.getMessage());
			}
		}
		return permissoes;
	}
	
	/*public static void main(String[] args) {
		List<Comentario> p = new ArrayList<Comentario>();
		ComentarioDAO pd = new ComentarioDAO();
		
		pd.salvar(new Comentario(0,"Comum"));
		
		p = pd.listar();
		
		for (Comentario pe : p) {
			System.out.println(pe.getId() + " - "+pe.getTipo());
		}
		System.out.println(pd.buscaComentario(3).getTipo());
	}*/
	
	
}

