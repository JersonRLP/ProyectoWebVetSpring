package com.citvet.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_mascota")
    private int codMascota;

    @Column(name = "nombre_mascota")
    private String nombre_mascota;

    @Column(name = "chip_identificacion")
    private String chip_identificacion;

    @ManyToOne
    @JoinColumn(name = "cod_especie")
    Especie especie;

    @ManyToOne
    @JoinColumn(name = "cod_raza")
    Raza raza;
    
    @Column(name = "color_mascota")
    private String color;
    
    @Column(name = "sexo_mascota")
    private String sexo;
    
    @Column(name = "fecha_nacimiento_mascota")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @Column(name = "peso_mascota")
    private double peso;

    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    Cliente cliente;

    @Column(name = "estado")
    private String estado;
    
    @Column(name = "foto_mascota")
    private String foto_mascota;
    
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Cita> Citas = new HashSet<>();
    // Getters y setters
    


	public String getNombre_mascota() {
		return nombre_mascota;
	}

	public int getCodMascota() {
		return codMascota;
	}

	public void setCodMascota(int codMascota) {
		this.codMascota = codMascota;
	}

	public void setNombre_mascota(String nombre_mascota) {
		this.nombre_mascota = nombre_mascota;
	}

	public String getChip_identificacion() {
		return chip_identificacion;
	}

	public void setChip_identificacion(String chip_identificacion) {
		this.chip_identificacion = chip_identificacion;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFoto_mascota() {
		return foto_mascota;
	}

	public void setFoto_mascota(String foto_mascota) {
		this.foto_mascota = foto_mascota;
	}
     
}
