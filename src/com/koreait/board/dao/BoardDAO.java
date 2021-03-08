package com.koreait.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.koreait.board.model.BoardDTO;
import com.koreait.board.model.BoardEntity;

public class BoardDAO {
	public static void insBoard(BoardEntity vo) { // 글등록
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO t_board"
				+ " (title, ctnt)"
				+ "VALUES"
				+ "(?, ?)";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getCtnt());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps);
		}
	}
	
	public static void delBoard(BoardEntity param) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM t_board WHERE i_board = ?";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps);
		}
	}
	
	public static void updBoard(BoardEntity param) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE t_board "
				+ "SET title = ?, ctnt = ? "
				+ "WHERE i_board = ?";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getTitle());
			ps.setString(2, param.getCtnt());
			ps.setInt(3, param.getI_board());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps);
		}
	}
	
	public static List<BoardEntity> selBoardList(BoardDTO param) {
		List<BoardEntity> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // select문에만 rs 사용
		
		String sql = "SELECT i_board, title, r_dt FROM t_board"
				+ " ORDER BY i_board DESC "
				+ " LIMIT ?, ? ";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getStartIdx());
			ps.setInt(2, param.getRowCountPerPage());
			rs = ps.executeQuery(); // *select용*
			
			while(rs.next()) {
				int i_board = rs.getInt("i_board");
				String title = rs.getNString("title");
				String r_dt = rs.getString("r_dt");
			
				BoardEntity vo = new BoardEntity();
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setR_dt(r_dt);
				list.add(vo);
			} 
		} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DbUtils.close(con, ps, rs);
		}
		return list;
	}
	
	public static BoardEntity selBoard(BoardEntity param) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT title, ctnt, r_dt FROM t_board WHERE i_board = ?";
		
		try {
			con = DbUtils.getCon();
			ps = con.prepareStatement(sql); // 값 넣는 담당 > ps
			ps.setInt(1, param.getI_board());
			rs = ps.executeQuery(); // *select용* >> executeQuery : DB에서 데이터를 가져와서 결과 집합을 반환
			
			if(rs.next()) {
				BoardEntity vo = new BoardEntity();
				String title = rs.getString("title");
				String ctnt = rs.getString("ctnt");
				String r_dt = rs.getString("r_dt");
				
				vo.setTitle(title);
				vo.setCtnt(ctnt);
				vo.setR_dt(r_dt);
				vo.setI_board(param.getI_board());
				
				return vo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return null;
	}
	
	public static int selPageLength(BoardDTO param) { //list페이지에서 호출
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT CEIL(COUNT(i_board) / ?) FROM t_board"; // 무조건 하나의 레코드는 출력이 됨 > count가 몇개인 지 보여줘야 하니까 / 값이 하나도 없어도 (NULL)이나 0이 출력됨
		
		try {
			con=DbUtils.getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getRowCountPerPage());
			rs = ps.executeQuery(); // ResultSet return
			if(rs.next()) {
				return rs.getInt(1); // (1) 정수 값이 들어갔으니 첫번째 컬럼
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(con, ps, rs);
		}
		return 0;
		
	}
}