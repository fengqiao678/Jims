package org.mo.open.common.security;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mo.open.common.entity.Role;
import org.mo.open.common.entity.User;
import org.mo.open.common.repository.RoleRepository;
import org.mo.open.common.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	protected static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

	private RoleRepository roleRepository;
	
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		String newStr = null;
		try {
			newStr = new String(account.getBytes("iso8859-1"), "UTF-8");
			//TODO:显示登录用户名字
			System.out.println("显示登录用户名字"+newStr);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		UserDetails userDetails = null;
		try {
			User selcetByAccount = userRepository.selectByPK(newStr);
			if (selcetByAccount == null) {
				throw new UsernameNotFoundException("该" + account + "不存在");
			}
			userDetails = new Authentication(selcetByAccount,selcetByAccount.getAccount(),
					selcetByAccount.getPassword(), true, true, true, true,grantAuthorities(selcetByAccount));
		} catch (UsernameNotFoundException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("错误在检索用户");
			throw new UsernameNotFoundException("错误在检索用户");
		}
		return userDetails;
	}
	
	private List<GrantedAuthority> grantAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> roles = roleRepository.selectByUser(user.getAccount());
		for (Role permission : roles) {
			authorities.add(new SimpleGrantedAuthority(permission.getName()));
		}
		return authorities;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	@Resource(name = "roleRepository")
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	@Resource(name = "userRepository")
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
