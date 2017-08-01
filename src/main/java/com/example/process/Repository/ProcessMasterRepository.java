package com.example.process.Repository;

import com.example.process.Domains.ProcessMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by xplode on 27/7/17.
 */
@Repository
public interface ProcessMasterRepository extends JpaRepository<ProcessMaster,Long>{
    @Query("select p from ProcessMaster p where p.processMasterID=:id")
    ProcessMaster findById(@Param("id") Long id);
}
