package com.example.process.Services;

import com.example.process.DTO.ProcessFlowDTO;
import com.example.process.DTO.ProcessMasterDTO;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Repository.ProcessMasterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xplode on 27/7/17.
 */
@Service
public class ProcessMasterService {


    @Autowired
    ProcessMasterRepository processMasterRepository;

    public ResponseEntity saveAll(ProcessMasterDTO processMasterDTO){

        if(processMasterDTO.getProcessName()==null || processMasterDTO.getProcess()==null){
            return new ResponseEntity("Please fill all the details", HttpStatus.FORBIDDEN);
        }
        ProcessMaster processMaster=new ProcessMaster();

        HashMap<String,Object > process=getProcessHash(processMasterDTO.getProcess());

        ObjectMapper objectMapper=new ObjectMapper();
        String Test="";
        try {
            Test = objectMapper.writeValueAsString(process);
        }
        catch (Exception e){}
        processMaster.setProcessName(processMasterDTO.getProcessName());
        processMaster.setProcess(Test);

        System.out.print(Test);
        processMasterRepository.save(processMaster);
            return new ResponseEntity(HttpStatus.OK);
    }

    HashMap<String,Object > getProcessHash(HashMap<String,ProcessFlowDTO> hashmap){
        HashMap<String,Object> hash= new HashMap<>();
        for (Map.Entry<String, ProcessFlowDTO> entry : hashmap.entrySet()) {
             ProcessFlowDTO pfDto=entry.getValue();
             ProcessFlowDTO pf=new ProcessFlowDTO();
             pf.setCode(pfDto.getCode());
             pf.setConditionExpression(pfDto.getConditionExpression());
             pf.setTitle(pfDto.getTitle());
             pf.setNextFalseCondition(pfDto.getNextFalseCondition());
             pf.setNextFalseCondition(pfDto.getNextFalseCondition());
             hash.put(entry.getKey(),pf);

        }
        return hash;
    }

    public ResponseEntity getAll(){
        List<ProcessMaster>  processMasters=processMasterRepository.findAll();
        if(processMasters==null){
            return new ResponseEntity("Database Empty",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(processMasters,HttpStatus.OK);



    }


}
