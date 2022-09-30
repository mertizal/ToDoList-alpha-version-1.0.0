package com.example.ToDoList.api;

import com.example.ToDoList.model.Board;
import com.example.ToDoList.model.ToDo;
import com.example.ToDoList.model.status;
import com.example.ToDoList.servies.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("api/ToDoProject")
@RestController
public class ApiController {
    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping("/Boards/{board_id}")
    public void addtodo(@RequestBody ToDo todo, @PathVariable UUID board_id) {

        apiService.addTodo(todo, board_id);

    }

    @GetMapping("/Boards/{board_id}/")     // Status durumuna göre sorgulama yapan EndPointdir. //
    public List<ToDo> selectAllTodo(@PathVariable UUID board_id, @RequestParam(value = "status", required = false) String durum, ToDo todo) {

        return apiService.getselectAllTodo(board_id, durum, todo);
    }

    @GetMapping("/Boards/{board_id}/{todoId}")  // board_id ile todoid arasıdaki validasyonu sağlayan endpoint. //
    public List<?> gecerliTodo(@PathVariable UUID board_id, Board board, ToDo toDo, @PathVariable UUID todoId) {

        return apiService.getgecerliTodo(board_id, board, toDo, todoId);
    }


    @PostMapping("/Board")
    public void addBoard(@RequestBody Board board) {

        apiService.addBoard(board);

    }

    @GetMapping("/Board")
    public List<Board> selectAllBoard() {

        return apiService.getselectBoard();
    }

    @DeleteMapping("/Board/Delete/{boardid}")  // Board silen delete endpointi //
    public void DeleteByIdBoard(@PathVariable("boardid") UUID board_id) {

        apiService.DeleteBoard(board_id);
    }

    @DeleteMapping("/Boards/Delete/{boardid}/{todoId}")
    public void deletebyidtodo(@PathVariable("boardid") UUID boardid , @PathVariable("todoId") UUID todoId){

        apiService.deletetodo(boardid,todoId);
    }

    @PatchMapping("/Boards/update/{boardid}/{todoId}")
    public int updatebyidtodo(@PathVariable("boardid") UUID boardid , @PathVariable("todoId") UUID todoId ,
                                     @RequestBody Map<Object,Object> objectsMap){
        return apiService.updatebyidtodo(boardid,todoId,objectsMap);
    }

    @PutMapping("/Board/Put/{boardid}")
    public void PutByIdBoard(@PathVariable("boardid") UUID board_id, @RequestBody Board board) {

        apiService.PutBoard(board_id, board);
    }

    @PutMapping("/Board/update/{boardid}")
    public void updateboard(@PathVariable("boardid") UUID boardid, @RequestBody Board board) {

        apiService.update(boardid, board);
    }

}



