package br.com.banco.repositories;

import br.com.banco.entities.Conta;
import br.com.banco.projection.TotalTransferenciaContaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
public interface ContaRepository extends JpaRepository<Conta, Long> {

  @Query(nativeQuery = true, value =
          " WITH contaMovimentacao AS ( " +
                  "SELECT t.valor AS valor,t.data_transferencia  " +
                  "    FROM conta c " +
                  "    INNER JOIN transferencia t ON t.conta_id = c.id_conta " +
                  "    WHERE c.id_conta = :id " +
                  "    GROUP BY t.id, c.id_conta " +
                  "), contaMovimentacaoDatas AS ( " +
                  "SELECT cm.* " +
                  "    FROM contaMovimentacao cm " +
                  "    WHERE cm.data_transferencia BETWEEN :min AND :max " +
                  "), sumMovimentacao as ( " +
                  "SELECT ROUND(SUM(cm.valor), 2) AS somatoria " +
                  "FROM contaMovimentacao cm " +
                  "), sumMovimentacaoDatas as ( " +
                  "SELECT ROUND(SUM(cmd.valor), 2) AS somatoriaData " +
                  "FROM contaMovimentacaoDatas cmd " +
                  ")SELECT sm.somatoria AS somatoria, smd.somatoriaData AS somatoriaData FROM sumMovimentacao sm " +
                  "JOIN sumMovimentacaoDatas smd ")
  TotalTransferenciaContaProjection buscarValorTotalEmContaOuPeriodo(Long id, LocalDate min, LocalDate max);

}
