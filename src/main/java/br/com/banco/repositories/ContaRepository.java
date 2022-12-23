package br.com.banco.repositories;

import br.com.banco.dtos.TransferenciaDTO;
import br.com.banco.entities.Conta;
import br.com.banco.projection.TotalTransferenciaContaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ContaRepository extends JpaRepository<Conta, Long> {

  @Query(value =
          "SELECT new br.com.banco.dtos.TransferenciaDTO(t.id, t.dataTransferencia, t.valor, t.tipo, t.nomeOperadorTransacao)  " +
                  "FROM Conta c, Transferencia t " +
                  "WHERE c.idConta = :id AND c.idConta = t.conta.idConta " +
                  "AND t.dataTransferencia BETWEEN :min AND :max ")
  Page<TransferenciaDTO> buscarContaPorTransacoesPeriodo(Long id, LocalDate min, LocalDate max, Pageable pageable);

  @Query(value =
          "SELECT new br.com.banco.dtos.TransferenciaDTO(t.id, t.dataTransferencia, t.valor, t.tipo, t.nomeOperadorTransacao)  " +
                  "FROM Conta c, Transferencia t " +
                  "WHERE c.idConta = :id AND c.idConta = t.conta.idConta " +
                  "AND t.dataTransferencia BETWEEN :min AND :max " +
                  "AND UPPER(t.nomeOperadorTransacao) LIKE UPPER(CONCAT('%', :operador, '%')) ")
  Page<TransferenciaDTO> buscarContaPorTransacoesOperador(Long id, LocalDate min, LocalDate max, String operador, Pageable pageable);

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
                  "   FROM contaMovimentacao cm " +
                  "), sumMovimentacaoDatas as ( " +
                  "SELECT ROUND(SUM(cmd.valor), 2) AS somatoriaData " +
                  "   FROM contaMovimentacaoDatas cmd " +
                  ")SELECT sm.somatoria AS somatoria, smd.somatoriaData AS somatoriaData FROM sumMovimentacao sm " +
                  "JOIN sumMovimentacaoDatas smd ")
  TotalTransferenciaContaProjection buscarValorTotalEmContaOuPeriodo(Long id, LocalDate min, LocalDate max);

  @Query(nativeQuery = true, value =
          " WITH contaMovimentacao AS ( " +
                  "SELECT t.valor AS valor,t.data_transferencia, t.nome_operador_transacao  " +
                  "    FROM conta c " +
                  "    INNER JOIN transferencia t ON t.conta_id = c.id_conta " +
                  "    WHERE c.id_conta = :id " +
                  "    GROUP BY t.id, c.id_conta " +
                  "), contaMovimentacaoDatas AS ( " +
                  "SELECT cm.* " +
                  "    FROM contaMovimentacao cm " +
                  "    WHERE cm.data_transferencia BETWEEN :min AND :max " +
                  "    AND UPPER(cm.nome_operador_transacao) LIKE UPPER(CONCAT('%', :operador, '%'))" +
                  "), sumMovimentacao as ( " +
                  "SELECT ROUND(SUM(cm.valor), 2) AS somatoria " +
                  "   FROM contaMovimentacao cm " +
                  "), sumMovimentacaoDatas as ( " +
                  "SELECT ROUND(SUM(cmd.valor), 2) AS somatoriaData " +
                  "   FROM contaMovimentacaoDatas cmd " +
                  ")SELECT sm.somatoria AS somatoria, smd.somatoriaData AS somatoriaData FROM sumMovimentacao sm " +
                  "JOIN sumMovimentacaoDatas smd ")
  TotalTransferenciaContaProjection buscarValorTotalEmContaPeriodoPorOperador(Long id, LocalDate min, LocalDate max, String operador);

}
