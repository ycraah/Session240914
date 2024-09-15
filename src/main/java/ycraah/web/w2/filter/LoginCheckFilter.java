package ycraah.web.w2.filter;

import lombok.extern.log4j.Log4j2;
import ycraah.web.w2.dto.MemberDTO;
import ycraah.web.w2.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter("/todo/*")
@Log4j2
public class LoginCheckFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    log.info("로그인 체크 필터 실행");
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();

    if(session.getAttribute("loginInfo")!=null){
      chain.doFilter(request, response);
      return;
    }

    //loginInfo 값이 없다면 쿠키 체크
    Cookie cookie = findCookie(req.getCookies(), "remember-me");

    //세션도 없고 쿠키도 없을 시에 로그인창으로
    if(cookie == null){
      resp.sendRedirect("/login");
      return;
    }

    log.info("쿠키 발견");
    String uuid = cookie.getValue();

    try {
      MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);
      log.info("쿠키ㅏ의 값으로 조회한 사용자 정보: " + memberDTO);
      if(memberDTO==null){
        throw new Exception("쿠키값이 유효하지 않습니다.");
      }
      //회원 정보 세션에 추가
      session.setAttribute("loginInfo", memberDTO);
      chain.doFilter(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      resp.sendRedirect("/login");
    }


  }

  private Cookie findCookie(Cookie[] cookies, String name) {
    if(cookies == null || cookies.length == 0) return null;

    Optional<Cookie> result = Arrays.stream(cookies)
        .filter(ck -> ck.getName().equals(name))
        .findFirst();

    return result.isPresent() ? result.get() : null;
  }
}
