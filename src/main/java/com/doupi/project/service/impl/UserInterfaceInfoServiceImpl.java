package com.doupi.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doupi.douapicommon.model.entity.UserInterfaceInfo;
import com.doupi.project.common.ErrorCode;
import com.doupi.project.exception.BusinessException;
import com.doupi.project.mapper.UserInterfaceInfoMapper;
import com.doupi.project.service.UserInterfaceInfoService;
import org.springframework.stereotype.Service;

/**
* @author doupi
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-05-31 19:12:32
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {
    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userinterfaceInfo, boolean add) {


        if (userinterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        if (add) {
            if (userinterfaceInfo.getInterfaceInfoId() <= 0 || userinterfaceInfo.getUserId() <= 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户不存在");
            }
        }
        // 如果接口名称不为空且长度大于50,抛出参数错误的异常,错误信息为"名称过长"
        // 本期写成<50,(没有解决),第二期视频中解决了
        if (userinterfaceInfo.getLeftNum() < 0 ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于0次");
        }


    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断(其实这里还应该校验存不存在，这里就不用校验了，因为它不存在，也更新不到那条记录)
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 使用 UpdateWrapper 对象来构建更新条件
        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        // 在 updateWrapper 中设置了两个条件：interfaceInfoId 等于给定的 interfaceInfoId 和 userId 等于给定的 userId。
        updateWrapper.eq("interfaceInfoId", interfaceInfoId);
        updateWrapper.eq("userId", userId);
        // setSql 方法用于设置要更新的 SQL 语句。这里通过 SQL 表达式实现了两个字段的更新操作：
        // leftNum=leftNum-1和totalNum=totalNum+1。意思是将leftNum字段减一，totalNum字段加一。
        updateWrapper.setSql("leftNum = leftNum - 1, totalNum = totalNum + 1");
        // 最后，调用update方法执行更新操作，并返回更新是否成功的结果
        return this.update(updateWrapper);
    }

}




