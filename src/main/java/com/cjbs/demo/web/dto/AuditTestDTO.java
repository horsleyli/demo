package com.cjbs.demo.web.dto;

import com.cjbs.demo.domain.AuditTest;

import java.time.Instant;

public class AuditTestDTO {

    private String id;

    private String name;

    private String sex;

    private String createBy;

    private Instant createDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    public AuditTestDTO(AuditTest auditTest) {
        this(auditTest.getId(), auditTest.getName(), auditTest.getSex(), auditTest.getCreateBy(), auditTest.getCreateDate(),
                auditTest.getLastModifiedBy(), auditTest.getLastModifiedDate());
    }

    public AuditTestDTO(String id, String name, String sex, String createBy, Instant createDate, String lastModifiedBy, Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.createBy = createBy;
        this.createDate = createDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
