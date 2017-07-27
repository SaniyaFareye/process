package com.example.process.Controller;

import com.example.process.DTO.ProcessMasterDTO;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Services.ProcessMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xplode on 27/7/17.
 */

@RestController
@RequestMapping(value = "/process")
public class ProcessMasterController {

    @Autowired
    private ProcessMasterService processMasterService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody ProcessMasterDTO processMasterDTO){

        try{
            return processMasterService.getAll(processMasterDTO);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
