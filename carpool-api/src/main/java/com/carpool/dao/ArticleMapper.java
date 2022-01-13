package com.carpool.dao;

import com.carpool.service.IBasicSetMapper;
import com.carpool.entity.Article;
import org.springframework.stereotype.Repository;

/**
 * 文章数据接口
 *
 * @author bjsonghongxu
 * @create 2019-03-18 19:20
 **/
@Repository
public interface ArticleMapper extends IBasicSetMapper<Article> {
}
