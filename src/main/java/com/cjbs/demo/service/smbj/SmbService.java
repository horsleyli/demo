package com.cjbs.demo.service.smbj;

import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author shj
 */
@Service
public class SmbService {

    public Session getSession() {
        SMBClient client = new SMBClient();
        try (Connection connection = client.connect("192.168.100.94")) {
            AuthenticationContext ac = new AuthenticationContext("samba", "samba".toCharArray(), "DOMAIN");
            Session session = connection.authenticate(ac);
            return session;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void listFiles() {

        Session session = getSession();
        // Connect to Share
        if(session != null) {
            try {
                try (DiskShare share = (DiskShare) session.connectShare("samba")) {
                    for (FileIdBothDirectoryInformation f : share.list("webapps", "*.tar.gz")) {
                        System.out.println("File : " + f.getFileName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downLoadFile() {
        Session session = getSession();
        if(session != null) {
            try {
                try (DiskShare share = (DiskShare) session.connectShare("samba")) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
