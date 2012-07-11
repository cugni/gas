package it.polito.ai.gas.dao_old;

public class DAOFactory {

	private static DAOFactoryHibernate daoFactoryHibernate = null;
	
	public static DAOFactoryHibernate getDAOFactoryHibernate() {
		if (daoFactoryHibernate == null)
			daoFactoryHibernate = new DAOFactoryHibernate();
		
		return daoFactoryHibernate;
	}
	
	
}
