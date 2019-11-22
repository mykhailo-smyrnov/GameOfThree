package com.msmyrnov.gameofthree.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_stats")
public class GameStatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    @ManyToOne
    @JoinColumn(name = "move_by", nullable = false)
    private PlayerTypeEntity moveBy;

    @Column
    private Integer moveNumber;

    @Column
    private LocalDateTime moveDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameEntity getGame() {
        return game;
    }

    public void setGame(GameEntity game) {
        this.game = game;
    }

    public PlayerTypeEntity getMoveBy() {
        return moveBy;
    }

    public void setMoveBy(PlayerTypeEntity moveBy) {
        this.moveBy = moveBy;
    }

    public Integer getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(Integer moveNumber) {
        this.moveNumber = moveNumber;
    }

    public LocalDateTime getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(LocalDateTime moveDate) {
        this.moveDate = moveDate;
    }
}
