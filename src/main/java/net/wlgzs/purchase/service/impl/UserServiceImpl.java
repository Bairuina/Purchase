package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.wlgzs.purchase.entity.User;
import net.wlgzs.purchase.mapper.UserMapper;
import net.wlgzs.purchase.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean insertUser(User user) {
        return baseMapper.insert(user) == 1;
    }

    @Override
    public boolean checkUser(String userName,String userPhone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(i -> i.eq("user_name",userName).eq("user_phone",userPhone));
        User user = baseMapper.selectOne(queryWrapper);
        return user != null;
    }

}
