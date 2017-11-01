package com.lecshop.friendlink.service.impl;

import com.lecshop.friendlink.bean.FriendLink;
import com.lecshop.friendlink.mapper.FriendLinkMapper;
import com.lecshop.friendlink.service.FriendLinkService;
import com.lecshop.util.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 友情链接service实现类
 *
 * Created by LecShop on 2017/5/19.
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    /**
     * 注入友情链接数据库接口
     */
    @Autowired
    private FriendLinkMapper friendLinkMapper;

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(FriendLinkServiceImpl.class);

    @Override
    public PageHelper<FriendLink> queryFriendLinks(PageHelper<FriendLink> pageHelper, String name, String url) {
        logger.debug("queryFriendLinks and pageHelper :{} \r\n name : {} \r\n url : {}", pageHelper, name, url);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("url", url);
        return pageHelper.setListDates(friendLinkMapper.queryFriendLinks(pageHelper.getQueryParams(params, friendLinkMapper.queryFriendLinksCount(params))));
    }

    @Override
    public FriendLink queryFriendLinkById(long id) {
        logger.debug("queryFriendLinkById and id {}", id);
        return friendLinkMapper.queryFriendLinkById(id);
    }

    @Override
    public int updateFriendLink(FriendLink friendLink) {
        logger.debug("updateFriendLink and friendLink {}", friendLink);
        return friendLinkMapper.updateFriendLink(friendLink);
    }

    @Override
    public int addFriendLink(FriendLink friendLink) {
        if (Objects.isNull(friendLink)) {
            logger.error("addFriendLink fail due to friendlink is null...");
            return 0;
        }
        logger.debug("addFriendLink and friendlink {}", friendLink);
        return friendLinkMapper.addFriendLink(friendLink);
    }

    @Override
    public int deleteFriendLink(long id) {
        logger.debug("deleteFriendLink and id {}", id);
        return friendLinkMapper.deleteFriendLink(id);
    }

    @Transactional
    @Override
    public int batchDeleteFriendLinks(long [] ids) {
        logger.debug("batchDeleteBrands and ids : {}", ids);
        if (ArrayUtils.isEmpty(ids)) {
            logger.error("batchDeleteBrands fail due to ids is null...");
            return 0;
        }
        return friendLinkMapper.batchDeleteFriendLinks(ids);
    }
}
