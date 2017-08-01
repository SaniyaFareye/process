package com.example.process.Services;

import com.example.process.DTO.ProcessFlowDTO;
import com.example.process.Domains.Job;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Repository.JobRepository;
import com.example.process.Repository.ProcessMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xplode on 31/7/17.
 */
@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ProcessMasterRepository processMasterRepository;


    public ResponseEntity getAll(){
        List<Job> job=jobRepository.findAll();
        if(job==null){
            return new ResponseEntity("Database Empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(job,HttpStatus.OK);
    }

    public ResponseEntity getCurrentFlow(Long id,String crnNo){
        ProcessMaster processMaster=processMasterRepository.findById(id);
        HashMap<String, ProcessFlowDTO>  map=new HashMap<String,ProcessFlowDTO>();
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            map=objectMapper.readValue(processMaster.getProcess(), new TypeReference<HashMap<String,ProcessFlowDTO>>() {});
            System.out.print(map);
        }catch (IOException io){

        }

        String type="";
        String nextTrueCond= getNextTrueCondition("start",map,type);
        System.out.print(nextTrueCond);


        return null;

    }

    String getNextTrueCondition(String code,HashMap<String,ProcessFlowDTO> map,String type){
    while(!type.equals("Task")) {
        ProcessFlowDTO value = map.get(code);

        code=value.getNextTrueCondition();

        ProcessFlowDTO value1 =  map.get(code);
        type=value1.getType();
    }
        return type;
    }









}
