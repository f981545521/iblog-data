package cn.acyou.iblogdata.security;

import cn.acyou.iblogdata.entity.MemberInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author youfang
 * @version [1.0.0, 2018-9-6 下午 09:37]
 **/
@Slf4j
@Component
public class MemberInfoService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if(username.equals("admin")) {
            //假设返回的用户信息如下;
            MemberInfo userInfo = new MemberInfo("admin", "admin123", "ROLE_ADMIN", true,true,true, true);
            return userInfo;

        }        //这里可以通过数据库来查找到实际的用户信息，这里我们先模拟下,后续我们用数据库来实现
        if(username.equals("user")) {
            //假设返回的用户信息如下;
            MemberInfo userInfo = new MemberInfo("user", "user123", "ROLE_USER", true,true,true, true);
            return userInfo;

        }
        return null;
    }
}
