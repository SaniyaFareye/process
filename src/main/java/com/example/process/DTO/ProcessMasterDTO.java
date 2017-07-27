package com.example.process.DTO;

import javax.persistence.Column;
import java.util.HashMap;

/**
 * Created by xplode on 27/7/17.
 */
public class ProcessMasterDTO {

    private String processName;

    private HashMap<String ,Object> process;

    public ProcessMasterDTO() {
    }

    public HashMap<String, Object> getProcess() {
        return process;
    }

    public void setProcess(HashMap<String, Object> process) {
        this.process = process;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
