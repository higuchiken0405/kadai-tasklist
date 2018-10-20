<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/layout.jsp">
    <c:param name="content">
        <%-- タスクがある場合 --%>
        <c:choose>
            <c:when test="${task != null}">
                <h2>${task.id}の詳細ページ</h2>
                    <table>
                        <tbody>
                            <tr>
                                <td>タスク内容</td>
                                <td><c:out value="${task.content}" /></td>
                            </tr>
                            <tr>
                                <td>作成日時</td>
                                <td><fmt:formatDate value="${task.created_at}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></td>
                            </tr>
                            <tr>
                                <td>更新日時</td>
                                <td><fmt:formatDate value="${task.updated_at}" pattern="yyyy年MM月dd日 HH時mm分ss秒" /></td>
                            </tr>
                        </tbody>
                    </table>
                </c:when>
                <%-- タスクがない場合 --%>
                <c:otherwise>
                    <h2>タスクがありません</h2>
                </c:otherwise>
            </c:choose>
        <p><a href="${pageContext.request.contextPath}/index">一覧ページへ戻る</a></p>
        <c:if test="${task != null}">
            <p><a href="${pageContext.request.contextPath}/edit?id=${task.id}">内容を編集する</a></p>
        </c:if>
    </c:param>
</c:import>