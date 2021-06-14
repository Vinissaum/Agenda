package br.com.agenda.repository;

import br.com.agenda.model.Agenda;
import org.springframework.data.repository.CrudRepository;


public interface AgendaRepository extends CrudRepository<Agenda,Long>{

}