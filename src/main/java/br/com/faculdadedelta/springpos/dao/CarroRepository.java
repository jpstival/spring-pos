package br.com.faculdadedelta.springpos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.springpos.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

}
