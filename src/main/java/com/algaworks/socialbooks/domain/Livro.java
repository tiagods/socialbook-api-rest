package com.algaworks.socialbooks.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Livro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	private String nome;
	@JsonInclude(Include.NON_NULL)
	@Temporal(TemporalType.DATE)
	private Date publicacao;
	@JsonInclude(Include.NON_NULL)
	private String editora;
	@JsonInclude(Include.NON_NULL)
	private String resumo;
	@JsonInclude(Include.NON_NULL)
	
	@OneToMany
	private List<Comentario> comentarios;
	@JsonInclude(Include.NON_NULL)
	private String autor;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the publicacao
	 */
	public Date getPublicacao() {
		return publicacao;
	}
	/**
	 * @param publicacao the publicacao to set
	 */
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	/**
	 * @return the editora
	 */
	public String getEditora() {
		return editora;
	}
	/**
	 * @param editora the editora to set
	 */
	public void setEditora(String editora) {
		this.editora = editora;
	}
	/**
	 * @return the resumo
	 */
	public String getResumo() {
		return resumo;
	}
	/**
	 * @param resumo the resumo to set
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	/**
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	/**
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}
	/**
	 * @param autor the autor to set
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
}
