package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.controllers.TransacaoController;
import app.entities.Transacao;

@SpringBootTest
public class TransacaoControllerTest {

	@Autowired
	TransacaoController transacaoController;
	
	@Test
	@DisplayName("save - deve funcionar")
	void cenario01() {
		Transacao transacao = new Transacao(135.40, OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00"));
		
		ResponseEntity<Void> resposta = this.transacaoController.save(transacao);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("save - deve falhar com Transacao nula")
	void cenario02() {
		Transacao transacao = null;
		
		ResponseEntity<Void> resposta = this.transacaoController.save(transacao);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("save - deve falhar com Transacao vazia")
	void cenario03() {
		Transacao transacao = new Transacao();
		
		ResponseEntity<Void> resposta = this.transacaoController.save(transacao);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("save - deve falhar com valor negativo")
	void cenario04() {
		Transacao transacao = new Transacao(-1.0, OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00"));
		
		ResponseEntity<Void> resposta = this.transacaoController.save(transacao);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resposta.getStatusCode());
	}
	
	@Test
	@DisplayName("save - deve falhar com data futura")
	void cenario05() {
		Transacao transacao = new Transacao(135.40, OffsetDateTime.now().plusMinutes(10));
		
		ResponseEntity<Void> resposta = this.transacaoController.save(transacao);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, resposta.getStatusCode());
	}
}
