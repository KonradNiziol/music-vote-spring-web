package com.example.music.server.services;

import com.example.music.server.dto.UserCreateDto;
import com.example.music.server.dto.UserDto;
import com.example.music.server.exceptions.AppException;
import com.example.music.server.model.User;
import com.example.music.server.model.tools.Mappers;
import com.example.music.server.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public boolean addUser(UserCreateDto userCreateDto){
        if (userCreateDto == null){
            return false;
        }
        User user = userRepo.save(Mappers.fromUserCreateDtoToUser(userCreateDto));
        return user!=null;
    }

    public List<UserDto> getUsers(){
        return userRepo.findAll().stream()
                .map(Mappers::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElseThrow(()-> new AppException(String.format("User with id: %s don't exist!", id)));
    }

    public void removeUserById(Long id){
        userRepo.deleteById(id);
    }

}
