package pe.aysconsultores.bootsolution.repository;


import pe.aysconsultores.bootsolution.domain.Player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="players", rel="player")
public interface PlayerRepository extends CrudRepository<Player, Long> {

}
