package hitbra.hackathon.warmup.spring.controllers;

import hitbra.hackathon.warmup.spring.model.Veiculo;
import hitbra.hackathon.warmup.spring.repositories.VeiculoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.swing.text.DateFormatter;


@RestController
@RequestMapping("/vehicles")
@Api(value = "/vehicles")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repo;
    
    @GetMapping("/")
    public List<Veiculo> getAll() {
        return repo.findAll();
    }
    
    @GetMapping("/with-param")
	@ApiOperation(value = "Obtém uma mensagem padrão",response = Veiculo.class)
	@ApiResponses(value = {@ApiResponse(code = 404, message = "Vehicle not found") })
	public Object getVehiclesBy(
			@ApiParam(value = "Valor da Marca", required = false) 
		@RequestParam(value="marca", required=false) String marca,
		
			@ApiParam(value = "Valor da Modelo", required = false) 
		@RequestParam(value="modelo", required=false) String modelo,
		
			@ApiParam(value = "Valor da Ano", required = false) 
		@RequestParam(value="ano", required=false) String ano) {
		if(marca!=null) {
			if(modelo!=null) {
				if(ano!=null) {    
					return repo.findByMarcaAndModeloAndAno(marca, modelo, ano);
				}
				return repo.findByMarcaAndModelo(marca, modelo);
			}
			if(ano!=null) {
				return repo.findByModeloAndAno(modelo, ano);
			}
			return repo.findByMarca(marca);
		}
		if(modelo!=null) {
			if(ano!=null) {
				return repo.findByModeloAndAno(modelo, ano);
			}
			return repo.findByModelo(modelo);
		}
		if(ano!=null) {
			return repo.findByAno(ano);
		}
			
		//retorno sem parametros
		return repo.findAll();
	}
    
    @PostMapping("/with-param")
    @ApiOperation(value = "Obtém uma mensagem padrão",response = Veiculo.class)
    public Veiculo saveVehicle(
    		@ApiParam(value = "Marca do Carro", required = true) 
    	@RequestParam(value="marca") String marca,
    			
			@ApiParam(value = "Modelo do Carro", required = true) 
    	@RequestParam(value="modelo") String modelo,
    			
			@ApiParam(value = "Ano do Carro", required = true) 
    	@RequestParam(value="ano") String ano,
    			
			@ApiParam(value = "Valor do Carro", required = true) 
    	@RequestParam(value ="valor") Integer valor,
    			
			@ApiParam(value = "Local do carro", required = true) 
    	@RequestParam(value="local") String local,
    			
    		@ApiParam(value = "Placa do carro", required = true) 
    	@RequestParam(value="placa") String placa,
    			
		    @ApiParam(value = "Status do carro", required = true) 
    	@RequestParam(value="status") String status,
    			
		    @ApiParam(value = "Disponibilidade do carro", required = false) 
    	@RequestParam(value="disponibilidade", defaultValue= "true", required=false) String disponibilidade,
    			
    		@ApiParam(value = "Data de entrada do carro", required = true) 
    	@RequestParam(value="dataEntrada", defaultValue= "30-07-2019") String dataEntrada,
    			
		    @ApiParam(value = "Descrição do carro", required = false) 
    	@RequestParam(value="desc", defaultValue="Descrição", required=false) String desc) {
    	
    	Veiculo veiculo  = new Veiculo();
    	veiculo.setMarca(marca);
    	veiculo.setModelo(modelo);
    	veiculo.setAno(ano);
    	veiculo.setValor(valor);
    	veiculo.setLocal(local);
    	veiculo.setPlaca(placa);
    	veiculo.setStatus(status);
    	veiculo.setDisponibilidade((disponibilidade=="true")?(true):(false));
    	veiculo.setDataEntrada(dataEntrada);
    	veiculo.setDesc(desc);
    	return repo.save(veiculo);
    }
    
//    @PutMapping
    
    
    @DeleteMapping("/with-param")
    @ApiOperation(value = "Obtém uma mensagem padrão",response = Veiculo.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Vehicle not found") })
    public void  deleteVehicleByID(
    	@ApiParam(value = "ID do carro", required = true) @RequestParam(value="_id") String _id) {
    	repo.delete(_id);
    }


}