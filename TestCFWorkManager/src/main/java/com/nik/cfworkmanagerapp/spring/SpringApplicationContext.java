package com.nik.cfworkmanagerapp.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Wrapper to always return a reference to the Spring Application Context from
 * within non-Spring enabled beans. 
 */
public class SpringApplicationContext implements ApplicationContextAware {

	public static ApplicationContext CONTEXT;

	/**
	 * This method is called from within the ApplicationContext once it is
	 * done starting up, it will stick a reference to itself into this bean.
	 * @param context a reference to the ApplicationContext.
	 */
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;		
	}
	
	/**
	 * Retrieves requested bean from underlying bean factory.
	 * @param beanName the name of the bean to get.
	 * @return an Object reference to the named bean.
	 */
	public static Object getBean(String beanName) {
		if (null!=CONTEXT) {
			return CONTEXT.getBean(beanName);
		}
		return null;
	}

	/**
	 * Checks whether requested bean exists in the underlying bean factory.
	 * @param beanName the name of the bean to check if exist.
	 * @return Whether a bean with the given name is defined .
	 */
	public static boolean containsBean(String beanName) {
		return CONTEXT.containsBean(beanName);
	}
}