package com.vti.controller;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> findAll()
    {
        List<Account> accounts = accountService.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{idSearch}")
    public ResponseEntity<Account> findById(@PathVariable(name = "idSearch") Integer id)
    {
        Account account = accountService.findById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{idDelete}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        accountService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Account account) {
        accountService.create(account);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody Account account,
                                         @PathVariable(name = "idUpdate") Integer id) {
        accountService.update(account, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
}
