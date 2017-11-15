package com.muyh.dao;

import com.muyh.model.TUser;

public interface TUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    TUser selectByUsername(String username);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}