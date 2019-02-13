package it.clever.spring.ui;

import java.util.List;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import it.clever.spring.config.ConfigurationBean;

import it.clever.spring.entities.Order;
import it.clever.spring.entities.Utente;
import it.clever.spring.exception.AuthenticationException;
import it.clever.spring.services.OrderService;
import it.clever.spring.services.UserService;

public class ApplicationUI {
	// appplicazione desktop ... il contesto di spring lo sto creando
	static AbstractApplicationContext  context = null;
	
	UserService userService;
	
	OrderService orderService;
	// questa classe nei progetti web sarà una classe di startup.... 
	// prima con xml... facevamo load on startup 1..
	// nella reference ... di spring trovi i due modi xml e annotation....
	
	
	public static void main(String[] args) {
		
		try {

			// Caricamento del contesto e lettura delle configurazioni.
			// Gli passiamo un .class una nostra classe per passargli le configunazioni
			// Ce ne potrebbero essere di più di una 
			
			// eccolo qui il contesto di spring... 
			context = new AnnotationConfigApplicationContext(ConfigurationBean.class);
			
			ApplicationUI ui = new ApplicationUI();
			ui.authenticateUser();
			
			ui.createNewOrder();
			
			ui.loadAllOrders();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			/*
			 * Spegnimento controllato del contesto;
			 * In questo modo, prima di eliminare tutti i bean controllati,
			 * vengono chiamati tutti i metodi dei bean annotati con @PreDestroy.
			 */
			if(context != null)
				context.registerShutdownHook();
			
			System.exit(0);
		}
		
	}
//	// esempio di come iniettare quella classe li... di configuration bean.
//	@Autowired 
//	@Qualifier("puntamentiWsSos")
//	public WSData puntamentiWsSos;
	
	/**
	 * Metodo per l'autenticazione di un utente
	 * @throws AuthenticationException
	 */
	public void authenticateUser() throws AuthenticationException {
		
		System.out.println("\nAutenticazione utente in corso..");
		
		userService = (UserService)context.getBean("userService");
		
		Utente user = userService.authorize("admin", "admin");
		if(user != null) {
			System.out.println("\n" + user + "\n");
			System.out.println("\nUtente correttamente autenticato\n");
		}
	}
	
	/**
	 * Metodo per la creazione di un nuovo ordine.
	 */
	public void createNewOrder() {
		
		System.out.println("\nCreazione nuovo ordine in corso..");
		
		orderService = (OrderService)context.getBean("orderService");
		try {
			Order order = orderService.createNewOrder(1L, 1l, 10, 500);
			System.out.println(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo per il recupero degli ordini.
	 */
	public void loadAllOrders() {
		
		System.out.println("\n\nCaricamento di tutti gli ordini..");
		
		orderService = (OrderService)context.getBean("orderService");
		try {
			List<Order> orderList = orderService.loadAllOrders();
			for(Order order : orderList) {
				System.out.println(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
