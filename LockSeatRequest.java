package com.project.BTS.dto;

import java.util.List;

public class LockSeatRequest {
    private List<Long> showSeatIds;

    public List<Long> getShowSeatIds() {
        return showSeatIds;
    }

    public void setShowSeatIds(List<Long> showSeatIds) {
        this.showSeatIds = showSeatIds;
    }
}