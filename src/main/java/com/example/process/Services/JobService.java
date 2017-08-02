package com.example.process.Services;

import com.example.process.DTO.JobDTO;
import com.example.process.DTO.ProcessFlowDTO;
import com.example.process.Domains.Job;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Repository.JobRepository;
import com.example.process.Repository.ProcessMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;


import java.sql.Timestamp;
import java.util.ArrayList;
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

        List<JobDTO> jobDTOList=new ArrayList<>();
        for (int i=0;i<job.size();i++){
            JobDTO jobDTO=new JobDTO();
           String date=job.get(i).getCreatedDate().toString();
           jobDTO.setCreatedDate(date);
           jobDTO.setCrnNo(job.get(i).getCrnNo());
           jobDTO.setCurrentFlow(job.get(i).getCurrentFlow());

           jobDTOList.add(i,jobDTO);
    }

        return new ResponseEntity(jobDTOList,HttpStatus.OK);
//        return new ResponseEntity(job,HttpStatus.OK);
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

        DateTime dt = new DateTime();
//        System.out.print(dt);

        Timestamp timeStamp = new Timestamp(dt.getMillis());

        Job job=new Job();
       // job.setCreatedDate(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(dt).replace(" ","T"));
        job.setCreatedDate(timeStamp);
        System.out.print(job.getCreatedDate());
        job.setCrnNo(crnNo);
        job.setCurrentFlow(nextTrueCond);
        job.setProcessMaster(processMaster);

        Job job1=jobRepository.save(job);
        System.out.print(job1);
        if(job1!=null){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("no data",HttpStatus.NO_CONTENT);

    }

    String getNextTrueCondition(String code,HashMap<String,ProcessFlowDTO> map,String type){
        while(!type.equals("Task") ) {
            if (!code.equals("end")){
                ProcessFlowDTO value = map.get(code);
                 ProcessFlowDTO value1 = new ProcessFlowDTO();
                code = value.getNextTrueCondition();

                  value1 = map.get(code);

                    type=value1.getType();
            }

        }
        return code;
    }









}
