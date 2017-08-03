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


    public ResponseEntity getAll(Long id){
        List<Job> job=jobRepository.findById(id);


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

        HashMap<String, ProcessFlowDTO>  map= stringToHashMap(processMaster.getProcess());

        String type="";
        String nextTrueCond= getCondition("start",map,type);
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

    String getCondition(String code,HashMap<String,ProcessFlowDTO> map,String type){

            while(!type.equals("Task") && !code.equals("end")) {

                ProcessFlowDTO value = map.get(code);
                 ProcessFlowDTO value1 = new ProcessFlowDTO();
                code = value.getNextTrueCondition();

                  value1 = map.get(code);
                  type=value1.getType();
                  if(type.equals("Condition")) {
                      String conditionExpression = value1.getConditionExpression();
                      if (conditionExpression.equals("true")) {
                          code = value1.getNextTrueCondition();
                      } else {
                          code = value1.getNextFalseCondition();
                      }


                  }

        }

        return code;
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

    public ResponseEntity getNextState(String crnNo,String status) {
        Job job = jobRepository.findByCrnNo(crnNo);
        ProcessMaster processMaster = job.getProcessMaster();
        HashMap<String, ProcessFlowDTO> map = stringToHashMap(processMaster.getProcess());
        String currentFlow = job.getCurrentFlow();
        String type = "";
        String nextTrueCond;
        if (status.equals("success")) {
            nextTrueCond = getCondition(currentFlow, map, type);
        } else {
            nextTrueCond = "end";

        }
        job.setCurrentFlow(nextTrueCond);

        Job job1 = jobRepository.save(job);
        System.out.print(job1);
        if (job1 != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity("no data", HttpStatus.NO_CONTENT);


    }
}
