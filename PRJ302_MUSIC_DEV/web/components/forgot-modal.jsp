<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="forgotModal" class="login-modal">
    <div class="login-box">
        <button class="close-btn" onclick="closeForgot()">✕</button>

        <h2>Cài đặt lại mật khẩu</h2>

        <form class="login-form">
            <input type="email"
                   placeholder="Nhập email của bạn"
                   required>

            <button type="submit" class="login-submit">
                Gửi mã
            </button>
        </form>
    </div>
</div>
