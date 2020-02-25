package pe.aysconsultores.bootstart.repository;

import pe.aysconsultores.bootstart.model.Team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {
}
