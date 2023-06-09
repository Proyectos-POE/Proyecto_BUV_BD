package controlador;

/** ****************************************************************************
 * Autores:
 * Nicolas Herrera Marulanda - 202182551
 * Samuel Galindo Cuevas - 202177491
 * Julian David Rendon Cardona - 202177387
 * *****************************************************************************/

import dao.DaoEmpleado;
import dao.DaoEstudiante;
import dao.DaoProfesor;
import modelo.*;
import vista.VentanaBiblioteca;
import vista.VentanaLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class ControladorBiblioteca
{

    private final VentanaBiblioteca ventanaBiblioteca;
    private final Usuario usuario;
    private final ManejadorDao manejadorDao;
    public ControladorBiblioteca(VentanaBiblioteca auxA, Usuario auxB, ManejadorDao manejadorDao)
    {
        this.ventanaBiblioteca = auxA;
        this.usuario = auxB;
        this.manejadorDao = manejadorDao;

        ventanaBiblioteca.initEncabezado();

        //validarCalculoPrestamoMulta();

        if(usuario instanceof Profesor)
        {
            ventanaBiblioteca.initMenuU();
            ventanaBiblioteca.initContU();
            ventanaBiblioteca.initPerfilP();
            ventanaBiblioteca.initListU();

            listarTablasUsuarios(usuario.getId());

            ventanaBiblioteca.addBotonesPerfilProfesorListener(new ProfesorUListener());
            ventanaBiblioteca.addBotonAgregarSolicitud(new SolicitudUListener());
            ventanaBiblioteca.addBotonesLibroUListener(new DescargaUListener());
            ventanaBiblioteca.addBotonesMultaUListener(new MultaListener());
        }
        if(usuario instanceof Estudiante)
        {
            ventanaBiblioteca.initMenuU();
            ventanaBiblioteca.initContU();
            ventanaBiblioteca.initPerfilE();
            ventanaBiblioteca.initListU();

            listarTablasUsuarios(usuario.getId());

            ventanaBiblioteca.addBotonesPerfilEsudianteListener(new EstudianteUListener());
            ventanaBiblioteca.addBotonAgregarSolicitud(new SolicitudUListener());
            ventanaBiblioteca.addBotonesLibroUListener(new DescargaUListener());
            ventanaBiblioteca.addBotonesMultaUListener(new MultaListener());
        }
        if(usuario instanceof Empleado)
        {
            ventanaBiblioteca.initMenuE();
            ventanaBiblioteca.initContE();
            ventanaBiblioteca.initListE();

            listarTablasEmpleado();

            ventanaBiblioteca.addBtnDevolucionListener(new DevolucionListener());
            ventanaBiblioteca.addBotonesPrestamoEmpListener(new PrestamoEmpListener());
        }
        else if(usuario == null)
        {
            ventanaBiblioteca.initMenuA();
            ventanaBiblioteca.initContA();
            ventanaBiblioteca.initListA();

            listarTablasAdmin();

            ventanaBiblioteca.addBotonesEmpleadoAdminListener(new EmpleadoListener());
            ventanaBiblioteca.addBotonesAreaAdminListener(new AreaConocimientoListener());
            ventanaBiblioteca.addBotonesAutorAdListener(new AutorListener());
            ventanaBiblioteca.addBotonesEditorialAdListener(new EditorialListener());
            ventanaBiblioteca.addBotonesLibroAdListener(new LibroListener());
            ventanaBiblioteca.addBotonesEjemplarAdListener(new EjemplarListener());
            ventanaBiblioteca.addBotonesDigitalAdListener(new DigitalListener());
            ventanaBiblioteca.addTablaLibroAListener(new TablaLibroAUListener());
            ventanaBiblioteca.addBotonesAutorLibroAdListener(new AutorLibroListener());
        }
        ventanaBiblioteca.addBotonesEncabezadoListener(new EncabezadoListener());
        ventanaBiblioteca.pantallaCompleta();
    }

    private void cerrarSesion()
    {
        ventanaBiblioteca.dispose();
        VentanaLogin a = new VentanaLogin();
        DaoEstudiante b = new DaoEstudiante();
        DaoProfesor d = new DaoProfesor();
        DaoEmpleado e = new DaoEmpleado();
        ControladorLogin c = new ControladorLogin(a, b, d, e);
    }

    private void perfilEstudiante()
    {
        ventanaBiblioteca.setCedulaEstudianteP(usuario.getId());
        ventanaBiblioteca.setNombreEstudianteP(usuario.getNombre());
        ventanaBiblioteca.setCorreoEstudianteP(usuario.getEmail());
        ventanaBiblioteca.setClaveEstudianteP(usuario.getContrasena());
        ventanaBiblioteca.setDireccionEstudianteP(usuario.getDireccion());
        ventanaBiblioteca.setTelefonoEstudianteP(usuario.getTelefono());
        ventanaBiblioteca.setCarreraEstudianteP(((Estudiante) usuario).getCarrera());
        ventanaBiblioteca.setUniversidadEstudianteP(((Estudiante) usuario).getUniversidad());
    }

    private void perfilProfesor()
    {
        ventanaBiblioteca.setCedulaProfesorP(usuario.getId());
        ventanaBiblioteca.setNombreProfesorP(usuario.getNombre());
        ventanaBiblioteca.setCorreoProfesorP(usuario.getEmail());
        ventanaBiblioteca.setClaveProfesorP(usuario.getContrasena());
        ventanaBiblioteca.setDireccionProfesorP(usuario.getDireccion());
        ventanaBiblioteca.setTelefonoProfesorP(usuario.getTelefono());
        ventanaBiblioteca.setTituloProfesorP(((Profesor) usuario).getTitulo());
        ventanaBiblioteca.setDependenciaProfesorP(((Profesor) usuario).getDependencia());
    }

    class EncabezadoListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("cerrarSesion"))
            {
                cerrarSesion();
            }
            if (e.getActionCommand().equalsIgnoreCase("usuario"))
            {
                if(usuario instanceof Profesor)
                {
                    ventanaBiblioteca.pagPerfilProfesor();
                    perfilProfesor();
                }
                else if(usuario instanceof Estudiante)
                {
                    ventanaBiblioteca.pagPerfilEstudiante();
                    perfilEstudiante();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No es posible para su perfil");
                }
            }
        }
    }

    private boolean comprobarCorreo(String email)
    {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    /**************************************************************************
     * Listar
     *************************************************************************/
    public void listarTablasUsuarios(String id)
    {
        if(!manejadorDao.listarSolicitudesUsuario(id).isEmpty())
        {
            listarSolicitudesTablaU(id);
        }
        if(!manejadorDao.listarPrestamosUsuario(id).isEmpty())
        {
            listarPrestamosTablaU(id);
        }
        if(!manejadorDao.listarMultasU(id).isEmpty())
        {
            listarMultasTablaU(id);
        }
        if(!manejadorDao.listarLibros().isEmpty())
        {
            listarLibroTablaU();
        }
    }

    public void listarTablasAdmin()
    {
        if(!manejadorDao.listarEmpleados().isEmpty())
        {
            listarEmpleadosTablaA();
        }
        if(!manejadorDao.listarUsuarios().isEmpty())
        {
            listarUsuariosTablaA();
        }
        if(!manejadorDao.listarSolicitudes().isEmpty())
        {
            listarSolicitudesTablaA();
        }
        if(!manejadorDao.listarPrestamos().isEmpty())
        {
            listarPrestamosTablaA();
        }
        if(!manejadorDao.listarAreas().isEmpty())
        {
            listarAreasTablaA();
        }
        if(!manejadorDao.listarAutores().isEmpty())
        {
            listarAutorTablaAd();
        }
        if(!manejadorDao.listarEditoriales().isEmpty())
        {
            listarEditorialTablaAd();
        }
        if(!manejadorDao.listarLibros().isEmpty())
        {
            listarLibroTablaAd();
        }
        if(!manejadorDao.listarEjemplares().isEmpty())
        {
            listarEjemplarTablaAd();
        }
        if(!manejadorDao.listarDigitales().isEmpty())
        {
            listarDigitalTablaAd();
        }
        if(!manejadorDao.listarDescargas().isEmpty())
        {
            listarDescargasTablaA();
        }
        if(!manejadorDao.listarMultasA().isEmpty())
        {
            listarMultasTablaA();
        }
    }

    public void listarTablasEmpleado()
    {
        if(!manejadorDao.listarSolicitudes().isEmpty())
        {
            listarSolicitudesTablaE();
        }
        if(!manejadorDao.listarPrestamos().isEmpty())
        {
            listarPrestamosTablaE();
        }
        if(!manejadorDao.listarLibros().isEmpty())
        {
            listarTablaPrestamosEmp();
        }
    }

    /**************************************************************************
     * usuario - Admin
     *************************************************************************/
    public void listarUsuariosTablaA()
    {
        ArrayList<Usuario> arrayUsu;
        arrayUsu = manejadorDao.listarUsuarios();
        if(arrayUsu != null)
        {
            String id;
            String nom;
            String email;
            String dir;
            String telefono;

            for (Usuario usuario : arrayUsu) {
                id = usuario.getId();
                nom = usuario.getNombre();
                email = usuario.getEmail();
                dir = usuario.getDireccion();
                telefono = usuario.getTelefono();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getUsuarioAdminTableModel();
                auxModeloTabla.addRow(new Object[]{id, nom, email, dir, telefono});
            }
        }
    }
    /**************************************************************************
     * Estudiante - Usuario
     *************************************************************************/
    class EstudianteUListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                modificarEstudiante();
            }
        }
    }
    private void modificarEstudiante()
    {
        Estudiante auxEstudiante;
        String auxId;
        String auxNombre;
        String auxDireccion;
        String auxTelefono;
        String auxEmail;
        String auxContrasena;
        String auxCarrera;
        String auxUniversidad;

        auxId = ventanaBiblioteca.getCedulaEstudianteP();
        auxNombre = ventanaBiblioteca.getNombreEstudianteP();
        auxDireccion = ventanaBiblioteca.getDireccionEstudianteP();
        auxTelefono = ventanaBiblioteca.getTelefonoEstudianteP();
        auxEmail = ventanaBiblioteca.getCorreoEstudianteP();
        auxContrasena = ventanaBiblioteca.getClaveEstudianteP();
        auxCarrera = ventanaBiblioteca.getCarreraEstudianteP();
        auxUniversidad = ventanaBiblioteca.getUniversidadEstudianteP();
        if(comprobarCamposEstudianteU())
        {
            if (comprobarCorreo(auxEmail))
            {
                auxEstudiante = new Estudiante(auxId, auxNombre, auxEmail, auxContrasena, auxDireccion, auxTelefono, auxCarrera, auxUniversidad);
                if (manejadorDao.modificarEst(auxEstudiante))
                {
                    ventanaBiblioteca.mostrarMensaje("Se ha actualizado la información");
                } else
                {
                    ventanaBiblioteca.mostrarMensajeError("No se ha podido actualizar la información");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Email invalido");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Datos invalidos, recuerde no dejar campos vacios");
        }
    }

    private boolean comprobarCamposEstudianteU()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getClaveEstudianteP().isEmpty() && !ventanaBiblioteca.getNombreEstudianteP().isEmpty() && !ventanaBiblioteca.getDireccionEstudianteP().isEmpty() && !ventanaBiblioteca.getCorreoEstudianteP().isEmpty() && !ventanaBiblioteca.getCarreraEstudianteP().isEmpty() && !ventanaBiblioteca.getUniversidadEstudianteP().isEmpty();
        return valido;
    }

    /**************************************************************************
     * Profesor - Usuario
     *************************************************************************/

    class ProfesorUListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                modificarProfesor();
            }

        }
    }
    private void modificarProfesor()
    {
        Profesor auxProfesor;
        String auxId;
        String auxNombre;
        String auxDireccion;
        String auxTelefono;
        String auxEmail;
        String auxContrasena;
        String auxTitulo;
        String auxDependencia;

        auxId = ventanaBiblioteca.getCedulaProfesorP();
        auxNombre = ventanaBiblioteca.getNombreProfesorP();
        auxDireccion = ventanaBiblioteca.getDireccionProfesorP();
        auxTelefono = ventanaBiblioteca.getTelefonoProfesorP();
        auxEmail = ventanaBiblioteca.getCorreoProfesorP();
        auxContrasena = ventanaBiblioteca.getClaveProfesorP();
        auxTitulo = ventanaBiblioteca.getTituloProfesorP();
        auxDependencia = ventanaBiblioteca.getDependenciaProfesorP();
        if(comprobarCamposProfesorU())
        {
            if(comprobarCorreo(auxEmail))
            {
                auxProfesor = new Profesor(auxId, auxNombre, auxEmail, auxContrasena, auxDireccion, auxTelefono, auxTitulo, auxDependencia);

                if (manejadorDao.modificarPro(auxProfesor))
                {
                    ventanaBiblioteca.mostrarMensaje("Se ha actualizado la información");
                } else
                {
                    ventanaBiblioteca.mostrarMensajeError("No se ha podido actualizar la información");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Email invalido");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Datos invalidos, recuerde no dejar campos vacios");
        }
    }

    private boolean comprobarCamposProfesorU()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getClaveProfesorP().isEmpty() && !ventanaBiblioteca.getNombreProfesorP().isEmpty() && !ventanaBiblioteca.getDireccionProfesorP().isEmpty() && !ventanaBiblioteca.getCorreoProfesorP().isEmpty() && !ventanaBiblioteca.getTituloProfesorP().isEmpty() && !ventanaBiblioteca.getDependenciaProfesorP().isEmpty();
        return valido;
    }

    /**************************************************************************
     * Solicitud - Usuario
     *************************************************************************/

    class SolicitudUListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("enviar"))
            {
                agregarSolicitud();
            }
        }
    }

    public void agregarSolicitud()
    {
        Solicitud solicitud;
        String auxIsbn;
        String auxTitulo;
        String auxDescripcion;

        auxIsbn = ventanaBiblioteca.getTxtIsbnSolicitudU();
        auxTitulo = ventanaBiblioteca.getTxtTituloSolicitudU();
        auxDescripcion = ventanaBiblioteca.getTxaDescripcionSolicitudU();

        if(comprobarCamposSolicitudU())
        {
            solicitud = new Solicitud(usuario.getId(), auxIsbn, auxTitulo, auxDescripcion);
            if(manejadorDao.agregarSolicitud(solicitud) > 0)
            {
                listarSolicitudesTablaUAgregar(solicitud);
                ventanaBiblioteca.mostrarMensaje("Solicitud realizada con exito");
                ventanaBiblioteca.limpiarSolicitudUsuario();
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Solicitud realizada sin exito");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Llene los campos ISBN y Titulo");
        }
    }

    public void listarSolicitudesTablaU(String id)
    {
        ArrayList<Solicitud> arraySol;
        arraySol = manejadorDao.listarSolicitudesUsuario(id);
        if(arraySol != null)
        {
            int auxId;
            String auxIsbn;
            String auxTitulo;
            String auxDescripcion;

            for (Solicitud solicitud : arraySol) {
                auxId = solicitud.getNumero();
                auxIsbn = solicitud.getIsbnLibro();
                auxTitulo = solicitud.getTitulo();
                auxDescripcion = solicitud.getDescripcion();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getSolicitudUTableModel();
                auxModeloTabla.addRow(new Object[]{auxId, auxIsbn, auxTitulo, auxDescripcion});
            }
        }
    }

    public void listarSolicitudesTablaUAgregar(Solicitud solicitud)
    {
        if(solicitud != null)
        {
            solicitud = manejadorDao.consultarUltimaSolicitud(Integer.parseInt(usuario.getId()));

            int auxId;
            String auxIsbn;
            String auxTitulo;
            String auxDescripcion;

                auxId = solicitud.getNumero();
                auxIsbn = solicitud.getIsbnLibro();
                auxTitulo = solicitud.getTitulo();
                auxDescripcion = solicitud.getDescripcion();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getSolicitudUTableModel();
                auxModeloTabla.addRow(new Object[]{auxId, auxIsbn, auxTitulo, auxDescripcion});
        }
    }

    public boolean comprobarCamposSolicitudU()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtIsbnSolicitudU().isEmpty() && !ventanaBiblioteca.getTxtTituloSolicitudU().isEmpty();
        return valido;
    }

    /**************************************************************************
     * Descarga - Usuario
     *************************************************************************/

    class DescargaUListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("descargar"))
            {
                agregarDescarga();
            }
        }
    }

    public void agregarDescarga() {

        Descarga descarga;
        String auxIsbn;
        String auxUrl;
        String auxIdUsuario;
        String auxFechaDescarga;
        String auxHoraDescarga;
        String auxIp;

        if(comprobarCamposLibrodU())
        {
            auxIsbn = ventanaBiblioteca.getTxtIsbnLibroU();
            auxUrl = manejadorDao.buscarDigital(auxIsbn).getUrl();

            if(auxUrl != null)
            {
                auxIdUsuario = usuario.getId();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                Date fecha = new Date();
                auxFechaDescarga = formatoFecha.format(fecha);
                auxHoraDescarga = formatoHora.format(fecha);

                try
                {
                    auxIp = String.valueOf(InetAddress.getLocalHost().getHostAddress());
                }
                catch (UnknownHostException e)
                {
                    auxIp = "0.0.0.0";
                }

                descarga = new Descarga(auxIsbn, auxUrl, auxIdUsuario, auxFechaDescarga, auxHoraDescarga, auxIp);
                Archivo archivo = new Archivo(new File(manejadorDao.buscarLibroIsbn(auxIsbn).getTitulo()));

                if(archivo.descargarArchivo(auxUrl))
                {
                    if(manejadorDao.agregarDescarga(descarga) > 0)
                    {
                        ventanaBiblioteca.mostrarMensaje("Descarga realizada con exito");
                        ventanaBiblioteca.limpiarLibroUsuario();
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("Descarga realizada sin exito");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Descarga realizada sin exito. La URL no existe");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("El libro no tiene version digital");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Llene el campo ISBN");
        }
    }

    public boolean comprobarCamposLibrodU()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtIsbnLibroU().isEmpty();
        return valido;
    }

    /**************************************************************************
     * Descarga - Admin
     *************************************************************************/
    public void listarDescargasTablaA()
    {
        ArrayList<Descarga> arrayDe;
        arrayDe = manejadorDao.listarDescargas();
        if(arrayDe != null)
        {
            String auxIsbn;
            String auxTitulo;
            String auxUrl;
            String auxIdUsuario;
            String auxFechaDescarga;
            String auxHoraDescarga;
            String auxIp;

            for (Descarga descarga : arrayDe) {
                auxIsbn = descarga.getIsbn();
                auxTitulo = manejadorDao.buscarLibroIsbn(auxIsbn).getTitulo();
                auxUrl = descarga.getUrl();
                auxIdUsuario = descarga.getIdUsuario();
                auxFechaDescarga = descarga.getFechaDescarga();
                auxHoraDescarga = descarga.getHoraDescarga();
                auxIp = descarga.getIp();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDescargaAdminTableModel();
                auxModeloTabla.addRow(new Object[]{auxIsbn, auxTitulo, auxIdUsuario, auxIp, auxFechaDescarga, auxHoraDescarga});
            }
        }
    }

    /**************************************************************************
     * Solicitud - Admin
     *************************************************************************/
    public void listarSolicitudesTablaA()
    {
        ArrayList<Solicitud> arraySol;
        arraySol = manejadorDao.listarSolicitudes();
        if(arraySol != null)
        {
            int auxId;
            String auxIsbn;
            String auxTitulo;
            String auxDescripcion;

            for (Solicitud solicitud : arraySol) {
                auxId = solicitud.getNumero();
                auxIsbn = solicitud.getIsbnLibro();
                auxTitulo = solicitud.getTitulo();
                auxDescripcion = solicitud.getDescripcion();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getSolicitudAdminTableModel();
                auxModeloTabla.addRow(new Object[]{auxId, auxIsbn, auxTitulo, auxDescripcion});
            }
        }
    }

    /**************************************************************************
     * Solicitud - Empleado
     *************************************************************************/
    public void listarSolicitudesTablaE()
    {
        ArrayList<Solicitud> arraySol;
        arraySol = manejadorDao.listarSolicitudes();
        if(arraySol != null)
        {
            int auxId;
            String auxIsbn;
            String auxTitulo;
            String auxDescripcion;

            for (Solicitud solicitud : arraySol) {
                auxId = solicitud.getNumero();
                auxIsbn = solicitud.getIsbnLibro();
                auxTitulo = solicitud.getTitulo();
                auxDescripcion = solicitud.getDescripcion();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getSolicitudEmpTableModel();
                auxModeloTabla.addRow(new Object[]{auxId, auxIsbn, auxTitulo, auxDescripcion});
            }
        }
    }

    /**************************************************************************
     * Prestamo - Usuario
     *************************************************************************/
    public void listarPrestamosTablaU(String id)
    {
        ArrayList<Prestamo> arrayPrestamo;
        ArrayList<PrestamoLibro> arrayPrestamoLibros;
        arrayPrestamo = manejadorDao.listarPrestamosUsuario(id);
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoUTableModel();
        if(arrayPrestamo != null)
        {
            int num;
            String nomE;
            String fecha;
            String isbn;
            int numEje;
            String fechaD;
            boolean estado;

            for (Prestamo prestamo : arrayPrestamo) {
                num = prestamo.getNumPrestamo();
                fecha = prestamo.getFechaR();
                nomE = manejadorDao.buscarNombreEmpleado(prestamo.getIdEmpleado());

                arrayPrestamoLibros = manejadorDao.listarPrestamosLibros(num);

                for(PrestamoLibro prestamoLibro: arrayPrestamoLibros)
                {
                    isbn = prestamoLibro.getIsbn();
                    numEje = prestamoLibro.getNumEjemplar();
                    fechaD = prestamoLibro.getFechaDev();
                    estado = prestamoLibro.getEstado();
                    auxModeloTabla.addRow(new Object[]{num, fecha, isbn, numEje, fechaD, nomE, stringEstPres(estado)});
                }
            }
        }
    }

    /**************************************************************************
     * Prestamo - Admin
     *************************************************************************/
    public void listarPrestamosTablaA()
    {
        ArrayList<Prestamo> arrayPrestamo;
        arrayPrestamo = manejadorDao.listarPrestamos();

        ArrayList<PrestamoLibro> arrayPrestamoLibros;

        if(arrayPrestamo != null)
        {
            int num;
            String nomE;
            String fecha;
            String isbn;
            int numEje;
            String fechaD;
            String tituloL;
            String idUsu;
            boolean estado;

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoAdminTableModel();

            for (Prestamo prestamo : arrayPrestamo)
            {
                num = prestamo.getNumPrestamo();
                idUsu = prestamo.getIdUsuario();
                fecha = prestamo.getFechaR();
                nomE = manejadorDao.buscarNombreEmpleado(prestamo.getIdEmpleado());

                arrayPrestamoLibros = manejadorDao.listarPrestamosLibros(num);

                for (PrestamoLibro prestamoLibro : arrayPrestamoLibros)
                {
                    isbn = prestamoLibro.getIsbn();
                    tituloL = manejadorDao.buscarLibroIsbn(isbn).getTitulo();
                    numEje = prestamoLibro.getNumEjemplar();
                    fechaD = prestamoLibro.getFechaDev();
                    estado = prestamoLibro.getEstado();
                    auxModeloTabla.addRow(new Object[]{num, isbn, numEje, tituloL, idUsu, nomE, fecha, fechaD, stringEstPres(estado)});
                }
            }
        }
    }

    /**************************************************************************
     * Prestamo - Empleado
     *************************************************************************/
    class PrestamoEmpListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarEjemPrestamo();
            }
            if (e.getActionCommand().equalsIgnoreCase("prestar"))
            {
                PrestarPrestamo();
            }
            if (e.getActionCommand().equalsIgnoreCase("cancelar"))
            {
                cancelarPrestamos();
            }
        }
    }

    public void PrestarPrestamo()
    {
        int cantLibrosPres;
        DefaultTableModel auxModeloTablaLibros = (DefaultTableModel) ventanaBiblioteca.getLibrosPresEmpTabMod();
        cantLibrosPres = auxModeloTablaLibros.getRowCount();

        if(cantLibrosPres >0)
        {
            if(validarCamposPrestamo())
            {
                Prestamo prestamo;
                String cedulaU;
                String fechaR;
                String cedulaE;
                int numPres;
                cedulaU = ventanaBiblioteca.getCedulaPresEmp();
                if(manejadorDao.usuariosNoEmpleados(cedulaU).getNombre() != null)
                {
                    try
                    {
                        cedulaE = usuario.getId();
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                        fechaR = formatoFecha.format(ventanaBiblioteca.getFechaRPresEmp());
                            prestamo = new Prestamo();
                            prestamo.setIdUsuario(cedulaU);
                            prestamo.setIdEmpleado(cedulaE);
                            prestamo.setFechaR(fechaR);

                            if (manejadorDao.agregarPrestamo(prestamo) > -1) {
                                numPres = manejadorDao.listarUltimoPres();
                                agregarPrestamoslibro(numPres);
                                ventanaBiblioteca.limpiarPrestamoEmpleado();
                            }
                            else
                            {
                                ventanaBiblioteca.mostrarMensajeError("Ocurrio un error");
                            }
                        }
                    catch(Exception ex)
                        {
                            System.out.println(ex);
                            ventanaBiblioteca.mostrarMensajeError("Digite bien el campo cedula");
                        }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No existe el usuario asocidado con esa cedula");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("No deje los campos fecha realizacion ni cedula vacios");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("No puede hacer un prestamo vacio");
        }
    }

    public void agregarPrestamoslibro(int numPres)
    {
        PrestamoLibro prestamoLibro;
        String isbn;
        int ejemplar;
        String fechaD;
        DefaultTableModel auxModeloTablaLibros = (DefaultTableModel) ventanaBiblioteca.getLibrosPresEmpTabMod();
        for(int i = 0; i < auxModeloTablaLibros.getRowCount(); i++)
        {
            isbn = auxModeloTablaLibros.getValueAt(i,0).toString();
            ejemplar = (int) auxModeloTablaLibros.getValueAt(i,1);
            fechaD = auxModeloTablaLibros.getValueAt(i,2).toString();
            prestamoLibro = new PrestamoLibro(numPres, isbn, ejemplar, fechaD);
            manejadorDao.modificarEstadoEjem(isbn, ejemplar, false);
            if(manejadorDao.agregarPrestamoLibro(prestamoLibro) > 0)
            {
                listarPrestamosDev(numPres, isbn, ejemplar, fechaD);
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Ocurrio un error al agregar un libro al prestamo");
            }
        }
        ventanaBiblioteca.mostrarMensaje("Prestamo agregado con exito");
        auxModeloTablaLibros.setRowCount(0);
    }

    public void listarPrestamosDev(int numPres, String isbn, int ejemplar, String fechaDev)
    {
        Prestamo prestamo;
        String idUsu;
        String idE;
        String fecha;
        String fechaD;

        prestamo = manejadorDao.consultarPrestamo(numPres);
        idUsu = prestamo.getIdUsuario();
        idE = prestamo.getIdEmpleado();
        fecha = prestamo.getFechaR();
        fechaD = fechaDev;
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDevEmpTabMod();
        auxModeloTabla.addRow(new Object[]{numPres, isbn, ejemplar, idUsu, idE , fecha, fechaD});
    }

    public boolean validarCamposPrestamo()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getCedulaPresEmp().isEmpty() && ventanaBiblioteca.getFechaRPresEmp()!=null;
        return valido;
    }

    public void agregarEjemPrestamo()
    {
        int fila = ventanaBiblioteca.getFilaPresEmp();
        if(fila > -1)
        {
            String isbn;
            int ejemplar;
            String fechaDev;

            try
            {
                isbn = ventanaBiblioteca.getIsbnPresEmp();
                ejemplar = ventanaBiblioteca.getEjemplarPresEmp();
                 if(validarFilaNoRepetida(isbn, ejemplar))
                 {
                     SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                     fechaDev = formatoFecha.format(ventanaBiblioteca.getFechaDPresEmp());
                     if (!fechaDev.equals(""))
                     {
                         DefaultTableModel auxModeloTablaLib = (DefaultTableModel) ventanaBiblioteca.getLibrosPresEmpTabMod();
                         auxModeloTablaLib.addRow(new Object[]{isbn, ejemplar, fechaDev});
                         eliminarFilaPrestamo(fila);
                         ventanaBiblioteca.limpiarPrestamoEmpleado();
                     }
                     else
                     {
                         ventanaBiblioteca.mostrarMensajeError("Digite bien el campo fecha devolucion");
                     }
                 }
                 else
                 {
                     ventanaBiblioteca.mostrarMensajeError("Este ejemplar ya esta en el prestamo");
                 }
            }
            catch (Exception ex)
            {
                ventanaBiblioteca.mostrarMensajeError("Digite bien los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione una fila");
        }
    }

    public void eliminarFilaPrestamo(int fila)
    {
        DefaultTableModel auxModeloTablaPres = (DefaultTableModel) ventanaBiblioteca.getPrestamoEmpTabMod();
        auxModeloTablaPres.removeRow(fila);
    }

    public void cancelarPrestamos()
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibrosPresEmpTabMod();
        int libros = auxModeloTabla.getRowCount();
        if(libros > 0)
        {
            String isbn;
            int ejemplar;
            for(int i = 0; i< libros; i++)
            {
                isbn = auxModeloTabla.getValueAt(i,0).toString();
                ejemplar = (int) auxModeloTabla.getValueAt(i,1);
                listarLibroCancelar(isbn, ejemplar);
            }
            auxModeloTabla.setRowCount(0);
            ventanaBiblioteca.mostrarMensaje("Prestamo cancelado");
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("No hay ningun ejemplar en la lista");
        }
    }

    public void listarLibroCancelar(String isbn, int ejemplar)
    {
        Libro libro;
        libro = manejadorDao.buscarLibroIsbn(isbn);

        String titulo = libro.getTitulo();
        ArrayList<String> nombresAutores = manejadorDao.getNombresAutoresLibro(isbn);
        String autores = String.join(", ", nombresAutores);
        String nomEditorial = manejadorDao.getNombreEditorial(libro.getCodEditorial());
        int anhoPublicacion = libro.getAnhoPublicacion();
        String idioma = libro.getIdioma();
        String numPaginas = libro.getNumPaginas();

        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoEmpTabMod();
        auxModeloTabla.addRow(new Object[]{isbn, ejemplar, titulo, autores, nomEditorial, anhoPublicacion, idioma, numPaginas});

    }

    public void listarTablaPrestamosEmp()
    {
        ArrayList<Libro> arrayLibro;
        arrayLibro = manejadorDao.getLibrosEjemDisp();
        if(arrayLibro != null)
        {
            String isbn;
            String titulo;

            ArrayList<String> nombresAutores;
            String autores;

            int codEditorial;
            String nomEditorial;

            ArrayList<Integer> ejemplares;
            int anhoPublicacion;
            String numPaginas;
            String idioma;


            for (Libro libro : arrayLibro) {
                isbn = libro.getIsbn();
                titulo = libro.getTitulo();
                codEditorial = libro.getCodEditorial();
                nomEditorial = manejadorDao.getNombreEditorial(codEditorial);
                anhoPublicacion = libro.getAnhoPublicacion();
                numPaginas = libro.getNumPaginas();
                idioma = libro.getIdioma();
                nombresAutores = manejadorDao.getNombresAutoresLibro(isbn);
                autores = String.join(", ", nombresAutores);

                ejemplares = manejadorDao.listarEjemplaresDisponibles(isbn);
                for(Integer ejemplar: ejemplares)
                {
                    DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoEmpTabMod();
                    auxModeloTabla.addRow(new Object[]{isbn, ejemplar, titulo, autores, nomEditorial, anhoPublicacion, idioma, numPaginas});
                }
            }
        }
    }

    public String stringEstPres(boolean b)
    {
        String cadena;
        if(!b)
        {
            cadena = "NO DEVUELTO";
        }
        else
        {
            cadena = "DEVUELTO";
        }
        return cadena;
    }

    public boolean validarFilaNoRepetida(String isbn, int ejemplar)
    {
        boolean valido = true;
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibrosPresEmpTabMod();
        for(int i=0; i< auxModeloTabla.getRowCount(); i++)
        {
            if(isbn.equals(auxModeloTabla.getValueAt(i,0)) && ejemplar == (int) auxModeloTabla.getValueAt(i,1))
            {
                valido = false;
                break;
            }
        }
        return valido;
    }
    /**************************************************************************
     * Devolucion - Empleado
     *************************************************************************/
    class DevolucionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("devolver"))
            {
                devolverLibro();
            }
        }
    }


    public void listarPrestamosTablaE()
    {
        ArrayList<Prestamo> arrayPrestamo;
        arrayPrestamo = manejadorDao.listarPrestamos();

        ArrayList<PrestamoLibro> arrayPrestamoLibros;

        if(arrayPrestamo != null)
        {
            int num;
            String idE;
            String fecha;
            String isbn;
            int numEje;
            String fechaD;
            String idUsu;

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDevEmpTabMod();

            for (Prestamo prestamo : arrayPrestamo)
            {
                num = prestamo.getNumPrestamo();
                idUsu = prestamo.getIdUsuario();
                fecha = prestamo.getFechaR();
                idE = prestamo.getIdEmpleado();
                if(!manejadorDao.listarPrestamosActivos(num).isEmpty())
                {
                    arrayPrestamoLibros = manejadorDao.listarPrestamosActivos(num);

                    for (PrestamoLibro prestamoLibro : arrayPrestamoLibros)
                    {
                        isbn = prestamoLibro.getIsbn();
                        numEje = prestamoLibro.getNumEjemplar();
                        fechaD = prestamoLibro.getFechaDev();
                        auxModeloTabla.addRow(new Object[]{num, isbn, numEje, idUsu, idE , fecha, fechaD});
                    }
                }
            }
        }
    }



    public void devolverLibro()
    {
        int fila = ventanaBiblioteca.getFilaSeleccionadaDev();
        if(fila > -1)
        {
            int numPres;
            int numEjem;
            String isbn;
            String cedula;
            String fechaDevolucion;
            try
            {
                numPres = ventanaBiblioteca.getNumPresDevEmp();
                numEjem = ventanaBiblioteca.getNumEjemDevEmp();
                isbn = ventanaBiblioteca.getIsbnDev();
                cedula = ventanaBiblioteca.getCedulaDevUsu();

                Ejemplar ejemplar = manejadorDao.buscarEjemplar(isbn, numEjem);
                PrestamoLibro presLb = manejadorDao.getPrestamoLib(numPres,isbn,numEjem);
                fechaDevolucion = presLb.getFechaDev();

                if(ejemplar != null && presLb != null)
                {
                    if(!ejemplar.getEstado() && !presLb.getEstado())
                    {
                        if (cambiarEstadosDev(numPres, numEjem, isbn, true))
                        {
                            ventanaBiblioteca.mostrarMensaje("Libro devuelto con exito");
                            ventanaBiblioteca.limpiarDevolucionEmpleado();
                            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDevEmpTabMod();
                            auxModeloTabla.removeRow(fila);
                            listarEjemplaresDevueltos(isbn, numEjem);
                            ventanaBiblioteca.deseleccionarFilaDev();
                            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = formatoFecha.parse(fechaDevolucion);
                            agregarMulta(cedula, isbn, numEjem, date);
                        }
                        else
                        {
                            ventanaBiblioteca.mostrarMensajeError("No se pudo devolver el libro");
                        }
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("El ejemplar ya fue devuelto o ocurrio un error");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No existe el ejemplar y/o el prestamo");
                }
            }
            catch (NumberFormatException ex)
            {
                ventanaBiblioteca.mostrarMensajeError("Digite numeros en los campos # prestamo y # ejemplar");
            } catch (ParseException e) {
                ventanaBiblioteca.mostrarMensajeError("Ocurrio un error relacionado con la fecha");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione una fila");
        }
    }

    public void listarEjemplaresDevueltos(String isbn, int ejemplar)
    {
        Libro libro;
        libro = manejadorDao.buscarLibroIsbn(isbn);

        String titulo = libro.getTitulo();
        ArrayList<String> nombresAutores = manejadorDao.getNombresAutoresLibro(isbn);
        String autores = String.join(", ", nombresAutores);
        String nomEditorial = manejadorDao.getNombreEditorial(libro.getCodEditorial());
        int anhoPublicacion = libro.getAnhoPublicacion();
        String idioma = libro.getIdioma();
        String numPaginas = libro.getNumPaginas();

        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoEmpTabMod();
        auxModeloTabla.addRow(new Object[]{isbn, ejemplar, titulo, autores, nomEditorial, anhoPublicacion, idioma, numPaginas});
    }
    public boolean cambiarEstadosDev(int numPres, int numEjem, String isbn, boolean estado)
    {
        if(manejadorDao.modificarEstPresLib(numPres, numEjem, isbn, estado) && manejadorDao.modificarEstadoEjem(isbn, numEjem, estado))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean validarCamposDevo()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getCedulaDevEmp().equals("") && !ventanaBiblioteca.getCedulaDevUsu().equals(" ") && !ventanaBiblioteca.getIsbnDev().equals("");
        return valido;
    }

    /**************************************************************************
     * Empleado - admin
     *************************************************************************/
    class EmpleadoListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarEmpleado();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarEmpleado();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarEmpleado();
            }
        }
    }

    public void listarEmpleadosTablaA()
    {
        ArrayList<Empleado> arrayEmpleado;
        arrayEmpleado = manejadorDao.listarEmpleados();
        if(arrayEmpleado != null)
        {
            String id;
            String nom;
            String email;
            String clave;
            String dir;
            String telefono;
            String cargo;

            for (Empleado empleado : arrayEmpleado) {
                id = empleado.getId();
                nom = empleado.getNombre();
                email = empleado.getEmail();
                clave = empleado.getContrasena();
                dir = empleado.getDireccion();
                telefono = empleado.getTelefono();
                cargo = empleado.getCargo();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEmpleadoTableModel();
                auxModeloTabla.addRow(new Object[]{id, nom, email, clave, dir, telefono, cargo});
            }
        }
    }
    public boolean comprobarCamposEmpleadoA()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtCedulaEmpleadoA().isEmpty() && !ventanaBiblioteca.getTxtNombreEmpleadoA().isEmpty() && !ventanaBiblioteca.getTxtClaveEmpleadoA().isEmpty() && !ventanaBiblioteca.getTxtCorreoEmpleadoA().isEmpty() && !ventanaBiblioteca.getTxtTelefonoEmpleadoA().isEmpty() && !ventanaBiblioteca.getTxtCargoEmpleadoA().isEmpty() && !ventanaBiblioteca.getTxtDireccionEmpleadoA().isEmpty();
        return valido;
    }

    public void agregarEmpleado()
    {
        Empleado empleado;
        String cedula;
        String clave;
        String nombre;
        String direccion;
        String correo;
        String telefono;
        String cargo;

        if(comprobarCamposEmpleadoA())
        {
            cedula = ventanaBiblioteca.getTxtCedulaEmpleadoA();
            clave = ventanaBiblioteca.getTxtClaveEmpleadoA();
            nombre = ventanaBiblioteca.getTxtNombreEmpleadoA();
            direccion = ventanaBiblioteca.getTxtDireccionEmpleadoA();
            correo = ventanaBiblioteca.getTxtCorreoEmpleadoA();
            telefono = ventanaBiblioteca.getTxtTelefonoEmpleadoA();
            cargo = ventanaBiblioteca.getTxtCargoEmpleadoA();

            if(comprobarCorreo(correo)) {
                empleado = new Empleado(cedula, nombre, correo, clave, direccion, telefono, cargo);
                if (manejadorDao.agregarEmpleado(empleado) > 0) {
                    listarEmpleadoTablaAdAgregar(empleado);
                    ventanaBiblioteca.mostrarMensaje("Empleado agregado con exito");
                    ventanaBiblioteca.limpiarEmpleadoAdmin();
                } else {
                    ventanaBiblioteca.mostrarMensajeError("Empleado agregado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Ingrese un correo valido");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
        }
    }

    public void listarEmpleadoTablaAdAgregar(Empleado empleado)
    {
        if(empleado != null)
        {
            String cedula;
            String clave;
            String nombre;
            String direccion;
            String correo;
            String telefono;
            String cargo;

            cedula = empleado.getId();
            clave = empleado.getContrasena();
            nombre = empleado.getNombre();
            direccion = empleado.getDireccion();
            correo = empleado.getEmail();
            telefono = empleado.getTelefono();
            cargo = empleado.getCargo();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEmpleadoTableModel();
            auxModeloTabla.addRow(new Object[]{cedula, nombre, correo, clave, direccion, telefono, cargo});
        }
    }

    public void editarEmpleado()
    {
        Empleado empleado;
        String cedula;
        String correo;

        cedula = ventanaBiblioteca.getTxtCedulaEmpleadoA();
        empleado = manejadorDao.buscarEmpleado(cedula);

        if(ventanaBiblioteca.getFilaSeleccionadaEmpleado() > -1)
        {
            if (empleado != null)
            {
                correo = ventanaBiblioteca.getTxtCorreoEmpleadoA();
                if (comprobarCamposEmpleadoA())
                {
                    if(comprobarCorreo(correo))
                    {
                        empleado.setContrasena(ventanaBiblioteca.getTxtClaveEmpleadoA());
                        empleado.setNombre(ventanaBiblioteca.getTxtNombreEmpleadoA());
                        empleado.setEmail(ventanaBiblioteca.getTxtCorreoEmpleadoA());
                        empleado.setDireccion(ventanaBiblioteca.getTxtDireccionEmpleadoA());
                        empleado.setTelefono(ventanaBiblioteca.getTxtTelefonoEmpleadoA());
                        empleado.setCargo(ventanaBiblioteca.getTxtCargoEmpleadoA());

                        if (manejadorDao.editarEmpleado(empleado))
                        {
                            ventanaBiblioteca.mostrarMensaje("Empleado editado con exito");
                            listarEmpleadoTabAdEditar(empleado);
                            ventanaBiblioteca.deseleccionarFilaTablaEmpleado();
                            ventanaBiblioteca.limpiarEmpleadoAdmin();
                        }
                        else
                        {
                            ventanaBiblioteca.mostrarMensajeError("Empleado editado sin exito");
                        }
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("Ingrese un correo valido");
                    }

                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("No se encontro el empleado");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un empleado");
        }
    }

    public void listarEmpleadoTabAdEditar(Empleado empleado)
    {
        if(empleado != null) {
            String clave;
            String nombre;
            String direccion;
            String correo;
            String telefono;
            String cargo;

            clave = empleado.getContrasena();
            nombre = empleado.getNombre();
            direccion = empleado.getDireccion();
            correo = empleado.getEmail();
            telefono = empleado.getTelefono();
            cargo = empleado.getCargo();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEmpleadoTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaEmpleado();

            auxModeloTabla.setValueAt(nombre, auxFila, 1);
            auxModeloTabla.setValueAt(correo, auxFila, 2);
            auxModeloTabla.setValueAt(clave, auxFila, 3);
            auxModeloTabla.setValueAt(direccion, auxFila, 4);
            auxModeloTabla.setValueAt(telefono, auxFila, 5);
            auxModeloTabla.setValueAt(cargo, auxFila, 6);
        }
    }

    public void eliminarEmpleado()
    {
        String id = ventanaBiblioteca.getTxtCedulaEmpleadoA();
        Empleado empleado = manejadorDao.buscarEmpleado(id);

        if(ventanaBiblioteca.getFilaSeleccionadaEmpleado() > -1)
        {
            if(empleado != null)
            {
                if(manejadorDao.eliminarEmpleado(id))
                {
                    ventanaBiblioteca.mostrarMensaje("Empleado eliminado con exito");
                    ventanaBiblioteca.limpiarEmpleadoAdmin();
                    listarEmpleadoTabAdEliminar();
                    ventanaBiblioteca.deseleccionarFilaTablaEmpleado();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Empleado eliminado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("No se encontro el empleado");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un empleado");
        }

    }

    public void listarEmpleadoTabAdEliminar()
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEmpleadoTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaEmpleado();
        auxModeloTabla.removeRow(auxFila);
    }

    /**************************************************************************
     Controlador_AreaConocimiento
     * AreaConocimiento - admin
     *************************************************************************/

    class AreaConocimientoListener implements ActionListener
    {
       @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarArea();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarArea();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarArea();
            }
            if (e.getActionCommand().equalsIgnoreCase("RELACIONAR"))
            {
                relacionarArea();
            }
        }
    }
      
    public void listarAreasTablaA()
    {
        ArrayList<AreaConocimiento> arrayArea;
        arrayArea = manejadorDao.listarAreas();
        if(arrayArea != null)
        {
            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();

            int codArea;
            String nomArea;
            String descripcion;
            String nomAreasHija;

            for (AreaConocimiento area : arrayArea)
            {
                codArea = area.getCodigoArea();
                nomArea = area.getNomArea();
                descripcion = area.getDescripcion();
                nomAreasHija = manejadorDao.consultarCodAreasConformaString(codArea);

                auxModeloTabla.addRow(new Object[]{codArea, nomArea, descripcion, nomAreasHija});
            }
        }
    }
    public boolean comprobarCamposAreaA()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtNombreAreaA().isEmpty();
        return valido;
    }

    public void listarAreaTablaAdAgregar(AreaConocimiento area)
    {
        if(area != null)
        {
            area = manejadorDao.buscarUltimoArea();

            int codArea;
            String nomArea;
            String descripcion;
            String areaHija;

            codArea = area.getCodigoArea();
            nomArea = area.getNomArea();
            descripcion = area.getDescripcion();
            areaHija = area.getAreaHija();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();
            auxModeloTabla.addRow(new Object[]{codArea, nomArea, descripcion, areaHija});
        }
    }

    public void relacionarArea()
    {
        AreaConocimiento areaConocimiento1;
        AreaConocimiento areaConocimiento2;
        AreaConforma areaConforma;

        int codArea1;
        int codArea2;

        try
        {
            codArea1 = Integer.parseInt(ventanaBiblioteca.getTxtCodigoArea1A());
            codArea2 = Integer.parseInt(ventanaBiblioteca.getTxtCodigoArea2A());

            if(codArea2 != -1)
            {
                areaConocimiento1 = manejadorDao.buscarArea(codArea1);
                areaConocimiento2 = manejadorDao.buscarArea(codArea2);

                if(areaConocimiento1 != null && areaConocimiento2 != null)
                {
                    areaConforma = new AreaConforma(codArea1, codArea2);

                    if(codArea1 == codArea2)
                    {
                        ventanaBiblioteca.mostrarMensajeError("No se puede relacionar la area consigo misma");
                    }
                    else if(manejadorDao.agregarAreaConforma(areaConforma))
                    {
                        listarAreaTabAdEditar(areaConocimiento1);
                        ventanaBiblioteca.mostrarMensaje("Areas relacionadas con exito");
                        ventanaBiblioteca.deseleccionarFilaTablaArea();
                        ventanaBiblioteca.limpiarAreaAdmin();
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("Area relacionada sin exito");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Area relacionada sin exito");
                }
            }
        }
        catch (NumberFormatException e)
        {
            ventanaBiblioteca.mostrarMensajeError("Ingrese un codigo de area valido");
        }
    }

    public void agregarArea()
    {
        AreaConocimiento area;
        int codArea;
        String nomArea;
        String descripcion;

        codArea = Integer.parseInt(ventanaBiblioteca.getTxtCodAreaA());
        if(codArea == 0)
        {
            if (comprobarCamposAreaA())
            {
                try
                {
                    nomArea = ventanaBiblioteca.getTxtNombreAreaA();
                    descripcion = ventanaBiblioteca.getTxaDescripcionAreaA();

                    area = new AreaConocimiento(nomArea, descripcion);

                    if (manejadorDao.agregarArea(area) > 0)
                    {
                        listarAreaTablaAdAgregar(area);
                        ventanaBiblioteca.mostrarMensaje("Area agregada con exito");
                        ventanaBiblioteca.limpiarAreaAdmin();
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("No se pudo crear el area");
                    }
                }
                catch (NumberFormatException e)
                {
                    ventanaBiblioteca.mostrarMensajeError("Llene el campo del nombre");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene el campo del nombre");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Deseleccione el area");
        }
    }

    public void listarAreaTabAdEditar(AreaConocimiento area){
        if(area != null)
        {
            int codArea;
            String nomArea;
            String descripcion;
            String nomAreasHija;

            codArea = area.getCodigoArea();
            nomArea = area.getNomArea();
            descripcion = area.getDescripcion();
            nomAreasHija = manejadorDao.consultarCodAreasConformaString(codArea);

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaArea();

            auxModeloTabla.setValueAt(nomArea, auxFila, 1);
            auxModeloTabla.setValueAt(descripcion, auxFila, 2);
            auxModeloTabla.setValueAt(nomAreasHija, auxFila, 3);
        }
    }

    public void editarArea()
    {
        AreaConocimiento area;
        int codArea;

        codArea = Integer.parseInt(ventanaBiblioteca.getTxtCodAreaA());
        area = manejadorDao.buscarArea(codArea);

        if(codArea != 0)
        {
            if(comprobarCamposAreaA())
            {
                area.setNomArea(ventanaBiblioteca.getTxtNombreAreaA());
                area.setDescripcion(ventanaBiblioteca.getTxaDescripcionAreaA());

                if(manejadorDao.editarArea(area))
                {
                    listarAreaTabAdEditar(area);
                    editarAreaHija(area);

                    ventanaBiblioteca.mostrarMensaje("Area editado con exito");
                    ventanaBiblioteca.deseleccionarFilaTablaArea();
                    ventanaBiblioteca.limpiarAreaAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Area editada sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene el campo del nombre");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un area");
        }
    }

    public void listarAreaTablaAdEliminar(AreaConocimiento area)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaArea();
        auxModeloTabla.removeRow(auxFila);
    }

    public void eliminarArea()
    {
        int codArea = Integer.parseInt(ventanaBiblioteca.getTxtCodAreaA());
        AreaConocimiento area = manejadorDao.buscarArea(codArea);

        if (codArea != 0)
        {
            if(!manejadorDao.consultarAreaLibro(codArea))
            {
                eliminarAreaHija(area);
                manejadorDao.eliminarAreasPadre(codArea);
            }

           if (manejadorDao.eliminarArea(codArea))
           {
               listarAreaTablaAdEliminar(area);

               ventanaBiblioteca.mostrarMensaje("Area eliminada");
               ventanaBiblioteca.deseleccionarFilaTablaArea();
               ventanaBiblioteca.limpiarAreaAdmin();
           }
           else
           {
               ventanaBiblioteca.mostrarMensajeError("Area eliminada sin exito");
           }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un area");
        }
    }

    public void editarAreaHija(AreaConocimiento area)
    {
        ArrayList<Integer> codAreasConformadas = manejadorDao.listarCodAreasConforman(area.getCodigoArea());
        if(!codAreasConformadas.isEmpty())
        {
            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();

            for(int auxCodArea: codAreasConformadas)
            {
                String nomAreasHijas = manejadorDao.consultarCodAreasConformaString(auxCodArea);
                auxModeloTabla.setValueAt(nomAreasHijas, indexId(auxCodArea), 3);
            }
        }
    }

    public void eliminarAreaHija(AreaConocimiento area)
    {
        ArrayList<Integer> codAreasConformadas = manejadorDao.listarCodAreasConforman(area.getCodigoArea());
        if(!codAreasConformadas.isEmpty())
        {
            manejadorDao.eliminarAreasHija(area.getCodigoArea());
            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();

            for(int auxCodArea: codAreasConformadas)
            {
                String nomAreasHijas = manejadorDao.consultarCodAreasConformaString(auxCodArea);
                auxModeloTabla.setValueAt(nomAreasHijas, indexId(auxCodArea), 3);
            }
        }
    }

    public int indexId(int codArea)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();
        ArrayList<Integer> index = new ArrayList<>();

        for(int i = 0; i<auxModeloTabla.getRowCount();i++)
        {
            index.add((int)auxModeloTabla.getValueAt(i,0)); //get the all row values at column index 0
        }
        return index.indexOf(codArea);
    }

    /**************************************************************************
    Controlador_Autor
    * Autor - admin
    *************************************************************************/
    class AutorListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarAutor();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarAutor();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarAutor();
            }
        }
    }

    public void listarAutorTablaAd()
    {
        ArrayList<Autor> arrayList;
        arrayList = manejadorDao.listarAutores();

        int cod;
        String primerN;
        String segundoN;
        String primerA;
        String segundoA;
        if(arrayList != null)
        {
            for(Autor autor : arrayList)
            {
                cod = autor.getCodAutor();
                primerN = autor.getPrimerNombre();
                segundoN = autor.getSegundoNombre();
                primerA =  autor.getPrimerApellido();
                segundoA = autor.getSegundoApellido();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorAdminTableModel();
                auxModeloTabla.addRow(new Object[]{cod, primerN, segundoN, primerA, segundoA});
            }
        }
    }

    public void agregarAutor()
    {
        Autor autor;
        String primerN;
        String segundoN;
        String primerA;
        String segundoA;
        if(ventanaBiblioteca.getFilaSeleccionadaAutor() <1)
        {
            if (comprobarCamposAutor())
            {
                primerN = ventanaBiblioteca.getTxtPrimerNomAu();
                segundoN = ventanaBiblioteca.getTxtSegundoNomAu();
                primerA = ventanaBiblioteca.getTxtPrimerApeAu();
                segundoA = ventanaBiblioteca.getTxtSegundoApeAu();
                autor = new Autor(primerN, segundoN, primerA, segundoA);

                if (manejadorDao.agregarAutor(autor) > 0)
                {
                    autor.setCodAutor(manejadorDao.ultimoCodigoAutor());
                    listarAutorAgregar(autor);
                    ventanaBiblioteca.mostrarMensaje("Autor agregado con exito");
                    ventanaBiblioteca.limpiarAutorAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Autor agregado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene el campo de primer nombre y primer apellido");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Deseleccione al autor");
        }
    }

    public void listarAutorAgregar(Autor autor)
    {
        if(autor != null)
        {
            int cod;
            String primerN;
            String segundoN;
            String primerA;
            String segundoA;

            cod = autor.getCodAutor();
            primerN = autor.getPrimerNombre();
            segundoN = autor.getSegundoNombre();
            primerA = autor.getPrimerApellido();
            segundoA = autor.getSegundoApellido();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorAdminTableModel();
            auxModeloTabla.addRow(new Object[]{cod, primerN, segundoN, primerA, segundoA});
        }
    }

    public void editarAutor()
    {
        Autor autor;
        int codAutor;
        String primerN;
        String segundoN;
        String primerA;
        String segundoA;

        if (ventanaBiblioteca.getFilaSeleccionadaAutor() > -1) {
            if (comprobarCamposAutor()) {
                codAutor = (int) ventanaBiblioteca.getAutorAdminTableModel().getValueAt(ventanaBiblioteca.getFilaSeleccionadaAutor(), 0);
                System.out.println(codAutor);
                primerN = ventanaBiblioteca.getTxtPrimerNomAu();
                segundoN = ventanaBiblioteca.getTxtSegundoNomAu();
                primerA = ventanaBiblioteca.getTxtPrimerApeAu();
                segundoA = ventanaBiblioteca.getTxtSegundoApeAu();
                autor = new Autor(codAutor, primerN, segundoN, primerA, segundoA);

                if (manejadorDao.modificarAutor(autor)) {
                    listarAutorEditar(autor);
                    ventanaBiblioteca.mostrarMensaje("Autor editado con exito");
                    ventanaBiblioteca.limpiarAutorAdmin();
                    ventanaBiblioteca.deseleccionarFilaTablaAutor();
                } else {
                    ventanaBiblioteca.mostrarMensajeError("Autor editado sin exito");
                }
            } else {
                ventanaBiblioteca.mostrarMensajeError("Llene el campo de primer nombre y primer apellido");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un autor");
        }
    }

    public void listarAutorEditar(Autor autor)
    {
        if(autor != null)
        {
            String primerN;
            String segundoN;
            String primerA;
            String segundoA;

            primerN = autor.getPrimerNombre();
            segundoN = autor.getSegundoNombre();
            primerA = autor.getPrimerApellido();
            segundoA = autor.getSegundoApellido();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaAutor();

            auxModeloTabla.setValueAt(primerN, auxFila, 1);
            auxModeloTabla.setValueAt(segundoN, auxFila, 2);
            auxModeloTabla.setValueAt(primerA, auxFila, 3);
            auxModeloTabla.setValueAt(segundoA, auxFila, 4);
        }
    }

    public void eliminarAutor()
    {
        if(ventanaBiblioteca.getFilaSeleccionadaAutor() >0)
        {
            int codAutor;
            codAutor = (int) ventanaBiblioteca.getAutorAdminTableModel().getValueAt(ventanaBiblioteca.getFilaSeleccionadaAutor(), 0);
            if (manejadorDao.eliminarAutor(codAutor))
            {
                ventanaBiblioteca.mostrarMensaje("Autor eliminado con exito");
                ventanaBiblioteca.limpiarAutorAdmin();
                listarAutorEliminar();
                ventanaBiblioteca.deseleccionarFilaTablaAutor();
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Autor eliminado sin exito");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un autor");
        }
    }

    public void listarAutorEliminar()
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorAdminTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaAutor();
        auxModeloTabla.removeRow(auxFila);
    }

    public boolean comprobarCamposAutor()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtPrimerNomAu().isEmpty() && !ventanaBiblioteca.getTxtPrimerApeAu().isEmpty();
        return valido;
    }

    /**************************************************************************
     Controlador_Editorial
     * Editorial - admin
     *************************************************************************/

    class EditorialListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarEditorial();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarEditorial();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarEditorial();
            }
        }
    }

    public void listarEditorialTablaAd()
    {
        ArrayList<Editorial> arrayEditorial;
        arrayEditorial = manejadorDao.listarEditoriales();
        if(arrayEditorial != null)
        {
            int codEditorial;
            String nomEditorial;
            String paginaWeb;
            String paisOrigen;

            for (Editorial editorial : arrayEditorial) {
                codEditorial = editorial.getCodEditorial();
                nomEditorial = editorial.getNomEditorial();
                paginaWeb = editorial.getPaginaWeb();
                paisOrigen = editorial.getPaisOrigen();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEditorialAdminTableModel();
                auxModeloTabla.addRow(new Object[]{codEditorial, nomEditorial, paisOrigen, paginaWeb});
            }
        }
    }
    public boolean comprobarCamposEditorialA()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtNombreEditorialA().isEmpty() && !ventanaBiblioteca.getTxtPaginaWebEditorialA().isEmpty() && !ventanaBiblioteca.getTxtPaisEditorialA().isEmpty();
        return valido;
    }

    public void listarEditorialTablaAdAgregar(Editorial editorial)
    {
        if(editorial != null)
        {
            editorial = manejadorDao.consultarUltimoEditorial();

            int codEditorial;
            String nomEditorial;
            String paginaWeb;
            String paisOrigen;

            codEditorial = editorial.getCodEditorial();
            nomEditorial = editorial.getNomEditorial();
            paginaWeb = editorial.getPaginaWeb();
            paisOrigen = editorial.getPaisOrigen();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEditorialAdminTableModel();
            auxModeloTabla.addRow(new Object[]{codEditorial, nomEditorial, paisOrigen, paginaWeb});
        }
    }

    public void agregarEditorial()
    {
        Editorial editorial;
        int codEditorial;
        String nomEditorial;
        String paginaWeb;
        String paisOrigen;

        codEditorial = Integer.parseInt(ventanaBiblioteca.getTxtIdEditorialA());
        if(codEditorial == 0)
        {
            if (comprobarCamposEditorialA())
            {
                try
                {
                    nomEditorial = ventanaBiblioteca.getTxtNombreEditorialA();
                    paginaWeb = ventanaBiblioteca.getTxtPaginaWebEditorialA();
                    paisOrigen = ventanaBiblioteca.getTxtPaisEditorialA();

                    editorial = new Editorial(nomEditorial, paginaWeb, paisOrigen);

                    if (manejadorDao.agregarEditorial(editorial) > 0)
                    {
                        listarEditorialTablaAdAgregar(editorial);
                        ventanaBiblioteca.mostrarMensaje("Editorial agregada con exito");
                        ventanaBiblioteca.limpiarEditorialAdmin();
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("Editorial agregada sin exito");
                    }
                }
                catch (NumberFormatException e)
                {
                    ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Deseleccione la editorial");
        }
    }

    public void listarEditorialTabAdEditar(Editorial editorial){
        if(editorial != null)
        {
            String nomEditorial;
            String paginaWeb;
            String paisOrigen;

            nomEditorial = editorial.getNomEditorial();
            paginaWeb = editorial.getPaginaWeb();
            paisOrigen = editorial.getPaisOrigen();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEditorialAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaEditorial();

            auxModeloTabla.setValueAt(nomEditorial, auxFila, 1);
            auxModeloTabla.setValueAt(paginaWeb, auxFila, 3);
            auxModeloTabla.setValueAt(paisOrigen, auxFila, 2);
        }
    }

    public void editarEditorial()
    {
        Editorial editorial;
        int codEditorial;

        codEditorial = Integer.parseInt(ventanaBiblioteca.getTxtIdEditorialA());
        editorial = manejadorDao.buscarEditorial(codEditorial);

        if(codEditorial != 0)
        {
            if(comprobarCamposEditorialA())
            {
                editorial.setNomEditorial(ventanaBiblioteca.getTxtNombreEditorialA());
                editorial.setPaginaWeb(ventanaBiblioteca.getTxtPaginaWebEditorialA());
                editorial.setPaisOrigen(ventanaBiblioteca.getTxtPaisEditorialA());

                if(manejadorDao.editarEditorial(editorial))
                {
                    ventanaBiblioteca.mostrarMensaje("Editorial editada con exito");
                    listarEditorialTabAdEditar(editorial);
                    ventanaBiblioteca.deseleccionarFilaTablaEditorial();
                    ventanaBiblioteca.limpiarEditorialAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Editorial editada sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Selecciones una editorial");
        }
    }

    public void listarEditorialTablaAdEliminar(Editorial editorial)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEditorialAdminTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaEditorial();
        auxModeloTabla.removeRow(auxFila);
    }

    public void eliminarEditorial()
    {
        Editorial editorial;
        int codEditorial;

        codEditorial = Integer.parseInt(ventanaBiblioteca.getTxtIdEditorialA());
        editorial = manejadorDao.buscarEditorial(codEditorial);

        if (codEditorial != 0)
        {
            if (manejadorDao.eliminarEditorial(codEditorial))
            {
                ventanaBiblioteca.mostrarMensaje("Editorial eliminada con exito");
                ventanaBiblioteca.limpiarEditorialAdmin();
                listarEditorialTablaAdEliminar(editorial);
                ventanaBiblioteca.deseleccionarFilaTablaEditorial();
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Editorial eliminada sin exito");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione una editorial");
        }
    }

    /**************************************************************************
     Controlador_Libro
     * Libro - admin
     *************************************************************************/

    class LibroListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarLibro();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarLibro();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarLibro();
            }
        }
    }

    public void listarLibroTablaAd()
    {
        ArrayList<Libro> arrayLibro;
        arrayLibro = manejadorDao.listarLibros();
        if(arrayLibro != null)
        {
            String isbn;
            String titulo;
            ArrayList<String> nombresAutores;
            String autores;
            int codEditorial;
            int codArea;
            int anhoPublicacion;
            String numPaginas;
            String idioma;
            int numEjemplares;
            boolean existeDigital;

            for (Libro libro : arrayLibro) {
                isbn = libro.getIsbn();
                titulo = libro.getTitulo();
                codEditorial = libro.getCodEditorial();
                codArea = libro.getCodArea();
                anhoPublicacion = libro.getAnhoPublicacion();
                numPaginas = libro.getNumPaginas();
                idioma = libro.getIdioma();

                nombresAutores = manejadorDao.getNombresAutoresLibro(isbn);
                autores = String.join(", ", nombresAutores);

                numEjemplares = manejadorDao.numeroEjemplares(isbn);
                existeDigital = manejadorDao.existeDigital(isbn);

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibroAdminTableModel();
                auxModeloTabla.addRow(new Object[]{isbn, titulo, autores, codEditorial, codArea, anhoPublicacion, idioma, numPaginas, numEjemplares, textoDigital(existeDigital)});
            }
        }
    }

    public boolean comprobarCamposLibroA()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtIsbnLibroA().isEmpty() && !ventanaBiblioteca.getTxtTituloLibroA().isEmpty() && !ventanaBiblioteca.getTxtEditorialLibroA().isEmpty() && !ventanaBiblioteca.getTxtNumPaginasLibroA().isEmpty() && !ventanaBiblioteca.getTxtIdiomaLibroA().isEmpty() && !ventanaBiblioteca.getTxtCodAreaA().isEmpty();
        return valido;
    }

    public String textoDigital(boolean existeDigital)
    {
        String cadena;

        if(existeDigital)
        {
            cadena = "NO";
        }
        else
        {
            cadena = "SI";
        }

        return cadena;
    }

    public void listarLibroTablaAdAgregar(Libro libro)
    {
        if(libro != null)
        {
            String isbn;
            String titulo;
            int codEditorial;
            int codArea;
            int anhoPublicacion;
            String numPaginas;
            String idioma;

            isbn = libro.getIsbn();
            titulo = libro.getTitulo();
            codEditorial = libro.getCodEditorial();
            codArea = libro.getCodArea();
            anhoPublicacion = libro.getAnhoPublicacion();
            numPaginas = libro.getNumPaginas();
            idioma = libro.getIdioma();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibroAdminTableModel();
            auxModeloTabla.addRow(new Object[]{isbn, titulo, null, codEditorial, codArea, anhoPublicacion, idioma, numPaginas, 0, "NO"});
        }
    }

    public void agregarLibro()
    {
        Libro libro;
        String isbn;
        String titulo;
        int codEditorial;
        int codArea;
        int anhoPublicacion;
        String numPaginas;
        String idioma;

        if(comprobarCamposLibroA())
        {
            try
            {
                isbn = ventanaBiblioteca.getTxtIsbnLibroA();
                titulo = ventanaBiblioteca.getTxtTituloLibroA();
                codEditorial = Integer.parseInt(ventanaBiblioteca.getTxtEditorialLibroA());
                codArea = Integer.parseInt(ventanaBiblioteca.getAreaLibroA());
                anhoPublicacion = ventanaBiblioteca.getJyAnoPublicLibroA();
                numPaginas = ventanaBiblioteca.getTxtNumPaginasLibroA();
                idioma = ventanaBiblioteca.getTxtIdiomaLibroA();

                if (manejadorDao.buscarEditorial(codEditorial) != null)
                {
                    if(manejadorDao.buscarArea(codArea) != null)
                    {
                        libro = new Libro(isbn, titulo, codEditorial, codArea, anhoPublicacion, numPaginas, idioma);

                        if (anhoPublicacion < 2024)
                        {
                            if (manejadorDao.agregarLibro(libro) > 0)
                            {
                                listarLibroTablaAdAgregar(libro);
                                ventanaBiblioteca.mostrarMensaje("Libro agregado con exito");
                                ventanaBiblioteca.limpiarLibroAdmin();
                            }
                            else
                            {
                                ventanaBiblioteca.mostrarMensajeError("No se pudo crear el libro");
                            }
                        } else
                        {
                            ventanaBiblioteca.mostrarMensajeError("Digite una fecha correcta");
                        }
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("No se encuentra un area con ese id");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No se encuentra una editorial con ese id");
                }
            }
            catch (NumberFormatException e)
            {
                ventanaBiblioteca.mostrarMensajeError("Ingrese datos validos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
        }
    }

    public void listarLibroTabAdEditar(Libro libro){
        if(libro != null)
        {
            String isbn;
            String titulo;
            String autor;
            int codEditorial;
            int codArea;
            int anhoPublicacion;
            String numPaginas;
            String idioma;

            isbn = libro.getIsbn();
            titulo = libro.getTitulo();
            autor = String.join(", ", manejadorDao.getNombresAutoresLibro(isbn));
            codEditorial = libro.getCodEditorial();
            codArea = libro.getCodArea();
            anhoPublicacion = libro.getAnhoPublicacion();
            numPaginas = libro.getNumPaginas();
            idioma = libro.getIdioma();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibroAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaLibroAd();

            auxModeloTabla.setValueAt(isbn, auxFila, 0);
            auxModeloTabla.setValueAt(titulo, auxFila, 1);
            auxModeloTabla.setValueAt(autor, auxFila, 2);
            auxModeloTabla.setValueAt(codEditorial, auxFila, 3);
            auxModeloTabla.setValueAt(codArea, auxFila, 4);
            auxModeloTabla.setValueAt(anhoPublicacion, auxFila, 5);
            auxModeloTabla.setValueAt(idioma, auxFila, 6);
            auxModeloTabla.setValueAt(numPaginas, auxFila, 7);
        }
    }

    public void editarLibro()
    {
        Libro libro;
        String isbn;
        int anhoPublicacion;


        isbn = ventanaBiblioteca.getTxtIsbnLibroA();
        libro = manejadorDao.buscarLibroIsbn(isbn);

        if(ventanaBiblioteca.getFilaSeleccionadaLibroAd() >= 0)
        {
            if(libro != null)
            {
                if(comprobarCamposLibroA())
                {
                    try
                    {
                        libro.setTitulo(ventanaBiblioteca.getTxtTituloLibroA());
                        libro.setCodEditorial(Integer.parseInt(ventanaBiblioteca.getTxtEditorialLibroA()));
                        libro.setCodArea(Integer.parseInt(ventanaBiblioteca.getAreaLibroA()));
                        libro.setAnhoPublicacion(ventanaBiblioteca.getJyAnoPublicLibroA());
                        libro.setNumPaginas(ventanaBiblioteca.getTxtNumPaginasLibroA());
                        libro.setIdioma(ventanaBiblioteca.getTxtIdiomaLibroA());

                        if (manejadorDao.buscarEditorial(libro.getCodEditorial()) != null)
                        {
                            if(manejadorDao.buscarArea(libro.getCodArea())!= null)
                            {
                                anhoPublicacion = ventanaBiblioteca.getJyAnoPublicLibroA();

                                if (anhoPublicacion < 2024)
                                {
                                    if (manejadorDao.editarLibro(libro))
                                    {
                                        ventanaBiblioteca.mostrarMensaje("Libro editado con exito");
                                        listarLibroTabAdEditar(libro);
                                        ventanaBiblioteca.deseleccionarFilaTablaLibroAd();
                                        ventanaBiblioteca.limpiarLibroAdmin();
                                        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorLibroTableModelA();
                                        auxModeloTabla.setNumRows(0);
                                    }
                                    else
                                    {
                                        ventanaBiblioteca.mostrarMensajeError("libro editado sin exito");
                                    }
                                }
                                else
                                {
                                    ventanaBiblioteca.mostrarMensajeError("Digite una fecha correcta");
                                }
                            }
                            else
                            {
                                ventanaBiblioteca.mostrarMensajeError("No se encuntra un area con el id ingresado");
                            }
                        }
                        else
                        {
                            ventanaBiblioteca.mostrarMensajeError("No se encuntra una editorial con el id ingresado");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        ventanaBiblioteca.mostrarMensajeError("Ingrese datos validos");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Ocurrio un error");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un libro");
        }
    }

    public void listarLibroTablaAdEliminar(Libro libro)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibroAdminTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaLibroAd();
        auxModeloTabla.removeRow(auxFila);
    }

    public void eliminarLibro()
    {
        String isbn = ventanaBiblioteca.getTxtIsbnLibroA();
        Libro libro = manejadorDao.buscarLibroIsbn(isbn);

        if(ventanaBiblioteca.getFilaSeleccionadaLibroAd() >= 0)
        {
            if (libro != null)
            {
                if(manejadorDao.existeDigital(isbn) && manejadorDao.numeroEjemplares(isbn) == 0)
                {
                    manejadorDao.eliminarAutorLibro(isbn);
                }

                if (manejadorDao.eliminarLibro(isbn))
                {
                    ventanaBiblioteca.mostrarMensaje("Libro eliminado con exito");
                    ventanaBiblioteca.limpiarLibroAdmin();
                    listarLibroTablaAdEliminar(libro);
                    ventanaBiblioteca.deseleccionarFilaTablaLibroAd();
                    DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorLibroTableModelA();
                    auxModeloTabla.setNumRows(0);
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Libro eliminado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("No se encontró el libro");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un libro");
        }
    }

    /**************************************************************************
     Controlador_Libro
     * Libro - usuario
     *************************************************************************/

    public void listarLibroTablaU()
    {
        ArrayList<Libro> arrayLibro;
        arrayLibro = manejadorDao.listarLibros();
        if(arrayLibro != null)
        {
            String isbn;
            String titulo;
            ArrayList<String> nombresAutores;
            String autores;
            int codEditorial;
            int codArea;
            int anhoPublicacion;
            String numPaginas;
            String idioma;
            int numEjemplares;
            boolean existeDigital;

            for (Libro libro : arrayLibro) {
                isbn = libro.getIsbn();
                titulo = libro.getTitulo();
                codEditorial = libro.getCodEditorial();
                codArea = libro.getCodArea();
                anhoPublicacion = libro.getAnhoPublicacion();
                numPaginas = libro.getNumPaginas();
                idioma = libro.getIdioma();

                nombresAutores = manejadorDao.getNombresAutoresLibro(isbn);
                autores = String.join(", ", nombresAutores);

                numEjemplares = manejadorDao.numeroEjemplares(isbn);
                existeDigital = manejadorDao.existeDigital(isbn);

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getLibroUTableModel();
                auxModeloTabla.addRow(new Object[]{isbn, titulo, autores, codEditorial, codArea, anhoPublicacion, idioma, numPaginas, numEjemplares, textoDigital(existeDigital)});
            }
        }
    }
    /**************************************************************************
     Controlador_Ejemplar
     * Ejemplar - admin
     *************************************************************************/

    class EjemplarListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarEjemplar();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarEjemplar();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarEjemplar();
            }
        }
    }

    public void listarEjemplarTablaAd()
    {
        ArrayList<Ejemplar> arrayEjemplar;
        arrayEjemplar = manejadorDao.listarEjemplares();
        if(arrayEjemplar != null)
        {
            String isbn;
            int numEjemplar;
            int estante;
            int numCajon;
            String nomSala;
            int numPasillo;
            boolean estado;

            for (Ejemplar ejemplar : arrayEjemplar) {
                isbn = ejemplar.getIsbn();
                numEjemplar = ejemplar.getNumEjemplar();
                estante = ejemplar.getEstante();
                numCajon = ejemplar.getNumCajon();
                nomSala = ejemplar.getNomSala();
                numPasillo = ejemplar.getNumPasillo();
                estado = ejemplar.getEstado();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEjemplarAdminTableModel();
                auxModeloTabla.addRow(new Object[]{numEjemplar, isbn, estante, numCajon, nomSala, numPasillo, stringEstEjem(estado)});
            }
        }
    }
    public boolean comprobarCamposEjemplarAd()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtIsbnEjemplarA().isEmpty() && !ventanaBiblioteca.getTxtEstanteEjemplarA().isEmpty() && !ventanaBiblioteca.getTxtNumCajonEjemplarA().isEmpty() && !ventanaBiblioteca.getTxtNomSalaEjemplarA().isEmpty() && !ventanaBiblioteca.getTxtNumPasilloEjemplarA().isEmpty();
        return valido;
    }

    public void listarEjemplarTablaAdAgregar(Ejemplar ejemplar)
    {
        if(ejemplar != null)
        {
            ejemplar = manejadorDao.consultarUltimoEjemplar(ejemplar.getIsbn());

            String isbn;
            int numEjemplar;
            int estante;
            int numCajon;
            String nomSala;
            int numPasillo;

            isbn = ejemplar.getIsbn();
            numEjemplar = ejemplar.getNumEjemplar();
            estante = ejemplar.getEstante();
            numCajon = ejemplar.getNumCajon();
            nomSala = ejemplar.getNomSala();
            numPasillo = ejemplar.getNumPasillo();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEjemplarAdminTableModel();
            auxModeloTabla.addRow(new Object[]{numEjemplar, isbn, estante, numCajon, nomSala, numPasillo, stringEstEjem(true)});
        }
    }

    public void agregarEjemplar()
    {
        Ejemplar ejemplar;
        String isbn;
        int numEjemplar;
        int estante;
        int numCajon;
        String nomSala;
        int numPasillo;

        numEjemplar = Integer.parseInt(ventanaBiblioteca.getTxtNumEjemplarA());

        if(numEjemplar == 0)
        {
            if (comprobarCamposEjemplarAd())
            {
                try
                {
                    isbn = ventanaBiblioteca.getTxtIsbnEjemplarA();
                    estante = Integer.parseInt(ventanaBiblioteca.getTxtEstanteEjemplarA());
                    numCajon = Integer.parseInt(ventanaBiblioteca.getTxtNumCajonEjemplarA());
                    nomSala = ventanaBiblioteca.getTxtNomSalaEjemplarA();
                    numPasillo = Integer.parseInt(ventanaBiblioteca.getTxtNumPasilloEjemplarA());

                    if (manejadorDao.buscarLibroIsbn(isbn) != null)
                    {
                        ejemplar = new Ejemplar(isbn, estante, numCajon, nomSala, numPasillo);

                        if (manejadorDao.agregarEjemplar(ejemplar) > 0)
                        {
                            listarEjemplarTablaAdAgregar(ejemplar);
                            ventanaBiblioteca.mostrarMensaje("Ejemplar agregado con exito");
                            ventanaBiblioteca.limpiarEjemplarAdmin();
                        }
                        else
                        {
                            ventanaBiblioteca.mostrarMensajeError("No se pudo crear el ejemplar");
                        }
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("No existe un libro con ese ISBN");
                    }
                }
                catch(NumberFormatException e)
                {
                    ventanaBiblioteca.mostrarMensajeError("Ingrese datos validos");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Deseleccione el ejemplar");
        }
    }

    public void listarEjemplarTabAdEditar(Ejemplar ejemplar)
    {
        if(ejemplar != null)
        {
            String isbn;
            int numEjemplar;
            int estante;
            int numCajon;
            String nomSala;
            int numPasillo;

            isbn = ejemplar.getIsbn();
            numEjemplar = ejemplar.getNumEjemplar();
            estante = ejemplar.getEstante();
            numCajon = ejemplar.getNumCajon();
            nomSala = ejemplar.getNomSala();
            numPasillo = ejemplar.getNumPasillo();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEjemplarAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaEjemplarAd();

            auxModeloTabla.setValueAt(numEjemplar, auxFila, 0);
            auxModeloTabla.setValueAt(isbn, auxFila, 1);
            auxModeloTabla.setValueAt(estante, auxFila, 2);
            auxModeloTabla.setValueAt(numCajon, auxFila, 3);
            auxModeloTabla.setValueAt(nomSala, auxFila, 4);
            auxModeloTabla.setValueAt(numPasillo, auxFila, 5);
        }
    }

    public void editarEjemplar()
    {
        Ejemplar ejemplar;
        String isbn;
        int numEjemplar;

        isbn = ventanaBiblioteca.getTxtIsbnEjemplarA();
        numEjemplar = Integer.parseInt(ventanaBiblioteca.getTxtNumEjemplarA());
        ejemplar = manejadorDao.buscarEjemplar(isbn, numEjemplar);

        if(ventanaBiblioteca.getFilaSeleccionadaEjemplarAd() >= 0)
        {
            if(ejemplar != null)
            {
                if(comprobarCamposEjemplarAd())
                {
                    try
                    {
                        ejemplar.setEstante(Integer.parseInt(ventanaBiblioteca.getTxtEstanteEjemplarA()));
                        ejemplar.setNumCajon(Integer.parseInt(ventanaBiblioteca.getTxtNumCajonEjemplarA()));
                        ejemplar.setNomSala(ventanaBiblioteca.getTxtNomSalaEjemplarA());
                        ejemplar.setNumPasillo(Integer.parseInt(ventanaBiblioteca.getTxtNumPasilloEjemplarA()));

                        if(manejadorDao.buscarLibroIsbn(ejemplar.getIsbn()) != null)
                        {
                            if (manejadorDao.editarEjemplar(ejemplar))
                            {
                                ventanaBiblioteca.mostrarMensaje("Ejemplar editado con exito");
                                listarEjemplarTabAdEditar(ejemplar);
                                ventanaBiblioteca.deseleccionarTablaFilaEjemplarAd();
                                ventanaBiblioteca.limpiarEjemplarAdmin();
                            }
                            else
                            {
                                ventanaBiblioteca.mostrarMensajeError("No se pudo editar el ejemplar");
                            }
                        }
                        else
                        {
                            ventanaBiblioteca.mostrarMensajeError("No existe un libro con ese ISBN");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        ventanaBiblioteca.mostrarMensajeError("Ingrese datos validos");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Ocurrio un error");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Selecciones un ejemplar");
        }
    }

    public void listarEjemplarTablaAdEliminar(Ejemplar ejemplar)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEjemplarAdminTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaEjemplarAd();
        auxModeloTabla.removeRow(auxFila);
    }

    public void eliminarEjemplar()
    {
        Ejemplar ejemplar;
        String isbn;
        int numEjemplar;

        isbn = ventanaBiblioteca.getTxtIsbnEjemplarA();
        numEjemplar = Integer.parseInt(ventanaBiblioteca.getTxtNumEjemplarA());
        ejemplar = manejadorDao.buscarEjemplar(isbn, numEjemplar);

        if(ventanaBiblioteca.getFilaSeleccionadaEjemplarAd() >= 0)
        {
            try
            {
                if (ejemplar != null)
                {
                    if (manejadorDao.eliminarEjemplar(isbn, numEjemplar))
                    {
                        ventanaBiblioteca.mostrarMensaje("Ejemplar eliminado con exito");
                        ventanaBiblioteca.limpiarEjemplarAdmin();
                        listarEjemplarTablaAdEliminar(ejemplar);
                        ventanaBiblioteca.deseleccionarTablaFilaEjemplarAd();
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("Ejemplar eliminado sin exito");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No se encontró el ejemplar");
                }
            }
            catch(NumberFormatException e)
            {
                ventanaBiblioteca.mostrarMensajeError("Ingrese datos validos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un ejemplar");
        }
    }


    public String stringEstEjem(boolean b)
    {
        String cadena;
        if(!b)
        {
            cadena = "NO DISPONIBLE";
        }
        else
        {
            cadena = "DISPONIBLE";
        }
        return cadena;
    }
    /**************************************************************************
     Controlador_Digital
     * Digital - admin
     *************************************************************************/

    class DigitalListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarDigital();
            }
            if (e.getActionCommand().equalsIgnoreCase("modificar"))
            {
                editarDigital();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarDigital();
            }
        }
    }

    public void listarDigitalTablaAd()
    {
        ArrayList<Digital> arrayDigital;
        arrayDigital = manejadorDao.listarDigitales();
        if(arrayDigital != null)
        {
            int numDigital;
            String isbn;
            String url;
            String formato;
            String bytes;

            for (Digital digital : arrayDigital) {
                numDigital = digital.getNumDigital();
                isbn = digital.getIsbn();
                url = digital.getUrl();
                formato = digital.getFormato();
                bytes = digital.getBytes();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDigitalAdminTableModel();
                auxModeloTabla.addRow(new Object[]{numDigital, isbn, url, formato, bytes});
            }
        }
    }
    public boolean comprobarCamposDigitalAd()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getTxtIsbnDigitalA().isEmpty() && !ventanaBiblioteca.getTxtUrlDigitalA().isEmpty() && !ventanaBiblioteca.getTxtFormatoDigitalA().isEmpty() && !ventanaBiblioteca.getTxtBytesDigitalA().isEmpty();
        return valido;
    }

    public void listarDigitalTablaAdAgregar(Digital digital)
    {
        if(digital != null)
        {
            digital = manejadorDao.consultarUltimoDigital();

            int numDigital;
            String isbn;
            String url;
            String formato;
            String bytes;

            numDigital = digital.getNumDigital();
            isbn = digital.getIsbn();
            url = digital.getUrl();
            formato = digital.getFormato();
            bytes = digital.getBytes();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDigitalAdminTableModel();
            auxModeloTabla.addRow(new Object[]{numDigital, isbn, url, formato, bytes});
        }
    }

    public void agregarDigital()
    {
        Digital digital;
        int numDigital;
        String isbn;
        String url;
        String formato;
        String bytes;

        numDigital = Integer.parseInt(ventanaBiblioteca.getTxtNumDigitalA());

        if(ventanaBiblioteca.getFilaSeleccionadaDigitalAd() == -1)
        {
            if (comprobarCamposDigitalAd())
            {
                isbn = ventanaBiblioteca.getTxtIsbnDigitalA();
                url = ventanaBiblioteca.getTxtUrlDigitalA();
                formato = ventanaBiblioteca.getTxtFormatoDigitalA();
                bytes = ventanaBiblioteca.getTxtBytesDigitalA();

                if (manejadorDao.buscarLibroIsbn(isbn) != null)
                {
                    digital = new Digital(isbn, url, formato, bytes);

                    if (manejadorDao.agregarDigital(digital) > 0)
                    {
                        listarDigitalTablaAdAgregar(digital);
                        ventanaBiblioteca.mostrarMensaje("Libro digital agregado con exito");
                        ventanaBiblioteca.limpiarDigitalAdmin();
                    }
                    else
                    {
                        ventanaBiblioteca.mostrarMensajeError("Libro digital agregado sin exito");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No existe un libro con ese ISBN");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Deseleccione el libro digital");
        }
    }

    public void listarDigitalTabAdEditar(Digital digital)
    {
        if(digital != null) {
            int numDigital;
            String isbn;
            String url;
            String formato;
            String bytes;

            numDigital = digital.getNumDigital();
            isbn = digital.getIsbn();
            url = digital.getUrl();
            formato = digital.getFormato();
            bytes = digital.getBytes();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDigitalAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaDigitalAd();

            auxModeloTabla.setValueAt(numDigital, auxFila, 0);
            auxModeloTabla.setValueAt(isbn, auxFila, 1);
            auxModeloTabla.setValueAt(url, auxFila, 2);
            auxModeloTabla.setValueAt(formato, auxFila, 3);
            auxModeloTabla.setValueAt(bytes, auxFila, 4);
        }
    }

    public void editarDigital()
    {
        Digital digital;
        String isbn;

        isbn = ventanaBiblioteca.getTxtIsbnDigitalA();
        digital = manejadorDao.buscarDigital(isbn);

        if(ventanaBiblioteca.getFilaSeleccionadaDigitalAd() >= 0)
        {
            if(digital != null)
            {
                if(comprobarCamposDigitalAd())
                {
                    try
                    {
                        digital.setFormato(ventanaBiblioteca.getTxtFormatoDigitalA());
                        digital.setBytes(ventanaBiblioteca.getTxtBytesDigitalA());

                        if(manejadorDao.buscarLibroIsbn(digital.getIsbn()) != null)
                        {
                            if (manejadorDao.editarDigital(digital))
                            {
                                ventanaBiblioteca.mostrarMensaje("Libro digital editado con exito");
                                listarDigitalTabAdEditar(digital);
                                ventanaBiblioteca.deseleccionarFilaTablaDigitalAd();
                                ventanaBiblioteca.limpiarDigitalAdmin();
                            }
                            else
                            {
                                ventanaBiblioteca.mostrarMensajeError("Libro digital editado sin exito");
                            }
                        }
                        else
                        {
                            ventanaBiblioteca.mostrarMensajeError("No existe un libro con el ISBN ingresado");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        ventanaBiblioteca.mostrarMensajeError("Ingrese datos validos");
                    }
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Ocurrio un error");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un libro digital");
        }
    }

    public void listarDigitalTablaAdEliminar(Digital digital)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getDigitalAdminTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaDigitalAd();
        auxModeloTabla.removeRow(auxFila);
    }

    public void eliminarDigital()
    {
        Digital digital;
        String isbn;
        String url;

        if(ventanaBiblioteca.getFilaSeleccionadaDigitalAd() >= 0)
        {
            isbn = ventanaBiblioteca.getTxtIsbnDigitalA();
            url = ventanaBiblioteca.getTxtUrlDigitalA();
            digital = manejadorDao.buscarDigital(isbn);

            if (digital != null)
            {
                if (manejadorDao.eliminarDigital(isbn))
                {
                    ventanaBiblioteca.mostrarMensaje("Libro digital eliminado con exito");
                    listarDigitalTablaAdEliminar(digital);
                    ventanaBiblioteca.limpiarDigitalAdmin();
                    ventanaBiblioteca.deseleccionarFilaTablaDigitalAd();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Libro eliminado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("No se encontró el libro digital");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un libro digital");
        }
    }

    /**************************************************************************
     * Multa
     *************************************************************************/
    public void listarMultasTablaA()
    {
        ArrayList<Multa> arrayMulta;
        arrayMulta = manejadorDao.listarMultasA();
        if(arrayMulta != null)
        {
            String auxCedula;
            String auxIsbn;
            int auxNumeroEjemplar;
            String auxFechaMulta;
            int auxValor;
            String auxDescripcion;

            for (Multa multa : arrayMulta) {
                auxCedula = multa.getIdUsuario();
                auxIsbn = multa.getIsbn();
                auxNumeroEjemplar = multa.getNumEjemplar();
                auxFechaMulta = multa.getFechaMulta();
                auxValor = multa.getValor();
                auxDescripcion = multa.getDescripcion();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getMultaATableModel();
                auxModeloTabla.addRow(new Object[]{auxNumeroEjemplar, auxIsbn, manejadorDao.buscarLibroIsbn(auxIsbn).getTitulo(), auxCedula, auxValor, auxDescripcion, auxFechaMulta});
            }
        }
    }

    public void eliminarMulta()
    {
        String auxCedula;
        String auxIsbn;
        int auxNumeroEjemplar;
        String auxFechaMulta;

        auxCedula = usuario.getId();
        auxIsbn = ventanaBiblioteca.getIsbnMultaU();
        auxNumeroEjemplar = Integer.parseInt(ventanaBiblioteca.getNumeEjemMultaU());
        auxFechaMulta = ventanaBiblioteca.getFechaMultaU();

        if(ventanaBiblioteca.getFilaSeleccionadaMultaU() >= 0)
        {
            if(comprobarCamposMulta())
            {
                if(manejadorDao.eliminarMulta(auxCedula, auxIsbn, auxNumeroEjemplar, auxFechaMulta))
                {
                    ventanaBiblioteca.mostrarMensaje("Multa pagada con exito");
                    listarAreaTablaUMulta();
                    ventanaBiblioteca.deseleccionarFilaMultaU();
                    ventanaBiblioteca.limpiarMultaUsuario();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Multa pagada sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene todos los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione una multa");
        }
    }

    public void listarMultasTablaU(String cedula)
    {
        ArrayList<Multa> arrayMulta;
        arrayMulta = manejadorDao.listarMultasU(cedula);
        if(arrayMulta != null)
        {
            String auxCedula;
            String auxIsbn;
            int auxNumeroEjemplar;
            String auxFechaMulta;
            int auxValor;
            String auxDescripcion;

            for (Multa multa : arrayMulta) {
                auxCedula = multa.getIdUsuario();
                auxIsbn = multa.getIsbn();
                auxNumeroEjemplar = multa.getNumEjemplar();
                auxFechaMulta = multa.getFechaMulta();
                auxValor = multa.getValor();
                auxDescripcion = multa.getDescripcion();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getMultaUTableModel();
                auxModeloTabla.addRow(new Object[]{auxNumeroEjemplar, auxIsbn, manejadorDao.buscarLibroIsbn(auxIsbn).getTitulo(), auxValor, auxDescripcion, auxFechaMulta});
            }
        }
    }

    public void agregarMulta(String auxCedula, String auxIsbn, int auxNumEjemplar, Date fechaDevolucion)
    {
        Multa multa;
        Date fechaActual = new Date();

        if(fechaDevolucion.before(fechaActual))
        {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            long dias = calcularDias(fechaDevolucion, fechaActual);
            String descripcion = "Multa por "+dias+" dias";
            multa = new Multa(auxCedula, auxIsbn, auxNumEjemplar, formatoFecha.format(fechaActual), (int)dias*2000, descripcion,  true);

            if(manejadorDao.agregarMulta(multa) < 0)
            {
                System.out.println("Ha ocurrido un error creando las multas");
            }
            else
            {
                System.out.println("Se genero multa");
            }
        }
        else
        {
            System.out.println("No se genero multa");
        }
    }

    public boolean comprobarCamposMulta()
    {
        boolean valido;
        valido = !ventanaBiblioteca.getIsbnMultaU().isEmpty() && !ventanaBiblioteca.getNumeEjemMultaU().isEmpty() && !ventanaBiblioteca.getFechaMultaU().isEmpty();
        return valido;
    }

    public long calcularDias(Date auxFechaDevolucion, Date auxFechaActual)
    {
        long dias = Math.abs(ChronoUnit.DAYS.between(Instant.ofEpochMilli(auxFechaActual.getTime()).atZone(ZoneId.systemDefault()).toLocalDate(),
                Instant.ofEpochMilli(auxFechaDevolucion.getTime()).atZone(ZoneId.systemDefault()).toLocalDate()));
        return dias;
    }

    public void listarAreaTablaUMulta()
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getMultaUTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaMultaU();
        auxModeloTabla.removeRow(auxFila);
    }

    public void listarNumEjemplares()
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getMultaUTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaMultaU();
        auxModeloTabla.removeRow(auxFila);
    }

    class MultaListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("pagar"))
            {
                eliminarMulta();
            }
        }
    }


    class TablaLibroAUListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            JTable tabla = (JTable)e.getSource();
            int fila = tabla.getSelectedRow();
            if(fila == -1)
            {
                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorLibroTableModelA();
                auxModeloTabla.setNumRows(0);
            }
            else
            {
                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorLibroTableModelA();
                auxModeloTabla.setNumRows(0);
                String isbn = ventanaBiblioteca.getTxtIsbnLibroA();
                ArrayList<Integer> codsAutores = manejadorDao.getCodigosAutoresLibro(isbn);

                for(int codAutor: codsAutores)
                {
                    Autor autor = manejadorDao.consultarAutor(codAutor);

                    auxModeloTabla.addRow(new Object[]{autor.getCodAutor(), autor.getPrimerNombre() + " " + autor.getPrimerApellido()});
                }
            }
        }
    }

    public void agregarAutorLibro()
    {
        Autor autor;
        AutorLibro autorLibro;
        int codAutor;
        String codIsbn;

        try
        {
            codAutor = Integer.parseInt(ventanaBiblioteca.getTxtAutorLibroA());
            autor = manejadorDao.consultarAutor(codAutor);

            if(autor.getPrimerNombre() != null)
            {
                codIsbn = ventanaBiblioteca.getTxtIsbnLibroA();
                autorLibro = new AutorLibro(codAutor, codIsbn);

                if(manejadorDao.agregarAutorlibro(autorLibro) > 0)
                {
                    ventanaBiblioteca.mostrarMensaje("Autor relacionado con exito");
                    listarAutorLibroAgregar(autorLibro);
                    listarLibroTabAdEditar(manejadorDao.buscarLibroIsbn(codIsbn));
                    ventanaBiblioteca.limpiarAutorLibroAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Autor relacionado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("El autor no existe");
            }
        }
        catch(NumberFormatException e)
        {
            ventanaBiblioteca.mostrarMensajeError("Ingrese un codigo valido");
        }

    }

    public void listarAutorLibroAgregar(AutorLibro autorLibro)
    {
        if(autorLibro != null)
        {
            int codAutor;
            String isbn;
            String nombre;

            codAutor = autorLibro.getCodAutor();
            nombre = manejadorDao.getNombreAutor(codAutor);

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorLibroTableModelA();
            auxModeloTabla.addRow(new Object[]{codAutor, nombre});
        }
    }

    public void eliminarAutorLibro()
    {
        Autor autor;
        AutorLibro autorLibro;
        int codAutor;
        String codIsbn;

        if(ventanaBiblioteca.getFilaSeleccionadaAutorLibro() >= 0)
        {
            codAutor = Integer.parseInt(ventanaBiblioteca.getTxtAutorLibroA());
            autor = manejadorDao.consultarAutor(codAutor);

            if(autor.getPrimerNombre() != null)
            {
                codIsbn = ventanaBiblioteca.getTxtIsbnLibroA();
                autorLibro = new AutorLibro(codAutor, codIsbn);

                if(manejadorDao.eliminarAutorLibroA(codIsbn, codAutor))
                {
                    ventanaBiblioteca.mostrarMensaje("Autor eliminado con exito");
                    listarAutorLibroTablaAdEliminar(autorLibro);
                    listarLibroTabAdEditar(manejadorDao.buscarLibroIsbn(codIsbn));
                    ventanaBiblioteca.limpiarAutorLibroAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("Autor eliminado sin exito");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("El autor no existe");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Seleccione un autor");
        }
    }

    public void listarAutorLibroTablaAdEliminar(AutorLibro autorLibro)
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAutorLibroTableModelA();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaAutorLibro();
        auxModeloTabla.removeRow(auxFila);
    }

    class AutorLibroListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equalsIgnoreCase("agregar"))
            {
                agregarAutorLibro();
            }
            if (e.getActionCommand().equalsIgnoreCase("eliminar"))
            {
                eliminarAutorLibro();
            }
        }
    }


}
