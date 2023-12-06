package br.com.danielfood.pagamento.service;

import br.com.danielfood.pagamento.dto.PagamentoDto;
import br.com.danielfood.pagamento.model.Pagamento;
import br.com.danielfood.pagamento.model.Status;
import br.com.danielfood.pagamento.pagamentoRepository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    private ModelMapper modelMapper;

    public Page<PagamentoDto> obterTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(p->modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorId(Long id){
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException());

        return modelMapper.map(pagamento,PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto){
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto){
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id){
        repository.deleteById(id);
    }



}
