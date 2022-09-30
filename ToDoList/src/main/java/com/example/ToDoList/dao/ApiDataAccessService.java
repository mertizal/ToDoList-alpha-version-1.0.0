package com.example.ToDoList.dao;

import com.example.ToDoList.model.Board;
import com.example.ToDoList.model.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Repository("postgres")
public class ApiDataAccessService {


    private static List<Board> DB1 = new ArrayList<>();
    private static List<ToDo> DB2 = new ArrayList<>();

    public int insertBoard(UUID boardid, Board board) {
        DB1.add(board);
        return 1;
    }

    public int insertBoard(Board board) {
        UUID boardid = UUID.randomUUID();
        board.SetBoardId(boardid);
        board.SetBoardName(board.getBoardname());
        board.SetBoardDescription(board.getBoardDescription());
        return insertBoard(boardid, board);
    }

    public int insertTodo(UUID todoId, ToDo todo) {
        DB2.add(todo);
        return 1;
    }

    public int insertTodoo(ToDo todo, UUID board_id) {
        UUID todoId = UUID.randomUUID();
        todo.setTodoId(todoId);
        todo.setTodotitle1(todo.getTitle1());
        todo.setTodotitle2(todo.getTitle2());
        UUID id = board_id;
        todo.setboardid(id);
        todo.setStatusname(todo.getStatusname());

        return insertTodo(todoId, todo);
    }

    public List<Board> selectBoard() {
        return DB1;
    }

    public int deleteboard(UUID board_id) {

        DB1.removeIf(boards -> boards.getBoardid().equals(board_id));
        return 1;

    }

    public int deletetodo(UUID boardid, UUID todoId) {

        List<ToDo> result = DB2.stream().filter(id->id.getBoard_id().equals(boardid)).toList();

        if(!result.isEmpty()){

            DB2.removeIf(todoid->todoid.getTodoId().equals(todoId));
        }
        return 1;
    }

    public int Put(UUID board_id, Board newboard) {

        newboard.SetBoardId(board_id);
        DB1.replaceAll(board -> board.getBoardid().equals(board_id) ? newboard : board);
        return 1;

    }

    public Board updatebyid(Board result, Board board) {

        result.SetBoardName(board.getBoardname());
        return result;
    }

    public Board getbyId(UUID boardid, Board board) {

        Board result = DB1.stream().filter(id -> id.getBoardid().equals(boardid)).findFirst().get();
        return updatebyid(result, board);
    }


    public List<ToDo> selectTodo(UUID board_id) {

        List<ToDo> result = DB2.stream().filter(id -> id.getBoard_id().equals(board_id)).collect(Collectors.toList());

        return result;
    }



    public int updatebyidtodo(UUID boardid, UUID todoId, Map<Object,Object> objectsMap) {

        ToDo todo  = DB2.stream().filter(id -> id.getTodoId().equals(todoId)).findFirst().get();
        objectsMap.forEach((key,value) -> {
                Field field = ReflectionUtils.findField(ToDo.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,todo,value);
            });
            return 1;
        }


    public List<ToDo> getallTodo(UUID board_id, String durum, ToDo todo) {

        // status durumuna göre sorgu döndüren database metodudur.

        if (durum.equals("Done")) {

            List<ToDo> result = DB2.stream().filter(id -> (id.getBoard_id().equals(board_id)) && (id.getStatusname().getName().equals("Done"))).collect(Collectors.toList());
            return result;

        }

        if (durum.equals("Planned")) {

            List<ToDo> result = DB2.stream().filter(id -> (id.getBoard_id().equals(board_id)) && (id.getStatusname().getName().equals("Planned"))).collect(Collectors.toList());
            return result;

        }

        if (durum.equals("In Progress")) {

            List<ToDo> result = DB2.stream().filter(id -> (id.getBoard_id().equals(board_id)) && (id.getStatusname().getName().equals("In Progress"))).collect(Collectors.toList());
            return result;

        }

        if (durum.isEmpty()) {

            List<ToDo> result = DB2.stream().filter(id -> (id.getBoard_id().equals(board_id)) && !(id.getStatusname().getName().isEmpty())).collect(Collectors.toList());
            return result;

        }

        {
            return null;
        }

    }

    public List<?> getallgecerlitodo(UUID board_id, Board board, ToDo toDo, UUID todoId) {

        List<Board> result = DB1.stream().filter(id -> id.getBoardid().equals(board_id)).toList();
        List<ToDo> result2 = DB2.stream().filter(id -> (id.getBoard_id().equals(board_id)) && (id.getTodoId().equals(todoId))).toList();

        if (result.isEmpty()) {

            return Collections.singletonList(HttpStatus.valueOf(404) +
                    " Belirtilen: " + board_id + " no'lu board id bulunamadı. :( ");

        } else {

            if (result2.isEmpty()) {
                return Collections.singletonList(HttpStatus.valueOf(404) +
                        "\n Belirtilen: " + todoId + " no'lu todo id bulunamadı. :( ");
            } else {
                return result2;
            }
        }

    }

}

