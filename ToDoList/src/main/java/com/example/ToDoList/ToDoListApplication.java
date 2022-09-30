package com.example.ToDoList;
import com.example.ToDoList.model.Board;
import com.example.ToDoList.model.ToDo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
public class ToDoListApplication {


    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

}