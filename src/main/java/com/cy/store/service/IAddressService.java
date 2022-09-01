package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

// 收货地址业务层接口
public interface IAddressService {

    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username);

    void delete(Integer aid, Integer uid, String username);

    Address getByAid(Integer aid,Integer uid);
}
