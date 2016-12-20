/**
 * 
 */
package com.br.util;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

/**
 * @author Filipe Bezerra
 * 
 */
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private @Inject	EntityManager manager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {

		EntityTransaction transacao = manager.getTransaction();

		boolean criador = false;

		try {
			if (!transacao.isActive()) {
				/*
				 * truque para fazer rollback no que já passou (senão, um futuro
				 * commit, confirmaria até mesmo operações sem transação)
				 */
				transacao.begin();
				transacao.rollback();

				// agora sim inicia a transação
				transacao.begin();

				criador = true;
			}

			return context.proceed();
		} catch (Exception e) {

			if (transacao != null && criador) {

				transacao.rollback();
			}

			throw e;
		} finally {
			if (transacao != null && transacao.isActive() && criador) {

				transacao.commit();
			}

		}

	}
}
