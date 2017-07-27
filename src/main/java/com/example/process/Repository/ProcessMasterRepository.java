package com.example.process.Repository;

import com.example.process.Domains.ProcessMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xplode on 27/7/17.
 */
@Repository
public interface ProcessMasterRepository extends JpaRepository<ProcessMaster,Long>{
}
