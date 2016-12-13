/**
 * 
 */
package com.br.servicos;

import java.io.Serializable;
import java.util.Date;

import com.br.modelos.Lancamento;
import com.br.repositorio.LancamentoRepositorio;

/**
 * @author Filipe Bezerra
 * 
 */
public class CadastroLancamentos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LancamentoRepositorio lancamentos;

	public CadastroLancamentos(LancamentoRepositorio lancamentos) {

		this.lancamentos = lancamentos;
	}

	public void salvar(Lancamento lancamento) throws NegocioException {

		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {

			throw new NegocioException("Data de pagamento não pode ser no futuro.");

		}

		this.lancamentos.adicionar(lancamento);

	}

}
