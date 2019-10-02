package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
public interface IUserService extends IService<User> {
    /**
     * 添加一个用户
     * @param user
     * @return
     */
    boolean insertUser(User user);

    boolean checkUser();

}
