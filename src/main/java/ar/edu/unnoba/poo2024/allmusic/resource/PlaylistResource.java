package ar.edu.unnoba.poo2024.allmusic.resource;

import ar.edu.unnoba.poo2024.allmusic.dto.PlaylistRequestDTO;
import ar.edu.unnoba.poo2024.allmusic.dto.PlaylistResponseDTO;
import ar.edu.unnoba.poo2024.allmusic.model.Playlist;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.service.AuthorizationService;
import ar.edu.unnoba.poo2024.allmusic.service.PlaylistService;
import ar.edu.unnoba.poo2024.allmusic.service.SongService;
import ar.edu.unnoba.poo2024.allmusic.service.UserService;
import ar.edu.unnoba.poo2024.allmusic.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
            modelMapper.map(Playlist.class, PlaylistRequestDTO.class);

            List<Playlist> playlist = playlistService.getAllPlaylists();
            List<PlaylistRequestDTO> dtos = playlist.stream()
                    .map(playlist1 -> modelMapper.map(playlist1, PlaylistRequestDTO.class))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/{playlistID}", produces = "application/json")
    public ResponseEntity<?> getPlaylistById(@RequestHeader("Authorization") String token, @PathVariable("playlistID") Long playlistID) {
        try {
            authorizationService.authorize(token);

            PlaylistResponseDTO playlistDetails = playlistService.getPlaylistDetailsById(playlistID);

            return new ResponseEntity<>(playlistDetails, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createPlaylist(@RequestHeader("Authorization") String token, @RequestBody PlaylistResponseDTO playlistResponseDTO) {
        try{
            authorizationService.authorize(token);
            User activeUser = userService.findByUsername(jwtTokenUtil.getSubject(token));

            ModelMapper modelMapper = new ModelMapper();
            Playlist playlist = modelMapper.map(playlistResponseDTO, Playlist.class);
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
            playlistEdit.setPlaylistName(playlistRequestDTO.getNamePlaylist());
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
    public ResponseEntity<?> deletePlaylist(@RequestHeader("Authorization") String token, @PathVariable Long playlistID, @PathVariable Long songID){
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

}
