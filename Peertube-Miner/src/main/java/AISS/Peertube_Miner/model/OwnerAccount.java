
package AISS.Peertube_Miner.model;

import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "name",
    "host",
    "avatars",
    "id",
    "hostRedundancyAllowed",
    "displayName"
})
@Generated("jsonschema2pojo")
public class OwnerAccount {

    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("host")
    private String host;
    @JsonProperty("avatars")
    private List<Avatar> avatars;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("hostRedundancyAllowed")
    private Boolean hostRedundancyAllowed;
    @JsonProperty("displayName")
    private String displayName;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("host")
    public String getHost() {
        return host;
    }

    @JsonProperty("host")
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty("avatars")
    public List<Avatar> getAvatars() {
        return avatars;
    }

    @JsonProperty("avatars")
    public void setAvatars(List<Avatar> avatars) {
        this.avatars = avatars;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("hostRedundancyAllowed")
    public Boolean getHostRedundancyAllowed() {
        return hostRedundancyAllowed;
    }

    @JsonProperty("hostRedundancyAllowed")
    public void setHostRedundancyAllowed(Boolean hostRedundancyAllowed) {
        this.hostRedundancyAllowed = hostRedundancyAllowed;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OwnerAccount.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("host");
        sb.append('=');
        sb.append(((this.host == null)?"<null>":this.host));
        sb.append(',');
        sb.append("avatars");
        sb.append('=');
        sb.append(((this.avatars == null)?"<null>":this.avatars));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("hostRedundancyAllowed");
        sb.append('=');
        sb.append(((this.hostRedundancyAllowed == null)?"<null>":this.hostRedundancyAllowed));
        sb.append(',');
        sb.append("displayName");
        sb.append('=');
        sb.append(((this.displayName == null)?"<null>":this.displayName));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
