package org.mai.service.impl;

import java.util.List;

import org.mai.bean.Member;
import org.mai.cache.CodeCache;
import org.mai.cache.TokenCache;
import org.mai.dao.MemberDao;
import org.mai.service.MemberService;
import org.mai.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDao memberDao;

	private final static Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	/**
	 * 判断手机号是否存在
	 * @param phone 手机号
	 * @return true：存在，false：不存在
	 */
	@Override
	public boolean exists(Long phone) {
		Member member = new Member();
		member.setPhone(phone);
		List<Member>list = memberDao.select(member);
		return list!=null && list.size() ==1;
	}
	
	/**
	 * 保存手机号与对应的验证码的MD5码到缓存中
	 * @param phone 手机号
	 * @param code 验证码
	 * @return 是否保存成功：true：保存成功，false：保存失败
	 */
	@Override
	public boolean saveCode(Long phone, String code) {
		// TODO 在真实环境中，改成借助第三方实现
		CodeCache codeCache = CodeCache.getInstance();
		return codeCache.save(phone, MD5Util.getMD5(code));
	}
	
	/**
	 * 下发短信验证码
	 * @param phone 手机号
	 * @param content 验证码
	 * @return 是否发送成功：true：发送成功，false：发送失败
	 */
	@Override
	public boolean sendCode(Long phone, String content) {
		logger.info(phone+"|"+content);
		return true;
	}
	
	/**
	 * 根据手机号获取验证码的MD5码值
	 * @param phone 手机号
	 * @return 验证码的MD5码值
	 */
	@Override
	public String getCode(Long phone) {
		// TODO 在真实环境中，改成借助第三方实现
		CodeCache codeCache = CodeCache.getInstance();
		return codeCache.getCode(phone);
	}
	
	/**
	 * 保存token与对应的手机号
	 * @param token
	 * @param phone 手机号
	 */
	@Override
	public void saveToken(String token, Long phone) {
		TokenCache tokenCache = TokenCache.getInstance();
		tokenCache.save(token, phone);
	}

	/**
	 * 根据token获取用户信息(手机号)
	 * @param token
	 * @return 手机号
	 */
	@Override
	public Long getPhone(String token) {
		TokenCache tokenCache = TokenCache.getInstance();
		return tokenCache.getPhone(token);
	}
	
	/**
	 * 根据手机号获取会员表主键
	 * @param phone 手机号
	 * @return 会员表主键
	 */
	@Override
	public Long getIdByPhone(Long phone) {
		Member member = new Member();
		member.setPhone(phone);
		List<Member> list = memberDao.select(member);
		if (list != null && list.size() == 1) {
			return list.get(0).getId();
		}
		return null;
	}

}
