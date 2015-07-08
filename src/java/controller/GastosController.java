/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoriasDao;
import dao.Fecha;
import dao.GastosDao;
import dao.UsuariosDao;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import orm.Gastos;

/**
 *
 * @author gtoffa
 */
@ManagedBean(name = "gC")
@SessionScoped
public class GastosController {

    //inyectar dependencias a los EJB
    @EJB
    GastosDao gasDao;

    @EJB
    CategoriasDao catDao;

    @EJB
    Fecha fecDao;

    @EJB
    UsuariosDao usuDao;

    //Objetos de contexto
    private Gastos gasto;
    private List<Gastos> listaGastos;
    private List<String> listaUsarios;

    private List<String> listaCats;

    private String totalGastos;

    private String Descripcion;
    private String nombreCategoria;
    private String nombreUsuario;
    private Date FecDesde;
    private Date FecHasta;
    private String titulo;
    private String Pagina;
    char op;

    public void doGasto(char op) {

        if (op == 'g') {
            setPagina("Gastos.xhtml");
        } else {
            setPagina("Ingresos.xhtml");
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("contenido");

    }

    public GastosController() {
        gasto = new Gastos();

    }

    public void doNuevo() {
        try {

            op = 'n';
            setTitulo("Nuevo");

            gasto = new Gastos();
            gasto.setFec(fecDao.FechaActual());
            setGasto(gasto);

        } catch (Exception e) {
        }

    }

    public void doEditar(Gastos ga) {

        op = 'e';
        setTitulo("Editar Gasto:");
        

        setGasto(ga);

    }

    void cargarGastos() {
        float tot = 0;

        listaGastos = gasDao.selectGastos_filtro(Descripcion, nombreCategoria, FecDesde, FecHasta);
        for (Gastos g : listaGastos) {
            tot += Float.parseFloat(g.getVal().toString());
        }
        totalGastos = String.valueOf(tot);

    }

    void cargarCategorias() {
        listaCats = catDao.selectNombreCategorias();
    }

    @PostConstruct // ap ost de contruir los objetos en el servidor
    public void inicializar() {
        setPagina("Ingresos.xhtml");
//        nombreCategoria = "";
        gasto = new Gastos();
        Descripcion = "";
        nombreCategoria = "";
        FecDesde = fecDao.PrimeraFecha();
        FecHasta = fecDao.UltimaFecha();
        cargarCategorias();
        cargarGastos();

        listaUsarios = usuDao.selectSoloNombres();

        setTitulo("cambiar:");
//        listaCats = catDao.selectNombreCategorias();

    }

    public void doBuscarGasto(AjaxBehaviorEvent e) {

        cargarGastos();

    }

    public void doBuscarCate(AjaxBehaviorEvent e) {

        listaGastos = gasDao.selectGastos_filtro(Descripcion, nombreCategoria, FecDesde, FecHasta);

    }

    public void doGuardar() {
        gasto.setFkIdUsu(usuDao.selectUsuarioPornombre(nombreUsuario));
        gasto.setFkIdCat(catDao.selectCategoriaPorNombre(nombreCategoria));
        if (op == 'n') {
            gasDao.insertGasto(gasto);
        } else {
            gasDao.updateGasto(gasto);
        }
        nombreCategoria = "";
        cargarGastos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("contenido:lista:tabla");

    }

    public void doEliminanr(Gastos ga) {

        gasDao.delteGasto(ga);

        cargarGastos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("contenido:lista:tabla");

    }

    //GETTER and SETTERS
    public Gastos getGasto() {
        return gasto;
    }

    public void setGasto(Gastos gasto) {
        this.gasto = gasto;
    }

    public List<Gastos> getListaGastos() {
        return listaGastos;
    }

    public void setListaGastos(List<Gastos> listaGastos) {

        this.listaGastos = listaGastos;
    }

    /**
     * @return the totalGastos
     */
    public String getTotalGastos() {
        return totalGastos;
    }

    /**
     * @param totalGastos the totalGastos to set
     */
    public void setTotalGastos(String totalGastos) {
        this.totalGastos = totalGastos;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the listaCats
     */
    public List<String> getListaCats() {
        return listaCats;
    }

    /**
     * @param listaCats the listaCats to set
     */
    public void setListaCats(List<String> listaCats) {
        this.listaCats = listaCats;
    }

    /**
     * @return the nombreCategoria
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * @param nombreCategoria the nombreCategoria to set
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * @return the FecDesde
     */
    public Date getFecDesde() {
        return FecDesde;
    }

    /**
     * @param FecDesde the FecDesde to set
     */
    public void setFecDesde(Date FecDesde) {
        this.FecDesde = FecDesde;
    }

    /**
     * @return the FecHasta
     */
    public Date getFecHasta() {
        return FecHasta;
    }

    /**
     * @param FecHasta the FecHasta to set
     */
    public void setFecHasta(Date FecHasta) {
        this.FecHasta = FecHasta;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the listaUsarios
     */
    public List<String> getListaUsarios() {
        return listaUsarios;
    }

    /**
     * @param listaUsarios the listaUsarios to set
     */
    public void setListaUsarios(List<String> listaUsarios) {
        this.listaUsarios = listaUsarios;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the Pagina
     */
    public String getPagina() {
        return Pagina;
    }

    /**
     * @param Pagina the Pagina to set
     */
    public void setPagina(String Pagina) {
        this.Pagina = Pagina;
    }

}
