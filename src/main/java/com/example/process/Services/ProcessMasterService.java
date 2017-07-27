package com.example.process.Services;

import com.example.process.DTO.ProcessMasterDTO;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Repository.ProcessMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by xplode on 27/7/17.
 */
@Service
public class ProcessMasterService {


    @Autowired
    private ProcessMasterRepository processMasterRepository;

    public ResponseEntity getAll(ProcessMasterDTO processMasterDTO){

        if(processMasterDTO.getProcessName()==null || processMasterDTO.getProcess()==null){
            return new ResponseEntity("Please fill all the details", HttpStatus.FORBIDDEN);
        }
        ProcessMaster processMaster=new ProcessMaster();
        processMaster.setProcessName(processMasterDTO.getProcessName());
        processMaster.setProcess(processMasterDTO.getProcess().toString());
        processMasterRepository.save(processMaster);
            return new ResponseEntity(HttpStatus.OK);
    }
}
