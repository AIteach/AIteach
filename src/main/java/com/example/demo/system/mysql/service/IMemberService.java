/**
	时间：2018年3月28日
	地点：
	包名：com.example.demo.system.service
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service;

import java.util.List;

import com.example.demo.system.mysql.entity.Member;

/**
 * @author Administrator
 *
 */
public interface IMemberService {
	public List<Member> findAll();
	public void saveUser(Member member);
	public boolean checkByUserName(String username);
	public Member checkByUserNameAndPassword(String username, String password);
}
