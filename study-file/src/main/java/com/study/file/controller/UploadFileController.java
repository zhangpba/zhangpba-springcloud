package com.study.file.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传文件控制类
 *
 * @author zhangpba
 * @date 2022-03-21
 */
@RestController
@Api(tags = "上传文件控制类")
public class UploadFileController {

    /**
     * @param file
     * @return
     * @author zhangpba
     * @date 2022-03-21
     */
    @ApiOperation("上传文件")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") @ApiParam(value = "二进制文件流") MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        fileName = getFileName(fileName);
        String filepath = getUploadPath();
        if (!file.isEmpty()) {
            try (BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new File(filepath + File.separator + fileName)))) {
                out.write(file.getBytes());
                out.flush();
                return "上传成功！";
            } catch (FileNotFoundException e) {
                return "上传文件失败 FileNotFoundException：" + e.getMessage();
            } catch (IOException e) {
                return "上传文件失败 IOException：" + e.getMessage();
            }
        } else {
            return "上传文件失败，文件为空";
        }
    }

    /**
     * 文件名后缀前添加一个时间戳
     *
     * @author zhangpba
     * @date 2022-03-21
     */
    private String getFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
        String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
        fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
        return fileName;
    }

    /**
     * 获取当前系统路径
     *
     * @author zhangpba
     * @date 2022-03-21
     */
    private String getUploadPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) path = new File("");
        File upload = new File(path.getAbsolutePath(), "static/upload/");
        if (!upload.exists()) upload.mkdirs();
        return upload.getAbsolutePath();
    }
}
