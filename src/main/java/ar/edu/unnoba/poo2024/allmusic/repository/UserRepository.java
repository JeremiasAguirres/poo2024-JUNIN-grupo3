package ar.edu.unnoba.poo2024.allmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unnoba.poo2024.allmusic.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    //Cualquiera de las siguientes anotaciones se puede usar
    //@Query(value = "SELECT u FROM User u WHERE u.username = ?1") <--- Esta usa la especificacion de JPA.
    //                                                                  Donde 'u' es el nombre del objeto,
    //                                                                  'User u' es la tabla que guarda los objetos de la clase User,
    //                                                                  'u.username' es el atributo a comparar del objeto y '?1'
    //                                                                  es referencia al valor ingresado en el metodo.
    //
    //@Query(nativeQuery = true, value = "SELECT * FROM users WHERE username = ?1") <--- Esta es nativa de sql

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE username = ?1")
    public User findByUsername(@Param("username") String username);
}
