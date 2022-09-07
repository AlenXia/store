package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid          购物车数据id
     * @param num          更新的数量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(Integer cid,
                           Integer num,
                           String modifiedUser,
                           Date modifiedTime);

    /**
     * 根据用户的id和商品的id来查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 购物车数据
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    /**
     * 根据用户的id查询购物车记录
     * @param uid 用户id
     * @return CartVO
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 根据cid查找Cart
     * @param cid 购物车id
     * @return 购物车商品
     */
    Cart findByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> findVOByCids(Integer[] cids);
}
