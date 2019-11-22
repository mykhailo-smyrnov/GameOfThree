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
@Table(name = "game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "started_by_id", nullable = false)
    private PlayerTypeEntity startedBy;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private GameStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "winner_id", nullable = false)
    private PlayerTypeEntity winner;

    @Column
    private Integer initNumber;

    @Column
    private Integer currentNumber;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime finishDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public PlayerTypeEntity getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(PlayerTypeEntity startedBy) {
        this.startedBy = startedBy;
    }

    public GameStatusEntity getStatus() {
        return status;
    }

    public void setStatus(GameStatusEntity status) {
        this.status = status;
    }

    public PlayerTypeEntity getWinner() {
        return winner;
    }

    public void setWinner(PlayerTypeEntity winner) {
        this.winner = winner;
    }

    public Integer getInitNumber() {
        return initNumber;
    }

    public void setInitNumber(Integer initNumber) {
        this.initNumber = initNumber;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }
}
