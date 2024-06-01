package com.doupi.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doupi.project.annotation.AuthCheck;
import com.doupi.project.common.BaseResponse;
import com.doupi.project.common.DeleteRequest;
import com.doupi.project.common.ErrorCode;
import com.doupi.project.common.ResultUtils;
import com.doupi.project.constant.CommonConstant;
import com.doupi.project.constant.UserConstant;
import com.doupi.project.exception.BusinessException;
import com.doupi.project.model.dto.userinterfaceinfo.UserInterfaceInfoAddRequest;
import com.doupi.project.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.doupi.project.model.dto.userinterfaceinfo.UserInterfaceInfoUpdateRequest;
import com.doupi.project.model.entity.User;
import com.doupi.project.model.entity.UserInterfaceInfo;
import com.doupi.project.service.UserInterfaceInfoService;
import com.doupi.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口管理
 *
 * @author doupi
 */
@RestController
@RequestMapping("/UserinterfaceInfo")
@Slf4j
public class UserUserInterfaceInfoController {

    @Resource
    private UserInterfaceInfoService UserinterfaceInfoService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param UserinterfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addUserInterfaceInfo(@RequestBody UserInterfaceInfoAddRequest UserinterfaceInfoAddRequest, HttpServletRequest request) {
        if (UserinterfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo UserinterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(UserinterfaceInfoAddRequest, UserinterfaceInfo);
        // 校验
        UserinterfaceInfoService.validUserInterfaceInfo(UserinterfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        UserinterfaceInfo.setUserId(loginUser.getId());
        boolean result = UserinterfaceInfoService.save(UserinterfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newUserInterfaceInfoId = UserinterfaceInfo.getId();
        return ResultUtils.success(newUserInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = UserinterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = UserinterfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param UserinterfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateUserInterfaceInfo(@RequestBody UserInterfaceInfoUpdateRequest UserinterfaceInfoUpdateRequest,
                                                         HttpServletRequest request) {
        if (UserinterfaceInfoUpdateRequest == null || UserinterfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo UserinterfaceInfo = new UserInterfaceInfo();
        BeanUtils.copyProperties(UserinterfaceInfoUpdateRequest, UserinterfaceInfo);
        // 参数校验
        UserinterfaceInfoService.validUserInterfaceInfo(UserinterfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = UserinterfaceInfoUpdateRequest.getId();
        // 判断是否存在
        UserInterfaceInfo oldUserInterfaceInfo = UserinterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = UserinterfaceInfoService.updateById(UserinterfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<UserInterfaceInfo> getUserInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo UserinterfaceInfo = UserinterfaceInfoService.getById(id);
        return ResultUtils.success(UserinterfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param UserinterfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/list")
    public BaseResponse<List<UserInterfaceInfo>> listUserInterfaceInfo(UserInterfaceInfoQueryRequest UserinterfaceInfoQueryRequest) {
        UserInterfaceInfo UserinterfaceInfoQuery = new UserInterfaceInfo();
        if (UserinterfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(UserinterfaceInfoQueryRequest, UserinterfaceInfoQuery);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(UserinterfaceInfoQuery);
        List<UserInterfaceInfo> UserinterfaceInfoList = UserinterfaceInfoService.list(queryWrapper);
        return ResultUtils.success(UserinterfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param UserinterfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<UserInterfaceInfo>> listUserInterfaceInfoByPage(UserInterfaceInfoQueryRequest UserinterfaceInfoQueryRequest, HttpServletRequest request) {
        if (UserinterfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserInterfaceInfo UserinterfaceInfoQuery = new UserInterfaceInfo();
        BeanUtils.copyProperties(UserinterfaceInfoQueryRequest, UserinterfaceInfoQuery);
        long current = UserinterfaceInfoQueryRequest.getCurrent();
        long size = UserinterfaceInfoQueryRequest.getPageSize();
        String sortField = UserinterfaceInfoQueryRequest.getSortField();
        String sortOrder = UserinterfaceInfoQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>(UserinterfaceInfoQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<UserInterfaceInfo> UserinterfaceInfoPage = UserinterfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(UserinterfaceInfoPage);
    }



}
