package services;

import model.Customer;

public class CheckerThread extends Thread{
	protected OrderService orderService;
	protected CustomerService customerService;
	
	
	public CheckerThread(OrderService orderService, CustomerService customerService) {
		this.customerService = customerService;
		this.orderService = orderService;
	}
	
	
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true) {
			for (Customer customer : customerService.getAll()) {
				customerService.updateSuspicion(customer.getEntityID(),orderService.checkSuspicion(customer.getEntityID()));			
				
			}
			try {
				sleep(21600000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
