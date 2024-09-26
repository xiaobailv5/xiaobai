package com.example.lv.es;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * @projectName: xiaobai
 * @package: com.example.lv.es
 * @className: EsRestClientTest
 * @author: dus
 * @description: es测试
 * @date: 2024/9/26 8:57
 * @version: 1.0
 */
public class EsRestClientTest {

    public  static void main(String[] args) throws IOException {

        // ================== 创建索引

        // 创建对象
        RestHighLevelClient restClient = new RestHighLevelClient(RestClient
        .builder(new HttpHost("127.0.0.1",9200)));
        /**
         * 1创建索引 2 获取索引信息 3 删除索引 4 新增数据 5 修改数据 6 简单查询 7 高级查询
         * 8 删除数据 9 批量新增数据 10 全量查询 11 简单条件查询 12 分页查询
         * 13 排序查询 14 指定字段返回 15 多条件查询 16 区范围查询 17 模糊查询
         * 18 高亮查询 19 聚合查询
        */
        int num = 1;
        switch (num) {
            case 1:

                // 创建索引的 请求对象 user索引名称 userAli索引别名
                CreateIndexRequest createIndexRequest = new CreateIndexRequest("user").alias(new Alias("userAli"));
                // 调用create 执行 创建索引
                CreateIndexResponse createIndexResponse = restClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
                // 获取请求 结果
                boolean acknowledged = createIndexResponse.isAcknowledged();
                System.out.println(acknowledged);
                // 关闭连接
                restClient.close();
                return;
            case 2:

                // ================== 获取索引 相关信息，别名 setting   mapping 等

                // 创建索引的 请求对象
                GetIndexRequest getIndexRequest = new GetIndexRequest("user");
                // 调用get 获取
                GetIndexResponse getIndexResponse = restClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(getIndexResponse.getAliases());
                System.out.println(getIndexResponse.getSettings());
                System.out.println(getIndexResponse.getMappings());

                restClient.close();
                return;
            case 3:

                // ================== 删除索引 （一般用不到）


                // 创建索引的 请求对象
                DeleteIndexRequest deIndexRequest = new DeleteIndexRequest("user");
                // 调用delete  删除
                AcknowledgedResponse delete = restClient.indices().delete(deIndexRequest, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(delete.isAcknowledged());

                restClient.close();
                return;
            case 4:

                // ================== 新增(更新)数据

                // 创建索数据
                IndexRequest indexRequest = new IndexRequest("user");
                indexRequest.index("user").id("1112");
                User user = new User();
                user.setAddress("河南省郑州市");
                user.setAge(30);
                user.setName("张三");
                user.setSex("男");
                String userStr = JSONObject.toJSONString(user);
                IndexRequest source = indexRequest.source(userStr, XContentType.JSON);
                // index 执行 创建索引数据
                IndexResponse index = restClient.index(indexRequest, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(index.getResult());

                restClient.close();
                return;

            case 5:

                // ================== 修改 数据


                // 创建索数据
                UpdateRequest updateRequest = new UpdateRequest();
                updateRequest.index("user").id("1112");
                updateRequest.doc(XContentType.JSON,"sex","女");
                // index 执行 创建索引数据
                UpdateResponse update = restClient.update(updateRequest, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(update.getResult());
                restClient.close();
                return;

            case 6:

                // ================== 简单查询

                // 查询
                GetRequest GetRequest = new GetRequest();
                GetRequest.index("user").id("1112");

                // index 执行执行查询
                GetResponse documentFields = restClient.get(GetRequest, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(documentFields.getSourceAsString());

                // 关闭连接
                restClient.close();

                return;

            case 7:

                // ================== 删除
                // 查询
                DeleteRequest deleteRequest = new DeleteRequest();
                DeleteRequest user2 = deleteRequest.index("user").id("1112");

                // index 执行 删除
                DeleteResponse delete2 = restClient.delete(user2, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(delete2.toString());

                restClient.close();
                return;


            case 8:

                // ================== 批量新增

                // 批量
                BulkRequest bulkRequest = new BulkRequest();
                bulkRequest.add(new IndexRequest().index("user").id("10001").source("name","zhangsan","age","60","add","河南省郑州市","sex","男"));
                bulkRequest.add(new IndexRequest().index("user").id("10002").source("name","zhangsan2","age","44","add","河南省郑州市","sex","女"));
                bulkRequest.add(new IndexRequest().index("user").id("10003").source("name","zhangsan3","age","55","add","河南省郑州市","sex","男"));
                bulkRequest.add(new IndexRequest().index("user").id("10004").source("name","zhangsan4","age","33","add","河南省郑州市","sex","女"));
                bulkRequest.add(new IndexRequest().index("user").id("10005").source("name","zhangsan5","age","88","add","河南省郑州市","sex","男"));
                bulkRequest.add(new IndexRequest().index("user").id("10006").source("name","zhangsan5","age","45","add","河南省郑州市","sex","男"));

                bulkRequest.add(new IndexRequest().index("user").id("10007").source("name","zhangsan7","age","55","add","河南省郑州市","sex","男"));

                bulkRequest.add(new IndexRequest().index("user").id("10008").source("name","zhangsan44","age","45","add","河南省郑州市","sex","男"));
                bulkRequest.add(new IndexRequest().index("user").id("10009").source("name","zhangsan33","age","45","add","河南省郑州市","sex","女"));
                bulkRequest.add(new IndexRequest().index("user").id("10010").source("name","zhangsa55","age","45","add","河南省郑州市","sex","女"));
                bulkRequest.add(new IndexRequest().index("user").id("10011").source("name","zhangsan555","age","45","add","河南省郑州市","sex","男"));

                // index 执行 删除
                BulkResponse bulk = restClient.bulk(bulkRequest, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(bulk.getTook());

                restClient.close();
                return;


            case 9:
                // ================== 批量删除

                // 批量
                BulkRequest bulkRequest2 = new BulkRequest();
                bulkRequest2.add(new DeleteRequest().index("user").id("10001"));
                bulkRequest2.add(new DeleteRequest().index("user").id("10002"));
                // index 执行 删除
                BulkResponse bulk2 = restClient.bulk(bulkRequest2, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(bulk2.getTook());

                restClient.close();

                return;
            case 10:

                //         ================== 全量查询

                // 查询
                SearchRequest searchRequest = new SearchRequest();
                searchRequest.indices("user");

                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
                searchRequest.source(searchSourceBuilder);

                // index 执行查询
                SearchResponse search = restClient.search(searchRequest, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(search.getHits().getTotalHits());
                for (SearchHit hit:search.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                restClient.close();

                return;

            case 11:

                //         ================== 简单条件查询

                // 查询
                SearchRequest searchRequest2 = new SearchRequest();
                searchRequest2.indices("user");

                SearchSourceBuilder searchSourceBuilder2 = new SearchSourceBuilder().query(QueryBuilders.termQuery("sex","女"));
                searchRequest2.source(searchSourceBuilder2);

                // index 执行查询
                SearchResponse search2 = restClient.search(searchRequest2, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(search2.getHits().getTotalHits());
                for (SearchHit hit:search2.getHits()){
                    System.out.println(hit.getSourceAsString());
                }

                restClient.close();
                return;

            case 12:

                //         ================== 分页查询

                // 查询
                SearchRequest searchRequest3 = new SearchRequest();
                searchRequest3.indices("user");

                SearchSourceBuilder searchSourceBuilder3 = new SearchSourceBuilder().query(QueryBuilders.termQuery("sex","男"));
                searchSourceBuilder3.from(0);
                searchSourceBuilder3.size(2);
                searchRequest3.source(searchSourceBuilder3);

                // index 执行查询
                SearchResponse search3 = restClient.search(searchRequest3, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(search3.getHits().getTotalHits());
                for (SearchHit hit:search3.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                restClient.close();
                return;

            case 13:

                //         ================== 排序查询

                // 查询
                SearchRequest searchRequest4 = new SearchRequest();
                searchRequest4.indices("user");

                SearchSourceBuilder searchSourceBuilder4 = new SearchSourceBuilder().query(QueryBuilders.termQuery("sex","男"));
                searchSourceBuilder4.sort("age", SortOrder.DESC);
                searchSourceBuilder4.from(0);
                searchSourceBuilder4.size(2);
                searchRequest4.source(searchSourceBuilder4);
                // index 执行查询
                SearchResponse search4 = restClient.search(searchRequest4, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(search4.getHits().getTotalHits());
                for (SearchHit hit:search4.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                // 关闭连接
                restClient.close();
                return;

            case 14:
                //         ================== 指定字段返回
                // 查询
                SearchRequest searchRequest5 = new SearchRequest();
                searchRequest5.indices("user");

                SearchSourceBuilder searchSourceBuilder5 = new SearchSourceBuilder().query(QueryBuilders.termQuery("sex","男"));

                String[] excludes ={"add"};
                String[] includes ={"age","name"}; // 优先级高

                searchSourceBuilder5.fetchSource(includes,excludes);

                searchSourceBuilder5.sort("age", SortOrder.DESC);
                searchSourceBuilder5.from(0);
                searchSourceBuilder5.size(2);
                searchRequest5.source(searchSourceBuilder5);

                // index 执行查询
                SearchResponse search5 = restClient.search(searchRequest5, RequestOptions.DEFAULT);
                // 获取请求 结果

                System.out.println(search5.getHits().getTotalHits());
                for (SearchHit hit:search5.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                // 关闭连接
                restClient.close();
                return;

            case 15:

                //         ================== 多条件查询

                // 查询
                SearchRequest searchRequest6 = new SearchRequest();
                searchRequest6.indices("user");

                BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
                boolQueryBuilder.must(QueryBuilders.matchQuery("age","45").boost(20));
                boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex","男"));
                boolQueryBuilder.should(QueryBuilders.matchQuery("add","河南省郑州市"));

                SearchSourceBuilder searchSourceBuilder6 = new SearchSourceBuilder().query(boolQueryBuilder);

                searchSourceBuilder6.from(0);
                searchSourceBuilder6.size(10);
                searchRequest6.source(searchSourceBuilder6);
                // index 执行查询
                SearchResponse search6 = restClient.search(searchRequest6, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(search6.getHits().getTotalHits());
                for (SearchHit hit:search6.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                // 关闭连接
                restClient.close();
                return;

            case 16:

                //         ================== 区范围查询

                // 查询
                SearchRequest searchRequest7 = new SearchRequest();
                searchRequest7.indices("user");

                BoolQueryBuilder boolQueryBuilder7 = new BoolQueryBuilder();
                RangeQueryBuilder age = QueryBuilders.rangeQuery("age");
                age.gte("30");
                age.lte("60");
                boolQueryBuilder7.must(age);
                SearchSourceBuilder searchSourceBuilder7 = new SearchSourceBuilder().query(boolQueryBuilder7);

                searchSourceBuilder7.from(0);
                searchSourceBuilder7.size(10);
                searchRequest7.source(searchSourceBuilder7);
                // index 执行查询
                SearchResponse search7 = restClient.search(searchRequest7, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(search7.getHits().getTotalHits());
                for (SearchHit hit:search7.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                // 关闭连接
                restClient.close();
                return;

            case 17:

                //         ================== 模糊查询

                // 查询
                SearchRequest searchRequest8 = new SearchRequest();
                searchRequest8.indices("user");

                FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "zhangsan");
                //指定和搜索词 相差多少个字符被匹配
                fuzzyQueryBuilder.fuzziness(Fuzziness.TWO);
                SearchSourceBuilder searchSourceBuilder8 = new SearchSourceBuilder().query(fuzzyQueryBuilder);

                searchSourceBuilder8.from(0);
                searchSourceBuilder8.size(400);
                searchRequest8.source(searchSourceBuilder8);
                // index 执行查询
                SearchResponse search8 = restClient.search(searchRequest8, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(search8.getHits().getTotalHits());
                for (SearchHit hit:search8.getHits()){
                    System.out.println(hit.getSourceAsString());
                }
                // 关闭连接
                restClient.close();
                return;

            case 18:

                //         ================== 高亮查询

                // 查询
                SearchRequest searchRequest9 = new SearchRequest();
                searchRequest9.indices("user");

                SearchSourceBuilder searchSourceBuilder9 = new SearchSourceBuilder();
                TermQueryBuilder queryBuilder = QueryBuilders.termQuery("name", "zhangsan");

                HighlightBuilder highlightBuilder = new HighlightBuilder();
                highlightBuilder.preTags("<font color='red'>");
                highlightBuilder.postTags("</font>");
                highlightBuilder.field("name");

                searchSourceBuilder9.highlighter(highlightBuilder);
                searchSourceBuilder9.query(queryBuilder);

                searchRequest9.source(searchSourceBuilder9);
                // index 执行查询
                SearchResponse search9 = restClient.search(searchRequest9, RequestOptions.DEFAULT);
                // 获取请求 结果
                System.out.println(search9.getHits().getTotalHits());
                for (SearchHit hit:search9.getHits()){
                    System.out.println(hit.getSourceAsString());
                    System.out.println(hit.getHighlightFields());

                }

                // 关闭连接
                restClient.close();
                return;

            case 19:
                //         ================== 聚合查询

                // 查询
                SearchRequest searchRequest10 = new SearchRequest();
                searchRequest10.indices("user");

                SearchSourceBuilder searchSourceBuilder10 = new SearchSourceBuilder();

                AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupByAge").field("age");

                searchSourceBuilder10.aggregation(aggregationBuilder);
                searchRequest10.source(searchSourceBuilder10);
                // index 执行查询
                SearchResponse searchResponse = restClient.search(searchRequest10, RequestOptions.DEFAULT);
                // 获取请求 结果
                Aggregations aggregations = searchResponse.getAggregations();
                Terms byAgeAggregation = aggregations.get("groupByAge");
                for (Terms.Bucket buck : byAgeAggregation.getBuckets()) {
                    long docCount = buck.getDocCount();
                    String keyAsString = buck.getKeyAsString();
                    System.out.println("年龄："+keyAsString+" 聚合数量："+docCount);
                }

                for (SearchHit hit:searchResponse.getHits()){
                    System.out.println(hit.getSourceAsString());

                }

                // 关闭连接
                restClient.close();
                return;

            default:
                break;

        }













    }

}

 class User {
    private String name;
    private Integer age;
    private String address;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }


 }
