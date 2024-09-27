package com.example.lv.controller;

import com.example.lv.bean.base.Result;
import com.example.lv.config.es.ElasticsearchUtil;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @Resource
    private RestHighLevelClient client;


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



    /**
     * 自定义评分
     * @param userId
     * @param province
     * @param face
     * @return FunctionScoreQueryBuilder.FilterFunctionBuilder[]
     * @author gxjh2
     * @date 2024/9/27 09:08:02
    */
    public FunctionScoreQueryBuilder.FilterFunctionBuilder[] changeFunction(long userId, String province, Map<String,Float> face) {
        //userId==-1表示游客登录，不需要个性化，只用根据省份
        double labelNumScore = 1.2;
        double maxLabelScore = 1.0;
        double minLabelScore = 0.0;
        List<String> labels = new ArrayList<>();
        labels.add("#");
        String[] provinces = new String[] {"河南","河北"};
        List<String> provinceList = Arrays.asList(provinces);
        StringBuilder scoreScript= new StringBuilder();
        //记录function的数量
        int functionNum=0;
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders;
        //1.游客登录，仅记录省份影响，数组长度=1（设置过长会导致function==null产生错误）
        if(userId==-1){
            filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[1];
        }
        //2.非游客登录：添加对应用户标签画像,标签score_script脚本自定义评分
        else {
            filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[2];
            //对省份和标签的自定义结果进行求和
            Object[] label= face.keySet().toArray();
            List<Short> labelNum = new ArrayList<>();
            // 将字符串标签转换为数字编号形式，用于排序规则的编写
            for (Object o : label) {
                labelNum.add((short) labels.indexOf(o));
            }
            //label在用户占比超过25%，认为这个label是有利的，此时匹配省份=0.5，标签>1，score评分升高
            //若匹配省份=0，标签>1，score评分升高也合理
            //若占比小于25%，则此标签对用户没有明显影响，此时匹配省份=0.5，标签=1，score评分升高
            //若匹配省份=0，标签=1，则score评分保持不变（也合理，如果查询评分非常高则足以超越前面的内容）
            //对标签的影响进行一定限制，避免查询结果完全由标签控制
            double labelScore=Math.min(face.get(label[0]) * labelNumScore,maxLabelScore);
            labelScore=Math.max(labelScore,minLabelScore);
            scoreScript.append("if(doc['labelNum'].value==").append(labelNum.get(0)).append("){return ").append(labelScore).append(";}");
            for (int i=1;i<label.length;i++){
                if(face.get(label[i]) * labelNumScore<minLabelScore){
                    continue;
                }
                labelScore=Math.min(face.get(label[i]) * labelNumScore,maxLabelScore);
                scoreScript.append("else if(doc['labelNum'].value==").append(labelNum.get(i)).append("){return ").append(labelScore).append(";}");
            }
            scoreScript.append("else {return 1.0}");
            //**层层包装填充放到functions中：https://blog.csdn.net/xiaoll880214/article/details/86716393
            ScoreFunctionBuilder<ScriptScoreFunctionBuilder> labelScoreFunction = ScoreFunctionBuilders.scriptFunction(new Script(scoreScript.toString()));
            FunctionScoreQueryBuilder.FilterFunctionBuilder labelFunction=new FunctionScoreQueryBuilder.FilterFunctionBuilder(labelScoreFunction);
            filterFunctionBuilders[functionNum]=labelFunction;
            functionNum++;
        }
        //2.省份num衰减评分
        //利用衰减函数，设定在给定省份id（仅此id）对应的得分为0.5（以id+偏移量为原点，搜索偏移量范围得分为decay）
        ScoreFunctionBuilder<LinearDecayFunctionBuilder> provinceScoreFunction = ScoreFunctionBuilders.linearDecayFunction("provinceNum", provinceList.indexOf(province)+0.1, 0.1, 0, 0.5);
        FunctionScoreQueryBuilder.FilterFunctionBuilder provinceFunction=new FunctionScoreQueryBuilder.FilterFunctionBuilder(provinceScoreFunction);
        filterFunctionBuilders[functionNum]=provinceFunction;

        return filterFunctionBuilders;
    }

    /**
     * 自定义评分
     * @return java.util.List
     * @author gxjh2
     * @date 2024/9/27 10:19:36
    */
    @PostMapping("/searchList")
    public List searchList() throws IOException {
        String searchContent = "白雪";

        String indexName = "books";

        //组装查询对象
        SearchRequest request = new SearchRequest();
        request.indices(indexName);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 搜索 title字段包含IPhone的数据
        queryBuilder.must(QueryBuilders.matchQuery("title", searchContent));

        SearchSourceBuilder searchBuilder = new SearchSourceBuilder().query(queryBuilder);
        //分页
        searchBuilder.from(0);
        searchBuilder.size(10);
        //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
        searchBuilder.explain(true);


        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[3];

        //过滤条件1：分类为：品牌手机最重要 -- 权重查询Weight
        ScoreFunctionBuilder<WeightBuilder> scoreFunctionBuilder = new WeightBuilder();
        scoreFunctionBuilder.setWeight(2);
        QueryBuilder termQuery = QueryBuilders.termQuery("categoryName", "品牌手机");
        FunctionScoreQueryBuilder.FilterFunctionBuilder category = new FunctionScoreQueryBuilder.FilterFunctionBuilder(termQuery, scoreFunctionBuilder);
        filterFunctionBuilders[0] = category;

        // 过滤条件2：销量越高越排前 --计分查询 FieldValueFactor
        ScoreFunctionBuilder<FieldValueFactorFunctionBuilder> fieldValueScoreFunction = new FieldValueFactorFunctionBuilder("salesVolume");
        ((FieldValueFactorFunctionBuilder) fieldValueScoreFunction).factor(1.2f);
        FunctionScoreQueryBuilder.FilterFunctionBuilder sales = new FunctionScoreQueryBuilder.FilterFunctionBuilder(fieldValueScoreFunction);
        filterFunctionBuilders[1] = sales;

        // 给定每个用户随机展示：  --random_score
        ScoreFunctionBuilder<RandomScoreFunctionBuilder> randomScoreFilter = new RandomScoreFunctionBuilder();
        ((RandomScoreFunctionBuilder) randomScoreFilter).seed(2);
        FunctionScoreQueryBuilder.FilterFunctionBuilder random = new FunctionScoreQueryBuilder.FilterFunctionBuilder(randomScoreFilter);
        filterFunctionBuilders[2] = random;

        // 多条件查询 FunctionScore
        FunctionScoreQueryBuilder query = QueryBuilders.functionScoreQuery(queryBuilder, filterFunctionBuilders)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM).boostMode(CombineFunction.SUM);

        request.source(searchBuilder);




        SearchResponse response =  client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        String searchSource;
        for (SearchHit hit : hits)
        {
            searchSource = hit.getSourceAsString();
            System.out.println(searchSource);
        }
        //        long took = response.getTook().getMillis();
        TotalHits totalHits = hits.getTotalHits();
        long total = totalHits.value;
        System.out.println(total);

        return new ArrayList();
    }
}
