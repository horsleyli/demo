package com.cjbs.demo.web.rest;

import com.cjbs.demo.domain.AuditTest;
import com.cjbs.demo.service.AuditTestService;
import com.cjbs.demo.web.dto.AuditTestDTO;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shj
 */
@RestController
@RequestMapping("/api")
public class AuditTestController {

    private AuditTestService auditTestService;

    public AuditTestController(AuditTestService auditTestService) {
        this.auditTestService = auditTestService;
    }

    /**
     * 新增AuditTest数据，查看审计项是否自动填入
     * @param auditTestDTO auditTestDTO
     * @return AuditTest
     */
    @PostMapping("/audit/create")
    @Timed
    public ResponseEntity<AuditTest> create(@RequestBody AuditTestDTO auditTestDTO) {
        AuditTest auditTest = auditTestService.create(auditTestDTO);
        return new ResponseEntity<>(auditTest, HttpStatus.CREATED);
    }

}
