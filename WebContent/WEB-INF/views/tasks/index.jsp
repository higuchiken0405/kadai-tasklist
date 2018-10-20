<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/layout.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>タスク一覧</h2>
        <ul>
            <c:forEach var="task" items="${tasks}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${task.id}">
                        <c:out value="${task.content}" />
                    </a>
                </li>
            </c:forEach>
        </ul>
        <%-- ページネーション--%>
        <div id="pagination">
            (全 ${tasks_count} 件)<br>
            <c:forEach var="i" begin="1" end="${((tasks_count - 1) / 5) + 1}" step="1" >
                <c:choose>
                    <c:when test="${i == page}">
                        <%-- 変数iとpageが等しいなら、表示だけ --%>
                        <c:out value="${i}" /> &nbsp;
                    </c:when>
                    <c:otherwise>
                        <%-- 変数iとpageが等しくないなら、リンク付きページ表示 --%>
                        <a href="${pageContext.request.contextPath}/index?page=${i}"><c:out value="${i}" /></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="${pageContext.request.contextPath}/new">タスクの新規作成</a></p>

    </c:param>
</c:import>