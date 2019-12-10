package lomo.hust.dictvocab.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupUser {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_confirmation")
    @Expose
    private String passwordConfirmation;

    /**
     * No args constructor for use in serialization
     *
     */
    public SignupUser() {
    }

    /**
     *
     * @param passwordConfirmation
     * @param password
     * @param name
     * @param email
     */
    public SignupUser(String name, String email, String password, String passwordConfirmation) {
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}