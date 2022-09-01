package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    // 购物车的业务层依赖于购物车的持久层以及商品的持久层
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;

    @Override
    public void addToCart(Integer uid,
                          Integer pid,
                          Integer amount,
                          String username) {
        // 查询当前要添加的这个购物车是否在表中已经存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if (result == null) {
            // 表示这个商品从来没有被添加到购物车中，则进行新增操作
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 价格来源于商品中的数据
            Product product = productService.findById(pid);
            cart.setPrice(product.getPrice());
            // 补全日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());

            // 执行输入的插入操作
            Integer rows = cartMapper.insert(cart);
            if(rows!=1){
                throw new InsertException("插入时产生未知的异常");
            }
        } else {
            // 表示当前的商品在购物车中已经存在，则更新num值
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, new Date());
            if (rows != 1) {
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        int num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据失败");
        }
        // 返回心得购物车数据的总量
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart=it.next();
            if (!cart.getUid().equals(uid)) {
                // 当前的数据不属于当前用户
                // 从集合中移除这个元素
                list.remove(cart);
            }
        }
        return list;
    }
}
