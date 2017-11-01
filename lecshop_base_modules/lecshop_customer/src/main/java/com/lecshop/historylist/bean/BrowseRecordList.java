package com.lecshop.historylist.bean;

import lombok.Data;

import java.util.List;

/**
 * 浏览历史列表
 *
 * Created by LecShop on 2017/7/4.
 */
@Data
public class BrowseRecordList implements Comparable<BrowseRecordList> {

    /**
     * 浏览时间
     */
    private String time;

    /**
     * 某一天的浏览历史列表
     */
    private List<BrowseRecord> browseRecordList;

    /**
     * 将某一天的浏览历史按时间排序
     *
     * @param browseRecordList 浏览历史列表
     * @return 相等返回0，小于返回<0，大于返回>0
     */
    @Override
    public int compareTo(BrowseRecordList browseRecordList) {
        return this.getTime().compareTo(browseRecordList.getTime());
    }

}
