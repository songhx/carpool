package com.carpool.api;

import com.carpool.constants.CommonConstant;
import com.carpool.dto.InfoVo;
import com.carpool.entity.ScInfo;
import com.carpool.service.IInfoService;
import com.carpool.util.ApiBaseAction;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 信息控制器
 *
 * @author bjsonghongxu
 * @create 2018-06-21 14:59
 **/
@RestController
@RequestMapping("/api/info")
public class InfoController extends ApiBaseAction {

    public static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private IInfoService iInfoService;

    /**
     * 查询信息列表
     * @param infoVo
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestBody InfoVo infoVo) {
        logger.debug("list ： 参数为 ： " + infoVo.toString());
        PageHelper.startPage(infoVo.getStart(), infoVo.getLimit(), true, false); //设置分页
        Example example = new Example(ScInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus", CommonConstant.USEABLE_STATUS);
        if (null != infoVo.getType()){
            criteria.andEqualTo("type", infoVo.getType());
        }
        example.setOrderByClause(" createTime DESC"); ///创建时间倒叙输出
        List<ScInfo> list = iInfoService.selectByExample(example);
        PageInfo<ScInfo> pageInfo = new PageInfo<>(list);
        Map<String, Object> returnMap = new HashMap<>();
        //设置返回参数
        returnMap.put("total",pageInfo.getPages());
        returnMap.put("list", list);
        return toResponsSuccess(returnMap);
    }

    /**
     * 查询具体信息
     * @param infoVo
     * @return
     */
    @RequestMapping("item")
    public Object item(@RequestBody InfoVo infoVo) {
        logger.debug("item ： 参数为 ： " + infoVo.toString());
        Example example = new Example(ScInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dataStatus",CommonConstant.USEABLE_STATUS);
        if (null != infoVo.getId()){
            criteria.andEqualTo("id", infoVo.getId());
        }
        List<ScInfo> list = iInfoService.selectByExample(example);
        if (list == null || (list != null && list.size() <=0 )){return toResponsFail("查询失败"); }
        return toResponsSuccess(list.get(0));
    }

    /**
     * 发布消息
     * @return
     */
    @RequestMapping("publish")
    public Object publish(@RequestBody ScInfo scInfo) {
        logger.debug("进入publish消息 ： 参数为 ： " + scInfo.toString());
        if (StringUtils.isBlank(scInfo.getAvatar())) {return toResponsFail("发布失败，用户信息不完整");}
        try {
            //scInfo.setAgreeNum(CommonUtil.getRamdomNum(10));
            scInfo.setCreateTime(new Date());
            scInfo.setDataStatus(CommonConstant.USEABLE_STATUS);
            iInfoService.insertSelective(scInfo);
        }catch (Exception e){
            logger.error("publish消息 error ",e);
            return toResponsFail("网络原因,发布失败");
        }
        return toResponsSuccess("发布成功");
    }

    /**
     * 点赞
     * @param scInfo
     * @return
     */
    @RequestMapping("agree")
    public Object agree(@RequestBody ScInfo scInfo) {
        logger.debug("进入agree消息 ： 参数为 ： " + scInfo.toString());
        if (null == scInfo.getId()) {return toResponsFail("点赞失败，参数错误！");}
        try {
            ScInfo si = new ScInfo(scInfo.getId(),CommonConstant.USEABLE_STATUS);
            ScInfo info = iInfoService.selectOne(si);
            if (null != info){
                scInfo.setAgreeNum((null != info.getAgreeNum()) ? info.getAgreeNum().longValue() + 1L : 1L);
                iInfoService.updateByPrimaryKeySelective(scInfo);
            }

        }catch (Exception e){
            logger.error("publish消息 error ",e);
            return toResponsFail("网络原因,点赞失败");
        }
        return toResponsSuccess("点赞成功");
    }

    /**
     * 踩
     * @param scInfo
     * @return
     */
    @RequestMapping("diss")
    public Object diss(@RequestBody ScInfo scInfo) {
        logger.debug("进入diss消息 ： 参数为 ： " + scInfo.toString());
        if (null == scInfo.getId()) {return toResponsFail("踩失败，参数错误！");}
        try {
            ScInfo si = new ScInfo(scInfo.getId(),CommonConstant.USEABLE_STATUS);
            ScInfo info = iInfoService.selectOne(si);
            if (null != info){
                scInfo.setDissNum((null != info.getDissNum()) ? info.getDissNum().longValue() + 1L : 1L);
                iInfoService.updateByPrimaryKeySelective(scInfo);
            }

        }catch (Exception e){
            logger.error("publish消息 error ",e);
            return toResponsFail("网络原因,踩失败");
        }
        return toResponsSuccess("踩成功");
    }

    /**
     * 转发
     * @param scInfo
     * @return
     */
    @RequestMapping("repost")
    public Object repost(@RequestBody ScInfo scInfo) {
        logger.debug("进入repost消息 ： 参数为 ： " + scInfo.toString());
        if (null == scInfo.getId()) {return toResponsFail("转发失败，参数错误！");}
        try {
            ScInfo si = new ScInfo(scInfo.getId(),CommonConstant.USEABLE_STATUS);
            ScInfo info = iInfoService.selectOne(si);
            if (null != info){
                ///增加源数据转发数
                ScInfo info1 = new ScInfo();
                info1.setId(info.getId());
                info1.setRepostNum((null != info.getRepostNum()) ? info.getRepostNum().longValue() + 1L : 1L);
                iInfoService.updateByPrimaryKeySelective(info1);

                ///转发数据
                info.setId(null);
                info.setAvatar(scInfo.getAvatar());
                info.setName(scInfo.getName());
                info.setRepostNum(0L);
                info.setDissNum(0L);
                info.setAgreeNum(0L);
                info.setCommentNum(0L);
                info.setCreateTime(new Date());
                info.setDataStatus(CommonConstant.USEABLE_STATUS);
                iInfoService.insertSelective(info);
            }

        }catch (Exception e){
            logger.error("publish消息 error ",e);
            return toResponsFail("网络原因,踩失败");
        }
        return toResponsSuccess("踩成功");
    }



}
