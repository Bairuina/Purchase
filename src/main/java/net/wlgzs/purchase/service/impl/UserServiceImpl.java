package net.wlgzs.purchase.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.wlgzs.purchase.entity.User;
import net.wlgzs.purchase.mapper.UserMapper;
import net.wlgzs.purchase.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.wlgzs.purchase.util.Result;
import net.wlgzs.purchase.util.ResultCode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public Result login(HttpServletRequest request, String userName, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName).or().eq("user_phone", userName);
        User user = baseMapper.selectOne(queryWrapper);
        if (user != null) {
            HttpSession session = request.getSession(true);
            if (user.getUserPassword().equals(password)) {
                //登录成功
                session.setAttribute("user", user);
                System.out.println(user);
                return new Result(ResultCode.SUCCESS, "用户登录成功！");
            } else {
                //密码错误
                return new Result(ResultCode.FAIL, "密码错误！");
            }
        } else {
            //账号不存在
            return new Result(ResultCode.FAIL, "账号不存在！");
        }
    }

    @Override
    public boolean insertUser(User user) {
        //判断是否插入成功
        return baseMapper.insert(user) == 1;
    }

    @Override
    public boolean checkUser(String userName, String userPhone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //查询用户名和手机号
        queryWrapper.and(i -> i.eq("user_name", userName).eq("user_phone", userPhone));
        User user = baseMapper.selectOne(queryWrapper);
        return user != null;
    }

    @Override
    public Result userList(String findName, Integer current, Integer limit) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page page = new Page(current, limit);
        IPage<User> iPage = null;
        //模糊查询用户名，和手机号
        queryWrapper.like("user_name", findName).or().eq("user_phone", findName);
        iPage = baseMapper.selectPage(page, queryWrapper);
        List<User> userList = iPage.getRecords();
        return new Result(ResultCode.SUCCESS, findName, userList, iPage.getPages(), iPage.getCurrent());
    }

    @Override
    public Result deleteUser(Integer userId) {
        User user = baseMapper.selectById(userId);
        if (user != null) {
            baseMapper.deleteById(userId);
            return new Result(ResultCode.SUCCESS, "删除成功！");
        }
        return new Result(ResultCode.FAIL, "删除失败！");
    }

    @Override
    public Result modifyUser(User user) {
        if (user != null) {
            baseMapper.updateById(user);
            return new Result(ResultCode.SUCCESS, "修改成功！");
        }
        return new Result(ResultCode.FAIL, "修改失败！");
    }

}
