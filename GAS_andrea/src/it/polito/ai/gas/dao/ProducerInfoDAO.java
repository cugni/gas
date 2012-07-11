package it.polito.ai.gas.dao;

import it.polito.ai.gas.business.ProducerInfo;

public interface ProducerInfoDAO {

	public int addProducerInfo(ProducerInfo producerInfo);
	
	public int deleteProducerInfo(int id);
	
	public int updateProducerInfo(ProducerInfo producerInfo);
}
