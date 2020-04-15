<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MSG</title>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
    <script src="${pageContext.request.contextPath }/resources/contextMenu/dist/jquery.ui.position.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/contextMenu/dist/jquery.contextMenu.min.js"></script>
    
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css">
    <link href="${pageContext.request.contextPath }/resources/contextMenu/dist/jquery.contextMenu.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Sen:wght@400;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/4c554cd518.js" crossorigin="anonymous"></script>
</head>
<style>
.welcomeBox {
    opacity: 0.4;
    position: absolute;
    top: 355px;
    width: 100%;
    height: 100%;
    z-index: 0;
    font-size: 30px;
    text-align: center;
}
.welcomeBox p {
	font-size: 34px;
	margin: 0;
	font-weight: 900;
}
</style>
<script>
</script>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section>
        <div>
            <article>
                <div style="margin-left: 15px;" class="subNav">
                    <div style="margin-top: 55px;">
                        <button type="button" name="" id="boardBtn1" class="yellowBtn">접속자 확인</button>
                        <button type="button" name="" id="boardBtn2" class="yellowBtn">팀 채널 가기</button>
                    </div>
                    <div style="bottom: 60px; width:100px; position: relative; left: 825px;text-align: right;">
                        <img style="right: 0px; width: 190px; height: 75px; " src="${pageContext.request.contextPath }/resources/image/msgMain.png"/>
                    </div>
                </div>
                <div style="width: 500px;;" class="content1">
                    <div style="width:500px; margin-bottom: 50px; margin-top: 30px;" id="notice">
                        <p style="text-align: left; margin-left: 40px;"><i style="color:#f4ca25;" class="fas fa-circle"></i > 공지사항</p>
                        <table>
                            <tr>
                                <th>글쓴이</th>
                                <th>작성일</th>
                                <th>제목</th>
                                
                            </tr>
                            <c:forEach items="${boardList }" var="bl">
	                            <tr>
	                                <td>${bl.empNo }</td>
	                                <td>${bl.bdate }</td>
	                                <td>${bl.title }</td>
	                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div id="55">
                        <p style="text-align: left; margin-left: 40px;"><i style="color:#f4ca25;" class="fas fa-circle"></i > 전자문서</p>
                        <table>
                            <tr>
                                <th>기안자</th>
                                <th>문서종류</th>
                                <th>제목</th>
                            </tr>
                            <tr>
                                <td>장그래</td>
                                <td>자유</td>
                                <td >
                                    실현에 청춘의 대중을 설레는 얼마나 하(4)
                                </td>
                            </tr>
                            <tr>
                                <td>천광웅</td>
                                <td>건의</td>
                                <td>피고 대한 무한한 바이며, 사막이다.(5)</td>
                            </tr>
                            <tr>
                                <td>장그래</td>
                                <td>건의</td>
                                <td>안녕하세용가나다라마바사(1)</td>
                            </tr>
                            <tr>
                                <td>천광웅</td>
                                <td>건의</td>
                                <td>무서운이야기가나다라마바사</td>
                            </tr>
                            <tr>
                                <td>장그래</td>
                                <td>공지&행사</td>
                                <td>재밌는얘기가나다라마바사</td>
                            </tr>
                            <tr>
                                <td>장그래</td>
                                <td>자유</td>
                                <td>퇴근하겠습니다가나다라마바사</td>
                            </tr>     
                        </table>
                    </div>
                </div>
                <div style="width: 500px;" id="content2">
                    <div style="width:500px; margin-bottom: 34px; margin-top: 30px;" id="notice">
                        <div id="calender" style="width:400px; height: 300px; margin-left: 50px; border: 1px solid black;">
                            <p> 달력소스 오픈소스</p>
                        </div>
                    </div>
                    <div id="66" >
                        <p style="text-align: left; margin-left: 40px;"><i style="color:#f4ca25;" class="fas fa-circle"></i > 이번 주 일정</p>
                        <i class="fa fa-question-circle-o" style="font-size:24px; color:#999999; position:absolute; top:525px; left:1090px;"></i>
                        <table id="calTable" style="text-align: left; cursor:pointer;" onclick="location.href='${pageContext.request.contextPath}/sched/calcover.do'"">
                            <tr>
                                <td id="day_1"></td>
                                <td>월</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td id="day_2"></td>
                                <td>화</td>
                                <td></td> 
                            </tr>
                            <tr>
                                <td id="day_3"></td>
                                <td>수</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td id="day_4"></td>
                                <td>목</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td id="day_5"></td>
                                <td>금</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td id="day_6"></td>
                                <td>토</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td id="day_7"></td>
                                <td>일</td>
                                <td></td>
                            </tr>

                        </table>
                          <!--   <div title="그룹웨어만들기" style="width:30px; height:30px; border-radius:30px; background:#f4ca25;"></div> -->
                    </div>
                </div>
            </article>
           	<div id="informSched">
           		<div class="alldayY"></div>
			    <p>하루종일</p>
			    <div class="alldayN closer"></div>
			    <p class="div">하루 미만</p>
			    <hr />
			    <div class="schedCircle" id="c1"></div>
			    <p>회의</p>
			    <div class="schedCircle" id="c2"></div>
			    <p>기타</p>
			    <div class="schedCircle" id="c3"></div>
			    <p>외근</p>
			    <div class="schedCircle" id="c4"></div>
			    <p>휴가</p>
			    <div class="schedCircle" id="c5"></div>
			    <p>출장</p>
			    <div class="schedCircle" id="c6"></div>
			    <p id="over">데드라인</p>
			    <div class="schedCircle" id="c7"></div>
			    <p>회의실 예약</p>
			    <div class="schedCircle closer2" id="c8"></div>
			    <p class="over2">차량 예약</p>
			    <div class="schedCircle" id="c9"></div>
			    <p>생일</p>
		    </div>
        </div>
    </section>
    
<script src="${pageContext.request.contextPath }/resources/js/main.js"></script>
</body>
</html>
