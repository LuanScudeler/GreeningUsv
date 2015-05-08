package com.greeningu.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.greeningu.bean.Comentario;
import com.greeningu.bean.Comunidade;
import com.greeningu.bean.Permissao;
import com.greeningu.bean.Postagem;
import com.greeningu.bean.Usuario;
import com.greeningu.bean.Voto;

public class HibernateUtil {

	private static final SessionFactory	sessionFactory	= buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");	
			return cfg.buildSessionFactory();
		} catch (Throwable e) {
			System.out.println("Criação inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/*public static void criaDatabase(){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
	
		cfg.addAnnotatedClass(Permissao.class);
		cfg.addAnnotatedClass(Usuario.class);
		cfg.addAnnotatedClass(Comentario.class);
		cfg.addAnnotatedClass(Comunidade.class);
		cfg.addAnnotatedClass(Postagem.class);
		cfg.addAnnotatedClass(Voto.class);

		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.create(true, true);
	}*/

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/*public static void main(String[] args) {
		HibernateUtil.criaDatabase();
	}*/
}

