package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mai.bean.User;
import org.mai.dao.UserDao;
import org.mai.dto.UserDto;
import org.mai.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext.xml","classpath:spring/applicationContext-service.xml"})
public class RegisterTest {
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userSerivce;
	@Test
	public void regTest() {
		User user = new User();
		user.setName("reger");
		user.setPassword("123");
		user.setChName("小红");
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		Boolean result=userSerivce.add(userDto);
		System.out.println(result);
	}
}
