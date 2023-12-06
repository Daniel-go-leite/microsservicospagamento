package br.com.danielfood.pagamento.pagamentoRepository;

import br.com.danielfood.pagamento.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {



}
