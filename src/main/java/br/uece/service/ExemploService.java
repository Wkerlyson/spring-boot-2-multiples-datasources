package br.uece.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.uece.entity.postgres.Lei;
import br.uece.entity.sqlserver.Aluno;
import br.uece.exceptions.ObjectNotFoundException;
import br.uece.repository.postgres.LeiRepository;
import br.uece.repository.sqlserver.AlunoRepository;

@Service
public class ExemploService {
		
		@Autowired
		private LeiRepository leiRepository;
		
		@Autowired
		private AlunoRepository alunoRepository;
		
		
		@Transactional("chainedTransactionManager")
		public void salvarEmDoisBancos() throws Exception {
			salvarNoSQLServer();
			editarNoPostgres();
		}
		
		public Aluno buscarAlunoPorId(Long id){
			Optional<Aluno> obj =  alunoRepository.findById(id);
			  
			return obj.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado"));

		}
		
		public Lei  buscarLeiPorId(Long id){
			Optional<Lei> obj =  leiRepository.findById(id);
			  
			return obj.orElseThrow(() -> new ObjectNotFoundException("Lei não encontrada"));

		}
		
		private void editarNoPostgres(){
			
			Optional<Lei> obj = leiRepository.findById(13L);
			Lei lei = obj.orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado"));
			
			lei.setDs_lei("#"+lei.getDs_lei());
			
			leiRepository.saveAndFlush(lei);
		}
		
		
		
		private void salvarNoSQLServer() throws Exception {
			
			Aluno aluno = new Aluno();
			
			aluno.setDsNome("Fulano de tal");
			aluno.setId(1L);
			
			alunoRepository.saveAndFlush(aluno);

		}
		
		
		
}
