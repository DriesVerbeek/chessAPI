package be.verbeek.chessAPI.service;

import be.verbeek.chessAPI.exceptions.EntryNotFoundException;
import be.verbeek.chessAPI.model.Player;
import be.verbeek.chessAPI.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class PlayerService {

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
     * @throws EntryNotFoundException if player is not found
     */
    public Player getPlayerById(Long id) throws EntryNotFoundException {
        return playerRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Player", "id", id.toString()));
    }

    /**
     * ranks all players by elo
     * @return all players
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
     * @throws EntryNotFoundException when player is not found
     */
    public boolean deletePlayer(Long id) throws EntryNotFoundException {
        Player player = playerRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Player", "id", id.toString()));
        playerRepository.delete(player);
        return true;
    }

    /**
     * updates a players details (currently only the name)
     * elo, wins, losses can not be changed by end users
     * @param id the id of the player
     * @param playerDetails the players new details
     * @return the updated player
     * @throws EntryNotFoundException if player is not found
     */
    public Player updatePlayerDetails(Long id, Player playerDetails)  throws EntryNotFoundException{
        Player player = playerRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Player", "id", id.toString()));;
        player.setName(playerDetails.getName());

        return playerRepository.save(player);
    }
}
