package lomo.hust.dictvocab.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WordPost {
    @SerializedName("oid")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("list")
    @Expose
    private String tag;

    public WordPost(String id, String type, String tag) {
        this.id = id;
        this.type = type;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
