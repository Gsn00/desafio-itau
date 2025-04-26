package app.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.entities.Transacao;

public class TransacaoRepository {

	private List<Transacao> transacoes = new ArrayList<>();
	
	public void save(Transacao transacao) {
		this.transacoes.add(transacao);
	}
	
	public List<Transacao> findAll() {
		return Collections.unmodifiableList(transacoes);
	}
	
	public void deleteAll() {
		transacoes.clear();
	}
}
