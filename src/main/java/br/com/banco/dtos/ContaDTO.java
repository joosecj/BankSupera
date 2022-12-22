package br.com.banco.dtos;


import br.com.banco.entities.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ContaDTO {
  private Long idConta;
  private String nomeResponsavel;

  public ContaDTO(Conta contaEntity) {
    idConta = contaEntity.getIdConta();
    nomeResponsavel = contaEntity.getNomeResponsavel();
  }
}
