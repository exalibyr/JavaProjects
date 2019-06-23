package com.excalibur.ftp.service;

import com.excalibur.ftp.util.FTPNoOpThread;
import com.excalibur.ftp.dao.response.body.FTPRetrieveResult;
import com.excalibur.ftp.dao.response.body.FTPStoreResult;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FTPService {

    private String SERVER_NAME;
    private int SERVER_PORT;
    private String USER_NAME;
    private String USER_PASS;
    private static final String ROOD_DIR = "/";
    private FTPClient ftpClient;
//    private ReentrantLock lock;
//    private FTPNoOpThread noOpThread;

    FTPService() {
        Properties properties = new Properties();
        try (InputStream stream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(stream);
            SERVER_NAME = properties.getProperty("FTPServer.name");
            SERVER_PORT = Integer.valueOf(properties.getProperty("FTPServer.port"));
            USER_NAME = properties.getProperty("FTPServer.user.name");
            USER_PASS = properties.getProperty("FTPServer.user.password");
            startNewSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FTPRetrieveResult retrieveFiles(String dirName, Set<String> fileNames) {
        List<String> errors = new ArrayList<>();
        try {
//            lock.lock();
            if ( !ftpClient.isConnected()) restoreConnection();
            if (changeToRootDir()) {
                if (ftpClient.changeWorkingDirectory(dirName)) {
                    Map<String, byte[]> nameContent = new HashMap<>();
                    for (String fileName : fileNames) {
                        ftpClient.enterLocalPassiveMode();
                        InputStream stream = ftpClient.retrieveFileStream(fileName);
                        if (stream == null) {
                            errors.add("RETRIEVE " + fileName+ " FILE FAILED");
                            return new FTPRetrieveResult(false, errors, null);
                        } else {
                            nameContent.put(fileName, stream.readAllBytes());
                        }
                    }
                    return new FTPRetrieveResult(true, errors, nameContent);
                } else {
                    errors.add("CHANGE TO " + dirName + " DIR FAILED");
                    return new FTPRetrieveResult(false, errors, null);
                }
            } else {
                errors.add("CHANGE TO ROOT DIR FAILED");
                return new FTPRetrieveResult(false, errors, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            errors.add(ex.getMessage());
            return new FTPRetrieveResult(false, errors, null);
        } finally {
//            lock.unlock();
        }
    }

    public FTPStoreResult storeSingleFile(String dirName, String fileName, byte[] fileContent) {
        List<String> errors = new ArrayList<>();
        try {
//            lock.lock();
            if ( !ftpClient.isConnected()) restoreConnection();
            if (changeToRootDir()) {
                ftpClient.enterLocalPassiveMode();
                if (ftpClient.changeWorkingDirectory(dirName)) {
                    if (storeSingleFile(ftpClient, fileName, fileContent)) {
                        return new FTPStoreResult(true, fileName, errors);
                    } else {
                        errors.add("STORE " + fileName + " FILE FAILED");
                        return new FTPStoreResult(false, null, errors);
                    }
                } else {
                    if (ftpClient.makeDirectory(ftpClient.printWorkingDirectory() + dirName)) {
                        if (ftpClient.changeWorkingDirectory(dirName)) {
                            if (storeSingleFile(ftpClient, fileName, fileContent)) {
                                return new FTPStoreResult(true, fileName, errors);
                            } else {
                                errors.add("STORE " + fileName + " FILE FAILED");
                                return new FTPStoreResult(false, null, errors);
                            }
                        } else {
                            errors.add("CHANGE TO " + dirName + " DIR FAILED");
                            return new FTPStoreResult(false, null, errors);
                        }
                    } else {
                        errors.add("MAKE " + dirName + " DIR FAILED");
                        return new FTPStoreResult(false, null, errors);
                    }
                }
            } else {
                errors.add("CHANGE TO ROOT DIR FAILED");
                return new FTPStoreResult(false, null, errors);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            errors.add(ex.getMessage());
            return new FTPStoreResult(false, fileName, errors);
        } finally {
//            lock.unlock();
        }
    }


//   public FTPStoreResult storeSingleFile(String dirName, String fileName, byte[] fileContent) {
//       FTPClient ftpClient = new FTPClient();
//       List<String> errors = new ArrayList<>();
//       try {
//           ftpClient.connect(SERVER_NAME, SERVER_PORT);
//           if (ftpClient.isConnected()) {
//               if (ftpClient.login(USER_NAME, USER_PASS)) {
//                   ftpClient.enterLocalPassiveMode();
//                   if (ftpClient.changeWorkingDirectory(dirName)) {
//                       storeSingleFile(ftpClient, fileName, fileContent);
//                       return new FTPStoreResult(true, fileName, errors);
//                   } else {
//                       if (ftpClient.makeDirectory(ftpClient.printWorkingDirectory() + dirName)) {
//                           if (ftpClient.changeWorkingDirectory(dirName)) {
//                               storeSingleFile(ftpClient, fileName, fileContent);
//                               return new FTPStoreResult(true, fileName, errors);
//                           } else {
//                               errors.add("CHANGE TO " + dirName + " DIR FAILED");
//                               return new FTPStoreResult(false, fileName, errors);
//                           }
//                       } else {
//                           errors.add("MAKE " + dirName + " DIR FAILED");
//                           return new FTPStoreResult(false, fileName, errors);
//                       }
//                   }
//               } else {
//                   errors.add("LOGIN FAILED");
//                   return new FTPStoreResult(false, fileName, errors);
//               }
//           } else {
//               errors.add("CONNECT FAILED");
//               return new FTPStoreResult(false, fileName, errors);
//           }
//       } catch (Exception ex) {
//           ex.printStackTrace();
//           errors.add(ex.getMessage());
//           return new FTPStoreResult(false, fileName, errors);
//       } finally {
//           try {
//               ftpClient.abor();
//               ftpClient.quit();
//           } catch (Exception e) {
//               e.printStackTrace();
//           }
//       }
//   }

   private Boolean storeSingleFile(FTPClient ftpClient, String fileName, byte[] fileContent) throws IOException{
       InputStream stream = new ByteArrayInputStream(fileContent);
       ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
       Boolean success = ftpClient.storeFile(fileName, stream);
       stream.close();
       return success;
   }

   private Boolean changeToRootDir() throws IOException{
        return ftpClient.changeWorkingDirectory(ROOD_DIR);
   }

//   private void startNoOpThread() {
//       noOpThread = new FTPNoOpThread(ftpClient);
//       lock = noOpThread.getLock();
//       noOpThread.run();
//   }

   private void startNewSession() throws IOException {
       ftpClient = new FTPClient();
       ftpClient.connect(SERVER_NAME, SERVER_PORT);
       if (ftpClient.isConnected()) {
           if (ftpClient.login(USER_NAME, USER_PASS)) {
//               startNoOpThread();
           } else {
               throw new IOException("LOGIN FAILED");
           }
       } else {
           throw new IOException("CONNECT FAILED");
       }
   }

   private void restoreConnection() throws IOException{
//       noOpThread.setAlive(false);
       startNewSession();
   }

}
