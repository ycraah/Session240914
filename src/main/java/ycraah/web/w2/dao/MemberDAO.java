package ycraah.web.w2.dao;

import lombok.Cleanup;
import ycraah.web.w2.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
  public MemberVO getWithPassword(String mid, String mpw) throws SQLException {
    String sql = "select * from tbl_member where mid = ? and mpw = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, mid);
    preparedStatement.setString(2, mpw);

    @Cleanup ResultSet rs = preparedStatement.executeQuery();
    rs.next();

    MemberVO memberVO = MemberVO.builder()
        .mid(rs.getString(1))
        .mpw(rs.getString(2))
        .mname(rs.getString(3))
        .build();
    return memberVO;
  }

  public void updateUuid(String mid, String uuid) throws SQLException {
    String sql = "update tbl_member set uuid = ? where mid = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, uuid);
    preparedStatement.setString(2, mid);

    preparedStatement.executeUpdate();
  }

  public MemberVO selectUUID(String uuid) throws SQLException {
    String sql = "select mid, mpw, mname, uuid from tbl_member where uuid = ?";
    @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
    @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, uuid);

    @Cleanup ResultSet rs = preparedStatement.executeQuery();
    rs.next();
    MemberVO memberVO = MemberVO.builder()
        .mid(rs.getString(1))
        .mpw(rs.getString(2))
        .mname(rs.getString(3))
        .uuid(rs.getString(4))
        .build();
    return memberVO;
  }
}
