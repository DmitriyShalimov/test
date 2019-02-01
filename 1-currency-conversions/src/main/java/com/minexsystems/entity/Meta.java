package com.minexsystems.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
    @JsonProperty("effective_params")
    private EffectiveParams effectiveParams;

    public EffectiveParams getEffectiveParams() {
        return effectiveParams;
    }

    public void setEffectiveParams(EffectiveParams effectiveParams) {
        this.effectiveParams = effectiveParams;
    }
}
