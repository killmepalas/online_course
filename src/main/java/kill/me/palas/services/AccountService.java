package kill.me.palas.services;

import kill.me.palas.models.Account;
import kill.me.palas.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findOne(int id) {
        Optional<Account> foundAccount = accountRepository.findById(id);
        return foundAccount.orElse(null);
    }

    @Transactional
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Transactional
    public void update(int id, Account updatedAccount) {
        updatedAccount.setId(id);
        accountRepository.save(updatedAccount);
    }

    @Transactional
    public void delete(int id) {
        accountRepository.deleteById(id);
    }
}