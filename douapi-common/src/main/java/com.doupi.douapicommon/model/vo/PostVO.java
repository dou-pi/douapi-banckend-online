package com.doupi.douapicommon.model.vo;

import com.doupi.douapicommon.model.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 帖子视图
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostVO extends Post {

    /**
     * 是否已点赞
     */
    private Boolean hasThumb;

    private static final long serialVersionUID = 1L;
}