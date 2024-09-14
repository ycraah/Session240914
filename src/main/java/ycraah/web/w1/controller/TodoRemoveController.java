package ycraah.web.w1.controller;

import lombok.extern.log4j.Log4j2;
import ycraah.web.w1.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/todo/remove")
public class TodoRemoveController extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Long tno = Long.parseLong(req.getParameter("tno"));

    try{
      TodoService.INSTANCE.remove(tno);
    } catch(Exception e){
      log.error(e.getMessage());
      throw new ServletException("read error");
    }
    resp.sendRedirect("/todo/list");
  }
}
