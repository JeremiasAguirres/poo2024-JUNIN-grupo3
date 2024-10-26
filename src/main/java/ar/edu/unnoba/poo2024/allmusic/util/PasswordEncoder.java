package ar.edu.unnoba.poo2024.allmusic.util;

import com.password4j.Hash;
import com.password4j.Password;

public class PasswordEncoder {
    public Hash encode(String rawPassword){
        return Password.hash(rawPassword).withBcrypt();
    }
    public boolean verify(String rawPassword,Hash encodedPassword){
        return this.encode(rawPassword) == encodedPassword;
    }
}
