package com.myc.scholarship.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.myc.scholarship.entity.User;
import com.myc.scholarship.entity.jsonUtil.JsonResultEntity;
import com.myc.scholarship.entity.jsonUtil.JsonResultUtils;
import com.myc.scholarship.entity.token.LogOut;
import com.myc.scholarship.entity.token.TokenData;
import com.myc.scholarship.entity.token.UserInfoToken;
import com.myc.scholarship.entity.token.ReturnToken;
import com.myc.scholarship.mian.entity.PasswordDto;
import com.myc.scholarship.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2018-11-29
 */
@RestController
@RequestMapping("/user")
@Api(value = "管理员测试模块", description =  "测试模块")
public class UserController implements Serializable {

    @Autowired
    private UserService userService;
    protected static String REDIRECT = "redirect:";

    @ApiOperation(value = "登录验证",notes = "登录验证")
    @PostMapping(value = "/login")
    @ResponseBody
    public UserInfoToken login(@RequestParam("num")  String num, @RequestParam("pwd") String pwd){
        Boolean success = false;
        List<User> users = userService.selectList(new EntityWrapper<User>().eq("num",num).eq("pwd",pwd).eq("isDeleted",false));
        if(users!=null && users.size()>0){
            success = true;
        }
        UserInfoToken userInfoToken = new UserInfoToken();
        if(success){
            Map<String,String> map = new HashMap<>();
            userInfoToken.setCode(20000);
            map.put("token",num);
            userInfoToken.setData(map);
        }
        return userInfoToken;
    }

    @ApiOperation(value = "获取token")
    @GetMapping(value = "/info")
    @ResponseBody
    public ReturnToken info(@RequestParam("token")String user){
        ReturnToken returnToken = new ReturnToken();
        TokenData tokenData = new TokenData();
        List<String> list = new ArrayList<>();
        returnToken.setCode(20000);
        list.add(user);
        tokenData.setRoles(list);
        tokenData.setName(user);
        tokenData.setAvatar("http://img.mp.itc.cn/upload/20161221/ed4582188d324eca9811ded71c94808a_th.jpeg");
        //tokenData.setAvatar("http://img.mp.itc.cn/upload/20161221/ed4582188d324eca9811ded71c94808a_th.jpeg");
        returnToken.setData(tokenData);
        return returnToken;
    }

    @ApiOperation(value = "登出")
    @PostMapping(value = "/logout")
    public LogOut logOut(){
        LogOut logOut = new LogOut();
        logOut.setCode(20000);
        logOut.setData("success");
        return logOut;
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping(value = "/get")
    public JsonResultEntity get(@RequestParam("token")String num){
        User user = userService.selectWithNum(num);
        JsonResultEntity resultEntity = JsonResultUtils.success(user);
        return  resultEntity;
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/changePwd")
    public JsonResultEntity changePassword(@RequestBody PasswordDto passwordDto){
        JsonResultEntity resultEntity = new JsonResultEntity();
        User user = userService.selectOne(new EntityWrapper<User>().eq("pwd",passwordDto.getOldPwd()));
        if(user != null){
            user.setPwd(passwordDto.getNewPwd());
            Boolean success = userService.updateById(user);
            if(success){
                String data = "密码修改成功！";
                resultEntity = JsonResultUtils.success(data,success);
            }
        }else {
            String data = "原密码输入错误！";
            resultEntity = JsonResultUtils.success(data,20000,false);
        }

        return resultEntity;
    }
}

