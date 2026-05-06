
package AISS.Peertube_Miner.model;

import java.util.List;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "uuid",
    "shortUUID",
    "url",
    "name",
    "category",
    "licence",
    "language",
    "privacy",
    "nsfw",
    "nsfwFlags",
    "nsfwSummary",
    "truncatedDescription",
    "description",
    "isLocal",
    "duration",
    "aspectRatio",
    "views",
    "viewers",
    "likes",
    "dislikes",
    "thumbnailPath",
    "previewPath",
    "thumbnails",
    "embedPath",
    "createdAt",
    "updatedAt",
    "publishedAt",
    "originallyPublishedAt",
    "isLive",
    "account",
    "channel",
    "comments"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Generated("jsonschema2pojo")
public class Video {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("shortUUID")
    private String shortUUID;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("licence")
    private Licence licence;
    @JsonProperty("language")
    private Language language;
    @JsonProperty("privacy")
    private Privacy privacy;
    @JsonProperty("nsfw")
    private Boolean nsfw;
    @JsonProperty("nsfwFlags")
    private Integer nsfwFlags;
    @JsonProperty("nsfwSummary")
    private Object nsfwSummary;
    @JsonProperty("truncatedDescription")
    private String truncatedDescription;
    @JsonProperty("description")
    private String description;
    @JsonProperty("isLocal")
    private Boolean isLocal;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("aspectRatio")
    private Object aspectRatio;
    @JsonProperty("views")
    private Integer views;
    @JsonProperty("viewers")
    private Integer viewers;
    @JsonProperty("likes")
    private Integer likes;
    @JsonProperty("dislikes")
    private Integer dislikes;
    @JsonProperty("thumbnailPath")
    private String thumbnailPath;
    @JsonProperty("previewPath")
    private String previewPath;
    @JsonProperty("thumbnails")
    private List<Thumbnail> thumbnails;
    @JsonProperty("embedPath")
    private String embedPath;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("originallyPublishedAt")
    private Object originallyPublishedAt;
    @JsonProperty("isLive")
    private Boolean isLive;
    @JsonProperty("account")
    private Account account;
    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("comments")
    private Integer comments;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("shortUUID")
    public String getShortUUID() {
        return shortUUID;
    }

    @JsonProperty("shortUUID")
    public void setShortUUID(String shortUUID) {
        this.shortUUID = shortUUID;
    }

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

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("licence")
    public Licence getLicence() {
        return licence;
    }

    @JsonProperty("licence")
    public void setLicence(Licence licence) {
        this.licence = licence;
    }

    @JsonProperty("language")
    public Language getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Language language) {
        this.language = language;
    }

    @JsonProperty("privacy")
    public Privacy getPrivacy() {
        return privacy;
    }

    @JsonProperty("privacy")
    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    @JsonProperty("nsfw")
    public Boolean getNsfw() {
        return nsfw;
    }

    @JsonProperty("nsfw")
    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    @JsonProperty("nsfwFlags")
    public Integer getNsfwFlags() {
        return nsfwFlags;
    }

    @JsonProperty("nsfwFlags")
    public void setNsfwFlags(Integer nsfwFlags) {
        this.nsfwFlags = nsfwFlags;
    }

    @JsonProperty("nsfwSummary")
    public Object getNsfwSummary() {
        return nsfwSummary;
    }

    @JsonProperty("nsfwSummary")
    public void setNsfwSummary(Object nsfwSummary) {
        this.nsfwSummary = nsfwSummary;
    }

    @JsonProperty("truncatedDescription")
    public String getTruncatedDescription() {
        return truncatedDescription;
    }

    @JsonProperty("truncatedDescription")
    public void setTruncatedDescription(String truncatedDescription) {
        this.truncatedDescription = truncatedDescription;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("isLocal")
    public Boolean getIsLocal() {
        return isLocal;
    }

    @JsonProperty("isLocal")
    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("aspectRatio")
    public Object getAspectRatio() {
        return aspectRatio;
    }

    @JsonProperty("aspectRatio")
    public void setAspectRatio(Object aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    @JsonProperty("views")
    public Integer getViews() {
        return views;
    }

    @JsonProperty("views")
    public void setViews(Integer views) {
        this.views = views;
    }

    @JsonProperty("viewers")
    public Integer getViewers() {
        return viewers;
    }

    @JsonProperty("viewers")
    public void setViewers(Integer viewers) {
        this.viewers = viewers;
    }

    @JsonProperty("likes")
    public Integer getLikes() {
        return likes;
    }

    @JsonProperty("likes")
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @JsonProperty("dislikes")
    public Integer getDislikes() {
        return dislikes;
    }

    @JsonProperty("dislikes")
    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    @JsonProperty("thumbnailPath")
    public String getThumbnailPath() {
        return thumbnailPath;
    }

    @JsonProperty("thumbnailPath")
    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    @JsonProperty("previewPath")
    public String getPreviewPath() {
        return previewPath;
    }

    @JsonProperty("previewPath")
    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }

    @JsonProperty("thumbnails")
    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    @JsonProperty("thumbnails")
    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    @JsonProperty("embedPath")
    public String getEmbedPath() {
        return embedPath;
    }

    @JsonProperty("embedPath")
    public void setEmbedPath(String embedPath) {
        this.embedPath = embedPath;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("publishedAt")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("publishedAt")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("originallyPublishedAt")
    public Object getOriginallyPublishedAt() {
        return originallyPublishedAt;
    }

    @JsonProperty("originallyPublishedAt")
    public void setOriginallyPublishedAt(Object originallyPublishedAt) {
        this.originallyPublishedAt = originallyPublishedAt;
    }

    @JsonProperty("isLive")
    public Boolean getIsLive() {
        return isLive;
    }

    @JsonProperty("isLive")
    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    @JsonProperty("account")
    public Account getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(Account account) {
        this.account = account;
    }

    @JsonProperty("channel")
    public Channel getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @JsonProperty("comments")
    public Integer getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Integer comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Video.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("uuid");
        sb.append('=');
        sb.append(((this.uuid == null)?"<null>":this.uuid));
        sb.append(',');
        sb.append("shortUUID");
        sb.append('=');
        sb.append(((this.shortUUID == null)?"<null>":this.shortUUID));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("category");
        sb.append('=');
        sb.append(((this.category == null)?"<null>":this.category));
        sb.append(',');
        sb.append("licence");
        sb.append('=');
        sb.append(((this.licence == null)?"<null>":this.licence));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("privacy");
        sb.append('=');
        sb.append(((this.privacy == null)?"<null>":this.privacy));
        sb.append(',');
        sb.append("nsfw");
        sb.append('=');
        sb.append(((this.nsfw == null)?"<null>":this.nsfw));
        sb.append(',');
        sb.append("nsfwFlags");
        sb.append('=');
        sb.append(((this.nsfwFlags == null)?"<null>":this.nsfwFlags));
        sb.append(',');
        sb.append("nsfwSummary");
        sb.append('=');
        sb.append(((this.nsfwSummary == null)?"<null>":this.nsfwSummary));
        sb.append(',');
        sb.append("truncatedDescription");
        sb.append('=');
        sb.append(((this.truncatedDescription == null)?"<null>":this.truncatedDescription));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("isLocal");
        sb.append('=');
        sb.append(((this.isLocal == null)?"<null>":this.isLocal));
        sb.append(',');
        sb.append("duration");
        sb.append('=');
        sb.append(((this.duration == null)?"<null>":this.duration));
        sb.append(',');
        sb.append("aspectRatio");
        sb.append('=');
        sb.append(((this.aspectRatio == null)?"<null>":this.aspectRatio));
        sb.append(',');
        sb.append("views");
        sb.append('=');
        sb.append(((this.views == null)?"<null>":this.views));
        sb.append(',');
        sb.append("viewers");
        sb.append('=');
        sb.append(((this.viewers == null)?"<null>":this.viewers));
        sb.append(',');
        sb.append("likes");
        sb.append('=');
        sb.append(((this.likes == null)?"<null>":this.likes));
        sb.append(',');
        sb.append("dislikes");
        sb.append('=');
        sb.append(((this.dislikes == null)?"<null>":this.dislikes));
        sb.append(',');
        sb.append("thumbnailPath");
        sb.append('=');
        sb.append(((this.thumbnailPath == null)?"<null>":this.thumbnailPath));
        sb.append(',');
        sb.append("previewPath");
        sb.append('=');
        sb.append(((this.previewPath == null)?"<null>":this.previewPath));
        sb.append(',');
        sb.append("thumbnails");
        sb.append('=');
        sb.append(((this.thumbnails == null)?"<null>":this.thumbnails));
        sb.append(',');
        sb.append("embedPath");
        sb.append('=');
        sb.append(((this.embedPath == null)?"<null>":this.embedPath));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("publishedAt");
        sb.append('=');
        sb.append(((this.publishedAt == null)?"<null>":this.publishedAt));
        sb.append(',');
        sb.append("originallyPublishedAt");
        sb.append('=');
        sb.append(((this.originallyPublishedAt == null)?"<null>":this.originallyPublishedAt));
        sb.append(',');
        sb.append("isLive");
        sb.append('=');
        sb.append(((this.isLive == null)?"<null>":this.isLive));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account));
        sb.append(',');
        sb.append("channel");
        sb.append('=');
        sb.append(((this.channel == null)?"<null>":this.channel));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
