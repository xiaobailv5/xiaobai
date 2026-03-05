package com.example.lv.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.lv.util.excle.ExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lmh
 * @version 1.0
 * @project xiaobai
 * @description
 * @date 2023/10/13 17:54:36
 */
@RestController
public class ExcleToJsonController {


    @PostMapping("/import")
    public JSONArray excleToJson(@RequestPart("file") MultipartFile file) throws Exception {

        JSONArray array = ExcelUtils.readMultipartFile(file);
        System.out.println("导入数据为:" + array);
        return array;

    }


}
