package lomo.hust.dictvocab.object;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("meaning")
    @Expose
    private List<String> meaning = null;
    @SerializedName("trait")
    @Expose
    private List<String> trait = null;
    @SerializedName("__v")
    @Expose
    private Integer v;


    public Word() {
    }

    public Word(String id, Integer index, String key, String type, List<String> meaning, List<String> trait, Integer v) {
        super();
        this.id = id;
        this.index = index;
        this.key = key;
        this.type = type;
        this.meaning = meaning;
        this.trait = trait;
        this.v = v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMeaning() {
        return meaning;
    }

    public void setMeaning(List<String> meaning) {
        this.meaning = meaning;
    }

    public List<String> getTrait() {
        return trait;
    }

    public void setTrait(List<String> trait) {
        this.trait = trait;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}