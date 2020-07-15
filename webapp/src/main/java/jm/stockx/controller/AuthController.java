package jm.stockx.controller;

import com.github.scribejava.apis.vk.VKOAuth2AccessToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import jm.stockx.auth.GoogleAuthorization;
import jm.stockx.entity.User;
import jm.stockx.UserService;
import jm.stockx.auth.VkAuthorisation;
import jm.stockx.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/authorization")
public class AuthController {

    private VkAuthorisation vkAuthorization;
    private GoogleAuthorization googleAuthorization;
    private UserService userService;

    @Autowired
    public AuthController(VkAuthorisation vkAuthorisation, GoogleAuthorization googleAuthorization, UserService userService) {
        this.vkAuthorization = vkAuthorisation;
        this.googleAuthorization = googleAuthorization;
        this.userService = userService;
    }

    @GetMapping("/vkAuth")
    public Response<Object> toVk() throws URISyntaxException {
        URI vk = new URI(vkAuthorization.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(vk);
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/returnCodeVK")
    public Response<Object> getCodeThird(@RequestParam String code) throws InterruptedException, ExecutionException, IOException, URISyntaxException {
        OAuth2AccessToken token = vkAuthorization.toGetTokenVK(code);
        String email = ((VKOAuth2AccessToken) token).getEmail();
        User currentUser = vkAuthorization.toCreateUser(token, email);
        return returnOrCreateNewUser(email, currentUser);
    }

    @GetMapping("/googleAuth")
    public Response<Object> toGoogle() throws URISyntaxException {
        URI google = new URI(googleAuthorization.getAuthorizationUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(google);
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }

    @GetMapping("/returnCodeGoogle")
    public Response<Object> getCodeGoogle (@RequestParam String code)
            throws InterruptedException, ExecutionException, IOException, URISyntaxException {
        OAuth2AccessToken token = googleAuthorization.getGoogleOAuth2AccessToken(code);
        String email = token.getParameter("email");
        User currentUser = googleAuthorization.getGoogleUser(token, email);
        return returnOrCreateNewUser(email, currentUser);
    }

    private Response<Object> returnOrCreateNewUser(String email, User currentUser) throws URISyntaxException {
        if (userService.getUserByUserName(email) == null) {
            userService.createUser(currentUser);
        }
        userService.login(currentUser.getUsername(), currentUser.getPassword(), currentUser.getAuthorities());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/index"));
        Response.BodyBuilder bodyBuilder = Response.ok();
        return bodyBuilder.headers(httpHeaders).build();
    }
}
