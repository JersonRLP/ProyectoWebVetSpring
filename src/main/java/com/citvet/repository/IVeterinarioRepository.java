package com.citvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citvet.model.Veterinario;

public interface IVeterinarioRepository extends JpaRepository<Veterinario, Integer> {
	Veterinario findByCodVeterinario(int codVeterinario);
}
