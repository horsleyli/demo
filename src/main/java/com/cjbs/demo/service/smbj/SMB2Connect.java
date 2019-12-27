package com.cjbs.demo.service.smbj;

import com.hierynomus.msdtyp.AccessMask;
import com.hierynomus.msfscc.fileinformation.FileIdBothDirectoryInformation;
import com.hierynomus.mssmb2.SMB2CreateDisposition;
import com.hierynomus.mssmb2.SMB2ShareAccess;
import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.SmbConfig;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.hierynomus.smbj.share.File;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * SMB2连接示例
 * 例: 我们当前要连接 IP为:123.123.123.123 目录为: SRC/SMB2/ 下的文件
 * @author liuyb
 *
 */

@Service
public class SMB2Connect {
    /**
     * 域名
     */
    private static final String SHARE_DOMAIN = "192.168.100.94";
    /**
     * 用户名
     */
    private static final String SHARE_USER = "samba";
    /**
     * 密码
     */
    private static final String SHARE_PASSWORD = "samba";
    /**
     * 共享文件夹名称
     */
    private static final String SHARE_SRC_DIR = "samba";
    /**
     * 子文件夹名称
     */
    private static final String SHARE_DST_DIR = "webapps/";

    public void downLoad() {
        // 设置超时时间(可选)
        SmbConfig config = SmbConfig.builder().withTimeout(120, TimeUnit.SECONDS)
                .withTimeout(120, TimeUnit.SECONDS) // 超时设置读，写和Transact超时（默认为60秒）
                .withSoTimeout(180, TimeUnit.SECONDS) // Socket超时（默认为0秒）
                .build();

        // 如果不设置超时时间	SMBClient client = new SMBClient();
        SMBClient client = new SMBClient(config);

        try {
            Connection connection = client.connect("192.168.100.94");	// 如:123.123.123.123
            AuthenticationContext ac = new AuthenticationContext(SHARE_USER, SHARE_PASSWORD.toCharArray(), SHARE_DOMAIN);
            Session session = connection.authenticate(ac);

            // 连接共享文件夹
            DiskShare share = (DiskShare) session.connectShare(SHARE_SRC_DIR);
            //要保存的本地文件夹路径 如: D:/smd2/
            String dstRoot = "D:/testSMB/";

            for (FileIdBothDirectoryInformation f : share.list(SHARE_DST_DIR, "111.txt")) {
                String filePath = SHARE_DST_DIR + f.getFileName();
                String dstPath = dstRoot + f.getFileName();

                FileOutputStream fos = new FileOutputStream(dstPath);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                if (share.fileExists(filePath)) {
                    System.out.println("正在下载文件:" + f.getFileName());

                    File smbFileRead = share.openFile(filePath, EnumSet.of(AccessMask.GENERIC_READ), null, SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_OPEN, null);
                    InputStream in = smbFileRead.getInputStream();
                    byte[] buffer = new byte[4096];
                    int len = 0;
                    while ((len = in.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, len);
                    }

                    bos.flush();
                    bos.close();

                    System.out.println("文件下载成功");
                    System.out.println("==========================");
                } else {
                    System.out.println("文件不存在");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    public void upLoad() {
        // 设置超时时间(可选)
        SmbConfig config = SmbConfig.builder().withTimeout(120, TimeUnit.SECONDS)
                .withTimeout(120, TimeUnit.SECONDS) // 超时设置读，写和Transact超时（默认为60秒）
                .withSoTimeout(180, TimeUnit.SECONDS) // Socket超时（默认为0秒）
                .build();

        // 如果不设置超时时间	SMBClient client = new SMBClient();
        SMBClient client = new SMBClient(config);

        try {
            Connection connection = client.connect("192.168.100.94");	// 如:123.123.123.123
            AuthenticationContext ac = new AuthenticationContext(SHARE_USER, SHARE_PASSWORD.toCharArray(), SHARE_DOMAIN);
            Session session = connection.authenticate(ac);


            // 连接共享文件夹
            DiskShare share = (DiskShare) session.connectShare(SHARE_SRC_DIR);
            //要保存的本地文件夹路径 如: D:/smd2/
            String root = SHARE_SRC_DIR + "/" + "cert/";

                //share.mkdir("111.txt");
                File subFileWrite = share.openFile("111.txt", EnumSet.of(AccessMask.GENERIC_WRITE), null, SMB2ShareAccess.ALL, SMB2CreateDisposition.FILE_CREATE, null);
            String filePath = "D:/testSMB/111.txt";
                try{
                FileInputStream fin = new FileInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(fin);

                //System.out.println("正在上传文件:" + multipartFile.getName());
                    OutputStream ot = subFileWrite.getOutputStream();
                    byte[] buffer = new byte[4096];
                    int len = 0;
                    while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
                        ot.write(buffer, 0, len);
                    }

                    ot.flush();
                    ot.close();

                    System.out.println("文件上传成功");
                    System.out.println("==========================");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
