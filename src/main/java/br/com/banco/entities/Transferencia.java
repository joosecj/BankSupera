package br.com.banco.entities;

import br.com.banco.entities.enums.TransferenciaTipo;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transferencia")
public class Transferencia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotNull
  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private LocalDateTime dataTransferencia;
  @NotNull
  private Double valor;
  @NotNull
  @Column(length = 15)
  @Enumerated(EnumType.STRING)
  private TransferenciaTipo tipo;
  @Column(length = 50)
  private String nomeOperadorTransacao;
  @ManyToOne
  @JoinColumn(name = "conta_id")
  private Conta conta;

}
