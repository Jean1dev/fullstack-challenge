package com.willian.AlpacaFilmes.domain.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.willian.AlpacaFilmes.domain.enums.CadeiraStatus;

import java.io.Serializable;

@JsonRootName("cadeira_status")
public class CadeiraStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private CadeiraStatus status;

    public CadeiraStatusDTO() {
    }

    public CadeiraStatusDTO(CadeiraStatus status) {
        this.status = status;
    }

    public CadeiraStatus getStatus() {
        return status;
    }

    public void setStatus(CadeiraStatus status) {
        this.status = status;
    }
}
