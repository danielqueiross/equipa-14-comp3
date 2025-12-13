package lp.JavaFxClient.model;

public class EventoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String data;        
    private int lotacaoMax;

    public EventoDTO() {}

    public EventoDTO(Long id, String titulo, String descricao, String data, int lotacaoMax) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.lotacaoMax = lotacaoMax;
    }

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
}
