package br.com.faculdadedelta.springpos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadedelta.springpos.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
