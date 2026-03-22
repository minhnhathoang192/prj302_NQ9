<%-- 
    Document   : login-modal
    Created on : Feb 6, 2026, 3:56:26 PM
    Author     : NQ9
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    
        <div id="loginModal" class="login-modal">
            <div class="login-box">
                <button class="close-btn" onclick="closeLogin()">✕</button>

                <h2>Đăng nhập</h2>

                <form action="MainController" method="post" class="login-form">
                    <input type="hidden" name="action" value="login">
                    <input type="text" name="username" placeholder="Nhập email/username của bạn" required>
                    <input type="password" name="password" placeholder="Nhập mật khẩu của bạn" required>
                    
                    <c:if test="${not empty message}">
                        <span style="color: red">${message}</span>
                    </c:if>
                        
                    <div class="login-options">
                        <div class="check-row">
                            <input type="checkbox" id="remember">
                            <label for="remember">Nhớ cho lần đăng nhập tới</label>
                        </div>

                        <a href="#" class="forgot-link" onclick="openForgot()">Quên mật khẩu?</a>
                        
                         <a href="#" class="forgot-link" onclick="openRegister()">Tạo tài khoản</a>

                    </div>


                    <div class="login-policy">
                        <div class="check-row">
                            <input type="checkbox" id="policy" required>
                            <label for="policy">
                                Tôi đã đọc, hiểu rõ và tự nguyện đồng ý các điều khoản về việc thu thập,
                                xử lý dữ liệu cá nhân, quyền và nghĩa vụ của tôi được quy định tại
                                <a href="#">Chính sách bảo mật</a> và
                                <a href="#">Điều khoản sử dụng</a>
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="login-submit">Đăng nhập</button>
                </form>
            </div>
        </div>
