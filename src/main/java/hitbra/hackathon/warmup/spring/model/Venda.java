package hitbra.hackathon.warmup.spring.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="vendas")
public class Venda {
	
	private String  idVeiculo;
	private String valor;
	private String nomeComprador;
	private String documentoComprador;
	private String data;

	@Id
    public String id;

	public String getId_veiculo() {
		return idVeiculo;
	}

	public void setId_veiculo(String idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	
	public String getNomeComprador() {
		return nomeComprador;
	}

	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}

	
	public String getDocumentoComprador() {
		return documentoComprador;
	}

	public void setDocumentoComprador(String documentoComprador) {
		this.documentoComprador = documentoComprador;
	}

	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
