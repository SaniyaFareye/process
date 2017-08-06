package com.example.process.Controller;

import com.example.process.DTO.ProcessMasterDTO;
import com.example.process.Domains.ProcessMaster;
import com.example.process.Services.ProcessMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return processMasterService.saveAll(processMasterDTO);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity all(){
        try{
            return processMasterService.getAll();
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/codeList",method = RequestMethod.POST)
    public ResponseEntity getCodeList(@Param("processMasterID")Long processMasterID){
        try{
            return processMasterService.getCodeList(processMasterID);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
