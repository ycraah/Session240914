package ycraah.web.w2.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import ycraah.web.w2.domain.TodoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TodoDAO {
  //TodoService와 연동되어 DB를 중재하는 클래스
  public void insert(TodoVO vo) throws SQLException {
    String sql = "insert into tbl_todo(title, dueDate, finished) values(?,?,?)";

    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, vo.getTitle());
    preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
    preparedStatement.setBoolean(3, vo.isFinished());

    preparedStatement.executeUpdate();
  }

  public List<TodoVO> selectAll() throws SQLException {
    String sql = "select * from tbl_todo";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    @Cleanup ResultSet rs = preparedStatement.executeQuery();
    List<TodoVO> list = new ArrayList<>();

    while (rs.next()) {
      TodoVO vo = TodoVO.builder()
          .tno(rs.getLong("tno"))
          .title(rs.getString("title"))
          .dueDate(rs.getDate("dueDate").toLocalDate())
          .finished(rs.getBoolean("finished"))
          .build();
      list.add(vo);
    }
    return list;
  }

  public TodoVO selectOne(Long tno) throws SQLException {
    String sql = "select * from tbl_todo where tno = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setLong(1, tno);

    @Cleanup ResultSet rs = preparedStatement.executeQuery();

    rs.next();
    TodoVO vo = TodoVO.builder()
        .tno(rs.getLong("tno"))
        .title(rs.getString("title"))
        .dueDate(rs.getDate("dueDate").toLocalDate())
        .finished(rs.getBoolean("finished"))
        .build();
    return vo;
  }

  public void deleteOne(Long tno) throws SQLException {
    String sql = "delete from tbl_todo where tno = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setLong(1, tno);
    preparedStatement.executeUpdate();

  }

  public void updateOne(TodoVO todoVO) throws SQLException {
    
    String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, todoVO.getTitle());
    preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
    preparedStatement.setBoolean(3, todoVO.isFinished());
    preparedStatement.setLong(4, todoVO.getTno());
    preparedStatement.executeUpdate();

  }
}
