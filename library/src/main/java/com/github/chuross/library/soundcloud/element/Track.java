package com.github.chuross.library.soundcloud.element;

import net.arnx.jsonic.JSONHint;

import java.util.Date;

public class Track {

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
    private String artworkUrl;
    private String purchaseUrl;
    private String description;
    private Long duration;
    private String genre;
    private String tagList;
    private Long labelId;
    private String labelName;
    private Long release;
    private Integer releaseDay;
    private Integer releaseMonth;
    private Integer releaseYear;
    private Boolean streamable;
    private Boolean downloadable;
    private String state;
    private String license;
    private String trackType;
    private String waveformUrl;
    private String streamUrl;
    private String videoUrl;
    private Integer bpm;
    private Boolean commentable;
    private String isrc;
    private String keySignature;
    private Integer commentCount;
    private Integer downloadCount;
    private Integer playbackCount;
    private Integer favoritingsCount;
    private String originalFormat;
    private Long originalContentSize;

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

    public String getArtworkUrl() {
        return artworkUrl;
    }

    public void setArtworkUrl(final String artworkUrl) {
        this.artworkUrl = artworkUrl;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(final String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
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

    public String getTagList() {
        return tagList;
    }

    public void setTagList(final String tagList) {
        this.tagList = tagList;
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

    public Long getRelease() {
        return release;
    }

    public void setRelease(final Long release) {
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

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(final String license) {
        this.license = license;
    }

    public String getTrackType() {
        return trackType;
    }

    public void setTrackType(final String trackType) {
        this.trackType = trackType;
    }

    public String getWaveformUrl() {
        return waveformUrl;
    }

    public void setWaveformUrl(final String waveformUrl) {
        this.waveformUrl = waveformUrl;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(final String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(final String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getBpm() {
        return bpm;
    }

    public void setBpm(final Integer bpm) {
        this.bpm = bpm;
    }

    public Boolean isCommentable() {
        return commentable;
    }

    public void setCommentable(final Boolean commentable) {
        this.commentable = commentable;
    }

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(final String isrc) {
        this.isrc = isrc;
    }

    public String getKeySignature() {
        return keySignature;
    }

    public void setKeySignature(final String keySignature) {
        this.keySignature = keySignature;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(final Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(final Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getPlaybackCount() {
        return playbackCount;
    }

    public void setPlaybackCount(final Integer playbackCount) {
        this.playbackCount = playbackCount;
    }

    public Integer getFavoritingsCount() {
        return favoritingsCount;
    }

    public void setFavoritingsCount(final Integer favoritingsCount) {
        this.favoritingsCount = favoritingsCount;
    }

    public String getOriginalFormat() {
        return originalFormat;
    }

    public void setOriginalFormat(final String originalFormat) {
        this.originalFormat = originalFormat;
    }

    public Long getOriginalContentSize() {
        return originalContentSize;
    }

    public void setOriginalContentSize(final Long originalContentSize) {
        this.originalContentSize = originalContentSize;
    }
}
