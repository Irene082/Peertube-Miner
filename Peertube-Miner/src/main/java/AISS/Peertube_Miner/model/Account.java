package AISS.Peertube_Miner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("host")
    private String host;
    @JsonProperty("avatars")
    private List<Object> avatars;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("hostRedundancyAllowed")
    private Boolean hostRedundancyAllowed;
    @JsonProperty("followingCount")
    private Integer followingCount;
    @JsonProperty("followersCount")
    private Integer followersCount;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("userId")
    private Integer userId;

    public Account() {}

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public List<Object> getAvatars() { return avatars; }
    public void setAvatars(List<Object> avatars) { this.avatars = avatars; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Boolean getHostRedundancyAllowed() { return hostRedundancyAllowed; }
    public void setHostRedundancyAllowed(Boolean hostRedundancyAllowed) { this.hostRedundancyAllowed = hostRedundancyAllowed; }

    public Integer getFollowingCount() { return followingCount; }
    public void setFollowingCount(Integer followingCount) { this.followingCount = followingCount; }

    public Integer getFollowersCount() { return followersCount; }
    public void setFollowersCount(Integer followersCount) { this.followersCount = followersCount; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
}