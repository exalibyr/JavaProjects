package com.excalibur.ftp.controller;

import com.excalibur.ftp.dao.request.body.FilesInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.excalibur.ftp.dao.response.body.FTPRetrieveResult;
import com.excalibur.ftp.dao.response.body.FTPStoreResult;
import com.excalibur.ftp.service.FTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    @Autowired
    private FTPService ftpService;

    @RequestMapping(method = RequestMethod.GET, value = "/services/rest/fileService/retrieveFiles")
    public String retriveFiles(@RequestBody FilesInfo filesInfo) {
        FTPRetrieveResult retrieveResult = ftpService.retrieveFiles(filesInfo.getUserId(), filesInfo.getKeys());
        try {
            return new ObjectMapper().writeValueAsString(retrieveResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "FATAL ERROR: Couldn't compose JSON response";
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/services/rest/fileService/storeFile")
    public String storeFile(@RequestParam(name = "userId") String userId,
                            @RequestParam(name = "fileName") String fileName,
                            @RequestBody byte[] fileContent) {
        FTPStoreResult storeResult = ftpService.storeSingleFile(userId, fileName, fileContent);
        try {
            return new ObjectMapper().writeValueAsString(storeResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "FATAL ERROR: Couldn't compose JSON response";
        }
    }

}
