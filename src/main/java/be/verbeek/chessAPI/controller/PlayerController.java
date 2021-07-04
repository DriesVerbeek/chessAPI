package be.verbeek.chessAPI.controller;

import be.verbeek.chessAPI.model.Player;
import be.verbeek.chessAPI.service.PlayerService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Player getPlayerById(@PathVariable(value= "id") Long id) throws NotFoundException {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/ranking")
    public List<Player> getPlayersByRanking(){
        return playerService.getPlayersByRanking();
    }

    @PostMapping
    public Player postPlayer(@Valid @RequestBody Player player){
        return playerService.addNewPlayer(player);
    }



}
