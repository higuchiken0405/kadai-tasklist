<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/layout.jsp">
    <c:param name="content">
        <h2>タスク新規作成フォーム</h2>
            <form action="${pageContext.request.contextPath}/create" method="POST">
                <c:import url="_form.jsp" />
            </form>
    </c:param>
</c:import>