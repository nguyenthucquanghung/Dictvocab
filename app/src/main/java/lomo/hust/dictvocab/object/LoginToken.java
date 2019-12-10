package lomo.hust.dictvocab.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginToken {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_at")
    @Expose
    private String expiresAt;
    @SerializedName("errors")
    @Expose
    private Errors errors;
    @SerializedName("message")
    @Expose
    private String message = "";
    @SerializedName("success")
    @Expose
    private Boolean success;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginToken() {
    }

    /**
     *
     * @param accessToken
     * @param tokenType
     * @param expiresAt
     */
    public LoginToken(String accessToken, String tokenType, String expiresAt) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
    }

    public LoginToken(String accessToken, String tokenType, String expiresAt, Errors errors, String message, Boolean success) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
        this.errors = errors;
        this.message = message;
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}