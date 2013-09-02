package it.polito.ai.gas.business;

public enum EventType {
	/**
	 * Nuovo utente da approvare
	 */
	NEW_USER,
	/**
	 * Creato un nuovo prodotto. Informa il delegate
	 */
	NEW_PRODUCT,
	/**
	 * Notifico a tutti gli utenti che è disponibile
	 * una nuova proposta di acquisto.
	 */
	NEW_PROPOSAL,
	/**
	 * Nuovo messaggio relativo alla proposal
	 */	
	NEW_MESSAGE,
	/**
	 * Notifica che c'è da ritirare un ordine
	 */
	NEW_DELIVERY,
	/**
	 * Nuova richiesta da acquisto per una proposta
	 * da notificare al delegato
	 */
	NEW_PURCHASE_REQUEST,
	/**
	 * I dati relativi al prodotto sono stati aggiornati.
	 * Il delegato va aggiornato
	 */
	UPDATED_PRODUCT,
	/**
	 * Una proposta di acquisto è stata modificata. Informo
	 * tutti i partecipanti ed il delegate.
	 */
	UPDATE_PURCHASE_REQUEST,
	/**
	 * Informazioni se cambiano le date
	 */
	
	UPDATE_PROPOSAL,
	/**
	 * Le informazioni sulle spedizioni sono state modificate. 
	 * Informo gli acquirenti. 
	 */
	UPDATE_DELIVERY,
	/**
	 * La proposta ha raggiunto il minimo numero. 
	 * Notifica utenti (all)
	 */
	
	ALERT_MIN_REACHED,
	/**
	 * Proposta in scadenza. Notifica tutti utenti.
	 */
	ALERT_ENDING_PROPOSAL,
	/**
	 * Proposta scaduta. Notifica solo acquirenti 
	 * e delegato e produttore.
	 */
	ALERT_ENDED_PROPOSAL,
	/**
	 * Allarme generico
	 */
	ADMIN_ALERT  ;

}
