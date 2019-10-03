package net.wlgzs.purchase.service;

import net.wlgzs.purchase.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import net.wlgzs.purchase.util.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-09-28
 */
public interface IUserService extends IService<User> {

    //登录
    Result login(HttpServletRequest request, String userName, String password);


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
    Result userList(String findName, Integer current, Integer limit);

    /**
     * 删除用户
     * @param userId 用户id
     * @return
     */
    Result deleteUser(Integer userId);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Result modifyUser(User user);
}
