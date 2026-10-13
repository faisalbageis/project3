package org.example.project3.Controller;

import lombok.RequiredArgsConstructor;
import org.example.project3.Model.Account;
import org.example.project3.Model.User;
import org.example.project3.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal User user, @RequestBody Account account) {
        accountService.CreateAccount(user.getId(), account);
        return ResponseEntity.status(200).body("account created successfully");
    }

    @PostMapping("/create/{customer_id}")
    public ResponseEntity<?> createAccountForCustomer(@PathVariable Integer customer_id, @RequestBody Account account) {
        accountService.CreateAccount(customer_id, account);
        return ResponseEntity.status(200).body("account created successfully");
    }

    @PutMapping("/active/{account_id}")
    public ResponseEntity<?> ActiveAccount(@PathVariable Integer account_id) {
        accountService.ActiveAccount(account_id);
        return ResponseEntity.status(200).body("account activated successfully");
    }

    @GetMapping("/get/all/user")
    public ResponseEntity<Set<Account>> getAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(accountService.getAccounts(user.getId()));
    }

    @GetMapping("/get/user/{account_id}")
    public ResponseEntity<Account> getAccountById(@AuthenticationPrincipal User user, @PathVariable Integer account_id) {
        return ResponseEntity.status(200).body(accountService.getAccountById(user.getId(), account_id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.status(200).body(accountService.getAccounts());
    }

    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal User user, @PathVariable Integer account_id, @PathVariable Double amount) {

        accountService.deposit(user.getId(), account_id, amount);
        return ResponseEntity.status(200).body("deposit completed successfully");
    }

    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity<?> withdrew(@AuthenticationPrincipal User user, @PathVariable Integer account_id, @PathVariable Double amount) {

        accountService.withdrew(user.getId(), account_id, amount);
        return ResponseEntity.status(200).body("withdraw completed successfully");
    }


    @PostMapping("/transfer/{account_id}/{amount}/{AccountNumber}")
    public ResponseEntity<?> transfer(@AuthenticationPrincipal User user, @PathVariable Integer account_id,
            @PathVariable Double amount,
            @PathVariable String AccountNumber) {

        accountService.transfer(user.getId(), account_id, amount, AccountNumber);
        return ResponseEntity.status(200).body("transfer completed successfully");
    }

    @PutMapping("/block/{account_id}")
    public ResponseEntity<?> blockAccount(@PathVariable Integer account_id) {
        accountService.blockAccount(account_id);
        return ResponseEntity.status(200).body("account blocked successfully");
    }
}
