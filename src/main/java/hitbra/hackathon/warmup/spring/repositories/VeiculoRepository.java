package hitbra.hackathon.warmup.spring.repositories;

import hitbra.hackathon.warmup.spring.model.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {

	/**
	 * Buscas Simples
	 */
	public List<Veiculo> findByMarca(String marca);
    public List<Veiculo> findByModelo(String modelo);
    public List<Veiculo> findByAno(String ano);
    public List<Veiculo> findByValor(String valor);
    public List<Veiculo> findByLocal(String local);
    public List<Veiculo> findByPlaca(String placa);
    public List<Veiculo> findByDataEntrada(String dataEntrada);
    
    /**
     * Buscas Complosta
     */
    public List<Veiculo> findByMarcaAndModeloAndAno(String marca,String modelo, String ano);
    public List<Veiculo> findByMarcaAndModelo(String marca, String modelo);
    public List<Veiculo> findByMarcaAndAno(String marca, String ano);
    public List<Veiculo> findByModeloAndAno(String modelo,String ano);
    
    

}
