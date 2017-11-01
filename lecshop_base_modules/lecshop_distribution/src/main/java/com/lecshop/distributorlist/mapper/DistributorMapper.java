package com.lecshop.distributorlist.mapper;

import com.lecshop.distributorlist.bean.Distributor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 分销商mapper
 *
 * @author sunluyang on 2017/5/25.
 */
@Repository
public interface DistributorMapper {

    /**
     * 查询该店铺下分销商总条数
     *
     * @param map 查询参数
     * @return 总条数
     */
    int queryDistributorCount(Map<String, Object> map);

    /**
     * 根据所属店铺id查询所有分销商
     *
     * @param map 查询参数
     * @return 分销商集合
     */
    List<Distributor> queryDistributor(Map<String, Object> map);

    /**
     * 根据会员id查询该会员下是否有下级分销商
     *
     * @param customerId 会员id
     * @return 分销商集合
     */
    List<Distributor> queryDistributorBySuperiorId(long customerId);

    /**
     * 根据会员id删除分销商(将分销商变成普通会员)
     *
     * @param customerId 会员id
     * @return 更新返回码
     */
    int deleteDistributor(long customerId);

    /**
     * 更新分销商信息
     *
     * @param distributor 分销商实体类
     * @return 更新返回码
     */
    int updateDistributor(Distributor distributor);
}
