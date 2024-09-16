package ycraah.web.w2.listerner;

import lombok.extern.log4j.Log4j2;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
@Log4j2
public class LoginListener implements HttpSessionAttributeListener {
  @Override
  public void attributeAdded(HttpSessionBindingEvent event) {
    String name = event.getName();
    Object obj = event.getValue();
    if(name.equals("loginInfo")){
      log.info("유저가 로그인하였습니다.");
      log.info(obj);
    }
  }
}
