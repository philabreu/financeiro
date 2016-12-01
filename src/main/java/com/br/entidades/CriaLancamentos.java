/**
 * 
 */
package com.br.entidades;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.modelos.Lancamento;
import com.br.modelos.Pessoa;
import com.br.modelos.TipoLancamento;
import com.br.util.JpaUtil;

/**
 * @author Filipe Bezerra
 * 
 */
public class CriaLancamentos {

	public static void main(String[] args) {

		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction transacao = manager.getTransaction();
		transacao.begin();

		Calendar dataVencimento1 = Calendar.getInstance();
		dataVencimento1.set(2016, 11, 1, 0, 0, 0);

		Calendar dataVencimento2 = Calendar.getInstance();
		dataVencimento2.set(2016, 12, 1, 0, 0, 0);

		Pessoa cliente = new Pessoa();
		cliente.setNome("Experian");

		Pessoa fornecedor = new Pessoa();
		fornecedor.setNome("Capemi");

		Lancamento lancamento1 = new Lancamento();
		lancamento1.setDescricao("Venda de software");
		lancamento1.setPessoa(cliente);
		lancamento1.setDataVencimento(dataVencimento1.getTime());
		lancamento1.setDataPagamento(dataVencimento1.getTime());
		lancamento1.setValor(new BigDecimal(13_000));
		lancamento1.setTipo(TipoLancamento.RECEITA);

		Lancamento lancamento2 = new Lancamento();
		lancamento2.setDescricao("Serviço de infraestrutura");
		lancamento2.setPessoa(cliente);
		lancamento2.setDataVencimento(dataVencimento1.getTime());
		lancamento2.setDataPagamento(dataVencimento1.getTime());
		lancamento2.setValor(new BigDecimal(10_000));
		lancamento2.setTipo(TipoLancamento.RECEITA);

		Lancamento lancamento3 = new Lancamento();
		lancamento3.setDescricao("Salários e treinamentos");
		lancamento3.setPessoa(fornecedor);
		lancamento3.setDataVencimento(dataVencimento2.getTime());
		lancamento3.setValor(new BigDecimal(20_000));
		lancamento3.setTipo(TipoLancamento.DESPESA);

		manager.persist(cliente);
		manager.persist(fornecedor);
		manager.persist(lancamento1);
		manager.persist(lancamento2);
		manager.persist(lancamento3);

		transacao.commit();
		manager.close();

	}

}
