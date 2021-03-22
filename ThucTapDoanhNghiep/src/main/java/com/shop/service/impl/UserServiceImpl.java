package com.shop.service.impl;

import com.shop.constant.annotation.Autowire;
import com.shop.constant.annotation.Service;
import com.shop.entity.User;
import com.shop.mapper.UserMapper;
import com.shop.model.request.user.*;
import com.shop.paging.PageRequest;
import com.shop.paging.PageResponse;
import com.shop.repositories.PermissionRepository;
import com.shop.repositories.UserRepository;
import com.shop.repositories.specification.UserSpecification;
import com.shop.service.UserService;
import com.shop.utils.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowire
    private final UserRepository userRepository;
    @Autowire
    private final PermissionRepository permissionRepository;



    public UserServiceImpl(UserRepository userRepository,
                           PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }



    @Override
    public void save(UserSaveRequest userSaveRequest) {

        User user = UserMapper.mapToEntity(userSaveRequest);
        userRepository.save(user);
        User user1 = userRepository.findUserByUserNameAndPassword(userSaveRequest.getUserName(),userSaveRequest.getPassword()).get();
        permissionRepository.save(userSaveRequest.getIds(),user1.getId());

    }


    @Override
    public Optional<User> findByUserNameAndPassword(AuthRequest authRequest) {
        //String password = PasswordHasher.hash(authRequest.getPassword()); // không có mã hóa password nữa
        Optional<User> user = userRepository.findUserByUserNameAndPassword(authRequest.getUserName(),authRequest.getPassword());
        user.orElseThrow(() -> new RuntimeException("User Not found"));
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().collect(Collectors.toList());
    }

    @Override
    public PageResponse<User> filterUser(UserFilterRequest filterRequest) {
        PageRequest pageRequest = PageRequest.of(filterRequest.getPageIndex(), filterRequest.getPageSize());

        return userRepository.findAll(UserSpecification.filterRequest(filterRequest),pageRequest);
    }

    @Override
    public void delete(UserDeleteRequest userDeleteRequest) {
        User user = UserMapper.mapToEntity(userDeleteRequest);

        userRepository.delete((long) user.getId());
    }

    @Override
    public void update(UserUpdateRequest userUpdateRequest) {
        User user = UserMapper.mapToEntity(userUpdateRequest);
        // xóa hết mấy cái permission cũ đi
        permissionRepository.delete(userUpdateRequest.getId());
        // lưu lại cái permission mới vào
        permissionRepository.save(userUpdateRequest.getIds(),userUpdateRequest.getId());
        userRepository.update(userUpdateRequest.getId(),user);

    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }
}
