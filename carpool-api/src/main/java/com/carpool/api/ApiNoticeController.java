package com.carpool.api;

import com.carpool.constants.CommonConstant;
import com.carpool.dto.NoticeVo;
import com.carpool.entity.Notice;
import com.carpool.service.INoticeService;
import com.carpool.util.ApiBaseAction;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告控制器
 *
 * @author bjsonghongxu
 * @create 2019-03-18 19:26
 **/
@RestController
@RequestMapping("/api/notice")
public class ApiNoticeController extends ApiBaseAction {


    @Autowired
    private INoticeService iNoticeService;


    /**
     * 分页查询公告列表
     * @param noticeVo
     * @return
     */
    @RequestMapping("list")
    public Object list(NoticeVo noticeVo) {
        PageHelper.startPage(noticeVo.getStart(), noticeVo.getLimit(), true, false); //设置分页
        Example example = new Example(Notice.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
        example.setOrderByClause(" createTime DESC"); ///创建时间倒叙输出
        List<Notice> list = iNoticeService.selectByExample(example);

        PageInfo<Notice> pageInfo = new PageInfo<>(list);

        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("totalPage",pageInfo.getPages());
        returnMap.put("list", list);
        return toResponsSuccess(returnMap);
    }





}
