package br.com.banco.dtos;

import br.com.banco.entities.Transferencia;
import br.com.banco.entities.enums.TransferenciaTipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransferenciaDTO {
  private Long id;
  private LocalDate dataTransferencia;
  private Double valor;
  private TransferenciaTipo tipo;
  private String nomeOperadorTransacao;

  public TransferenciaDTO(Transferencia transferenciaEntity) {
    id = transferenciaEntity.getId();
    dataTransferencia = transferenciaEntity.getDataTransferencia();
    valor = transferenciaEntity.getValor();
    tipo = transferenciaEntity.getTipo();
    nomeOperadorTransacao = transferenciaEntity.getNomeOperadorTransacao();
  }
}