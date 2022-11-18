package io.test.money_transfer.transaction.processor;

import com.github.dozermapper.core.Mapper;
import io.test.money_transfer.transaction.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionProcessorController {

    private final TransactionProcessorService service;
    private final Mapper mapper;

    @PostMapping("/create")
    public ResponseEntity<TransactionResponse> create(@RequestBody @Valid final TransactionProcessRequest request) {
        var result = service.process(request.getAccountFrom(), request.getAccountTo(), request.getAmount());
        return ResponseEntity.ok(mapper.map(result, TransactionResponse.class));
    }

    @PostMapping("/terminate")
    public ResponseEntity<TransactionResponse> terminate(@RequestBody @Valid final TransactionTerminateRequest request) {
        var result = service.terminate(request.getTransactionStatus(), request.getTransactionId());
        return ResponseEntity.ok(mapper.map(result, TransactionResponse.class));
    }
}
