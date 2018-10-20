<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/layout.jsp">
    <c:param name="content">
        <h2>"${task.id}"の詳細ページ</h2>
        <table>
            <tbody>
                <tr>
                    <td>タスク内容</td>
                    <td><c:out value="${taks.content}" /></td>
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

        <p><a href="${pageContext.request.contextPath}/index">一覧ページへ戻る</a></p>
        <p><a href="${pageContext.request.contextPath}/edit?id=${task.id}">内容を編集する</a></p>
    </c:param>
</c:import>