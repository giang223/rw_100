package com.vti.controller;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.AccountCreateOrUpdateForm;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll()
    {
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{idSearch}")
    public ResponseEntity<AccountDTO> findById(@PathVariable(name = "idSearch") Integer id)
    {
        return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search-username")
    public ResponseEntity<AccountDTO> findByUsername(@RequestParam(name = "username") String username)
    {
        AccountDTO accountDTO = accountService.findByUsername(username);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @GetMapping("/search-full-name")
    public ResponseEntity<List<AccountDTO>> findAllByFullName(@RequestParam(name = "fullName") String fullName)
    {
        List<AccountDTO> accountDTOs = accountService.findAllByFullName(fullName);
        return new ResponseEntity<>(accountDTOs, HttpStatus.OK);
    }

    @GetMapping("/search-and")
    public ResponseEntity<AccountDTO> findByFullNameAndUsername(@RequestParam(name = "username") String username,
                                                                      @RequestParam(name = "fullName") String fullName)
    {
        AccountDTO dto = accountService.findByFullNameAndUsername(fullName, username);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountDTO>> findByFullNameOrUsername(@RequestParam(name = "username") String username,
                                                                      @RequestParam(name = "fullName") String fullName)
    {
        List<AccountDTO> dtos = accountService.findByFullNameOrUsername(fullName, username);

        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }

    @DeleteMapping("/{idDelete}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "idDelete") Integer id) {
        accountService.deleteById(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AccountCreateOrUpdateForm form) {
        accountService.create(form);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    @PutMapping("/{idUpdate}")
    public ResponseEntity<String> update(@RequestBody AccountCreateOrUpdateForm form,
                                         @PathVariable(name = "idUpdate") Integer id) {
        accountService.update(form, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }
}
