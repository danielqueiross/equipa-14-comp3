package lp.JavaFxClient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String data;
    private int lotacaoMax;
    private int numParticipantes;
    private String estado;

    public EventoDTO() {}
    

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public int getLotacaoMax() { return lotacaoMax; }
    public void setLotacaoMax(int lotacaoMax) { this.lotacaoMax = lotacaoMax; }

    public int getNumParticipantes() { return numParticipantes; }
    public void setNumParticipantes(int numParticipantes) {
        this.numParticipantes = numParticipantes;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
