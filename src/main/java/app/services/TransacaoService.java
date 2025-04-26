package app.services;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import app.entities.Estatistica;
import app.entities.Transacao;
import app.repositories.TransacaoRepository;

@Service
public class TransacaoService {
	
	private TransacaoRepository transacaoRepository = new TransacaoRepository();
	
	public void save(Transacao transacao) {		
		if (transacao == null) throw new IllegalArgumentException();
		if (transacao.getValor() == null || transacao.getDataHora() == null) throw new IllegalArgumentException();
		
		if (transacao.getValor() < 0) throw new RuntimeException();
		if (transacao.getDataHora().isAfter(OffsetDateTime.now())) throw new RuntimeException();
		
		this.transacaoRepository.save(transacao);
	}
	
	public Estatistica getEstatistica() {
		List<Transacao> transacoes = this.transacaoRepository.findAll();
		
		Integer count = 0;
		Double sum = 0.0;
		Double avg = 0.0;
		Double min = 0.0;
		Double max = 0.0;
		
		for (Transacao t : transacoes) {
			if (t.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(60))) {
				count++;
				sum += t.getValor();
				
				if (count == 1) min = t.getValor();
				if (t.getValor() < min) min = t.getValor();
				if (t.getValor() > max) max = t.getValor();
			}
		}
		
		if (count > 0) avg = sum / count;
		
		Estatistica estatistica = new Estatistica(count, sum, avg, min, max);
		return estatistica;
	}
	
	public void deleteAll() {
		this.transacaoRepository.deleteAll();
	}
}
