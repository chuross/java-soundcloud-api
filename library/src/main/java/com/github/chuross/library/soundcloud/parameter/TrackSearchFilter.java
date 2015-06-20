package com.github.chuross.library.soundcloud.parameter;

import com.chuross.common.library.rest.RestRequestBuilder;
import com.google.common.base.Joiner;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TrackSearchFilter implements Serializable {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private String query;
    private List<String> tags;
    private String filter;
    private String license;
    private Integer bpmFrom;
    private Integer bpmTo;
    private Integer durationFrom;
    private Integer durationTo;
    private Date createdAtFrom;
    private Date createdAtTo;
    private List<Long> ids;
    private List<String> genres;
    private List<String> types;

    TrackSearchFilter() {
    }

    public String getQuery() {
        return query;
    }

    void setQuery(final String query) {
        this.query = query;
    }

    public List<String> getTags() {
        return tags;
    }

    void setTags(final List<String> tags) {
        this.tags = tags;
    }

    public String getFilter() {
        return filter;
    }

    void setFilter(final String filter) {
        this.filter = filter;
    }

    public String getLicense() {
        return license;
    }

    void setLicense(final String license) {
        this.license = license;
    }

    public Integer getBpmFrom() {
        return bpmFrom;
    }

    void setBpmFrom(final Integer bpmFrom) {
        this.bpmFrom = bpmFrom;
    }

    public Integer getBpmTo() {
        return bpmTo;
    }

    void setBpmTo(final Integer bpmTo) {
        this.bpmTo = bpmTo;
    }

    public Integer getDurationFrom() {
        return durationFrom;
    }

    void setDurationFrom(final Integer durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Integer getDurationTo() {
        return durationTo;
    }

    void setDurationTo(final Integer durationTo) {
        this.durationTo = durationTo;
    }

    public Date getCreatedAtFrom() {
        return createdAtFrom;
    }

    void setCreatedAtFrom(final Date createdAtFrom) {
        this.createdAtFrom = createdAtFrom;
    }

    public Date getCreatedAtTo() {
        return createdAtTo;
    }

    void setCreatedAtTo(final Date createdAtTo) {
        this.createdAtTo = createdAtTo;
    }

    public List<Long> getIds() {
        return ids;
    }

    void setIds(final List<Long> ids) {
        this.ids = ids;
    }

    public List<String> getGenres() {
        return genres;
    }

    void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    public List<String> getTypes() {
        return types;
    }

    void setTypes(final List<String> types) {
        this.types = types;
    }

    public static void setFilterParameters(final RestRequestBuilder builder, final TrackSearchFilter filter) {
        if(builder == null || filter == null) {
            return;
        }
        builder.addParameterIfNotNull("q", filter.getQuery());
        if(filter.getTags() != null && !filter.getTags().isEmpty()) {
            builder.addParameter("tags", Joiner.on(",").join(filter.getTags()));
        }
        builder.addParameterIfNotNull("filter", filter.getFilter());
        builder.addParameterIfNotNull("license", filter.getLicense());
        builder.addParameterIfNotNull("bpm[from]", filter.getBpmFrom());
        builder.addParameterIfNotNull("bpm[to]", filter.getBpmTo());
        builder.addParameterIfNotNull("duration[from]", filter.getDurationFrom());
        builder.addParameterIfNotNull("duration[to]", filter.getDurationTo());
        if(filter.getCreatedAtFrom() != null) {
            builder.addParameter("created_at[from]", DATE_FORMAT.format(filter.getCreatedAtFrom()));
        }
        if(filter.getCreatedAtTo() != null) {
            builder.addParameter("created_at[to]", DATE_FORMAT.format(filter.getCreatedAtTo()));
        }
        if(filter.getIds() != null && !filter.getIds().isEmpty()) {
            builder.addParameter("ids", Joiner.on(",").join(filter.getIds()));
        }
        if(filter.getTypes() != null && !filter.getTypes().isEmpty()) {
            builder.addParameter("types", Joiner.on(",").join(filter.getTypes()));
        }
    }
}
