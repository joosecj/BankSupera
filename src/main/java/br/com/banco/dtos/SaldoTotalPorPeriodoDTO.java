package br.com.banco.dtos;

import br.com.banco.projection.TotalTransferenciaContaProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SaldoTotalPorPeriodoDTO {
  private Double total;
  private Double totalPeriodo;

  public SaldoTotalPorPeriodoDTO(TotalTransferenciaContaProjection projection) {
    total = projection.getSomatoria();
    totalPeriodo = projection.getSomatoriaData();
  }
}
