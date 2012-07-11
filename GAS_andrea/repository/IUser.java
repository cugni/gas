package it.polito.ai.gas.beans;

import it.polito.ai.gas.business.ProducerInfo;
import it.polito.ai.gas.business.Statistics;

import java.util.Date;

public interface IUser_old {

	public abstract String getUsername();

	public abstract void setUsername(String name);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract int getRole();

	public abstract void setRole(int role);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getSurname();

	public abstract void setSurname(String surname);

	public abstract Date getBirthDate();

	public abstract void setBirthDate(Date birth_date);

	public abstract Statistics getStatistics();

	public abstract void setStatistics(Statistics statistics);

	public abstract ProducerInfo getProducerInfo();

	public abstract void setProducerInfo(ProducerInfo producer_info);

}