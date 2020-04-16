/**
 * 时间：2018年3月28日
 * 地点：
 * 包名：com.example.demo.system.service
 * 项目名：grap
 */
package com.example.demo.system.mysql.service;

import com.example.demo.system.mysql.entity.Member;

import java.util.List;

/**
 * @author Administrator
 *
 */
public interface IMemberService {
    public List<Member> findAll();

    public void saveUser(Member member);

    public boolean checkByUserName(String username);

    public Member checkByUserNameAndPassword(String username, String password);

    Member findById(int memberId);
}
