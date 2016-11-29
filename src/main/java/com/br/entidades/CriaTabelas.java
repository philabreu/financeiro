/**
 * 
 */
package com.br.entidades;

import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;


/**
 * @author fibezerr
 * 
 */
public class CriaTabelas {

	public static void main(String[] args) {

		Persistence.createEntityManagerFactory("FinanceiroPU");
	}

}
