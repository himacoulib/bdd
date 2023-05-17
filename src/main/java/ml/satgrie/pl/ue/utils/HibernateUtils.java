package ml.satgrie.pl.ue.utils;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import ml.satgrie.pl.ue.model.Budget;
import ml.satgrie.pl.ue.model.Calendrier;
import ml.satgrie.pl.ue.model.Cercle;
import ml.satgrie.pl.ue.model.CercleId;
import ml.satgrie.pl.ue.model.Chapitre;
import ml.satgrie.pl.ue.model.Commune;
import ml.satgrie.pl.ue.model.CommuneId;
import ml.satgrie.pl.ue.model.Contractant;
import ml.satgrie.pl.ue.model.Contractanttype;
import ml.satgrie.pl.ue.model.Devise;
import ml.satgrie.pl.ue.model.Document;
import ml.satgrie.pl.ue.model.Etatavancement;
import ml.satgrie.pl.ue.model.Instrumentfinancement;
import ml.satgrie.pl.ue.model.Localisation;
import ml.satgrie.pl.ue.model.LocalisationId;
import ml.satgrie.pl.ue.model.LocalisationSecteur;
import ml.satgrie.pl.ue.model.Pays;
import ml.satgrie.pl.ue.model.Photo;
import ml.satgrie.pl.ue.model.Pointfocal;
import ml.satgrie.pl.ue.model.Profile;
import ml.satgrie.pl.ue.model.Projet;
import ml.satgrie.pl.ue.model.Projetchapitre;
import ml.satgrie.pl.ue.model.Region;
import ml.satgrie.pl.ue.model.RegionId;
import ml.satgrie.pl.ue.model.Secteur;
import ml.satgrie.pl.ue.model.SecteurHierar;
import ml.satgrie.pl.ue.model.SecteurId;
import ml.satgrie.pl.ue.model.SourceFinancement;
import ml.satgrie.pl.ue.model.SousSecteur;
import ml.satgrie.pl.ue.model.SousSecteurId;
import ml.satgrie.pl.ue.model.Utilisateur;

public class HibernateUtils {
//	private static StandardServiceRegistry registry;
//
//	public static SessionFactory getSessionFactory() {
//
//		try {
//			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
//
//			Map<String, Object> settings = new HashMap<>();
//			settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
//			settings.put(Environment.URL, "jdbc:mysql://localhost:3306/lastuebdd?reconnect=true");
//			settings.put(Environment.USER, "PlUser");
//			settings.put(Environment.PASS, "Satgrie12PL@@");
//			settings.put(Environment.HBM2DDL_AUTO, "update");
//			settings.put(Environment.SHOW_SQL, true);
//			settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//			settings.put("hibernate.enable_lazy_load_no_trans", true);
//
//			// HikariCP settings
//
//			// Maximum waiting time for a connection from the pool
//			settings.put("hibernate.hikari.connectionTimeout", "20000");
//			// Minimum number of ideal connections in the pool
//			settings.put("hibernate.hikari.minimumIdle", "200");
//			// Maximum number of actual connection in the pool
//			settings.put("hibernate.hikari.maximumPoolSize", "15000");
//			// Maximum time that a connection is allowed to sit ideal in the pool
//			settings.put("hibernate.hikari.idleTimeout", "300000");
//			settings.put("hibernate.hikari.maxLifetime", "20000");
////	            settings.put("hibernate.hikari.leakDetectionThreshold", "60000");
////	            
////	            settings.put("hibernate.hikari.dataSource.prepStmtCacheSize", "5000");
////	            settings.put("hibernate.hikari.dataSource.prepStmtCacheSqlLimit", "10000");
////	            settings.put("hibernate.hikari.dataSource.cachePrepStmts", true);
////	            settings.put("hibernate.hikari.dataSource.useServerPrepStmts", true);
////	            settings.put("hibernate.hikari.dataSource.useLocalSessionState", true);
////	            settings.put("hibernate.hikari.dataSource.useLocalTransactionState", true);
////	            settings.put("hibernate.hikari.dataSource.rewriteBatchedStatements", true);
////
////	            settings.put("hibernate.hikari.dataSource.cacheResultSetMetadata", true);
////	            settings.put("hibernate.hikari.dataSource.cacheServerConfiguration", true);
////	            settings.put("hibernate.hikari.dataSource.elideSetAutoCommits", true);
////	            settings.put("hibernate.hikari.dataSource.maintainTimeStats", false);
//
//			registryBuilder.applySettings(settings);
//
//			registry = registryBuilder.build();
//			MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Budget.class)
//					.addAnnotatedClass(Calendrier.class).addAnnotatedClass(CercleId.class)
//					.addAnnotatedClass(Cercle.class).addAnnotatedClass(Chapitre.class).addAnnotatedClass(Commune.class)
//					.addAnnotatedClass(CommuneId.class).addAnnotatedClass(Contractant.class)
//					.addAnnotatedClass(Contractanttype.class).addAnnotatedClass(Devise.class)
//					.addAnnotatedClass(Etatavancement.class).addAnnotatedClass(Instrumentfinancement.class)
//					.addAnnotatedClass(Localisation.class).addAnnotatedClass(LocalisationId.class)
//					.addAnnotatedClass(Pays.class).addAnnotatedClass(Photo.class).addAnnotatedClass(Pointfocal.class)
//					.addAnnotatedClass(Profile.class).addAnnotatedClass(Projet.class)
//					.addAnnotatedClass(Projetchapitre.class).addAnnotatedClass(RegionId.class)
//					.addAnnotatedClass(Region.class).addAnnotatedClass(Secteur.class).addAnnotatedClass(SecteurId.class)
//					.addAnnotatedClass(SourceFinancement.class).addAnnotatedClass(SousSecteur.class)
//					.addAnnotatedClass(SousSecteurId.class).addAnnotatedClass(Document.class)
//					.addAnnotatedClass(Utilisateur.class).addAnnotatedClass(LocalisationSecteur.class)
//					.addAnnotatedClass(SecteurHierar.class);
//			Metadata metadata = sources.getMetadataBuilder().build();
//			SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//			return sessionFactory;
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//		}
//
//	}
//
//	public static void shutdown() {
//		if (registry != null) {
//			StandardServiceRegistryBuilder.destroy(registry);
//		}
//	}

	private static final Session currentSession = getSessionFactory().openSession();

	private static Transaction currentTransaction;

	private static StandardServiceRegistry registry;

	public static Session openCurrentSessionwithTransaction() {
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public static void closeCurrentSession() {
		currentSession.close();
	}

	public static void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public static SessionFactory getSessionFactory() {
		try {
			Configuration configuration = (new Configuration()).addPackage("ml.satgrie.pl.ue.model")
					.addAnnotatedClass(Budget.class).addAnnotatedClass(Calendrier.class)
					.addAnnotatedClass(CercleId.class).addAnnotatedClass(Cercle.class).addAnnotatedClass(Chapitre.class)
					.addAnnotatedClass(Commune.class).addAnnotatedClass(CommuneId.class)
					.addAnnotatedClass(Contractant.class).addAnnotatedClass(Contractanttype.class)
					.addAnnotatedClass(Devise.class).addAnnotatedClass(Etatavancement.class)
					.addAnnotatedClass(Instrumentfinancement.class).addAnnotatedClass(Localisation.class)
					.addAnnotatedClass(LocalisationId.class).addAnnotatedClass(Pays.class)
					.addAnnotatedClass(Photo.class).addAnnotatedClass(Pointfocal.class).addAnnotatedClass(Profile.class)
					.addAnnotatedClass(Projet.class).addAnnotatedClass(Projetchapitre.class)
					.addAnnotatedClass(RegionId.class).addAnnotatedClass(Region.class).addAnnotatedClass(Secteur.class)
					.addAnnotatedClass(SecteurId.class).addAnnotatedClass(SourceFinancement.class)
					.addAnnotatedClass(SousSecteur.class).addAnnotatedClass(SousSecteurId.class)
					.addAnnotatedClass(Document.class).addAnnotatedClass(Utilisateur.class)
					.addAnnotatedClass(LocalisationSecteur.class).addAnnotatedClass(SecteurHierar.class).configure();
			StandardServiceRegistryBuilder builder = (new StandardServiceRegistryBuilder())
					.applySettings(configuration.getProperties());
			registry = builder.build();
			SessionFactory sessionFactory = configuration.buildSessionFactory((ServiceRegistry) builder.build());
			return sessionFactory;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static void shutdown() {
		if (registry != null)
			StandardServiceRegistryBuilder.destroy((ServiceRegistry) registry);
	}

	public static Session getCurrentSession() {
		return currentSession;
	}

	public static Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		ml.satgrie.pl.ue.utils.HibernateUtils.currentTransaction = currentTransaction;
	}

}
