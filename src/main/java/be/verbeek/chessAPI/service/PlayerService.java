package be.verbeek.chessAPI.service;

import be.verbeek.chessAPI.model.Player;
import be.verbeek.chessAPI.repository.PlayerRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerService {

    //TODO: fix autowired
    @Autowired
    private PlayerRepository playerRepository;

    /**
     * get all method
     * @return all players
     */
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    /**
     *  get a player
     * @param id the id of the player
     * @return the player with that id
     * @throws NotFoundException when player is not found
     */
    public Player getPlayerById(Long id) throws NotFoundException{
        return playerRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    /**
     *
     * @return all players sorted by their elo, in desc. order
     */
    public List<Player> getPlayersByRanking(){
        List<Player> players = playerRepository.findAll();
        players.sort(Comparator.comparing(Player::getElo).reversed());
        return players;
    }

    /**
     * adds a new player to the db
     * @param player the new player
     * @return the player that is created in the DB
     */
    public Player addNewPlayer(Player player){
        return playerRepository.save(player);
    }
}
