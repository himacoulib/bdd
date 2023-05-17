/**
 * 
 */
package ml.satgrie.pl.ue.services;

import java.util.List;

import ml.satgrie.pl.ue.dao.ProfileDao;
import ml.satgrie.pl.ue.model.Profile;

/**
 * @author drTiefari
 *
 */
public class ProfileViewService {
	private static ProfileDao ProfileViewDao;

	/**
	 * 
	 */
	public ProfileViewService() {
		ProfileViewDao = new ProfileDao();
	}

	public void persist(Profile entity) {
		ProfileViewDao.persist(entity);
	}

	public void update(Profile entity) {
		ProfileViewDao.update(entity);
	}

	public Profile findById(int id) {
		Profile Profile = ProfileViewDao.findById(id);
		return Profile;
	}

	public void delete(int id) {
		Profile Profile = ProfileViewDao.findById(id);
		ProfileViewDao.delete(Profile);
	}

	public List<Profile> findAll() {
		List<Profile> Profiles = ProfileViewDao.findAll();
		return Profiles;
	}

	public void deleteAll() {
		ProfileViewDao.deleteAll();
	}

	public ProfileDao ProfileViewDao() {
		return ProfileViewDao;
	}

}
