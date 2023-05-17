/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.RegionDao;
import ml.satgrie.pl.ue.model.Region;

/**
 * @author drTiefari
 *
 */
public class RegionViewService {
	private static RegionDao RegionViewDao;

	/**
	 * 
	 */
	public RegionViewService() {
		RegionViewDao = new RegionDao();
	}

	public void persist(Region entity) {
		RegionViewDao.persist(entity);
	}

	public void update(Region entity) {
		RegionViewDao.update(entity);
	}

	public Region findById(int id) {
		Region Region = RegionViewDao.findById(id);
		return Region;
	}

	public void delete(int id) {
		Region Region = RegionViewDao.findById(id);
		RegionViewDao.delete(Region);
	}

	public List<Region> findAll() {
		List<Region> Regions = RegionViewDao.findAll();
		return Regions;
	}

	public void deleteAll() {
		RegionViewDao.deleteAll();
	}

	public RegionDao RegionViewDao() {
		return RegionViewDao;
	}

}
