package finanzas_pro.models.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "datos_compañia")
public class DatosCompañia {

    @Id
    @Column(name = "company_symbol", length = 50)
    private String companySymbol;

    @OneToOne
    @MapsId  // PK compartida con la entidad principal
    @JoinColumn(name = "company_symbol")
    private DatosEmpresa datosEmpresa;

    @Column(name = "moneda", length = 3)
    private String moneda;

    @Column(name = "ISIN", columnDefinition = "TINYTEXT")
    private String ISIN;

    @Column(name = "bolsa", length = 100)
    private String bolsa;

    @Column(name = "sector", length = 100)
    private String sector;

    @Column(name = "industria", length = 100)
    private String industria;

    @Column(name = "país", length = 100)
    private String pais;

    @Column(name = "empleados")
    private Integer empleados;

    @Column(name = "CEO", length = 150)
    private String CEO;

    @Column(name = "web", length = 200)
    private String web;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_Ipo")
    private Date fechaIpo;


    public String getCompanySymbol() {
        return companySymbol;
    }

    public void setCompanySymbol(String companySymbol) {
        this.companySymbol = companySymbol;
    }

    public String getMoneda() {
        return moneda;
    }

    public DatosEmpresa getDatosEmpresa() {
        return datosEmpresa;
    }

    public void setDatosEmpresa(DatosEmpresa datosEmpresa) {
        this.datosEmpresa = datosEmpresa;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getBolsa() {
        return bolsa;
    }

    public void setBolsa(String bolsa) {
        this.bolsa = bolsa;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustria() {
        return industria;
    }

    public void setIndustria(String industria) {
        this.industria = industria;
    }

    public String getPais() {
        return pais;
    }

    public void setPaís(String país) {
        this.pais = país;
    }

    public Integer getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Integer empleados) {
        this.empleados = empleados;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Date getFechaIpo() {
        return fechaIpo;
    }

    public void setFechaIpo(Date fechaIpo) {
        this.fechaIpo = fechaIpo;
    }

}
