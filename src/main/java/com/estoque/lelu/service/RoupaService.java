package com.estoque.lelu.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque.lelu.model.Roupa;
import com.estoque.lelu.repositories.RoupaRepository;

@Service
public class RoupaService {

	@Autowired
	private RoupaRepository roupaRepository;

	public List<Roupa> listarRoupas() {
		return roupaRepository.findAll();
	}

	public Roupa salvarRoupa(Roupa roupa) {
		return roupaRepository.save(roupa);
	}

	public Roupa buscarRoupaPorId(Long id) {
		return roupaRepository.findById(id).orElse(null);
	}

	public void deletarRoupa(Long id) {
		roupaRepository.deleteById(id);
	}

	public Roupa atualizarRoupa(Long id, Roupa roupaAtualizada) {
		Roupa roupaExistente = roupaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Roupa não encontrada"));

		// Atualiza apenas os campos fornecidos
		if (roupaAtualizada.getReferencia() != null) {
			roupaExistente.setReferencia(roupaAtualizada.getReferencia());
		}
		if (roupaAtualizada.getDescricao() != null) {
			roupaExistente.setDescricao(roupaAtualizada.getDescricao());
		}
		if (roupaAtualizada.getTamanho() != null) {
			roupaExistente.setTamanho(roupaAtualizada.getTamanho());
		}
		if (roupaAtualizada.getCor() != null) {
			roupaExistente.setCor(roupaAtualizada.getCor());
		}
		if (roupaAtualizada.getUnidades() != null) { // Usando Integer
			roupaExistente.setUnidades(roupaAtualizada.getUnidades());
		}
		if (roupaAtualizada.getPrecoAVista() != null) { // Usando Double
			roupaExistente.setPrecoAVista(roupaAtualizada.getPrecoAVista());
		}
		if (roupaAtualizada.getPrecoParcelado() != null) { // Usando Double
			roupaExistente.setPrecoParcelado(roupaAtualizada.getPrecoParcelado());
		}

		// Salvar a roupa atualizada
		return roupaRepository.save(roupaExistente);
	}
}
