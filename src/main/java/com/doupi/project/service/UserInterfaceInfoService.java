package com.doupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.doupi.project.model.entity.UserInterfaceInfo;


/**
* @author doupi
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-05-31 19:12:32
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userinterfaceInfo, boolean add);

    boolean invokeCount(long interfaceInfoId, long userId);
}
