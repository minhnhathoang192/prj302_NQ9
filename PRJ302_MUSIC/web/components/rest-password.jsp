<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Reset Password</title>

        <style>

            *{
                box-sizing:border-box;
            }

            body{
                font-family: Arial, Helvetica, sans-serif;
                background:linear-gradient(135deg,#0f0f0f,#1e1e1e);
                color:white;
                display:flex;
                justify-content:center;
                align-items:center;
                height:100vh;
                margin:0;
            }

            .reset-box{
                width:420px;
                background:#1e1e1e;
                padding:35px;
                border-radius:12px;
                box-shadow:0 10px 25px rgba(0,0,0,0.5);
            }

            .reset-box h2{
                text-align:center;
                margin-bottom:20px;
            }

            input{
                width:100%;
                padding:12px;
                margin:10px 0;
                border-radius:6px;
                border:none;
                outline:none;
            }

            input:focus{
                border:2px solid #1db954;
            }

            button{
                width:100%;
                padding:12px;
                background:#1db954;
                border:none;
                color:white;
                font-size:16px;
                border-radius:6px;
                cursor:pointer;
                transition:0.2s;
            }

            button:hover{
                background:#1ed760;
            }

            .back-btn{
                margin-top:10px;
                background:#333;
            }

            .back-btn:hover{
                background:#555;
            }

            .error{
                color:#ff4d4d;
                margin-top:10px;
                text-align:center;
            }

            .success{
                color:#1db954;
                margin-top:10px;
                text-align:center;
            }

        </style>
    </head>

    <body>

        <div class="reset-box">

            <h2>🔑 Đặt lại mật khẩu</h2>

            <form action="${pageContext.request.contextPath}/MainController" method="post">

                <input type="hidden" name="action" value="resetPassword">

                <input type="hidden" name="tokenEmail" value="${param.token}">

                <input type="password"
                       name="password"
                       placeholder="Nhập mật khẩu mới"
                       required>

                <input type="password"
                       name="confirmPassword"
                       placeholder="Xác nhận mật khẩu"
                       required>

                <button type="submit">
                    Đổi mật khẩu
                </button>

            </form>

            <!-- nút quay về trang chủ -->

            <form action="${pageContext.request.contextPath}/index.jsp">

                <button class="back-btn">
                    ← Quay về trang chủ
                </button>

            </form>

            <c:if test="${not empty errorEmail}">
                <p class="error">${errorEmail}</p>
            </c:if>

            <c:if test="${not empty msgEmail}">
                <p class="success">${msgEmail}</p>
            </c:if>

        </div>

    </body>
</html>