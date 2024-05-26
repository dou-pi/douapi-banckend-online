package com.doupi.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doupi.project.model.entity.InterfaceInfo;

/**
* @author doupi
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-05-21 21:26:42
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
