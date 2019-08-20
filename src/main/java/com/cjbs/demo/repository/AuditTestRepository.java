package com.cjbs.demo.repository;


import com.cjbs.demo.domain.AuditTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author shj
 */
@Repository
public interface AuditTestRepository extends JpaRepository<AuditTest, String> {


}
