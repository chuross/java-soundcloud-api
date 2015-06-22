package com.github.chuross.library.soundcloud.element;

import net.arnx.jsonic.JSONHint;

public class Status {

    private String status;

    @JSONHint(name = "status")
    public String getStatus() {
        return status;
    }

    @JSONHint(name = "status")
    public void setStatus(final String status) {
        this.status = status;
    }
}
