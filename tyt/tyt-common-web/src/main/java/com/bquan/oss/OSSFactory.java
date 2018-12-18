package com.bquan.oss;

import com.bquan.service.read.SysConfigReadService;
import com.bquan.util.ConfigConstant;
import com.bquan.util.Constant;
import com.bquan.util.SpringContextUtils;

/**
 * 文件上传Factory
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 10:18
 */
public final class OSSFactory {
	
    private static SysConfigReadService sysConfigReadService;

    static {
        OSSFactory.sysConfigReadService = (SysConfigReadService) SpringContextUtils.getBean("sysConfigReadService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigReadService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
