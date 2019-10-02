package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;

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

    /**
     * 检查用户是否存在
     * @param userName 用户名
     * @param userPhone 手机号
     * @return
     */
    boolean checkUser(String userName, String userPhone);

    /**
     * 查询所有用户
     * @param findName
     * @param current
     * @param limit
     * @return
     */
    Result UserList(String findName, Integer current, Integer limit);
}
