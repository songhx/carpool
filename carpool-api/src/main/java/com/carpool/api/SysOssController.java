package com.carpool.api;

import com.carpool.entity.SysOssEntity;
import com.carpool.service.SysOssService;
import com.carpool.util.ApiBaseAction;
import com.carpool.utils.*;
import com.carpool.oss.OSSFactory;
import com.carpool.utils.*;
import com.carpool.utils.upload.UploadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文件上传
 *
 * @author bjsonghognxu
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("api/oss")
public class SysOssController extends ApiBaseAction {
    @Autowired
    private SysOssService sysOssService;


    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;



    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        //上传文件
        String url = "https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png"; //OSSFactory.build().upload(file);
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("url",url);
        return toResponsSuccess(returnMap)  ;
    }

    /**
     * 上传文件
     */
    @RequestMapping("/uploadFtp")
    public R uploadFtp(HttpServletRequest request) throws Exception {
        R r = new R();
        //设置字符编码防止乱码
        request.setCharacterEncoding("UTF-8");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        String carpoolCode = multipartRequest.getParameter("carpoolCode");
        String dirFolderName = multipartRequest.getParameter("dirFolderName");
        List<UploadVo> uploadFileInfos  = null;
        String url = "";
        if (fileList.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }
        try {
            uploadFileInfos  = FtpUpload.upload(fileList,carpoolCode,dirFolderName, PropertiesUtil.getInstance("/upload.properties"));
            if (uploadFileInfos != null && uploadFileInfos.size() > 0){
                url = uploadFileInfos.get(0).getFileServerPath();
            }
            //保存文件信息
            SysOssEntity ossEntity = new SysOssEntity();
            ossEntity.setUrl(url);
            ossEntity.setCreateDate(new Date());
            sysOssService.save(ossEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        r.put("url", url);
        r.put("link", url);
        return r;
    }


}
