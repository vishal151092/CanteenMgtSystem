package com.tcs.login.dao;

import com.tcs.login.bean.Master;

public interface MasterDao {

	public Master checkLogin(int id,String password);
}
