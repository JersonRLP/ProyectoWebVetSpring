package com.citvet.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @Column(name = "cod_cita")
    private int codCita;

    @ManyToOne
    @JoinColumn(name = "cod_cliente")
    Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cod_mascota")
    Mascota mascota;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_cita")
    private Date fecha_cita;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "hora_cita")
    private String hora_cita;

    @ManyToOne
    @JoinColumn(name = "cod_servicio")
    Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "cod_veterinario")
    Veterinario vet;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "duracion_estimada")
    private String duracion_estimada;

    @Column(name = "estado_cita")
    private String estado_cita;

    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Pago> Pago = new HashSet<>();
    
    // Getters y setters
    

	public Cliente getCliente() {
		return cliente;
	}

	public String getHora_cita() {
		return hora_cita;
	}

	public void setHora_cita(String hora_cita) {
		this.hora_cita = hora_cita;
	}

	public String getDuracion_estimada() {
		return duracion_estimada;
	}

	public void setDuracion_estimada(String duracion_estimada) {
		this.duracion_estimada = duracion_estimada;
	}

	public int getCodCita() {
		return codCita;
	}

	public void setCodCita(int codCita) {
		this.codCita = codCita;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Date getFecha_cita() {
		return fecha_cita;
	}

	public void setFecha_cita(Date fecha_cita) {
		this.fecha_cita = fecha_cita;
	}



	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}



	public Veterinario getVet() {
		return vet;
	}

	public void setVet(Veterinario vet) {
		this.vet = vet;
	}



	public String getEstado_cita() {
		return estado_cita;
	}

	public void setEstado_cita(String estado_cita) {
		this.estado_cita = estado_cita;
	}

     
}
