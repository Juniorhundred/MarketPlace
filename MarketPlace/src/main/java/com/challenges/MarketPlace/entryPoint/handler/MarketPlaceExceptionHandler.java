package com.challenges.MarketPlace.entryPoint.handler;


import com.challenges.MarketPlace.entryPoint.validation.exception.CampoInvalidoOperacaoException;
import com.challenges.MarketPlace.useCase.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MarketPlaceExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public MarketPlaceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MensagemErrorModelResponse> handlerErroGenerico(Exception exception) {
        HttpStatus httpErroGenerico = HttpStatus.INTERNAL_SERVER_ERROR;

        MensagemErrorModelResponse mensagemExceptionHandler = montarMensagemErrorModel(
                httpErroGenerico, "Ocorreu um erro interno no serviço.",
                Collections.emptyList()
        );

        return new ResponseEntity<>(mensagemExceptionHandler, httpErroGenerico);
    }

    @ExceptionHandler(ConteudoNaoEncontradoException.class)
    public final ResponseEntity<?> handlerConteudoNaoEncontrado(Exception exception) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({IdNaoEncontradoException.class, DepartamentoInexistenteException.class})
    public final ResponseEntity<MensagemErrorModelResponse> handlerIdNaoEncontrado(Exception exception) {
        HttpStatus httpIdNaoEncontrado = HttpStatus.NOT_FOUND;

        MensagemErrorModelResponse mensagemErrorModelResponse = montarMensagemErrorModel(
                httpIdNaoEncontrado, exception.getMessage(), Collections.emptyList()
        );

        return new ResponseEntity<>(mensagemErrorModelResponse, httpIdNaoEncontrado);
    }

    @ExceptionHandler({ValidarNomeDepartamentoDuplicadoException.class, ValidarPrecoException.class,
            ValidarNomeDuplicadoException.class, PorcentagemProdutoException.class, ProdutoAtivoException.class,
            EntityNotExistException.class})
    public final ResponseEntity<MensagemErrorModelResponse> handlerValidacoes(Exception exception) {
        HttpStatus httpValidacoes = HttpStatus.UNPROCESSABLE_ENTITY;

        MensagemErrorModelResponse mensagemErrorModelResponse = montarMensagemErrorModel(
                httpValidacoes, exception.getMessage(), Collections.emptyList()
        );

        return new ResponseEntity<>(mensagemErrorModelResponse, httpValidacoes);
    }

    @ExceptionHandler(EntityException.class)
    public final ResponseEntity<MensagemErrorModelResponse> handleeEntidadeEmUso(Exception exception) {
        HttpStatus httpEntidadeEmUso = HttpStatus.CONFLICT;

        MensagemErrorModelResponse mensagemErrorModelResponse = montarMensagemErrorModel(
                httpEntidadeEmUso, exception.getMessage(), Collections.emptyList()
        );

        return new ResponseEntity<>(mensagemErrorModelResponse, httpEntidadeEmUso);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        HttpStatus httpStatusBadRequest = HttpStatus.BAD_REQUEST;
        MensagemErrorModelResponse mensagemExceptionHandler = new MensagemErrorModelResponse();
        List<CampoMensagemErrorModelResponse> camposMensagem = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(field -> {
            String mensagem = messageSource.getMessage(field, LocaleContextHolder.getLocale());
            CampoMensagemErrorModelResponse campoMensagem = new CampoMensagemErrorModelResponse();
            campoMensagem.setCampo((convertToSnakeCase(field.getField())));
            campoMensagem.setMensagem(mensagem);

            camposMensagem.add(campoMensagem);
        });

        mensagemExceptionHandler.setCodigo(String.valueOf(httpStatusBadRequest.value()));
        mensagemExceptionHandler.setMensagem("Ocorreu um erro de validação na requisição," +
                " tente novamente passando informações válidas.");
        mensagemExceptionHandler.setUrlErro(ExceptionEnum.Url(httpStatusBadRequest.value()));
        mensagemExceptionHandler.setCampos(camposMensagem);

        return new ResponseEntity<>(mensagemExceptionHandler, httpStatusBadRequest);
    }

    @ExceptionHandler(CampoInvalidoOperacaoException.class)
    public final ResponseEntity<Object> handlerExceptionInvalid(CampoInvalidoOperacaoException campoInvalidoOperacaoException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<CampoMensagemErrorModelResponse> campoMensagemErrorModelResponses = new ArrayList<>();

        Map<String, List<String>> campos = campoInvalidoOperacaoException.getCampos();
        campos.forEach((chave, mensagem ) ->{
            CampoMensagemErrorModelResponse campoMensagemErrorModelResponse = CampoMensagemErrorModelResponse.builder()
                    .campo(chave)
                    .mensagem(mensagem.get(0))
                    .build();
                    campoMensagemErrorModelResponses.add(campoMensagemErrorModelResponse);
                }
                );
        MensagemErrorModelResponse mensagemErrorModelResponse = montarMensagemErrorModel(
                httpStatus, "Validação campos", campoMensagemErrorModelResponses
        );

        return new ResponseEntity<>(mensagemErrorModelResponse, httpStatus);
    }

    private MensagemErrorModelResponse montarMensagemErrorModel(HttpStatus httpStatus, String mensagem,
                                                                List<CampoMensagemErrorModelResponse> campos) {
        MensagemErrorModelResponse mensagemErrorModelResponse = new MensagemErrorModelResponse();
        mensagemErrorModelResponse.setCodigo(String.valueOf(httpStatus.value()));
        mensagemErrorModelResponse.setMensagem(mensagem);
        mensagemErrorModelResponse.setUrlErro(ExceptionEnum.Url(httpStatus.value()));
        mensagemErrorModelResponse.setCampos(campos);

        return mensagemErrorModelResponse;
    }

    private String convertToSnakeCase(String nomeCampo){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return nomeCampo.replaceAll(regex, replacement).toLowerCase();
    }
}