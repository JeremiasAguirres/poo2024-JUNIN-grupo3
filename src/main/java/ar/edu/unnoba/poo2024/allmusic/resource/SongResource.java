package ar.edu.unnoba.poo2024.allmusic.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unnoba.poo2024.allmusic.dto.SongRequestDTO;
import ar.edu.unnoba.poo2024.allmusic.dto.SongResponseDTO;
import ar.edu.unnoba.poo2024.allmusic.model.MusicArtistUser;
import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.service.AuthorizationService;
import ar.edu.unnoba.poo2024.allmusic.service.SongService;
import ar.edu.unnoba.poo2024.allmusic.service.UserService;
import ar.edu.unnoba.poo2024.allmusic.util.JwtTokenUtil;




@RestController
@RequestMapping("/songs")
public class SongResource {

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private SongService songService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getSongs(@RequestHeader("Authorization") String token){
        try{
            authorizationService.authorize(token);

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.createTypeMap(Song.class, SongResponseDTO.class)
            .addMapping(src -> src.getAuthor().getUsername(),(dto, v) -> dto.getArtist().setName((String)v))
            .addMapping(src -> src.getAuthor().getId(),(dto, v) -> dto.getArtist().setId((Long)v));

            List<Song> songs = songService.getAll();
            List<SongResponseDTO> dtos = songs.stream()
            .map(song -> modelMapper.map(song, SongResponseDTO.class))
            .collect(Collectors.toList());
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{songId}",produces = "application/json")
    public ResponseEntity<?> getSong(@RequestHeader("Authorization") String token, @PathVariable Long songId){
        try{
            authorizationService.authorize(token);

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.createTypeMap(Song.class, SongResponseDTO.class)
            .addMapping(src -> src.getAuthor().getUsername(),(dto, v) -> dto.getArtist().setName((String)v))
            .addMapping(src -> src.getAuthor().getId(),(dto, v) -> dto.getArtist().setId((Long)v));

            SongResponseDTO dto = modelMapper.map(songService.findById(songId), SongResponseDTO.class);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(params = {"artist", "genre"})
    public ResponseEntity<?> getSongsFilter(@RequestHeader("Authorization") String token,@RequestParam(required = false) String artist ,@RequestParam(required = false) String genre){
        try{
            authorizationService.authorize(token);

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.createTypeMap(Song.class, SongResponseDTO.class)
            .addMapping(src -> src.getAuthor().getUsername(),(dto, v) -> dto.getArtist().setName((String)v))
            .addMapping(src -> src.getAuthor().getId(),(dto, v) -> dto.getArtist().setId((Long)v));

            List<Song> songs = songService.getByArtistOrGenre(artist, genre);
            List<SongResponseDTO> dtos = songs.stream()
            .map(song -> modelMapper.map(song, SongResponseDTO.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<?> createSong(@RequestHeader("Authorization") String token , @RequestBody SongRequestDTO songRequestDTO) {
        try{
            authorizationService.authorize(token);
            MusicArtistUser activeUser = (MusicArtistUser) userService.findByUsername(jwtTokenUtil.getSubject(token));
            if (!activeUser.canCreateSongs()){
                throw new Exception();
            }
            ModelMapper modelMapper = new ModelMapper();
            Song song = modelMapper.map(songRequestDTO, Song.class);
            song.setAuthor(activeUser);
            songService.create(song);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{songId}")
    public ResponseEntity<?> changeSongDetails(@RequestHeader("Authorization") String token, @PathVariable Long songId, @RequestBody SongRequestDTO songDTO) {
        try{
            authorizationService.authorize(token);
            MusicArtistUser activeUser = (MusicArtistUser) userService.findByUsername(jwtTokenUtil.getSubject(token));
            if(!songService.checkOwnership(activeUser, songId)){
                throw new Exception();
            }
            ModelMapper modelMapper = new ModelMapper();
            Song songEdit = modelMapper.map(songDTO, Song.class);
            songService.edit(songEdit, songId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "/{songId}")
    public ResponseEntity<?> removeSong(@RequestHeader("Authorization") String token, @PathVariable Long songId) {
        try{
            authorizationService.authorize(token);
            MusicArtistUser activeUser = (MusicArtistUser) userService.findByUsername(jwtTokenUtil.getSubject(token));
            if(!songService.checkOwnership(activeUser, songId)){
                throw new Exception();
            }
            songService.remove(songId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }
}
