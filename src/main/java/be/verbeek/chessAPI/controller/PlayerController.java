package be.verbeek.chessAPI.controller;

import be.verbeek.chessAPI.exceptions.EntryNotFoundException;
import be.verbeek.chessAPI.model.Player;
import be.verbeek.chessAPI.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    //TODO DI
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable(value= "id") Long id)  throws EntryNotFoundException{
        Player player = playerService.getPlayerById(id);
        if (player != null)
            return player;
        return null;
    }

    @GetMapping("/ranking")
    public List<Player> getPlayersByRanking(){
        return playerService.getPlayersByRanking();
    }

    @PostMapping
    public Player postPlayer(@Valid @RequestBody Player player){
        return playerService.addNewPlayer(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable(value = "id") Long id) throws EntryNotFoundException{
        if (playerService.deletePlayer(id))
            return ResponseEntity.ok().build();
        return null;
    }

    @PutMapping("/{id}/details")
    public Player updatePlayerDetails(@PathVariable(value = "id") Long id, @Valid @RequestBody Player playerDetails) throws EntryNotFoundException{
        return playerService.updatePlayerDetails(id, playerDetails);
    }



}
