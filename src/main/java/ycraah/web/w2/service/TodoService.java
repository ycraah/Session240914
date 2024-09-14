package ycraah.web.w2.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import ycraah.web.w2.dao.TodoDAO;
import ycraah.web.w2.domain.TodoVO;
import ycraah.web.w2.dto.TodoDTO;
import ycraah.web.w2.util.MapperUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
public enum TodoService {
  //Service는 프로그램 기능의 실제 처리 담당. 싱글톤 패턴을 위해 enum 타입으로 지정
  INSTANCE;

  private TodoDAO dao;
  private ModelMapper modelMapper;

  TodoService(){
    dao = new TodoDAO();
    modelMapper = MapperUtil.INSTANCE.getModelMapper();
  }

  public void register(TodoDTO todoDTO) throws SQLException {
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class); // map(인자로 변환할 인자 소스, 인자로 변환할 타켓 클래스 타입)

    log.info(todoVO);
    dao.insert(todoVO);
  }

  public List<TodoDTO> listAll() throws SQLException {
    List<TodoVO> voList = dao.selectAll();
    log.info(voList);
    List<TodoDTO> dtoList = voList.stream()
        .map(vo -> modelMapper.map(vo,TodoDTO.class))
        .collect(Collectors.toList());
    return dtoList;
  }

  public TodoDTO get(Long tno) throws Exception {
    log.info("검색 번호: " + tno);
    TodoVO todoVO = dao.selectOne(tno);
    TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
    return todoDTO;
  }

  public void remove(Long tno) throws Exception {
    log.info("삭제 번호 :" + tno);
    dao.deleteOne(tno);
  }

  public void modify(TodoDTO todoDTO) throws SQLException {
    log.info("수정 내용 :" + todoDTO);
    TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
    dao.updateOne(todoVO);
  }

}
