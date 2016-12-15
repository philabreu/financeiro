/**
 * 
 */
package com.br.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.br.modelos.Lancamento;
import com.br.util.JpaUtil;

/**
 * @author Filipe Bezerra
 * 
 */

/*@Named
 @ViewScoped
 public class ConsultaLancamentosBean implements Serializable {

 *//**
	 * 
	 */
/*
 * private static final long serialVersionUID = 1L;
 * 
 * @Inject private LancamentoRepositorio lancamentosRepository;
 * 
 * private List<Lancamento> lancamentos;
 * 
 * public void consultar() {
 * 
 * this.lancamentos = lancamentosRepository.todos(); }
 * 
 * public List<Lancamento> getLancamentos() { return lancamentos;
 * 
 * }
 * 
 * }
 */

@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Lancamento> lancamentos;

	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento",
				Lancamento.class);
		this.lancamentos = query.getResultList();
		manager.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
