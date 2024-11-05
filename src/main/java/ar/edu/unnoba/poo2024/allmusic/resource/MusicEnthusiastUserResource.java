package ar.edu.unnoba.poo2024.allmusic.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unnoba.poo2024.allmusic.dto.AuthenticationRequestDTO;
import ar.edu.unnoba.poo2024.allmusic.dto.CreateUserRequestDTO;
import ar.edu.unnoba.poo2024.allmusic.model.MusicArtistUser;
import ar.edu.unnoba.poo2024.allmusic.model.MusicEnthusiastUser;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.service.AuthenticationService;
import ar.edu.unnoba.poo2024.allmusic.service.AuthenticationServiceImp;
import ar.edu.unnoba.poo2024.allmusic.service.UserService;

@RestController
@RequestMapping("/enthusiast")
public class MusicEnthusiastUserResource {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserRequestDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDTO, MusicEnthusiastUser.class);
        try{
            service.create(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }

    @PostMapping(path="/auth",produces = "application/json")
    public ResponseEntity<?> authentication(@RequestBody AuthenticationRequestDTO authenticationRequestDTO){
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(authenticationRequestDTO,MusicEnthusiastUser.class);
        try{
            String token = authenticationService.authenticate(user);
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("token",token);
            return new ResponseEntity<>(token,null,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
    }

}
