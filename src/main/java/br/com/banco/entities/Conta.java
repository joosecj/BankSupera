package br.com.banco.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idConta;
  @NotNull
  @Column(length = 50)
  private String nomeResponsavel;
  @OneToMany(mappedBy = "conta")
  private List<Transferencia> transferencias = new ArrayList<>();

}