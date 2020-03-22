package com.wcq.tang.service.admin.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wcq.tang.dto.UserMangerDto;
import com.wcq.tang.mapper.CorpusMapper;
import com.wcq.tang.mapper.OriginalMapper;
import com.wcq.tang.mapper.UserMapper;
import com.wcq.tang.model.CorpusExample;
import com.wcq.tang.model.OriginalExample;
import com.wcq.tang.model.User;
import com.wcq.tang.model.UserExample;
import com.wcq.tang.service.admin.UserMangerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/1 13:53
 */
@Service
public class UserMangerServiceImpl implements UserMangerService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OriginalMapper originalMapper;
    @Autowired
    private CorpusMapper corpusMapper;

    public UserMangerDto setOtherName(UserMangerDto dto){
        if(dto.getRole()){
            dto.setRoleName("管理员");
        }else{
            dto.setRoleName("普通用户");
        }
        if(dto.getUnuse()){
            dto.setUnuseName("账号封停");
        }else{
            dto.setUnuseName("账号正常");
        }
        return dto;
    }
    @Override
    public UserMangerDto getUserMangerDtoById(Long id) {
        UserMangerDto dto = new UserMangerDto();
        User user = userMapper.selectByPrimaryKey(id);
        if(user != null){
            BeanUtils.copyProperties(user,dto);
            dto.setOriginalItems(getUserOriginalItems(id));
            dto.setCorpusItems(getUserCorpusItems(id));
            return setOtherName(dto);
        }else{
            return null;
        }

    }

    @Override
    public Page<User> getUserMangerDtoByName(String username,Integer page, Integer limit) {
        Page<User> userPage = PageHelper.startPage(page, limit);
        //查询user
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria2 = userExample.createCriteria();
        criteria2.andUserNameEqualTo(username);
        userMapper.selectByExample(userExample);
        //查找到返回，否则就返回null
        return userPage;
    }

    private List<UserMangerDto> getUserMangerDtos(List<UserMangerDto> userMangerDtoList, List<User> users) {
        if(users.size()>0 && users.get(0)!=null) {
            for (User u:users
                 ) {
                UserMangerDto dto =new UserMangerDto();
                BeanUtils.copyProperties(u,dto);
                dto.setOriginalItems(getUserOriginalItems(u.getUserId()));
                dto.setCorpusItems(getUserCorpusItems(u.getUserId()));
                userMangerDtoList.add(setOtherName(dto));
            }
            return userMangerDtoList;
        } else{
            return null;
        }
    }

    private Long getUserCorpusItems(Long userId) {
        CorpusExample corpusExample = new CorpusExample();
        CorpusExample.Criteria criteria1 = corpusExample.createCriteria();
        criteria1.andUploaderEqualTo(userId);
        return corpusMapper.countByExample(corpusExample);
    }

    private Long getUserOriginalItems(Long userId) {
        OriginalExample originalExample = new OriginalExample();
        OriginalExample.Criteria criteria = originalExample.createCriteria();
        criteria.andUploaderEqualTo(userId);
        return originalMapper.countByExample(originalExample);
    }

    @Override
    public UserMangerDto getUserMangerDtoByEmail(String email) {
        //构建传输对象
        UserMangerDto dto = new UserMangerDto();
        //查询user
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria2 = userExample.createCriteria();
        criteria2.andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        User user;
        //查找到返回，否则就返回null
        if(users.size()>0 && users.get(0)!=null) {
            user = users.get(0);
            BeanUtils.copyProperties(user,dto);
            dto.setOriginalItems(getUserOriginalItems(user.getUserId()));
            dto.setCorpusItems(getUserCorpusItems(user.getUserId()));
            return setOtherName(dto);
        } else{
            return null;
        }
    }

    @Override
    public Page<User> getDefaultUser(Integer page, Integer limit) {
        Page<User> userPage = PageHelper.startPage(page, limit);
        userMapper.selectByExample(new UserExample());
        return userPage;
    }
    @Override
    public List<UserMangerDto> userListToUMD(Page<User> userPage){
        List<UserMangerDto> dtoList = new LinkedList<>();
        List<User> result = userPage.getResult();
        for (User user :result) {
            UserMangerDto dto = new UserMangerDto();
            BeanUtils.copyProperties(user,dto);
            dto.setOriginalItems(getUserOriginalItems(user.getUserId()));
            dto.setCorpusItems(getUserCorpusItems(user.getUserId()));
            dto=setOtherName(dto);
            dtoList.add(dto);
        }
        return dtoList;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        if(user == null){
            return 0;
        }else {
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }
}
