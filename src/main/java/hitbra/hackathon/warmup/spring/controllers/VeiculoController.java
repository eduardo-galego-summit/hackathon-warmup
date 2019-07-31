package hitbra.hackathon.warmup.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hitbra.hackathon.warmup.spring.model.Veiculo;
import hitbra.hackathon.warmup.spring.repositories.VeiculoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/vehicles")
@Api(value = "/vehicles")
public class VeiculoController {

	@Autowired
	private VeiculoRepository repo;

	/*--Add Vehicle--*/
	@PostMapping
	public ResponseEntity<String> create(@RequestBody Veiculo veiculo) {
		veiculo.setDisponibilidade(true);
		String id = repo.save(veiculo).id;
		return ResponseEntity.ok().body("New vehicle has been saved with ID:" + id);
	}
	 /*---Get a vehicle by id---*/
	   @GetMapping("/{id}")
	   public ResponseEntity<Veiculo> get(@PathVariable("id") String id) {
		   Veiculo book = repo.findOne(id);
	      return ResponseEntity.ok().body(book);
	   }

	/*---Find Vehicles---*/
	@GetMapping
	@ApiOperation(value = "Obtém uma mensagem padrão", response = Veiculo.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Vehicle not found") })
	public ResponseEntity<List<Veiculo>> findBy(
			@ApiParam(value = "Valor da Marca", required = false) @RequestParam(value = "marca", required = false) String marca,
			@ApiParam(value = "Valor da Modelo", required = false) @RequestParam(value = "modelo", required = false) String modelo,
			@ApiParam(value = "Valor da Ano", required = false) @RequestParam(value = "ano", required = false) String ano) {

		if (marca != null) {
			if (modelo != null) {
				if (ano != null) {
					return ResponseEntity.ok().body(repo.findByMarcaLikeAndModeloLikeAndAnoLike(marca, modelo, ano));
				}
				return ResponseEntity.ok().body(repo.findByMarcaLikeAndModeloLike(marca, modelo));
			}
			if (ano != null) {
				return ResponseEntity.ok().body(repo.findByMarcaLikeAndAnoLike(marca, ano));
			}
			return ResponseEntity.ok().body(repo.findByMarcaLike(marca));
		}
		if (modelo != null) {
			if (ano != null) {
				return ResponseEntity.ok().body(repo.findByModeloLikeAndAnoLike(modelo, ano));
			}
			return ResponseEntity.ok().body(repo.findByModeloLike(modelo));
		}
		if (ano != null) {
			return ResponseEntity.ok().body(repo.findByAnoLike(ano));
		}
		return ResponseEntity.ok().body(repo.findAll());
	}

	/*---Update a Vehicle by id---*/
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable("id") String id, @RequestBody Veiculo veiculo) {
		repo.save(veiculo);
		return ResponseEntity.ok().body("Vehicle has been updated successfully.");
	}
	
//	/*---Delete a Vehicle by id---*/
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> delete(@ApiParam(value = "ID do carro", required = true) @PathVariable(value = "id") String id) {
//		repo.delete(id);
//		return ResponseEntity.ok().body("Vehicle has been deleted successfully.");
//	}
	
	/*---Disable a Vehicle by id---*/
	@DeleteMapping("/{id}")
	public ResponseEntity<String> disable(@ApiParam(value = "ID do carro", required = true) @PathVariable(value = "id") String id) {
		Veiculo veiculo = repo.findOne(id);
		veiculo.setDisponibilidade(false);
		repo.save(veiculo);
		return ResponseEntity.ok().body("Vehicle has been deleted successfully.");
	}


}