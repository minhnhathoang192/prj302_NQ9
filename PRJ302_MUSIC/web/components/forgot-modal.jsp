<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="forgotModal" class="login-modal">
    <div class="login-box">
        <button class="close-btn" onclick="closeForgot()">✕</button>

        <h2>Cài đặt lại mật khẩu</h2>

        <form action="MainController" method="post" class="login-form">

            <input type="hidden" name="action" value="forgotPassword">

            <input type="email"
                   name="email"
                   placeholder="Nhập email của bạn"
                   required>
            
            <c:if test="${not empty errorEmail}">
                <div style="color:red;">
                    ${errorEmail}
                </div>
            </c:if>

            <c:if test="${not empty msgEmail}">
                <div style="color:green;">
                    ${msgEmail}
                </div>
            </c:if>

            <button type="submit" class="login-submit">
                Gửi link reset
            </button>

        </form>
    </div>
</div>