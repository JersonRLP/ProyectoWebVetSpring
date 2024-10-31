package com.citvet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_servicio")
public class TipoServicio {

    @Id
    @Column(name = "cod_tipo_servicio")
    private int codTipoServicio;

    @Column(name = "descripcion")
    private String descripcion_tipo;

    @Column(name = "condicion")
    private char condicion;
    
    // Getters y setters

	public int getCodTipoServicio() {
		return codTipoServicio;
	}

	public void setCodTipoServicio(int codTipoServicio) {
		this.codTipoServicio = codTipoServicio;
	}

	public String getDescripcion_tipo() {
		return descripcion_tipo;
	}

	public void setDescripcion_tipo(String descripcion_tipo) {
		this.descripcion_tipo = descripcion_tipo;
	}

	public char getCondicion() {
		return condicion;
	}

	public void setCondicion(char condicion) {
		this.condicion = condicion;
	}

	 
}
