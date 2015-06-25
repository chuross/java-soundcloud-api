package com.github.chuross.library.soundcloud.element;

import net.arnx.jsonic.JSONHint;

import java.util.Date;
import java.util.List;

public class Playlist {

    private Long id;
    private Date createdAt;
    private Date lastModified;
    private Long userId;
    private User user;
    private String title;
    private String permalink;
    private String permalinkUrl;
    private String uri;
    private String sharing;
    private String embeddableBy;
    private String purchaseTitle;
    private String purchaseUrl;
    private String artworkUrl;
    private String description;
    private Long labelId;
    private String labelName;
    private Long duration;
    private String genre;
    private String license;
    private String tagList;
    private Integer trackCount;
    private String release;
    private Integer releaseDay;
    private Integer releaseMonth;
    private Integer releaseYear;
    private Boolean streamable;
    private Boolean downloadable;
    private String ean;
    private String playlistType;
    private List<Track> tracks;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @JSONHint(format = "yyyy/MM/dd HH:mm:ss Z")
    public Date getCreatedAt() {
        return createdAt;
    }

    @JSONHint(format = "yyyy/MM/dd HH:mm:ss Z")
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    @JSONHint(format = "yyyy/MM/dd HH:mm:ss Z")
    public Date getLastModified() {
        return lastModified;
    }

    @JSONHint(format = "yyyy/MM/dd HH:mm:ss Z")
    public void setLastModified(final Date lastModified) {
        this.lastModified = lastModified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(final String permalink) {
        this.permalink = permalink;
    }

    public String getPermalinkUrl() {
        return permalinkUrl;
    }

    public void setPermalinkUrl(final String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getSharing() {
        return sharing;
    }

    public void setSharing(final String sharing) {
        this.sharing = sharing;
    }

    public String getEmbeddableBy() {
        return embeddableBy;
    }

    public void setEmbeddableBy(final String embeddableBy) {
        this.embeddableBy = embeddableBy;
    }

    public String getPurchaseTitle() {
        return purchaseTitle;
    }

    public void setPurchaseTitle(final String purchaseTitle) {
        this.purchaseTitle = purchaseTitle;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(final String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(final String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(final Long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(final String labelName) {
        this.labelName = labelName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(final Long duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(final String license) {
        this.license = license;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(final String tagList) {
        this.tagList = tagList;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(final Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(final String release) {
        this.release = release;
    }

    public Integer getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(final Integer releaseDay) {
        this.releaseDay = releaseDay;
    }

    public Integer getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(final Integer releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Boolean isStreamable() {
        return streamable;
    }

    public void setStreamable(final Boolean streamable) {
        this.streamable = streamable;
    }

    public Boolean isDownloadable() {
        return downloadable;
    }

    public void setDownloadable(final Boolean downloadable) {
        this.downloadable = downloadable;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(final String ean) {
        this.ean = ean;
    }

    public String getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(final String playlistType) {
        this.playlistType = playlistType;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(final List<Track> tracks) {
        this.tracks = tracks;
    }
}
