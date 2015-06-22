package com.github.chuross.library.soundcloud.parameter;

import org.apache.commons.lang3.SerializationUtils;

import java.util.Date;
import java.util.List;

public class TrackSerchFilterBuilder {

    private TrackSearchFilter filter;

    public TrackSerchFilterBuilder() {
        filter = new TrackSearchFilter();
    }

    public TrackSerchFilterBuilder setQuery(final String query) {
        filter.setQuery(query);
        return this;
    }

    public TrackSerchFilterBuilder setTags(final List<String> tags) {
        filter.setTags(tags);
        return this;
    }

    public TrackSerchFilterBuilder setFilter(final String filter) {
        this.filter.setFilter(filter);
        return this;
    }

    public TrackSerchFilterBuilder setLicense(final String license) {
        filter.setLicense(license);
        return this;
    }

    public TrackSerchFilterBuilder setBpmFrom(final Integer bpmFrom) {
        filter.setBpmFrom(bpmFrom);
        return this;
    }

    public TrackSerchFilterBuilder setBpmTo(final Integer bpmTo) {
        filter.setBpmTo(bpmTo);
        return this;
    }

    public TrackSerchFilterBuilder setDurationFrom(final Integer durationFrom) {
        filter.setDurationFrom(durationFrom);
        return this;
    }

    public TrackSerchFilterBuilder setDurationTo(final Integer durationTo) {
        filter.setDurationTo(durationTo);
        return this;
    }

    public TrackSerchFilterBuilder setCreatedAtFrom(final Date createdAtFrom) {
        filter.setCreatedAtFrom(createdAtFrom);
        return this;
    }

    public TrackSerchFilterBuilder setCreatedAtTo(final Date createdAtTo) {
        filter.setCreatedAtTo(createdAtTo);
        return this;
    }

    public TrackSerchFilterBuilder setIds(final List<Long> ids) {
        filter.setIds(ids);
        return this;
    }

    public TrackSerchFilterBuilder setGenres(final List<String> genres) {
        filter.setGenres(genres);
        return this;
    }

    public TrackSerchFilterBuilder setTypes(final List<String> types) {
        filter.setTypes(types);
        return this;
    }

    public TrackSearchFilter build() {
        return SerializationUtils.clone(filter);
    }
}
