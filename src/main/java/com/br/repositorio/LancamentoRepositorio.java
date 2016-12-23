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
		TypedQuery<Lancamento> query = manager.createQuery(
				"from Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {

		this.manager.persist(lancamento);
	}

	public List<String> descricaoQueContem(String descricao) {

		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento"
						+ "where upper(descricao) like upper(:descricao)",
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}
	
	public Lancamento porId(Long id){
		
		return manager.find(Lancamento.class, id);
	}

	public Lancamento guardar(Lancamento lancamento){
		
		return this.manager.merge(lancamento);
	}
	
	public void remover(Lancamento lancamento){
		
		this.manager.remove(lancamento);
	}
	
}
