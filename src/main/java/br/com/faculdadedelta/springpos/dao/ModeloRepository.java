package br.com.faculdadedelta.springpos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.springpos.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}
