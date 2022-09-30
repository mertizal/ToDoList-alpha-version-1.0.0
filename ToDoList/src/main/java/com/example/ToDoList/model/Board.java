package com.example.ToDoList.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public class Board {

    private UUID boardid;
    
    private String boardname;

    private String boardDescription;

//    public Board(@JsonProperty UUID boardid) {
//        this.boardid = boardid;
//    }


//    public Board(@JsonProperty("boardid") UUID boardid, @JsonProperty("boardDescription") String boardDescription, @JsonProperty("boardname") String boardname) {
//        this.boardid = boardid;
//        this.boardname = boardname;
//        this.boardDescription = boardDescription;
//    }

    public void SetBoardId(@JsonProperty UUID boardid){

        this.boardid=boardid;
    }
    public void SetBoardName(@JsonProperty String boardname){

        this.boardname=boardname;


    }
    public void SetBoardDescription(@JsonProperty String boardDescription){

        this.boardDescription=boardDescription;
    }


    public UUID getBoardid() {
        return boardid;
    }

    public String getBoardname() {
        return boardname;
    }

    public String getBoardDescription() {
        return boardDescription;
    }


}