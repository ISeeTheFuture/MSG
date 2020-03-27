<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/edocList.css">
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/4c554cd518.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath }/resources/js/jquery-3.4.1.js"></script>
    <title>listForm</title>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <section>
        <div>
            <article>
                <div class="subNav">
                    <h3>전자결재</h3>
                    <ul>
                        <li onclick="location.href='${pageContext.request.contextPath}/edoc/srch.do'">문서검색</li>
                        <li onclick="location.href='${pageContext.request.contextPath}/edoc/list.do'">내문서함</li>
                        <li onclick="location.href='${pageContext.request.contextPath}/edoc/write.do'">문서기안</li>
                     </ul>
                </div>
                <div class="content">
                    <div class="control">
                        <div>
                            <h1>문서 유형</h1>
                            <div class="checkbox_wrap">
                                <label class="myDocu-container kor float" for="myDocu">내 기안문서
                                            <input type="checkbox" name="myDocu" id="myDocu">
                                            <span class="myDocu-checkmark"></span>
                               </label>
                                <label class="reqDocu-container kor float" for="reqDocu">결재요청문서
                                            <input type="checkbox" name="reqDocu" id="reqDocu">
                                            <span class="reqDocu-checkmark"></span>
                               </label>
                                <label class="compDocu-container kor float" for="compDocu">결재완료문서
                                            <input type="checkbox" name="compDocu" id="compDocu">
                                            <span class="compDocu-checkmark"></span>
                               </label>
                                <label class="refDocu-container kor float" for="refDocu">참조된 문서
                                            <input type="checkbox" name="refDocu" id="refDocu">
                                            <span class="refDocu-checkmark"></span>
                               </label>
                                <label class="endDocu-container kor float" for="endDocu">종결된 문서
                                            <input type="checkbox" name="endDocu" id="endDocu">
                                            <span class="endDocu-checkmark"></span>
                               </label>
                            </div>
                        </div>
                    </div>
                    <table>
                        <tr>
                            <th></th>
                            <th>문서종류</th>
                            <th>제목</th>
                            <th>기안자</th>
                            <th>보안등급</th>
                            <th>작성일</th>
                            <th>유형</th>
                            <th>상태</th>
                        </tr>
                            <style>
                            .forTest:hover{
                            	cursor: pointer;
                            }
                            </style>
                        <tr class="forTest" onclick="location.href='${pageContext.request.contextPath}/edoc/confirm.do'">
                            <td>110</td>
                            <td>재직증명서</td>
                            <td>재직증명서 발급 요청</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                        <tr>
                            <td>109</td>
                            <td>휴가신청서</td>
                            <td>휴가 신청</td>
                            <td>천광웅</td>
                            <td>B</td>
                            <td>2020/02/29</td>
                            <td>결재요청</td>
                            <td>대기</td>
                        <tr>
                            <td>110</td>
                            <td>기타양식</td>
                            <td>사직서</td>
                            <td>장그래</td>
                            <td>C</td>
                            <td>2020/02/29</td>
                            <td>기안한문서</td>
                            <td>반려</td>
                        </tr>
                    </table>
                    <div class="pagination">
                        <a href="#" class="arrow">&laquo;</a>
                        <a href="#">1</a>
                        <a href="#" class="active">2</a>
                        <a href="#">3</a>
                        <a href="#">4</a>
                        <a href="#">5</a>
                        <a href="#" class="arrow">&raquo;</a>
                    </div>
                    <div class="srchBar">
                        <div class="select-box">
                            <div class="select-box__current" tabindex="1">
                                <div class="select-box__value">
                                <input class="select-box__input" type="radio" id="0" value="1" name="Ben"/>
                                <p class="select-box__input-text">제목</p>
                                </div>
                                <div class="select-box__value">
                                <input class="select-box__input" type="radio" id="1" value="2" name="Ben"/>
                                <p class="select-box__input-text">기안자</p>
                                </div>
                                <div class="select-box__value">
                                <input class="select-box__input" type="radio" id="2" value="3" name="Ben"/>
                                <p class="select-box__input-text">문서종류</p>
                                </div>
                                <div class="select-box__value">
                                <input class="select-box__input" type="radio" id="3" value="4" name="Ben" checked="checked"/>
                                <p class="select-box__input-text">전체</p>
                                </div><img class="select-box__icon" src="http://cdn.onlinewebfonts.com/svg/img_295694.svg" alt="Arrow Icon" aria-hidden="true"/>
                            </div>
                            <ul class="select-box__list">
                                <li>
                                <label class="select-box__option" for="3" aria-hidden="aria-hidden">전체</label>
                                </li>
                                <li>
                                <label class="select-box__option" for="0" aria-hidden="aria-hidden">제목</label>
                                </li>
                                <li>
                                <label class="select-box__option" for="1" aria-hidden="aria-hidden">기안자</label>
                                </li>
                                <li>
                                <label class="select-box__option" for="2" aria-hidden="aria-hidden">문서종류</label>
                                </li>
                            </ul>
                        </div>
                        <input type="text" name="" id="srchWord" placeholder="검색창 나중에 위 쪽으로 올릴것">
                        <button type="button" name="" id="srchBtn" class="yellowBtn"><i class="fas fa-search" style="font-size:15px"></i> 검색</button>
                    </div>
                </div>
            </article>
        </div>
    </section>
</body>
</html>