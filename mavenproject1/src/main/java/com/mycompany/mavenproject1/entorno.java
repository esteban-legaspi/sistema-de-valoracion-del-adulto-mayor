package com.mycompany.mavenproject1;

public class entorno {

    private int id;
    private int valoracionId;

    private String tipoPiso;
    private String tipoPared;
    private String tipoTecho;
    private String tipoLuz;

    private String abastecimientoAgua;
    private String purificacionAgua;
    private String drenaje;
    private String tratamientoBasura;

    private String faunaNociva;
    private String animalesDomesticos;

    private String numAnimales;
    private String animalesVacunados;

    public entorno() {}

    // ── Getters y setters ──────────────────────────────

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValoracionId() {
        return valoracionId;
    }

    public void setValoracionId(int valoracionId) {
        this.valoracionId = valoracionId;
    }

    public String getTipoPiso() {
        return tipoPiso;
    }

    public void setTipoPiso(String tipoPiso) {
        this.tipoPiso = tipoPiso;
    }

    public String getTipoPared() {
        return tipoPared;
    }

    public void setTipoPared(String tipoPared) {
        this.tipoPared = tipoPared;
    }

    public String getTipoTecho() {
        return tipoTecho;
    }

    public void setTipoTecho(String tipoTecho) {
        this.tipoTecho = tipoTecho;
    }

    public String getTipoLuz() {
        return tipoLuz;
    }

    public void setTipoLuz(String tipoLuz) {
        this.tipoLuz = tipoLuz;
    }

    public String getAbastecimientoAgua() {
        return abastecimientoAgua;
    }

    public void setAbastecimientoAgua(String abastecimientoAgua) {
        this.abastecimientoAgua = abastecimientoAgua;
    }

    public String getPurificacionAgua() {
        return purificacionAgua;
    }

    public void setPurificacionAgua(String purificacionAgua) {
        this.purificacionAgua = purificacionAgua;
    }

    public String getDrenaje() {
        return drenaje;
    }

    public void setDrenaje(String drenaje) {
        this.drenaje = drenaje;
    }

    public String getTratamientoBasura() {
        return tratamientoBasura;
    }

    public void setTratamientoBasura(String tratamientoBasura) {
        this.tratamientoBasura = tratamientoBasura;
    }

    public String getFaunaNociva() {
        return faunaNociva;
    }

    public void setFaunaNociva(String faunaNociva) {
        this.faunaNociva = faunaNociva;
    }

    public String getAnimalesDomesticos() {
        return animalesDomesticos;
    }

    public void setAnimalesDomesticos(String animalesDomesticos) {
        this.animalesDomesticos = animalesDomesticos;
    }

    public String getNumAnimales() {
        return numAnimales;
    }

    public void setNumAnimales(String numAnimales) {
        this.numAnimales = numAnimales;
    }

    public String getAnimalesVacunados() {
        return animalesVacunados;
    }

    public void setAnimalesVacunados(String animalesVacunados) {
        this.animalesVacunados = animalesVacunados;
    }
}