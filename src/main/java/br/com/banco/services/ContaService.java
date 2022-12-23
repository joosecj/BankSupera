package br.com.banco.services;


import br.com.banco.dtos.ContaDTO;
import br.com.banco.dtos.SaldoTotalPorPeriodoDTO;
import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.projection.TotalTransferenciaContaProjection;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

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
    Page<TransferenciaDTO> transferenciaDTOPage = operador.isEmpty()
            ? contaRepository.buscarContaPorTransacoesPeriodo(id, tratarDataMin(minDate), tratarDataMax(maxDate), pageable)
            : contaRepository.buscarContaPorTransacoesOperador(id, tratarDataMin(minDate), tratarDataMax(maxDate), operador, pageable);
    return transferenciaDTOPage;
  }

  @Transactional(readOnly = true)
  public SaldoTotalPorPeriodoDTO buscarContaComSaldoTotalPorPeriodo(Long id, String minDate, String maxDate, String operador) {
    try {
      TotalTransferenciaContaProjection totalContaPro = operador.isEmpty()
              ? contaRepository.buscarValorTotalEmContaOuPeriodo(id, tratarDataMin(minDate), tratarDataMax(maxDate))
              : contaRepository.buscarValorTotalEmContaPeriodoPorOperador(id, tratarDataMin(minDate), tratarDataMax(maxDate), operador);
      return new SaldoTotalPorPeriodoDTO(totalContaPro);
    } catch (DateTimeParseException e) {
      throw new ResourceNotFoundException("Datas inválidas");
    }
  }

  private LocalDate tratarDataMin(String minDate) {
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    return minDate.equals("") ? today.minusDays(2190) : LocalDate.parse(minDate);
  }

  private LocalDate tratarDataMax(String maxDate) {
    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    return maxDate.equals("") ? today : LocalDate.parse(maxDate);
  }

}
