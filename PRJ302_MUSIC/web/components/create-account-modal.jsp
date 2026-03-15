<%-- 
    Document   : create-account-modal
    Created on : Mar 15, 2026, 2:18:22 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="registerModal" class="login-modal">
    <div class="login-box">
        <button class="close-btn" onclick="closeRegister()">✕</button>

        <h2>Tạo tài khoản</h2>

        <form action="MainController" method="POST" class="login-form">
            <input type="hidden" name="action" value="registerAccount"/>
            <input type="text" name="userName" placeholder="Username" required/>
            <input type="email" name="email" placeholder="Email" required/>
            <input type="password" id="password" name="password" placeholder="Password" required/>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required/>
            <input type="text" name="fullName" placeholder="Full Name" required/>
            <input type="date" name="birthday"  required/>

            <select name="gender" required>
                <option value="">Chọn giới tính</option>
                <option value="Male">Nam</option>
                <option value="Female">Nữ</option>
            </select>

            <span id="registerError" style="color:red"></span>

            <c:if test="${not empty error}">
                <div style="color:red;">
                    ${error}
                </div>
            </c:if>

            <c:if test="${not empty msg}">
                <div style="color:green;">
                    ${msg}
                </div>
            </c:if>

            <button type="submit" class="login-submit">
                Tạo tài khoản
            </button>
        </form>
    </div>
</div>
