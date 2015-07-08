/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.Fecha;
import dao.UsuariosDao;
import dao.ingresosDao;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;
import orm.Ingresos;

/**
 *
 * @author gtoffa
 */
@ManagedBean(name = "iC")
@SessionScoped

public class IngresosController {

    @EJB
    ingresosDao igDao;

    @EJB
    Fecha fecDao;

    @EJB
    UsuariosDao usuDao;

    private Ingresos ing;
    private List<Ingresos> listaIngresos;

    private String Descripcion;
    private Date FecDesde;
    private Date FecHasta;
    private String titulo;
    private String nombreUsuario;
    private String totalIngresos;

    char op;

    void cargarIngresos() {

        listaIngresos = igDao.select_filtro_(Descripcion, FecDesde, FecHasta);
        float tot = 0;
        for (Ingresos g : listaIngresos) {
            tot += Float.parseFloat(g.getVal().toString());
        }
        setTotalIngresos(String.valueOf(tot));

//        totalGastos = igDao.select_total_Gastos();
    }

    @PostConstruct // ap ost de contruir los objetos en el servidor
    public void inicializar() {

//        nombreCategoria = "";
        ing = new Ingresos();
        Descripcion = "";
        setTitulo("Nuevo");

        FecDesde = fecDao.PrimeraFecha();
        FecHasta = fecDao.UltimaFecha();
        cargarIngresos();

//        listaCats = catDao.selectNombreCategorias();
    }

    public void doBuscarIngresos(AjaxBehaviorEvent e) {

        cargarIngresos();

    }

    public void doNuevo() {
        try {

            op = 'n';
            setTitulo("Nuevo");

            ing = new Ingresos();
            ing.setFec(fecDao.FechaActual());
            setIng(ing);

        } catch (Exception e) {
        }

    }

    public void doEditar(Ingresos in) {

        op = 'e';
        setTitulo("Editar Ingresos:");
        setIng(in);

    }

    public void doGuardar() {
        ing.setFkIdUsu(usuDao.selectUsuarioPornombre(getNombreUsuario()));
        if (op == 'n') {
            igDao.insertIngresos(ing);
        } else {
            igDao.updateIngresos(ing);
        }

        cargarIngresos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("contenido:listaing:tablai_ing");

    }

    public void doEliminanr(Ingresos in) {

        igDao.delteIngresos(in);

        cargarIngresos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("contenido:listaing:tablai_ing");

    }

    public IngresosController() {
        ing = new Ingresos();
    }

    /**
     * @return the ing
     */
    public Ingresos getIng() {
        return ing;
    }

    /**
     * @param ing the ing to set
     */
    public void setIng(Ingresos ing) {
        this.ing = ing;
    }

    /**
     * @return the listaIngresos
     */
    public List<Ingresos> getListaIngresos() {
        return listaIngresos;
    }

    /**
     * @param listaIngresos the listaIngresos to set
     */
    public void setListaIngresos(List<Ingresos> listaIngresos) {
        this.listaIngresos = listaIngresos;
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
     * @return the totalIngresos
     */
    public String getTotalIngresos() {
        return totalIngresos;
    }

    /**
     * @param totalIngresos the totalIngresos to set
     */
    public void setTotalIngresos(String totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

}
