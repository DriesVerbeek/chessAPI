package be.verbeek.chessAPI.controller;

import be.verbeek.chessAPI.model.Player;
import be.verbeek.chessAPI.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @PostMapping
    public Player postPlayer(@Valid @RequestBody Player player){
        return playerRepository.save(player);
    }



}
