<%-- 
    Document   : login
    Created on : Feb 3, 2026, 11:38:04 PM
    Author     : NQ9
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập hệ thống</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <style>
            body{
                background-color: #f0f2f5;
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .login-card{
                width: 100%;
                max-width: 400px;
                padding: 2rem;
                border: none;
                border-radius: 1rem;
                box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.15);
            }
            .btn-login{
                padding: 0.8rem;
                font-weight: 600;
                border-radius: 0.5rem;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="card login-card mx-auto">
                <div class="text-center mb-4">
                    <i class="bi bi-person-cricle text-primary" style="font-size: 3rem;"></i>
                    <h2 class="fw-bold mt-2">Đăng Nhập</h2>
                    <p class="text-muted">Vui lòng nhập tài khoản của bạn</p>
                </div>

                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="login"/>
                    <div class="mb-3">
                        <label class="form-label">Tên Ðang Nhap</label>
                        <div class="input-group">
                            <span class="input-group-text bg-light"><i class="bi bi-person"></i></span>
                            <input type="text" name="txtUserName" class="form-controller"
                                   placeholder="Nhap username" required>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label">Mat Khau</label>
                        <div class="input-group">
                            <span class="input-group-text bg-light"><i class="bi bi-key"></i></span>
                            <input type="password" name="txtPassword" class="form-controller"
                                   placeholder="Nhap password" required>
                        </div>
                    </div>


                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary btn-login">
                            Dang Nhap
                        </button>
                    </div>
                </form>

                <c:if test="${not empty message}">
                    <div class="alert alert-danger mt-3 py-2 text-center" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i> ${message}
                    </div>
                </c:if>

                <div class="text-center mt-4">
                    <a href="index.jsp" class="text-decoration-none small text-secondary">
                        <i class="bi bi-arrow-left"></i> Quay lai trang chu
                    </a>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
