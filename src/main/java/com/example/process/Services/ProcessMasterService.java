package com.example.process.Services;

import com.example.process.DTO.ProcessFlowDTO;
import com.example.process.DTO.ProcessMasterDTO;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Repository.ProcessMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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
             pf.setNextTrueCondition(pfDto.getNextTrueCondition());
             pf.setType(pfDto.getType());
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

    public ResponseEntity getCodeList(Long id){

        ProcessMaster processMaster=processMasterRepository.findById(id);
        List<String> codeList=new ArrayList<>();
        HashMap<String, ProcessFlowDTO>  map= stringToHashMap(processMaster.getProcess());

        for (Map.Entry<String , ProcessFlowDTO> entry : map.entrySet()) {
            if(!entry.getKey().equals("start") && !entry.getKey().equals("end")) {
                codeList.add(entry.getKey());
            }
        }
        return new ResponseEntity(codeList,HttpStatus.OK);
    }

    public HashMap<String, ProcessFlowDTO> stringToHashMap(String process){
        HashMap<String, ProcessFlowDTO>  map=new HashMap<String ,ProcessFlowDTO>();

        ObjectMapper objectMapper= new ObjectMapper();
        try {
            map=objectMapper.readValue(process, new TypeReference<HashMap<String,ProcessFlowDTO>>() {});
            System.out.print(map);
        }catch (IOException io){

        }

        return map;

    }

}
