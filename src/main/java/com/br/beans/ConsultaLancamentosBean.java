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
import com.br.repositorio.LancamentoRepositorio;
import com.br.util.JpaUtil;

/**
 * @author Filipe Bezerra
 * 
 */
@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;

	public void consultar() {

		EntityManager manager = JpaUtil.getEntityManager();
		LancamentoRepositorio lancamentos = new LancamentoRepositorio(manager);
		this.lancamentos = lancamentos.todos();

		manager.close();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;

	}

}
