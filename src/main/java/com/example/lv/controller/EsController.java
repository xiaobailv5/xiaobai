package com.example.lv.controller;

import com.example.lv.bean.base.Result;
import com.example.lv.config.es.ElasticsearchUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.controller
 * @className: EsController
 * @author: dus
 * @description: es
 * @date: 2024/9/23 20:02
 * @version: 1.0
 */
@RestController
@RequestMapping("/es")
public class EsController {

    public static Logger logger = LoggerFactory.getLogger(EsController.class);

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    @PostMapping(value = "/createIndex")
    public Result createIndex(String index) {

        Result result = new Result();

        try {

            if (elasticsearchUtil.createIndex(index)) {
                result.setCode(200);
                result.setMessage("创建成功");
            } else {
                result.setCode(500);
                result.setMessage("创建失败");
            }
        } catch (Exception e) {
            logger.error("createIndex error", e);
        }


        return result;
    }

    /**
     * 搜索索引
     * @param indexName
     * @return com.example.lv.bean.base.Result
     * @author gxjh2
     * @date 2024/9/23 20:56:04
    */
    @PostMapping(value = "/search")
    public Result search(String indexName) {

        Result result = new Result();

        try {

            SearchResponse search = elasticsearchUtil.search(indexName, "id");
            if (search != null) {
                result.setCode(200);
                result.setMessage("查询成功");
            } else {
                result.setCode(500);
                result.setMessage("查询失败");
            }
        } catch (Exception e) {
            logger.error("search error", e);
        }
        return null;
    }


}
