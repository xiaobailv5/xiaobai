package com.example.lv.controller.thread;

import com.example.lv.service.thread.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project xiaobai
 * @description 线程控制层
 * @author gxjh2
 * @date 2024/10/19 16:50:46
 * @version 1.0
 */
@RestController
@RequestMapping("/thread")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async(){
        asyncService.executeAsync();
        //同步任务
        asyncService.syncTasks();
    }



}
