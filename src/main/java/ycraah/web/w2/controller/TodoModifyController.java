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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet("/todo/modify")
public class TodoModifyController extends HttpServlet {
  private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long tno = Long.parseLong(req.getParameter("tno"));
      TodoDTO todoDTO = TodoService.INSTANCE.get(tno);
      req.setAttribute("todoDTO", todoDTO);
      req.getRequestDispatcher("/todo/modify.jsp").forward(req, resp);
    } catch(Exception e){
      log.error(e.getMessage());
      throw new ServletException("modify(GET) 에러");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("todo/modify(POST)");
    String finishedStr = req.getParameter("finished");
    TodoDTO todoDTO = TodoDTO.builder()
        .tno(Long.parseLong(req.getParameter("tno")))
        .title(req.getParameter("title"))
        .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
        .finished(finishedStr != null && finishedStr.equals("on"))
        .build();
    log.info(todoDTO);
    try {
      TodoService.INSTANCE.modify(todoDTO);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    resp.sendRedirect("/todo/list");
  }
}
