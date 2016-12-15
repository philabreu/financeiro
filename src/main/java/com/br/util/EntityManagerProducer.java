/**
 * 
 */
package com.br.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Filipe Bezerra
 * 
 */

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory fabrica;

	public EntityManagerProducer() {

		this.fabrica = Persistence.createEntityManagerFactory("FinanceiroPU");
	}

	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {

		return fabrica.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager manager) {

		manager.close();
	}
}
