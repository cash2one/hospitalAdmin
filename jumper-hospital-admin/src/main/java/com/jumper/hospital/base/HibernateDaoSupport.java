package com.jumper.hospital.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class HibernateDaoSupport {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public final SessionFactory getSessionFactory() {
		return ((this.sessionFactory != null) ? this.sessionFactory : null);
	}

	protected final Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
}