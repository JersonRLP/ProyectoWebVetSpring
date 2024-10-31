package com.citvet.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pagos")
public class Pago {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_pago")
    private int cod_pago;

    @ManyToOne
    @JoinColumn(name = "cod_cita")
    Cita cita;

    @Column(name = "monto_total")
    private double monto_total;

    @Column(name = "fecha_hora_pago")
    private Date fecha_hora_pago;
    
    @Column(name = "tipo_comprobante")
    private String tipo_comprobante;

    // Getters y setters
    
	public int getCod_pago() {
		return cod_pago;
	}

	public void setCod_pago(int cod_pago) {
		this.cod_pago = cod_pago;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public double getMonto_total() {
		return monto_total;
	}

	public void setMonto_total(double monto_total) {
		this.monto_total = monto_total;
	}

	public Date getFecha_hora_pago() {
		return fecha_hora_pago;
	}

	public void setFecha_hora_pago(Date fecha_hora_pago) {
		this.fecha_hora_pago = fecha_hora_pago;
	}

	public String getTipo_comprobante() {
		return tipo_comprobante;
	}

	public void setTipo_comprobante(String tipo_comprobante) {
		this.tipo_comprobante = tipo_comprobante;
	}

      
}
