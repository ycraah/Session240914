package ycraah.web.w1.controller;

import lombok.extern.log4j.Log4j2;
import ycraah.web.w1.dto.TodoDTO;
import ycraah.web.w1.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/todo/read")
public class TodoReadController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      Long tno = Long.parseLong(req.getParameter("tno"));
      TodoDTO todoDTO = TodoService.INSTANCE.get(tno);
      req.setAttribute("todoDTO", todoDTO);
      req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new ServletException("TodoRead(GET) 오류");
    }
  }
}
