package ar.edu.unnoba.poo2024.allmusic.service;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.util.JwtTokenUtil;
import ar.edu.unnoba.poo2024.allmusic.util.PasswordEncoder;

public class AuthenticationServiceImp implements AuthenticationService{

    @Autowired
    private UserServiceImp userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String authenticate(User user) throws Exception {
        User userDB = userService.findByUsername(user.getUsername());
        if(userDB == null){
            throw new Exception();
        }
        else if(!passwordEncoder.verify(user.getPassword(), userDB.getPassword())){
            throw new Exception();
        }
        return jwtTokenUtil.generateToken(user.getUsername());

    }

}
