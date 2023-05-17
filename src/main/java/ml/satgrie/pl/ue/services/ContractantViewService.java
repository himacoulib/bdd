/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.ContractantDao;
import ml.satgrie.pl.ue.model.Contractant;

/**
 * @author drTiefari
 *
 */
public class ContractantViewService {
	private static ContractantDao ContractantViewDao;

	/**
	 * 
	 */
	public ContractantViewService() {
		ContractantViewDao = new ContractantDao();
	}

	public void persist(Contractant entity) {
		ContractantViewDao.persist(entity);
	}

	public void update(Contractant entity) {
		ContractantViewDao.update(entity);
	}

	public Contractant findById(int id) {
		Contractant Contractant = ContractantViewDao.findById(id);
		return Contractant;
	}

	public void delete(int id) {
		Contractant Contractant = ContractantViewDao.findById(id);
		ContractantViewDao.delete(Contractant);
	}

	public List<Contractant> findAll() {
		List<Contractant> Contractants = ContractantViewDao.findAll();
		return Contractants;
	}

	public void deleteAll() {
		ContractantViewDao.deleteAll();
	}

	public ContractantDao ContractantViewDao() {
		return ContractantViewDao;
	}

}
