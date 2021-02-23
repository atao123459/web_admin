package com.example.webadmin.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class FormController {

    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    //使用@RequestParam注解获取表单提交的参数,@RequestPart注解获取上传的文件,MultipartFile自动封装上传的文件
    public String upload(@RequestParam("email") String email, @RequestParam("username") String username
            ,@RequestPart("headerImg") MultipartFile headerImg,@RequestPart("photos") MultipartFile[] photos) throws IOException {
        log.info("上传的信息：email={},username={},headerImg={},photos={}",email,username,headerImg.getSize(),photos.length);
        if(!headerImg.isEmpty()){
            String originalFileName = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("G:\\new"+originalFileName));
        }
        if(photos.length>0){
            for (MultipartFile photo : photos) {
                if(!photo.isEmpty()){
                    String originalFileName = photo.getOriginalFilename();
                    photo.transferTo(new File("G:\\new"+originalFileName));
                }
            }
        }
        return "main";
    }
}
