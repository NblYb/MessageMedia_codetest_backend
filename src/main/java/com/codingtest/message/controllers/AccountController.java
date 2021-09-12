package com.codingtest.message.controllers;

import com.codingtest.message.dtos.account.*;
import com.codingtest.message.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AccountController {
    @Autowired
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountGetDto>> getAll(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{username}")
    public ResponseEntity<AccountGetDto> getByUsername(@PathVariable String username){
        return ResponseEntity.ok(accountService.findAccountByUsername(username));
    }

    @PostMapping
    public ResponseEntity<AccountGetDto> add(@RequestBody AccountPostDto accountPostDto) {
        AccountGetDto accountGetDto = new AccountGetDto();
        if(accountService.findAccountByUsername(accountPostDto.getUsername()) == null){
            accountGetDto = accountService.createAccount(accountPostDto);
            return ResponseEntity.ok(accountGetDto);
        } else {
            return ResponseEntity.badRequest().body(accountGetDto);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<AccountGetDto> changePassword(@PathVariable String username,
                                                        @RequestParam("encodedpassword") final String encodedPassoword){
        AccountGetDto accountGetDto = accountService.changePassword(username, encodedPassoword);
        return ResponseEntity.ok(accountGetDto);
    }

}
