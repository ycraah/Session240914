package ycraah.web.w2.controller;

import lombok.extern.log4j.Log4j2;
import ycraah.web.w2.dto.TodoDTO;
import ycraah.web.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

      Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
      String todoListStr = viewTodoCookie.getValue();
      boolean exist = false;

      //쿠키에 tno값이 저장되어 있는지 검색
      if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0) {
        exist = true;
      }

      log.info("exist: " + exist);

      //검색 내용이 없으면 쿠키에 기록 저장
      if(!exist){
        todoListStr += tno+"-";
        viewTodoCookie.setValue(todoListStr);
        viewTodoCookie.setMaxAge(60*60*24);
        viewTodoCookie.setPath("/");
        resp.addCookie(viewTodoCookie);
      }

      req.getRequestDispatcher("/todo/read.jsp").forward(req, resp);
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e.getMessage());
      throw new ServletException("TodoRead(GET) 오류");
    }
  }

  private Cookie findCookie(Cookie[] cookies, String cookieName) {
    Cookie targetCookie = null;

    //viewTodos 쿠키가 존재하는 지 확인
    if(cookies != null && cookies.length > 0) {
      for(Cookie ck : cookies){
        if(ck.getName().equals(cookieName)){
          targetCookie = ck;
          break;
        }
      }
    }
    //존재하지 않으면 쿠키 생성
    if(targetCookie == null) {
      targetCookie = new Cookie(cookieName, "");
      targetCookie.setPath("/");
      targetCookie.setMaxAge(60*60*24);
    }
    //쿠키 반환
    return targetCookie;
  }
}
