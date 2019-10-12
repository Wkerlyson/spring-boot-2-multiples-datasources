package br.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.example.entity.postgres.Law;
import br.example.entity.sqlserver.Student;
import br.example.exceptions.ObjectNotFoundException;
import br.example.repository.postgres.LawRepository;
import br.example.repository.sqlserver.StudentRepository;

@Service
public class ExampleService {
		
		@Autowired
		private LawRepository lawRepository;
		
		@Autowired
		private StudentRepository studentRepository;
		
		
		@Transactional("chainedTransactionManager")
		public void saveInTwoDatabases() throws Exception {
			saveInSQLServer();
			updateInPostgres();
		}
		
		public Student findStudentById(Long id){
			Optional<Student> obj =  studentRepository.findById(id);
			  
			return obj.orElseThrow(() -> new ObjectNotFoundException("Studenty not found"));

		}
		
		public Law  findLawById(Long id){
			Optional<Law> obj =  lawRepository.findById(id);
			  
			return obj.orElseThrow(() -> new ObjectNotFoundException("Law not found"));

		}
		
		private void updateInPostgres(){
			
			Optional<Law> obj = lawRepository.findById(13L);
			Law law = obj.orElseThrow(() -> new ObjectNotFoundException("Law not found"));
			
			law.setDsLaw("#"+law.getDsLaw());
			
			lawRepository.saveAndFlush(law);
		}
		
		
		
		private void saveInSQLServer() throws Exception {
			
			Student student = new Student();
			
			student.setDsName("Fulano de tal");
			student.setId(1L);
			
			studentRepository.saveAndFlush(student);

		}
		
		
		
}
