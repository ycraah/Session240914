package ycraah.web.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import ycraah.web.w2.dao.MemberDAO;
import ycraah.web.w2.domain.MemberVO;
import ycraah.web.w2.dto.MemberDTO;
import ycraah.web.w2.util.MapperUtil;

import java.sql.SQLException;

@Log4j2
public enum MemberService {
  INSTANCE;

  private MemberDAO dao;
  private ModelMapper mapper;

  MemberService() {
    dao = new MemberDAO();
    mapper = MapperUtil.INSTANCE.getModelMapper();
  }

  public MemberDTO login(String mid, String mpw) throws SQLException {
    MemberVO vo = dao.getWithPassword(mid, mpw);
    MemberDTO dto = mapper.map(vo, MemberDTO.class);
    return dto;
  }
}
