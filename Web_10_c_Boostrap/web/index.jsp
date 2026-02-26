<%-- 
    Document   : index
    Created on : Feb 3, 2026, 3:21:32 AM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                color: white;
            }
            .welcome-card {
                background: rgba(255, 255, 255, 0.1);
                backdrop-filter: blur(10px);
                padding: 3rem;
                border-radius: 20px;
                border: 1px solid rgba(255, 255, 255, 0.2);
                text-align: center;
                box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
            }
            .btn-login {
                padding: 12px 40px;
                font-size: 1.2rem;
                border-radius: 50px;
                transition: all 0.3s ease;
            }
            .btn-login:hover {
                transform: translateY(-3px);
                box-shadow: 0 5px 15px rgba(0,0,0,0.3);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="welcome-card mx-auto" style="max-width: 500px">
                <h1 class="display-4 fw-bold mb-3">Welcom Back</h1>
                <p class="lead mb-5">
                    Hệ thống quản lý thông tin các trường Đại học chuyên nghiệp.
                </p>
                <div class="d-grid gap-2">
                    <a href="login.jsp" class="btn btn-light btn-login text-primary fw-bold w-100">Đăng nhập ngay</a>

                </div>
                
                <div class="mt-4 small opacity-75">
                    © 2026 - Developed by NhatHoang
                </div>
            </div>

        </div>

    </body>
</html>
