package com.example.process.Repository;

import com.example.process.Domains.Job;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xplode on 27/7/17.
 */
@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    @Query("select j from Job j where j.processMaster.processMasterID=:id")
    List<Job> findById(@Param("id")Long id);

    @Query("select j from Job j where j.crnNo=:crnNo")
    Job findByCrnNo(@Param("crnNo")String crnNo);
}
