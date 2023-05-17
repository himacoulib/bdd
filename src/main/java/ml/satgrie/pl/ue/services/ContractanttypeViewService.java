/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.ContractantTypeDao;
import ml.satgrie.pl.ue.model.Contractanttype;

/**
 * @author drTiefari
 *
 */
public class ContractanttypeViewService {
	private static ContractantTypeDao ContractanttypeViewDao;

	/**
	 * 
	 */
	public ContractanttypeViewService() {
		ContractanttypeViewDao = new ContractantTypeDao();
	}

	public void persist(Contractanttype entity) {
		ContractanttypeViewDao.persist(entity);
	}

	public void update(Contractanttype entity) {
		ContractanttypeViewDao.update(entity);
	}

	public Contractanttype findById(int id) {
		Contractanttype Contractanttype = ContractanttypeViewDao.findById(id);
		return Contractanttype;
	}

	public void delete(int id) {
		Contractanttype Contractanttype = ContractanttypeViewDao.findById(id);
		ContractanttypeViewDao.delete(Contractanttype);
	}

	public List<Contractanttype> findAll() {
		List<Contractanttype> Contractanttypes = ContractanttypeViewDao.findAll();
		return Contractanttypes;
	}

	public void deleteAll() {
		ContractanttypeViewDao.deleteAll();
	}

	public ContractantTypeDao ContractanttypeViewDao() {
		return ContractanttypeViewDao;
	}

}
