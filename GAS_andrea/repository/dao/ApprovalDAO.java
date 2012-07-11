package it.polito.ai.gas.dao_old;

import it.polito.ai.gas.business.Approval;
public interface ApprovalDAO {

	public int addApproval(Approval approval);
	
	public int approve(int id, String admin);
	
}
