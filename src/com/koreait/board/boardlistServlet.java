package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.dao.BoardDAO;
import com.koreait.board.model.BoardDTO;


@WebServlet("/list")
public class boardlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strPage = request.getParameter("page");
		int page = (strPage == null ? 1 : Integer.parseInt(strPage));
		
		int rowCnt = 5; //한 화면에 나타날 레코드 수 (글 수)
		BoardDTO param = new BoardDTO();
		param.setRowCountPerPage(5);
		param.setStartIdx(rowCnt);
		param.setStartIdx(rowCnt * (page - 1));
		
		request.setAttribute("pageLength", BoardDAO.selPageLength(param));
		request.setAttribute("list", BoardDAO.selBoardList(param));
		
		String jsp = "/WEB-INF/jsp/list.jsp";
		request.getRequestDispatcher(jsp).forward(request,response);
	}

}