package com.cjbs.demo.web.rest;


import com.cjbs.demo.service.smbj.SMB2Connect;
import com.cjbs.demo.service.smbj.SmbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shj
 */

@RestController
@RequestMapping("/api")
public class TestServiceController {

    private SmbService smbService;
    private SMB2Connect smb2Connect;

    public TestServiceController(SmbService smbService, SMB2Connect smb2Connect) {
        this.smbService = smbService;
        this.smb2Connect = smb2Connect;
    }

    @GetMapping("/smbService/listFiles")
    public void smbServiceListFiles() {
        smbService.listFiles();
    }

    @GetMapping("/smb2Connect/downLoad")
    public void smb2ConnectDownLoad() {
        smb2Connect.downLoad();
    }

    @GetMapping("/smb2Connect/upLoad")
    public void smb2ConnectUpLoad() {
        smb2Connect.upLoad();
    }
}
