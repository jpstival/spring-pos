package br.com.faculdadedelta.springpos.model.type;

public enum Sexo {
	
	MASCULINO("Masculino"),  
	FEMININO("Feminino");
	
	private String descricao;
	
	private Sexo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
