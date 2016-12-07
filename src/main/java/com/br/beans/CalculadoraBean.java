/**
 * 
 */
package com.br.beans;

import javax.faces.bean.ManagedBean;

/**
 * @author Filipe Bezerra
 * 
 */
@ManagedBean
public class CalculadoraBean {

	private Double valorA;
	private Double valorB;
	private Double resultado;

	public void somar() {
		this.resultado = this.valorA + this.valorB;

	}

	public Double getValorA() {
		return valorA;
	}

	public void setValorA(Double valorA) {
		this.valorA = valorA;
	}

	public Double getValorB() {
		return valorB;
	}

	public void setValorB(Double valorB) {
		this.valorB = valorB;
	}

	public Double getResultado() {
		return resultado;
	}

	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}

}
