package ml.satgrie.pl.ue.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.stat.Statistics;
import org.mindrot.jbcrypt.BCrypt;

import ml.satgrie.pl.ue.model.Profile;
import ml.satgrie.pl.ue.model.Utilisateur;
import ml.satgrie.pl.ue.services.ProfileViewService;
import ml.satgrie.pl.ue.services.UtilisateurViewService;
import ml.satgrie.pl.ue.utils.SessionUtils;
import ml.satgrie.pl.ue.utils.Singleton;

@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginController.class);

	private ProfileViewService profilService;
	private String email = "";
	private String password = "";
	private boolean remember;
	private HttpSession session;
	private Utilisateur user = Singleton.getInstance().getUtilisateur();
	private List<Profile> profiles;

	public static void printStatistics(Statistics statistics) {
		System.out.println("Misses in second level cache:" + statistics.getSecondLevelCacheMissCount());
		System.out.println("Added to second level cache:" + statistics.getSecondLevelCachePutCount());
		System.out.println("Found in second level cache:" + statistics.getSecondLevelCacheHitCount());
		statistics.clear();
	}

	public void login() throws IOException {
		UtilisateurViewService userService = new UtilisateurViewService();

		// HazelcastInstance hz = Hazelcast.newHazelcastInstance();
		if (this.email != null && this.password != null) {
			try {
				//Statistics statistics = HibernateUtils.getCurrentSession().getSessionFactory().getStatistics();
				Utilisateur utilisateur = userService.findByAuth(this.email);
				//printStatistics(statistics);
				//HibernateUtils.getCurrentSession().getSessionFactory().getCache();
				FacesMessage message = null;
				if (utilisateur != null) {
					logger.info("******************************" );
					logger.info("Login manager find user: " + email + " and start login process");
					if (BCrypt.checkpw(this.password, utilisateur.getUtilisateurPwd())) {

						this.profilService = new ProfileViewService();
						this.profiles = this.profilService.findAll();
						for (Profile profile : profiles) {
							for (Utilisateur profiUtilisateur : profile.getUtilisateurs()) {
								if (profiUtilisateur.getUtilisateurId().equals(utilisateur.getUtilisateurId())) {
									utilisateur.setProfile(profile);
								}
							}
						}

						Singleton.getInstance().setUtilisateur(utilisateur);
						this.user = utilisateur;
						Singleton.getInstance().setLoggedIn(true);
						this.session = SessionUtils.getSession();
						this.session.setAttribute("prenom", utilisateur.getUtilisateurPrenom());
						this.session.setAttribute("nom", utilisateur.getUtilisateurNom());
						this.session.setAttribute("user", utilisateur);
						this.session.setAttribute("profil", utilisateur.getProfile());
						FacesContext context = FacesContext.getCurrentInstance();
						session.getAttribute("prenom");

						logger.info("Login success for: " + email);

						logger.info("******************************" );
						try {
							context.getExternalContext().redirect("pages/dashboard.xhtml");
							context.getExternalContext().getFlash().setKeepMessages(true);
							// hazelcast.shutdown();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Login ou mot de passe incorrect."));

				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Login ou mot de passe incorrect."));

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreure est survenue."));
			}

			this.password = "";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login ou mot de passe incorrect."));
		}

	}

	public void modifierMdp() {
		/* instruction to get the ListOfContacts ... */
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			context.getExternalContext().redirect("change-password.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doLogout() throws IOException {
		logger.info("Application start loutOut for user: " + email);
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			Singleton.setInstance(null);
			HttpSession session = SessionUtils.getSession();
			session.invalidate();
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

			addMessage("Vous allez etre", " deconnecté");
			context.getExternalContext().redirect("login.xhtml");
			context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		LoginController.logger = logger;
	}

	public ProfileViewService getProfilService() {
		return profilService;
	}

	public void setProfilService(ProfileViewService profilService) {
		this.profilService = profilService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

}
