package com.challenges.MarketPlace.useCase.service;

import com.challenges.MarketPlace.useCase.domain.Departamento;
import com.challenges.MarketPlace.useCase.domain.Produto;
import com.challenges.MarketPlace.useCase.exceptions.*;
import com.challenges.MarketPlace.useCase.gateway.DepartamentoGateway;
import com.challenges.MarketPlace.useCase.gateway.ProdutoGateway;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    private final DepartamentoGateway departamentoGateway;

    public ProdutoUseCaseImpl(ProdutoGateway produtoGateway, DepartamentoGateway departamentoGateway) {
        this.produtoGateway = produtoGateway;
        this.departamentoGateway = departamentoGateway;
    }

    public Produto criarProduto(Produto produto) {
        validarProduto(produto);
        produto.setId(idGeneration());
        produto.setAtivo(true);
        produto.setOfertado(false);
        produto.setPorcentagemOferta(0);
        produto.setDataCadastro(getDataHora());
        return produtoGateway.criarProduto(produto);
    }

    private void validarProduto(Produto produto) {
        validarNomeDuplicado(produto);
        validarPrecoZeradoOuNegativo(produto);
        validarDepartamento(produto);
    }

    private String getDataHora() {
        DateFormat dateFormat =
                new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
        //calendar  zonedTime localDateTime alterDateJavaNewVersion
    }

    private String idGeneration() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

    private void validarPrecoZeradoOuNegativo(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new ValidarPrecoException(String.format("O preço '%s' não pode ser zero ou negativo ", produto.getPreco()));
        }
    }

    private void validarNomeDuplicado(Produto produto) {
        produtoGateway.findByNomeProduto(produto.getNome()).ifPresent(exception -> {
            throw new ValidarNomeDuplicadoException
                    (String.format(" Nome '%s'  está duplicado no sistema, verificar ", produto.getNome()));
        });
    }

    @Override
    public List<Produto> buscarListaDeProdutos() {
        return produtoGateway.listaProdutos();
    }

    public Produto detalharProdutoPorId(String id) {
        return produtoGateway.detalharProdutoPorId(id)
                .orElseThrow(() -> new IdNaoEncontradoException(String
                        .format("Id não encontrado", id)));
    }

    @Override
    public void deletarProduto(String id) {
        Produto produto = detalharProdutoPorId(id);
        if (produto.getAtivo()) {
            throw new ProdutoAtivoException
                    (String.format("Produto não pode ser excluido. Inative para realizar operação", produto.getId()));
        }
            produtoGateway.deletarProdutoPorId(id);
        }

    @Override
    public Produto atualizarParcialmenteProduto(String id, Produto novoProduto) {
        Produto produtoAtual = detalharProdutoPorId(id);

        if (StringUtils.isNotBlank(novoProduto.getNome())) {
            Optional<Produto> produtoPresente = produtoGateway.findByNomeProduto(novoProduto.getNome());
            if (produtoPresente.isPresent() && !StringUtils.equalsIgnoreCase(produtoPresente.get().getId(), id)) {
                validarNomeDuplicado(novoProduto);
            }
            produtoAtual.setNome(novoProduto.getNome());
        }

        if (StringUtils.isNotBlank(novoProduto.getDescricao())) {
            produtoAtual.setDescricao(novoProduto.getDescricao());
        }

        if (StringUtils.isNotBlank(novoProduto.getMarca())) {
            produtoAtual.setMarca(novoProduto.getMarca());
        }

        if (Objects.nonNull(novoProduto.getPreco())) {
            validarPrecoZeradoOuNegativo(novoProduto);
            produtoAtual.setPreco(novoProduto.getPreco());
        }

        if(!novoProduto.getDepartamentos().isEmpty()){
            for (Departamento departamento : novoProduto.getDepartamentos()) {
                Optional<Departamento> novoDepartamento = departamentoGateway
                        .buscarDepartamentoPorId(departamento.getIdDepartamento ());
                if(novoDepartamento.isPresent()){
                produtoAtual.setDepartamentos(novoProduto.getDepartamentos());
                }
            }
        }

        validarDepartamento(novoProduto);

        produtoAtual.setDataAtualizacao(getDataHora());

        return produtoGateway.atualizarProduto(produtoAtual);
    }

    private void validarDepartamento(Produto produto) {
        produto.getDepartamentos().forEach(departamento ->
                departamentoGateway.buscarDepartamentoPorId(departamento.getIdDepartamento())
                        .orElseThrow(()-> new DepartamentoInexistenteException(
                                String.format("Não existe cadastro de departamento com código %s", departamento.getIdDepartamento()))));

    }

    }