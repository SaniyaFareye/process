package com.example.process.Services;

import com.example.process.Domains.Job;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xplode on 31/7/17.
 */
@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public ResponseEntity getAll(){
        List<Job> job=jobRepository.findAll();
        if(job==null){
            return new ResponseEntity("Database Empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(job,HttpStatus.OK);



    }
}
