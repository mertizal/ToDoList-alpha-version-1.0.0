package com.example.ToDoList.model;

import javax.print.DocFlavor;

public enum status {

    PLANNED ("Planned"),
    IN_PROGRESS("In Progress"),
    DONE("Done");

    private final String name;

     status(String name){
         this.name = name;
     }

     public String getName(){
         return name;
     }
}
