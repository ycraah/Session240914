package ycraah.web.w2.controller;

import lombok.extern.log4j.Log4j2;
import ycraah.web.w2.dto.TodoDTO;
import ycraah.web.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet("/todo/register")
public class TodoRegisterController extends HttpServlet {
  private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/register(GET)");
    HttpSession session = req.getSession();

    if(session.isNew()){
      log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
      resp.sendRedirect("/login");
      return;
    }

    if(session.getAttribute("LoginInfo") == null){
      log.info("로그인한 정보가 없는 사용자");
      resp.sendRedirect("/login");
      return;
    }

    req.getRequestDispatcher("/todo/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("/todo/register(POST)");
    req.setCharacterEncoding("UTF-8");
    TodoDTO todoDTO = TodoDTO.builder()
        .title(req.getParameter("title"))
        .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
        .build();
    log.info(todoDTO);
    try{
      TodoService.INSTANCE.register(todoDTO);
    } catch (Exception e) {
      e.printStackTrace();
    }
    resp.sendRedirect("/todo/list");
  }
}
