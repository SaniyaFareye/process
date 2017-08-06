package com.example.process.DTO;

import javax.persistence.Column;
import java.util.HashMap;

/**
 * Created by xplode on 27/7/17.
 */
public class ProcessMasterDTO {

    private String processName;

    private HashMap<String ,ProcessFlowDTO> process;

    public ProcessMasterDTO() {
    }

    public String getProcessName() {
        return processName;
    }

    public HashMap<String, ProcessFlowDTO> getProcess() {
        return process;
    }

    public void setProcess(HashMap<String, ProcessFlowDTO> process) {
        this.process = process;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }
}
