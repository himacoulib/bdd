/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.PointFocalDao;
import ml.satgrie.pl.ue.model.Pointfocal;

/**
 * @author drTiefari
 *
 */
public class PointfocalViewService {
	private static PointFocalDao PointfocalViewDao;

	/**
	 * 
	 */
	public PointfocalViewService() {
		PointfocalViewDao = new PointFocalDao();
	}

	public void persist(Pointfocal entity) {
		PointfocalViewDao.persist(entity);
	}

	public void update(Pointfocal entity) {
		PointfocalViewDao.update(entity);
	}

	public Pointfocal findById(int id) {
		Pointfocal Pointfocal = PointfocalViewDao.findById(id);
		return Pointfocal;
	}

	public void delete(int id) {
		Pointfocal Pointfocal = PointfocalViewDao.findById(id);
		PointfocalViewDao.delete(Pointfocal);
	}

	public List<Pointfocal> findAll() {
		List<Pointfocal> Pointfocals = PointfocalViewDao.findAll();
		return Pointfocals;
	}

	public void deleteAll() {
		PointfocalViewDao.deleteAll();
	}

	public PointFocalDao PointfocalViewDao() {
		return PointfocalViewDao;
	}

}
