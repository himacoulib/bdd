/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import javax.persistence.Cacheable;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import ml.satgrie.pl.ue.dao.BudgetDao;
import ml.satgrie.pl.ue.model.Budget;

/**
 * @author drTiefari
 *
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BudgetViewService {
	private static final Logger logger = Logger.getLogger(BudgetViewService.class);
	private static BudgetDao BudgetViewDao;

	/**
	 * 
	 */
	public BudgetViewService() {
		BudgetViewDao = new BudgetDao();
	}

	public void persist(Budget entity) {
		logger.info("Creating new Budget");
		BudgetViewDao.persist(entity);
	}
	
	public void persist2(Budget entity, Budget budgetComple) {
		BudgetViewDao.persist2(entity,budgetComple);
	}

	public void update(Budget entity) {
		BudgetViewDao.update(entity);
	}
	
	public void update2(Budget entity, Budget budgetComple) {
		BudgetViewDao.update2(entity,budgetComple);
	}

	public Budget findById(int id) {
		Budget Budget = BudgetViewDao.findById(id);
		return Budget;
	}

	public void delete(int id) {
		Budget Budget = BudgetViewDao.findById(id);
		BudgetViewDao.delete(Budget);
	}

	
	public List<Budget> findAll() {
		List<Budget> Budgets = BudgetViewDao.findAll();
		return Budgets;
	}
	
	public List<Budget> findByProject(int id) {
		List<Budget> Budgets = BudgetViewDao.findByProject(id);
		return Budgets;
	}

	public void deleteAll() {
		BudgetViewDao.deleteAll();
	}

	public BudgetDao BudgetViewDao() {
		return BudgetViewDao;
	}

}
