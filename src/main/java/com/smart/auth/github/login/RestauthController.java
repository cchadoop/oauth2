package com.smart.auth.github.login;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oauth")
public class RestauthController {
    @RequestMapping("/render")
    public void renderAuth(HttpServletResponse response) throws Exception {
        AuthRequest authRequest = getAuthGithubRequest();
//        AuthRequest authRequest = getAuthGiteeRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/callback")
    public Object login(AuthCallback callback) {
        AuthRequest authRequest = getAuthGithubRequest();
//        AuthGiteeRequest authRequest = getAuthGiteeRequest();
        String code = callback.getCode();
        //https://github.com/login/oauth/access_token这个api，传入 client_id, client_secret, code 这三个参数，可以获取到一个 access_token
        AuthResponse response = authRequest.login(callback);
        return response;
    }


    private AuthRequest getAuthGithubRequest() {
        return new AuthGithubRequest(AuthConfig.builder()
                .clientId("ffd9c5411fd14ecf84a3")
                .clientSecret("6ce34bfcf729786c97fe28f7c597c86127b93991")
                .redirectUri("http://127.0.0.1:8080/oauth/callback")
                .build());
    }

    private AuthGiteeRequest getAuthGiteeRequest() {
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId("c718f3cfae02c205137111b73b07819e2fd7bc2e2d05fded38a2a691764aebe8")
                .clientSecret("70abd201f64b08f2ad169b2a8821ba5ec1ba62d18cd7962fbac5a9a6a6042b06")
                .redirectUri("http://127.0.0.1:8080/oauth/callback")
                .build());
    }

}
