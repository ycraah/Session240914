package ycraah.web.w2.controller;

import lombok.extern.log4j.Log4j2;
import ycraah.web.w2.dto.TodoDTO;
import ycraah.web.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet("/todo/list")
public class TodoListController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/list");
    try{
      List<TodoDTO> dtoList = TodoService.INSTANCE.listAll();
      req.setAttribute("dtoList", dtoList);
      req.getRequestDispatcher("/todo/list.jsp").forward(req, resp);
    } catch (Exception e){
      log.error(e.getMessage());
      throw new ServletException("TodoList(GET) 에러");
    }

  }
}
