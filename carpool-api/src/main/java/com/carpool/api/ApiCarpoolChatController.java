package com.carpool.api;

import com.carpool.constants.CommonConstant;
import com.carpool.entity.CarpoolChat;
import com.carpool.service.CarpoolChatService;
import com.carpool.util.ApiBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 拼车聊天控制器
 *
 * @author bjsonghongxu
 * @create 2018-04-10 11:16
 **/
@RestController
@RequestMapping("/api/car/chat")
public class ApiCarpoolChatController extends ApiBaseAction {


    @Autowired
    private CarpoolChatService carpoolChatService;


    /**
     * 查找聊天内容
     * @param carpoolChat
     * @return
     */
    @RequestMapping("queryChats")
    public Object queryChats(@RequestBody CarpoolChat carpoolChat ) {

        if (null == carpoolChat.getPublishId()){
            return  toResponsFail("参数错误，查询失败");
        }
        carpoolChat.setDataStatus(CommonConstant.USEABLE_STATUS);
        List<CarpoolChat> chats = carpoolChatService.select(carpoolChat);
        return toResponsSuccess(chats);
    }


    /**
     * 发送聊天消息
     * @param carpoolChat
     * @return
     */
    @RequestMapping("send")
    public Object send(@RequestBody CarpoolChat carpoolChat ) {

        if (null == carpoolChat.getPublishId()){
            return  toResponsFail("参数错误");
        }
        carpoolChat.setDataStatus(CommonConstant.USEABLE_STATUS);
        carpoolChat.setCreateTime(new Date());
        carpoolChat.setMessageType(0); // 普通消息

        try {
            carpoolChatService.insertSelective(carpoolChat);
            return toResponsSuccess("发送成功");
        }catch (Exception e){
            return toResponsSuccess("发送失败");
        }

    }

}
