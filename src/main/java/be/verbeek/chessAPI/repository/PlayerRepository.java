package be.verbeek.chessAPI.repository;

import be.verbeek.chessAPI.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
