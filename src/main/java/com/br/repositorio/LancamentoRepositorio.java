/**
 * 
 */
package com.br.repositorio;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.br.modelos.Lancamento;

/**
 * @author Filipe Bezerra
 * 
 */
public class LancamentoRepositorio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public LancamentoRepositorio(EntityManager manager) {

		this.manager = manager;
	}

	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento",
				Lancamento.class);
		return query.getResultList();

	}

	public void adicionar(Lancamento lancamento) {

		this.manager.persist(lancamento);

	}

}
