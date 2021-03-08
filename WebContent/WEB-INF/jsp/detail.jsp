<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>
	<div>
		<a href="/list">리스트로 돌아가기</a>
		<a href="/del?i_board=${param.i_board}"><button>삭제</button></a>
		<a href="/update?i_board=${param.i_board}"><button>수정</button></a> <!-- 뭘 수정한지 알려줘야함 -->
	</div>
	<div>
		<div>번호 : ${param.i_board}</div> <!-- 쿼리스트링에 있는 값은 jsp에서도 받을 수 있음 -->
		<div>타이틀 : ${detail.title}</div>
		<div>작성일시 : ${detail.r_dt}</div>
		<hr>
		<div>${detail.ctnt}</div>
		</hr>
	</div>
</body>
</html>