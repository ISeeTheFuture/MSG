<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700&display=swap"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/css/datepicker.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/css/select_box.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/css/paging.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/css/hrBoard.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/css/timepicker.custom.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/resources/css/empLogBoard.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/js/datepicker.min.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/js/datepicker.ko.js"></script>

<style>
</style>
<title>MSG :: 근태 관리</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<section>
		<div>
			<article>
				<div class="subNav">
					<h3>인사관리</h3>
					<ul>
						<c:if test="${memberLoggedIn.authority ne 'N' }"> 		
								<li onclick="location.href='${pageContext.request.contextPath}/member/empLogBoard.do'" style="color:#333333;">근태관리</li>
								<li onclick="location.href='${pageContext.request.contextPath}/member/ioLog.do'">출입기록</li>
            			</c:if>
						<li onclick="location.href='${pageContext.request.contextPath}/member/orgChart.do'">조직도</li>
						<li
							onclick="location.href='${pageContext.request.contextPath}/leave/update.do'">휴가관리</li>
						<li
							onclick="location.href='${pageContext.request.contextPath}/leave/list.do'">휴가내역</li>
						<li onclick="location.href='${pageContext.request.contextPath}/leave/select.do'">나의휴가내역</li>
					</ul>
				</div>

				<form
					action="${pageContext.request.contextPath}/member/empLogBoard.do"
					method="get">
					<div class="content" style=" padding-top: 28px; ">
						<div class="srchBar">
							<div class="select-box">
								<div class="select-box__current" tabindex="1">
									<div class="select-box__value">
										<input class="select-box__input" type="radio" id="R1"
											value="dept_name" name="searchBy" checked="checked" />
										<p class="select-box__input-text">부서</p>
									</div>
									<div class="select-box__value">
										<input class="select-box__input" type="radio" id="R2"
											value="job_name" name="searchBy" />
										<p class="select-box__input-text">직위</p>
									</div>
									<div class="select-box__value">
										<input class="select-box__input" type="radio" id="R3"
											value="emp_name" name="searchBy" />
										<p class="select-box__input-text">이름</p>



									</div>
									<img class="select-box__icon"
										src="http://cdn.onlinewebfonts.com/svg/img_295694.svg"
										alt="Arrow Icon" aria-hidden="true" />
								</div>
								<ul class="select-box__list">
									<li><label class="select-box__option" for="R1"
										aria-hidden="aria-hidden">부서</label></li>
									<li><label class="select-box__option" for="R2"
										aria-hidden="aria-hidden">직위</label></li>
									<li><label class="select-box__option" for="R3"
										aria-hidden="aria-hidden">이름</label></li>
								</ul>
							</div>
							<input type="text" name="keyword" id="srchWord">
							<h1>총 영업일 <span style="font-weight:bold;font-size:1.5em;">${bsnsDay }</span> 일</h1>
						</div>
						<div class="control">
							<input type='text' id='timepicker-start' name="startDate"
								class='datepicker-here' data-language='ko'
								data-date-format="yyyy-mm-dd" autocomplete="off"
								minutesStep="10" /> <i class='far fa-calendar-alt starticon'
								style='font-size: 32px'></i> <span>~</span> <input type='text'
								id='timepicker-end' name="endDate" class='datepicker-here'
								data-language='ko' data-date-format="yyyy-mm-dd"
								autocomplete="off" minutesStep="10" /> <i
								class='far fa-calendar-alt endicon' style='font-size: 32px'></i>
							<button type="submit" name="" id="srchBtn" class="yellowBtn" style="float: none;
    margin-right: auto;
    margin-left: 194px;">
								<i class="fas fa-search" style="font-size: 15px"></i> 검색
							</button>
							
						</div>
						
						<table class="board">
							<tr>
								<th></th>
								<th>부서</th>
								<th>이름</th>
								<th>출근일</th>
								<th>휴가</th>
								<th>지각</th>
								<th>결근</th>
								<th>조퇴</th>
							</tr>
							<c:forEach items="${list }" var="hr" varStatus="vs">
								<tr
									onclick="location.href='${pageContext.request.contextPath}/member/empLog.do?empNo=${hr.empNo}'">
									<td>${vs.count }</td>
									<td>${hr.deptName }</td>
									<td>${hr.empName }</td>
									<td>${bsnsDay - hr.vctnCount<=0?0:bsnsDay - hr.vctnCount }</td>
									<td>${hr.vctnCount }일</td>
									<td>${hr.lateCount }일</td>
									<td>${hr.absentCount }일</td>
									<td>${hr.leaveCount }일</td>
								</tr>
							</c:forEach>
						</table>
						<div class="pagination">
							<c:if test="${paging.startPage != 1 }">
								<a
									href="${pageContext.request.contextPath}/member/empLogBoard.do?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&startDate=${srcDateStart}&endDate=${srcDateEnd}&searchBy=${searchBy}&keyword=${keyword}"
									class="arrow" style="margin-left: 0px; margin-right: 0px;">&laquo;</a>
							</c:if>
							<c:forEach begin="${paging.startPage }" end="${paging.endPage }"
								var="p">
								<c:choose>
									<c:when test="${p == paging.nowPage }">
										<a class="active">${p }</a>
									</c:when>
									<c:when test="${p != paging.nowPage }">
										<a
											href="${pageContext.request.contextPath}/member/empLogBoard.do?nowPage=${p }&cntPerPage=${paging.cntPerPage}&startDate=${srcDateStart}&endDate=${srcDateEnd}&searchBy=${searchBy}&keyword=${keyword}">${p }</a>
									</c:when>
								</c:choose>
							</c:forEach>
							<c:if test="${paging.endPage != paging.lastPage}">
								<a
									href="${pageContext.request.contextPath}/member/empLogBoard.do?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&startDate=${srcDateStart}&endDate=${srcDateEnd}&searchBy=${searchBy}&keyword=${keyword}"
									class="arrow" style="margin-left: 0px; margin-right: 0px;">&raquo;</a>
							</c:if>
						</div>
						
					</div>
				</form>

			</article>
		</div>
	</section>
	<script>
		//광역변수 선언
		let startDate;
		let endDate;

		//아이콘 클릭 시 dateTimePicker focus
		$(document).ready(function() {

			$('.starticon').click(function() {
				$('#timepicker-start').focus();
			});
			$('.endicon').click(function() {
				$('#timepicker-end').focus();
			});

			$("#timepicker-start").val("${srcDateStart}");
			$("#timepicker-end").val("${srcDateEnd}");
		});

		$("#timepicker-start").datepicker({
			onSelect : function onSelect(start) {
				startDate = start;
				if (startDate !== undefined && endDate !== undefined) {
					validate(startDate, endDate);
				}
			},
			maxDate : new Date()
		});
		$("#timepicker-end").datepicker({
			onSelect : function onSelect(end) {
				endDate = end;
				if (startDate !== undefined && endDate !== undefined) {
					validate(startDate, endDate);
				}
			},
			maxDate : new Date()
		});

		//날짜로 변환
		function toDate(strDate) {
			var y = strDate.substr(0, 4);
			var m = strDate.substr(5, 2);
			var d = strDate.substr(8, 2);

			return new Date(y, m - 1, d);
		}

		//유효성검사
		function validate(startDate, endDate) {
			var dstartDate = toDate(startDate);
			var dendDate = toDate(endDate);

			//검색하고자 하는 시작날짜가 종료날짜보다 나중인 경우
			if (+dstartDate > +dendDate) {
				alert("날짜를 다시 지정해주세요");
			} else {
				var bsnsDay = calcDate(dstartDate, dendDate);
				location.href = "${pageContext.request.contextPath}/member/empLogBoard.do?startDate="
						+ startDate
						+ "&endDate="
						+ endDate
						+ "&bsnsDay="
						+ bsnsDay;
			}
		}

		function calcDate(date1, date2) {

			var count = 0;

			while (true) {
				var temp_date = date1;
				if (temp_date.getTime() > date2.getTime()) {
					break;
				} else {
					var tmp = temp_date.getDay();
					if (tmp == 0 || tmp == 6) {
						// 주말
						console.log("주말");
					} else {
						// 평일
						console.log("평일");
						count++;
					}
					temp_date.setDate(date1.getDate() + 1);
				}
			}
			return count;
		}

		function searchBy() {
			let by = $("input[name='searchBy']").val();
			alert(by);
		}
	</script>
</body>
</html>