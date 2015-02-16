package com.dev4world.ctmemo.dao.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.dev4world.ctmemo.Ctmemo;
import com.dev4world.ctmemo.CtmemoSearchCondition;
import com.dev4world.ctmemo.dao.CtmemoDao;

/**
 * 하이버네이트을 이용한 메모장 DAO<br>
 * DBMS에 맞추어 상속받아 사용.
 * 
 * @version $Id$
 */
public abstract class AbstractCtmemoDao implements CtmemoDao {
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public Ctmemo getCtmemo(int ctmemoId) {
		Session session = sessionFactory.getCurrentSession();
		return (Ctmemo) session.get(Ctmemo.class, ctmemoId);
	}

	@Override
	public List<Ctmemo> listCtmemo(CtmemoSearchCondition condition) {
		Session session = sessionFactory.getCurrentSession();
		String q = " from Ctmemo where deleteF = 'N'";
		Query query = session.createQuery(q);

		@SuppressWarnings("unchecked")
		List<Ctmemo> resultList = query.list();
		return resultList;
	}

	@Override
	public void insert(Ctmemo ctmemo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ctmemo);
	}

	@Override
	public void updateCtmemo(Ctmemo ctmemo) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ctmemo);
	}

	@Override
	public void deleteCtmemo(int ctmemoId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getCtmemo(ctmemoId));
	}
}
