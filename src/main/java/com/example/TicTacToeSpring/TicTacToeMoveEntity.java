package com.example.TicTacToeSpring;

import javax.persistence.*;

enum Cell {Empty, X, O}

enum Player {X, O}

@Entity
public class TicTacToeMoveEntity {
    public Player player;
    public String serializedBoard;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    public TicTacToeMoveEntity() {
        this(new TicTacToeGame());
    }

    public TicTacToeMoveEntity(TicTacToeGame game) {
        this.serializedBoard = BoardSerializer.serialize(game.board);
        this.player = game.player;
    }


    public Long getId() {
        return id;
    }
}
