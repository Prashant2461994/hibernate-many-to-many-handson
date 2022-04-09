package com.seleniumexpress.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.seleniumexpress.models.Customer;
import com.seleniumexpress.models.Order;
import com.seleniumexpress.utils.HibernateUtils;

/**
 * Hello world!
 *
 */
public class StudentApp {

	private static Session session = null;

	public static void main(String[] args) {

		// 1. create configuration
		// 2. create session factory
		// 3. initialize the session object

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		session = sessionFactory.openSession();

		Transaction transaction = session.getTransaction();

		transaction.begin();

		Customer customer = new Customer();
		customer.setCustomerMobile("7829442793");
		customer.setCustomerName("Prashant");

		Order order = new Order();
		order.setItemName("TV");
		order.setCosts(4000.00);
		order.setCustomer(customer);

		
		List<Order> list =    session.createQuery("Select o from Order o where o.customer.customer_id =:CUSTOMER_ID ",Order.class)
				             .setParameter("CUSTOMER_ID", 1)
				             .list();
		
		list.forEach(o -> System.out.println(o));
		
		session.persist(order);
		transaction.commit();

		session.close();
		sessionFactory.close();

	}

}
