package com.carpool.service.impl;

import com.carpool.entity.Article;
import com.carpool.service.IArticleService;
import org.springframework.stereotype.Service;

/**
 * @author bjsonghongxu
 * @create 2019-03-18 19:23
 **/
@Service("iArticleService")
public class ArticleServiceImpl  extends BasicSetServiceImpl<Article> implements IArticleService {
}
