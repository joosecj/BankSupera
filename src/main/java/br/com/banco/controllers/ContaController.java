package br.com.banco.controllers;

import br.com.banco.dtos.ContaDTO;
import br.com.banco.dtos.SaldoTotalPorPeriodoDTO;
import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.services.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/contas")
@RequiredArgsConstructor
public class ContaController {
  private final ContaService contaService;

  @GetMapping
  public ResponseEntity<Page<ContaDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(contaService.findAll(pageable));
  }

  @GetMapping(value = "/{id}/extrato")
  public ResponseEntity<List<TransferenciaDTO>> buscarTransferenciasPorPeriodoConta(@PathVariable Long id,
                                                                                   @RequestParam(value = "minDate", defaultValue = "")
                                                                                   String minDate,
                                                                                   @RequestParam(value = "maxDate", defaultValue = "")
                                                                                   String maxDate) {
    List<TransferenciaDTO> transferenciaDTOS = contaService.buscarContaComTransferenciasPorPeriodo(id, minDate, maxDate);
    return ResponseEntity.ok(transferenciaDTOS);
  }

  @GetMapping(value = "/{id}/saldo")
  public ResponseEntity<SaldoTotalPorPeriodoDTO> saldoTotalPeriodo(@PathVariable Long id,
                                                                   @RequestParam(value = "minDate", defaultValue = "")
                                                                   String minDate,
                                                                   @RequestParam(value = "maxDate", defaultValue = "")
                                                                   String maxDate) {
    SaldoTotalPorPeriodoDTO saldoTotalPorPeriodoDTO = contaService.buscarContaComSaldoTotalPorPeriodo(id, minDate, maxDate);
    return ResponseEntity.ok(saldoTotalPorPeriodoDTO);
  }

}
