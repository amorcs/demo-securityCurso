package com.mballem.curso.security.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballem.curso.security.datatables.Datatables;
import com.mballem.curso.security.datatables.DatatablesColunas;
import com.mballem.curso.security.domain.Especialidade;
import com.mballem.curso.security.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	@Autowired
	private Datatables datatables;
	
	@Transactional(readOnly = false)
	public void salvar(Especialidade especialidade) {
		especialidadeRepository.save(especialidade);
	}

	@Transactional(readOnly = true)
	public Map<String, Object>buscaEspecialidades(HttpServletRequest request) {
		datatables.setRequest(request);
		datatables.setColunas(DatatablesColunas.ESPECIALIDADES);
		Page<?> page = datatables.getSearch().isEmpty()
				?especialidadeRepository.findAll(datatables.getPageable())
						:especialidadeRepository.findAllByTitulo(datatables.getSearch(), datatables.getPageable());
		return datatables.getResponse(page);
	}

	@Transactional(readOnly = true)
	public Especialidade buscarPorId(Long id) {
		return especialidadeRepository.findById(id).get();
	}

	@Transactional(readOnly = false)
	public void exluir(Long id) {
		especialidadeRepository.deleteById(id);
	}
	@Transactional(readOnly = true)
	public List<String> buscaEspecialidadeByTermo(String termo) {
		return especialidadeRepository.findEspecialidadesByTermo(termo);
	}

	@Transactional(readOnly = true)
	public Set<Especialidade> buscarPorTitulos(String[] titulos) {
		// TODO Auto-generated method stub
		return especialidadeRepository.findByTitulo(titulos);
	}

	public Map<String, Object> buscaEspecialidadeProMedico(Long id, HttpServletRequest request) {
			datatables.setRequest(request);
			datatables.setColunas(DatatablesColunas.ESPECIALIDADES);
			Page<Especialidade> page = especialidadeRepository.findBiIdMedio(id, datatables.getPageable());
			
			return datatables.getResponse(page);
	}
}
