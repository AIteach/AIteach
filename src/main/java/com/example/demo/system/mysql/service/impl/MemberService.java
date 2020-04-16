
package com.example.demo.system.mysql.service.impl;

import com.example.demo.system.mysql.dao.MenberJpaRepository;
import com.example.demo.system.mysql.entity.Member;
import com.example.demo.system.mysql.service.IMemberService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Administrator
 */
@Service("memberService")
public class MemberService implements IMemberService {
    @Resource
    private HttpSession httpsession;
    @Resource
    private MenberJpaRepository memberJpaRepository;


    @Override
    public List<Member> findAll() {
        // TODO Auto-generated method stub
        return memberJpaRepository.findAll();
    }


    @Modifying
    @Transactional
    @Override
    public void saveUser(Member member) {
        this.memberJpaRepository.save(member);
        // TODO Auto-generated method stub

    }

    @Override
    public Member checkByUserNameAndPassword(String username, String password) {
        // TODO Auto-generated method stub
        return this.memberJpaRepository.findByUsernameAndPassword(username, password);

    }

    @Override
    public Member findById(int memberId) {
        return memberJpaRepository.findById(memberId).get();
    }

    @Override
    public boolean checkByUserName(String username) {
        // TODO Auto-generated method stub
        if (this.memberJpaRepository.findByUsername(username).size() == 0) {
            return true;
        }
        return false;
    }

}
