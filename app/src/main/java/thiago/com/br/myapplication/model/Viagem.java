package thiago.com.br.myapplication.model;

import java.util.Date;

/**
 * Created by Samsung on 18/02/2015.
 */
public class Viagem {

    public static final String TABELA = "viagem";
    public static final String _ID = "_id";
    public static final String DESTINO = "destino";
    public static final String DATA_CHEGADA = "data_chegada";
    public static final String DATA_SAIDA = "data_saida";
    public static final String ORCAMENTO = "orcamento";
    public static final String QUANTIDADE_PESSOAS = "quantidade_pessoas";
    public static final String TIPO_VIAGEM = "tipo_viagem";


    private Long id;
    private String destino;
    private Integer tipoViagem;
    private Date dataChegada;
    private Date dataSaida;
    private Double orcamento;
    private Integer quantidadePessoas;

    public Viagem() {}

    public Viagem(Long id, String destino, Integer tipoViagem, Date dataChegada, Date dataSaida, Double orcamento, Integer quantidadePessoas) {
        this.id = id;
        this.destino = destino;
        this.tipoViagem = tipoViagem;
        this.dataChegada = dataChegada;
        this.dataSaida = dataSaida;
        this.orcamento = orcamento;
        this.quantidadePessoas = quantidadePessoas;
    }

    public static final String[] COLUNAS = new String[]{
            _ID, DESTINO, DATA_CHEGADA, DATA_SAIDA,
            TIPO_VIAGEM, ORCAMENTO, QUANTIDADE_PESSOAS };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getTipoViagem() {
        return tipoViagem;
    }

    public void setTipoViagem(Integer tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}
