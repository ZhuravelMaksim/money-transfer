package io.test.money_transfer.account;

import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;
    private final Mapper mapper;

    @PostMapping
    public ResponseEntity<AccountResponse> create() {
        var result = service.create();
        return ResponseEntity.ok(mapper.map(result, AccountResponse.class));
    }

    @PostMapping("/updateBalance")
    public ResponseEntity<AccountResponse> updateBalance(@RequestBody @Valid final UpdateBalanceRequest request) {
        var result = service.updateBalance(request.getId(), request.getAmount());
        return ResponseEntity.ok(mapper.map(result, AccountResponse.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById(@PathVariable final String id) {
        var result = service.getById(id);
        return ResponseEntity.ok(mapper.map(result, AccountResponse.class));
    }

}
