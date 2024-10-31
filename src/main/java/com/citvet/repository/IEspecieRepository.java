package com.citvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citvet.model.Especie;

public interface IEspecieRepository extends JpaRepository<Especie, Integer> {

}
