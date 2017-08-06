package com.example.process.DTO;

import org.joda.time.DateTime;

/**
 * Created by xplode on 2/8/17.
 */
public class JobDTO {

    private String  createdDate;
    private String  crnNo;
    private String currentFlow;

    public JobDTO() {

    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCrnNo() {
        return crnNo;
    }

    public void setCrnNo(String crnNo) {
        this.crnNo = crnNo;
    }

    public String getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(String currentFlow) {
        this.currentFlow = currentFlow;
    }
}
