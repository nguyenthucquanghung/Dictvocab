package lomo.hust.dictvocab.object;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Errors {

    @SerializedName("email")
    @Expose
    private List<String> email = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Errors() {
    }

    /**
     *
     * @param email
     */
    public Errors(List<String> email) {
        super();
        this.email = email;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

}