package com.example.process.Controller;

import com.example.process.Services.JobService;
import com.example.process.Services.ProcessMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xplode on 31/7/17.
 */
@RestController
@RequestMapping(value = "/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity all(){
        try{
            return jobService.getAll();
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
