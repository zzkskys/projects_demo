package com.example.mybatisplus.application;

import com.example.mybatisplus.domain.Role;
import com.example.mybatisplus.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    public void testAddUser3() {
        when(this.userRepository.insert(any())).thenReturn(1);
        when(userRepository.existsByName("Name")).thenReturn(false);
        when(userRepository.existsByName("a")).thenReturn(true);

        AddUserCommand addUserCommand = new AddUserCommand();
        addUserCommand.setRole(Role.USER);
        addUserCommand.setName("Name");
        this.userService.addUser(addUserCommand);
        verify(this.userRepository).existsByName(any());
        verify(this.userRepository).insert(any());

        assertThrows(RuntimeException.class, () -> userService.addUser(new AddUserCommand().setName("a")));

    }

}

