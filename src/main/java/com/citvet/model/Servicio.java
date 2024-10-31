package com.citvet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @Column(name = "cod_servicio")
    private int codServicio;

    @Column(name = "nombre_servicio")
    private String nombre_servicio;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cod_tipo_servicio")
    TipoServicio tipoServicio;

    @Column(name = "precio")
    private double precio;

    @Column(name = "duracion_estimada")
    private int duracion_estimada;

    @Column(name = "materiales_necesarios")
    private String materiales_necesarios;

    @Column(name = "estado")
    private String estado;
   
    // Getters y setters
    
	public int getCodServicio() {
		return codServicio;
	}

	public void setCodServicio(int codServicio) {
		this.codServicio = codServicio;
	}

	public String getNombre_servicio() {
		return nombre_servicio;
	}

	public void setNombre_servicio(String nombre_servicio) {
		this.nombre_servicio = nombre_servicio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getDuracion_estimada() {
		return duracion_estimada;
	}

	public void setDuracion_estimada(int duracion_estimada) {
		this.duracion_estimada = duracion_estimada;
	}

	public String getMateriales_necesarios() {
		return materiales_necesarios;
	}

	public void setMateriales_necesarios(String materiales_necesarios) {
		this.materiales_necesarios = materiales_necesarios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    

	
	
}
