/**
 * 
 */
package ml.satgrie.pl.ue.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.QueryHints;
import org.hibernate.query.Query;

import ml.satgrie.pl.ue.interfaces.BddInterface;
import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.utils.HibernateUtils;

/**
 * @author drTiefari
 *
 */
public class BudgetDao implements BddInterface<Budget, Integer> {

	/**
	 * 
	 */
	public BudgetDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void persist(Budget entity) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		transaction.commit();
		session.close();
		
	}

	public void persist2(Budget entity, Budget budgetCompl) {

		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.persist(entity);
		session.persist(budgetCompl);
		transaction.commit();
		session.close();
		
	}

	@Override
	public void update(Budget entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
		session.close();
		
	}

	public void update2(Budget entity, Budget budgCompl) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		session.update(budgCompl);
		transaction.commit();
		session.close();
		
	}

	@Override
	public Budget findById(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Budget budget = session.find(Budget.class, id);
		session.close();
		
		return budget;
	}

	@Override
	public void delete(Budget entity) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
		session.close();
		
	}

	/**
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Budget> findAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Budget> getBudgets = session.createQuery(" FROM " + Budget.class.getSimpleName()).list();
		session.close();
		

		return getBudgets;
	}

	@SuppressWarnings("unchecked")
	public List<Budget> findByProject(int id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Budget> budgets = new ArrayList<Budget>();

		try {
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(" FROM " + Budget.class.getSimpleName() + " WHERE projetId=:projetId");
			query.setParameter("projetId", id);
			budgets = query.getResultList();
			session.close();
			
		} catch (Exception e) {
			System.out.println(e);
			session.close();
			
		}
		return budgets;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
