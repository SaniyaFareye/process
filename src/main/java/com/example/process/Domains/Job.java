package com.example.process.Domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Time;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by xplode on 27/7/17.
 */
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_id")
    private Long jobId;

    @Column(name="crn_no")
    private String  crnNo;

//    @Column(name = "created_date")
//    private DateTime createdDate;
//        @Column(name = "created_date", columnDefinition = "TIMESTAMP")
//        @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
        private Timestamp createdDate;

    @Column(name="current_flow")
    private String currentFlow;

    @ManyToOne
    @JoinColumn(name="processMasterID",insertable=true,updatable=true,nullable = false)
    private ProcessMaster processMaster;

    public Job() {
    }

    public String getCrnNo() {
        return crnNo;
    }

    public void setCrnNo(String crnNo) {
        this.crnNo = crnNo;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

//    public Timestamp getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(Timestamp createdDate) {
//        this.createdDate = createdDate;
//    }


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(String currentFlow) {
        this.currentFlow = currentFlow;
    }

    public ProcessMaster getProcessMaster() {
        return processMaster;
    }

    public void setProcessMaster(ProcessMaster processMaster) {
        this.processMaster = processMaster;
    }
}
