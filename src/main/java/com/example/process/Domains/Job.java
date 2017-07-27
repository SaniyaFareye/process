package com.example.process.Domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name="job_name")
    private String jobName;

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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public ProcessMaster getProcessMaster() {
        return processMaster;
    }

    public void setProcessMaster(ProcessMaster processMaster) {
        this.processMaster = processMaster;
    }
}
