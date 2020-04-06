/**
	时间：2018年3月28日
	地点：
	包名：com.example.demo.system.service.impl
	项目名：grap
 * 
 */
package com.example.demo.system.mysql.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.example.demo.system.mysql.dao.MenberJpaRepository;
import com.example.demo.system.mysql.entity.Member;
import com.example.demo.system.mysql.service.IMemberService;

/**
 * @author Administrator
 *
 */
@Service("memberService")
public class MemberService implements IMemberService {
	@Resource
	private HttpSession httpsession;
	@Resource 
	private MenberJpaRepository memberJpaRepository;
	/* (non-Javadoc)
	 * @see com.example.demo.system.service.IMenberService#findAll()
	 */
	
	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return memberJpaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.example.demo.system.service.IMenberService#saveUser(com.example.demo.system.entity.Member)
	 */
	@Modifying
	@Transactional
	@Override
	public void saveUser(Member member) {
		this.memberJpaRepository.save(member);
		// TODO Auto-generated method stub

	}

	@Override
	public Member checkByUserNameAndPassword(String username,String password) {
		// TODO Auto-generated method stub
			//System.out.println(this.memberJpaRepository.findByUsernameAndPassword(username,password).toString());
			return this.memberJpaRepository.findByUsernameAndPassword(username,password);
		
	}

	@Override
	public boolean checkByUserName(String username) {
		// TODO Auto-generated method stub
		if(this.memberJpaRepository.findByUsername(username).size()==0) return true;
		return false;
	}

}
