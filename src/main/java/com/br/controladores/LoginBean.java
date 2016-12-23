/**
 * 
 */
package com.br.controladores;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Filipe Bezerra
 * 
 */

@Named
@ApplicationScoped
public class LoginBean {

	@Inject
	private Usuario usuario;

	private String nomeUsuario;

	private String senha;

/*	@PostConstruct
	public void init(){
		
		System.out.println("teste");
	}*/
	public String login() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (("admin".equals(this.nomeUsuario)) && ("123".equals(this.senha))) {

			this.usuario.setNome(this.nomeUsuario);
			this.usuario.setDataLogin(new Date());

			return "/ConsultaLancamentos?faces-redirect=true";
		} else {

			FacesMessage mensagem = new FacesMessage("Usuário/senha inválidos");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}

		return null;
	}

	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
