package com.br.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.controladores.Usuario;

@WebFilter("*.xhtml")
public class AutorizacaoFilter implements Filter {

	@Inject
	private Usuario autenticacao;

	@Override
	public void doFilter(ServletRequest requisicao, ServletResponse resposta, FilterChain chain) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) resposta;
		HttpServletRequest request = (HttpServletRequest) requisicao;

		if (!autenticacao.isLogado()
				&& !request.getRequestURI().endsWith("/login.xhtml")
				&& !request.getRequestURI().contains("/javax.faces.resource/")) {

			response.sendRedirect(request.getContextPath() + "/login.xhtml");
		} else {

			chain.doFilter(requisicao, resposta);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
