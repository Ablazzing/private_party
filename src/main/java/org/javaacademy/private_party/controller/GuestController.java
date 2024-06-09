package org.javaacademy.private_party.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.private_party.dto.GuestDtoRq;
import org.javaacademy.private_party.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @PostMapping("/add-guest")
    public void addGuest(@RequestBody GuestDtoRq dtoRq,
                         @RequestHeader String username,
                         @RequestHeader String password) {
        guestService.addGuest(dtoRq, username, password);
    }

    @GetMapping("/all-guests")
    public List<String> getAllGuests(@RequestHeader String username,
                                     @RequestHeader String password) {
        return guestService.getNames(username, password);
    }
}
