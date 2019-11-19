package com.lynn;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class AuthorizeIn {

    @NotBlank(message = "缺少response_type参数")
    private String responseType;
    @NotBlank(message = "缺少client_id参数")
    @Length(min = 5,max = 10)
    @Email(message = "email格式错误")
    private String clientId;

    private String state;

    @NotBlank(message = "缺少redirect_uri参数")
    private String redirectUri;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
