package com.example.process.Controller;

import com.example.process.Services.JobService;
import com.example.process.Services.ProcessMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xplode on 31/7/17.
 */
@RestController
@RequestMapping(value ="/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/all",method = RequestMethod.POST)
    public ResponseEntity all(@RequestParam ("processMasterID") Long processMasterID){
        try{
            return jobService.getAll(processMasterID);
        }catch(Exception e){
            return new ResponseEntity("no data",    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/currentFlow",method = RequestMethod.POST)
    public ResponseEntity getCurrentFlow(@RequestParam("processMasterID") Long processMasterID,
                                         @RequestParam("crnNo") String crnNo){
        try{
            return jobService.getCurrentFlow(processMasterID,crnNo);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/nexState",method =RequestMethod.POST)
    public ResponseEntity getNextState(@RequestParam("crnNo")String crnNo,@RequestParam("status") String status){

        try{
            return jobService.getNextState(crnNo,status);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
