<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/layout.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${task != null}">
                <h2> ID"${task.id}"のタスク編集フォーム</h2>
                <form action="${pageContext.request.contextPath}/update" method="POST">
                    <c:import url="_form.jsp" />
                </form>
                <p><a href="#" onclick="destroy()">このタスクを削除する</a></p>
                <form action="${pageContext.request.contextPath}/destroy" method="POST">
                    <input type="hidden" name="_token" value="${_token}">
                </form>
            </c:when>
            <c:otherwise>
                <h2>タスクが見つかりません</h2>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>
<script>
function destroy() {
	if(confirm("本当に削除してよろしいですか？")) {
		document.forms[1].submit();
	}
}
</script>