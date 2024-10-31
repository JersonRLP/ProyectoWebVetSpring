package com.citvet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "distritos")
public class Distrito {

    @Id
    @Column(name = "cod_distrito")
    private int codDistrito;

    @Column(name = "nom_distrito")
    private String nom_distrito;

    @Column(name = "condicion")
    private char condicion;

    // Getters y setters
    
	public int getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(int codDistrito) {
		this.codDistrito = codDistrito;
	}

	public String getNom_distrito() {
		return nom_distrito;
	}

	public void setNom_distrito(String nom_distrito) {
		this.nom_distrito = nom_distrito;
	}

	public char getCondicion() {
		return condicion;
	}

	public void setCondicion(char condicion) {
		this.condicion = condicion;
	}
    
    
}
