package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    public Order create(Integer aid, Integer uid, String username, Integer[] cids) {
        List<CartVO> list = cartService.getVOByCids(uid, cids);
        // 计算商品的总价
        long totalPrice = 0L;
        for (CartVO c : list) {
            totalPrice += c.getRealPrice() * c.getNum();
        }

        Address address = addressService.getByAid(aid, uid);
        Order order=new Order();
        order.setUid(uid);
        // 收货地址数据
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        // 支付、总价、时间
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());

        // 日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());


        // 查询数据
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据异常");
        }

        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : list) {
            // 创建订单商品数据
            OrderItem orderItem = new OrderItem();
            // 补全数据：setOid(order.getOid())
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(cart.getPid());
            orderItem.setTitle(cart.getTitle());
            orderItem.setImage(cart.getImage());
            orderItem.setPrice(cart.getRealPrice());
            orderItem.setNum(cart.getNum());
            // 补全数据：4项日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            // 插入订单商品数据
            rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入订单商品数据时出现异常");
            }
        }
        return order;
    }
}
