package com.github.chuross.library.soundcloud.parameter;

import org.apache.commons.lang3.SerializationUtils;

import java.util.Date;
import java.util.List;

public class TrackSearchFilterBuilder {

    private TrackSearchFilter filter;

    public TrackSearchFilterBuilder() {
        filter = new TrackSearchFilter();
    }

    public TrackSearchFilterBuilder setQuery(final String query) {
        filter.setQuery(query);
        return this;
    }

    public TrackSearchFilterBuilder setTags(final List<String> tags) {
        filter.setTags(tags);
        return this;
    }

    public TrackSearchFilterBuilder setFilter(final String filter) {
        this.filter.setFilter(filter);
        return this;
    }

    public TrackSearchFilterBuilder setLicense(final String license) {
        filter.setLicense(license);
        return this;
    }

    public TrackSearchFilterBuilder setBpmFrom(final Integer bpmFrom) {
        filter.setBpmFrom(bpmFrom);
        return this;
    }

    public TrackSearchFilterBuilder setBpmTo(final Integer bpmTo) {
        filter.setBpmTo(bpmTo);
        return this;
    }

    public TrackSearchFilterBuilder setDurationFrom(final Integer durationFrom) {
        filter.setDurationFrom(durationFrom);
        return this;
    }

    public TrackSearchFilterBuilder setDurationTo(final Integer durationTo) {
        filter.setDurationTo(durationTo);
        return this;
    }

    public TrackSearchFilterBuilder setCreatedAtFrom(final Date createdAtFrom) {
        filter.setCreatedAtFrom(createdAtFrom);
        return this;
    }

    public TrackSearchFilterBuilder setCreatedAtTo(final Date createdAtTo) {
        filter.setCreatedAtTo(createdAtTo);
        return this;
    }

    public TrackSearchFilterBuilder setIds(final List<Long> ids) {
        filter.setIds(ids);
        return this;
    }

    public TrackSearchFilterBuilder setGenres(final List<String> genres) {
        filter.setGenres(genres);
        return this;
    }

    public TrackSearchFilterBuilder setTypes(final List<String> types) {
        filter.setTypes(types);
        return this;
    }

    public TrackSearchFilter build() {
        return SerializationUtils.clone(filter);
    }
}
