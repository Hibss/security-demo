package com.czkj.security.service;

import com.czkj.security.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserMapper userMapper;

	Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("===============用户名："+username);

		com.czkj.security.entity.User user = userMapper.findByMobile(username);
		
		if(user == null){
			throw new UsernameNotFoundException("用户[" + username + "]不存在");
		}

		//获取菜单权限
		String[] roles = user.getRole().split(",");
		List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(roles);
		log.info("authorityList:{}",authorityList);
		return new User(username, user.getPassword(), true, true, true, true,authorityList);


	}

}
