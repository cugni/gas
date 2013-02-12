package it.polito.ai.gas.business;


public aspect Event_Pointcut {
	
	pointcut interceptPersist(InterceptPersist tbi) : 
		execution(void InterceptPersist.persist()) && this(tbi);
	
	after (InterceptPersist tbi) : interceptPersist(tbi) {		
		
		Event e = new Event(tbi);
		e.persist();
		
		// Creo "notification" per gli interessati all'evento
		
	}

}
