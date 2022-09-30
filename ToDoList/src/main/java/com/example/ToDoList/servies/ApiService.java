package com.example.ToDoList.servies;

import com.example.ToDoList.dao.ApiDataAccessService;
import com.example.ToDoList.model.Board;
import com.example.ToDoList.model.ToDo;
import com.example.ToDoList.model.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApiService {

    private final ApiDataAccessService apiDataAccessService;

    @Autowired
    public ApiService(@Qualifier("postgres") ApiDataAccessService apiDataAccessService) {
        this.apiDataAccessService = apiDataAccessService;
    }

    public int addBoard(Board board) {

        return apiDataAccessService.insertBoard(board);

    }

    public int addTodo(ToDo todo, UUID board_id) {

        return apiDataAccessService.insertTodoo(todo, board_id);

    }

    public List<Board> getselectBoard() {

        return apiDataAccessService.selectBoard();

    }

    public int DeleteBoard(UUID board_id) {

        return apiDataAccessService.deleteboard(board_id);
    }

    public int deletetodo(UUID boardid, UUID todoId) {

        return apiDataAccessService.deletetodo(boardid,todoId);
    }

    public int PutBoard(UUID board_id, Board board) {

        return apiDataAccessService.Put(board_id, board);
    }

    public Board update(UUID boardid, Board board) {

        return apiDataAccessService.getbyId(boardid, board);

    }

    public int updatebyidtodo(UUID boardid, UUID todoId, Map<Object, Object> objectsMap) {

        return apiDataAccessService.updatebyidtodo(boardid,todoId,objectsMap);
    }

    public List<ToDo> getselectTodo(UUID board_id) {

        return apiDataAccessService.selectTodo(board_id);

    }

    public List<ToDo> getselectAllTodo(UUID board_id, String durum, ToDo todo) {

        return apiDataAccessService.getallTodo(board_id, durum, todo);
    }

    public List<?> getgecerliTodo(UUID board_id, Board board, ToDo toDo, UUID todoId) {

        return apiDataAccessService.getallgecerlitodo(board_id, board, toDo, todoId);

    }


}