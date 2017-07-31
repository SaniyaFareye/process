package com.example.process.Domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import javax.persistence.*;

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

    @Column(name = "created_date")
    private DateTime createdDate;

    @Column(name="current_flow")
    private String currentFlow;

    @ManyToOne
    @JoinColumn(name="processMasterID",insertable=true,updatable=true,nullable = false)
    private ProcessMaster processMaster;

    public Job() {
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
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
