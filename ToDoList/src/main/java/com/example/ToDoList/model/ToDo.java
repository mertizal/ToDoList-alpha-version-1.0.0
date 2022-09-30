package com.example.ToDoList.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ToDo  {

    private UUID todoId;

    private String title1;

    private String title2;

    private UUID board_id;

    private status statusname ;



    public UUID getTodoId() {
        return todoId;
    }

    public void setStatusname(@JsonProperty status statusname){

        this.statusname = statusname;

    }

    public status getStatusname(){

        return statusname;
    }

    public void setTodoId(@JsonProperty  UUID todoId){

        this.todoId = todoId;
    }

    public void setTodotitle1(@JsonProperty String title1){

        this.title1 = title1;
    }

    public void setTodotitle2(@JsonProperty String title2){

        this.title2 = title2;
    }

    public void setboardid (@JsonProperty UUID boardid){

        this.board_id = boardid;
    }

    public String getTitle1() {
        return title1;
    }

    public String getTitle2() {
        return title2;
    }

    public UUID getBoard_id() {
        return board_id;
    }

}