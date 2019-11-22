package com.msmyrnov.gameofthree.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {

    private String infoMessage;

    @JsonProperty
    private boolean isStarted;

    @JsonProperty
    private boolean isFinished;

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
