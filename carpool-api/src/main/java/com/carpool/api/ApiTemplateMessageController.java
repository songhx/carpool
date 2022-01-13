package com.carpool.api;

import com.carpool.entity.CarpoolUserFormid;
import com.carpool.service.CarpoolUserFormidService;
import com.carpool.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 模板消息处理类
 *
 * @author bjsonghongxu
 * @create 2018-02-28 11:20
 **/
@RestController
@RequestMapping("/api/templateMessage")
public class ApiTemplateMessageController extends ApiBaseAction {


    @Autowired
    private CarpoolUserFormidService carpoolUserFormidService;

    /**
     * 收集拼车form_id
     * @param carpoolUserFormid
     * @return
     */
    @RequestMapping("collect")
    public Object collectCarpoolFormId(@RequestBody CarpoolUserFormid carpoolUserFormid) {

        if (null == carpoolUserFormid.getUserId()){
            return  toResponsFail("用户在系统中不存在，请先登录！");
        }
        carpoolUserFormid.setCreateTime(new Date());
        carpoolUserFormidService.insertSelective(carpoolUserFormid);
        return toResponsSuccess("保存成功");
    }


    @RequestMapping("send")
    public Object send() {


        return toResponsSuccess("保存成功");
    }



}
