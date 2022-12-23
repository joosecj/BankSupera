package br.com.banco.services;


import br.com.banco.dtos.ContaDTO;
import br.com.banco.dtos.SaldoTotalPorPeriodoDTO;
import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.projection.TotalTransferenciaContaProjection;
import br.com.banco.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ContaService {

  private final ContaRepository contaRepository;

  @Transactional(readOnly = true)
  public Page<ContaDTO> findAll(Pageable pageable) {
    Page<Conta> contas = contaRepository.findAll(pageable);
    return contas.map(ContaDTO::new);
  }

  @Transactional(readOnly = true)
  public Page<TransferenciaDTO> buscarContaComTransferenciasPorPeriodos(Long id, String minDate, String maxDate,
                                                                        String operador, Pageable pageable) {
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate min = minDate.equals("") ? today.minusDays(2190) : LocalDate.parse(minDate);
    LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
    if (operador.isEmpty()) {
      return contaRepository.buscarContaPorTransacoesPeriodo(id, min, max, pageable);
    } else {
      return contaRepository.buscarContaPorTransacoesOperador(id, min, max, operador, pageable);
    }
  }

  @Transactional(readOnly = true)
  public SaldoTotalPorPeriodoDTO buscarContaComSaldoTotalPorPeriodo(Long id, String minDate, String maxDate) {
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate min = minDate.equals("") ? today.minusDays(2190) : LocalDate.parse(minDate);
    LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
    TotalTransferenciaContaProjection totalContaPro = contaRepository.buscarValorTotalEmContaOuPeriodo(id, min, max);
    return new SaldoTotalPorPeriodoDTO(totalContaPro);
  }

}
