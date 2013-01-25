GAS
===

1. ennesimo refactor del SQL (sapevate che "order" è una parola riservata per hibernate?)
2. dopo aver betemmiato con Roo ho rifatto il progetto dall'inizio e forse sto iniziando a capire come prenderlo...
3. ho implementato il modulo di sicurezza, a livello di controller (vedere applicationContext-security.xml e la classe it.polito.ai.gas.security.GASAuthenticationProvider) e di interfaccia (vedere annotazioni <sec:authorize …> all'interno delle views, in particolare menu.jspx)
4. ad ogni ruolo corrisponde un'interfaccia diversa e relative azioni
5. aggiunto blocco di login/register nella pagina iniziale

ovviamente non c'è ancora nessun meccanismo di match tra le varie entità...