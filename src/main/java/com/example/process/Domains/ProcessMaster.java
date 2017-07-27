package com.example.process.Domains;

import javax.persistence.*;

/**
 * Created by xplode on 27/7/17.
 */
@Entity
@Table(name="process_master")
public class ProcessMaster {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_master_id")
    private Long processMasterID;

    @Column(name="process_name", nullable = false)
    private String processName;

    @Column (columnDefinition ="text",nullable = false)
    private String process;

    public ProcessMaster() {

    }

    public Long getProcessMasterID() {
        return processMasterID;
    }

    public void setProcessMasterID(Long processMasterID) {
        this.processMasterID = processMasterID;
    }


    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }


}
