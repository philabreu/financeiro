/**
 * 
 */
package com.br.controladores;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.br.modelos.Lancamento;
import com.br.modelos.Pessoa;
import com.br.modelos.TipoLancamento;
import com.br.repositorio.LancamentoRepositorio;
import com.br.repositorio.PessoaRepositorio;
import com.br.servicos.CadastroLancamentos;
import com.br.servicos.NegocioException;
import com.br.util.JpaUtil;

/**
 * @author Filipe Bezerra
 * 
 */
@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	public void prepararCadastro() {

		EntityManager manager = JpaUtil.getEntityManager();

		try {

			PessoaRepositorio pessoas = new PessoaRepositorio(manager);
			this.todasPessoas = pessoas.todas();
		} finally {

			manager.close();
		}
	}

	public void salvar() {

		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction transacao = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();

		try {

			transacao.begin();

			CadastroLancamentos cadastro = new CadastroLancamentos(	new LancamentoRepositorio(manager));
			cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();

			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));

			transacao.commit();
		} catch (NegocioException e) {

			transacao.rollback();

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);

		} finally {

			manager.close();
		}
	}

	public TipoLancamento[] getTipoLancamentos() {

		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}
}
