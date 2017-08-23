package br.com.faculdadedelta.springpos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.springpos.model.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

}
