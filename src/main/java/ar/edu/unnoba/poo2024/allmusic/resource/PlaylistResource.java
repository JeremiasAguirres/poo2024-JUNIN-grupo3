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
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unnoba.poo2024.allmusic.dto.PlaylistRequestDTO;
import ar.edu.unnoba.poo2024.allmusic.dto.PlaylistResponseDTO;
import ar.edu.unnoba.poo2024.allmusic.dto.PlaylistResponseLiteDTO;
import ar.edu.unnoba.poo2024.allmusic.dto.SongResponseDTO;
import ar.edu.unnoba.poo2024.allmusic.model.Playlist;
import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.service.AuthorizationService;
import ar.edu.unnoba.poo2024.allmusic.service.PlaylistService;
import ar.edu.unnoba.poo2024.allmusic.service.UserService;
import ar.edu.unnoba.poo2024.allmusic.util.JwtTokenUtil;

@RestController
@RequestMapping("/playlists")
public class PlaylistResource {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getPlaylists(@RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);

            ModelMapper modelMapper = new ModelMapper();

            List<Playlist> playlists = playlistService.getAllPlaylists();
            List<PlaylistResponseLiteDTO> dtos = playlists.stream()
                    .map(playlist -> modelMapper.map(playlist, PlaylistResponseLiteDTO.class))
                    .collect(Collectors.toList());

            for(PlaylistResponseLiteDTO dto : dtos){
                dto.setCount();
            }

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{playlistID}", produces = "application/json")
    public ResponseEntity<?> getPlaylistById(@RequestHeader("Authorization") String token, @PathVariable("playlistID") Long playlistID) {
        try {
            authorizationService.authorize(token);

            Playlist playlist = playlistService.getPlaylistDetailsById(playlistID);
            ModelMapper modelMapper = new ModelMapper();

            modelMapper.createTypeMap(Song.class, SongResponseDTO.class)
            .addMapping(src -> src.getAuthor().getUsername(),(dto, v) -> dto.getArtist().setName((String)v))
            .addMapping(src -> src.getAuthor().getId(),(dto, v) -> dto.getArtist().setId((Long)v));

            PlaylistResponseDTO responseDTO = new PlaylistResponseDTO();
            responseDTO = modelMapper.map(playlist, PlaylistResponseDTO.class);


            return new ResponseEntity<>(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createPlaylist(@RequestHeader("Authorization") String token, @RequestBody PlaylistRequestDTO playlistRequestDTO) {
        try{
            authorizationService.authorize(token);
            User activeUser = userService.findByUsername(jwtTokenUtil.getSubject(token));

            ModelMapper modelMapper = new ModelMapper();
            Playlist playlist = modelMapper.map(playlistRequestDTO, Playlist.class);
            playlist.setOwner(activeUser);
            playlistService.createPlaylist(playlist);

            return new ResponseEntity<>(null, HttpStatus.OK);

        }   catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping(value = "/{playlistID}")
    public ResponseEntity<?> changePlaylistDetails(@RequestHeader("Authorization") String token, @PathVariable Long playlistID, @RequestBody PlaylistRequestDTO playlistRequestDTO){
        try{
            authorizationService.authorize(token);
            User activeUser = userService.findByUsername(jwtTokenUtil.getSubject(token));

            if (!playlistService.checkOwnership(activeUser, playlistID)) {
                throw new Exception();
            }

            Playlist playlistEdit = new Playlist();
            playlistEdit.setPlaylistName(playlistRequestDTO.getPlaylistName());
            playlistService.editPlaylist(playlistID, playlistEdit);
            return new ResponseEntity<>(null, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "/{playlistID}")
    public ResponseEntity<?> deletePlaylist(@RequestHeader("Authorization") String token, @PathVariable Long playlistID){
        try {
            authorizationService.authorize(token);
            User activeUser = userService.findByUsername(jwtTokenUtil.getSubject(token));

            if(!playlistService.checkOwnership(activeUser, playlistID)){
                throw new Exception();
            }
            playlistService.deletePlaylist(playlistID);

            return new ResponseEntity<>(null, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{playlistID}/songs/{songId}")
    public ResponseEntity<?> addSongToPlaylist(@RequestHeader("Authorization") String token, @PathVariable Long playlistID, @PathVariable Long songId) {
        try {
            authorizationService.authorize(token);
            User activeUser = userService.findByUsername(jwtTokenUtil.getSubject(token));

            if (!playlistService.checkOwnership(activeUser, playlistID)) {
                throw new Exception();
            }

            playlistService.addSongToPlaylist(playlistID, songId);

            return new ResponseEntity<>(null, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{playlistID}/songs/{songID}")
    public ResponseEntity<?> deletePlaylistsSong(@RequestHeader("Authorization") String token, @PathVariable Long playlistID, @PathVariable Long songID){
        try {
            authorizationService.authorize(token);
            User activeUser = userService.findByUsername(jwtTokenUtil.getSubject(token));

            if(!playlistService.checkOwnership(activeUser, playlistID)){
                throw new Exception();
            }

            playlistService.removeSongFromPlaylist(playlistID, songID);

            return new ResponseEntity<>(null, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/me/playlists")
    public ResponseEntity<?> getCurrentUserPlaylists(@RequestHeader("Authorization") String token) {
        try {
            authorizationService.authorize(token);
            
            List<Playlist> curentUserPlaylists = playlistService.getCurrentUserPlaylists(jwtTokenUtil.getSubject(token));
            ModelMapper modelMapper = new ModelMapper();
            List<PlaylistResponseLiteDTO> dtos = curentUserPlaylists.stream()
            .map(playlist -> modelMapper.map(playlist, PlaylistResponseLiteDTO.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(dtos, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

}
