package app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Estatistica;
import app.entities.Transacao;
import app.services.TransacaoService;

@RestController
@RequestMapping("/api")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;
	
	@PostMapping("/transacao")
	public ResponseEntity<Void> save(@RequestBody Transacao transacao) {
		try {
			this.transacaoService.save(transacao);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		} catch (RuntimeException e) {
			return ResponseEntity.unprocessableEntity().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/estatistica")
	public ResponseEntity<Estatistica> getEstatistica() {
		Estatistica estatistica = this.transacaoService.getEstatistica();
		return new ResponseEntity<>(estatistica, HttpStatus.OK);
	}
	
	@DeleteMapping("/transacao")
	public ResponseEntity<Void> deleteAll() {
		this.transacaoService.deleteAll();
		return ResponseEntity.ok().build();
	}
}
