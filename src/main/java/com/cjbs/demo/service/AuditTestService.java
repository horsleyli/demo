package com.cjbs.demo.service;


import com.cjbs.demo.domain.AuditTest;
import com.cjbs.demo.repository.AuditTestRepository;
import com.cjbs.demo.web.dto.AuditTestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * @author shj
 */
@Service
public class AuditTestService {

    private AuditTestRepository auditTestRepository;

    public AuditTestService(AuditTestRepository auditTestRepository) {
        this.auditTestRepository = auditTestRepository;
    }

    /**
     * 新增AuditTest数据，查看审计项是否自动填入
     * @param auditTestDTO auditTestDTO
     * @return AuditTest
     */
    public AuditTest create(AuditTestDTO auditTestDTO) {
        AuditTest auditTest = new AuditTest();
        BeanUtils.copyProperties(auditTestDTO, auditTest);
        return auditTestRepository.save(auditTest);
    }
}
