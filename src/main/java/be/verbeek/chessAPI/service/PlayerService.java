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
    public Player getPlayerById(Long id){
        return playerRepository.findById(id).orElse(null);
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

    /**
     * deletes a player from the DB
     * @param id the id of the player
     * @return true if deleted, else false
     */
    public boolean deletePlayer(Long id){
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null)
            return false;
        playerRepository.delete(player);
        return true;
    }

    /**
     * updates a players details (currently only the name)
     * elo, wins, losses can not be changed by end users
     * @param id the id of the player
     * @param playerDetails the players new details
     * @return the updated player
     */
    public Player updatePlayerDetails(Long id, Player playerDetails) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player == null)
            return null;

        player.setName(playerDetails.getName());

        return playerRepository.save(player);
    }
}
