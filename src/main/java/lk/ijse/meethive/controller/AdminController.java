package lk.ijse.meethive.controller;

import lk.ijse.meethive.dto.UserDTO;
import lk.ijse.meethive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserService userService;
    /*@GetMapping("/checkRole")
    public String checkRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .findFirst()
                .orElse("UNKNOWN");

        return "{\"role\": \"" + role + "\"}";
    }*/
    @GetMapping("/checkRole")
    public ResponseEntity<Map<String, Object>> checkRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        UserDTO user = userService.getUserByEmail(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "User not found")
            );
        }

        String role = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .findFirst()
                .orElse("UNKNOWN");

        Map<String, Object> response = new HashMap<>();
        response.put("role", role);
        response.put("isActive", user.isActive());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test1")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String check(){
        return "passed~!1";
    }

    @GetMapping("/test2")
    @PreAuthorize("hasAuthority('USER')")
    public String checks(){
        return "passed~!2";
    }
    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('NEW_USER')")
    public String checkss(){
        return "passed~!2";
    }

}
