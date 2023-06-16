package controlador;

import dao.DaoEstudiante;
import dao.DaoProfesor;
import modelo.*;
import vista.VentanaBiblioteca;
import vista.VentanaLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



        if(usuario instanceof Profesor || usuario instanceof Estudiante)
        {
            ventanaBiblioteca.menuUsuario();
            ventanaBiblioteca.pagLibroUsuario();
            ventanaBiblioteca.pantallaCompleta();
            listarTablasUsuarios(usuario.getId());
        }
        else if(usuario == null)
        {
            ventanaBiblioteca.menuAdmin();
            ventanaBiblioteca.pagAreaAdmin();
            ventanaBiblioteca.pantallaCompleta();
            listarTablasAdmin();
        }

        ventanaBiblioteca.addBotonesEncabezadoListener(new EncabezadoListener());
        ventanaBiblioteca.addBotonesPerfilEsudianteListener(new EstudianteUListener());
        ventanaBiblioteca.addBotonesPerfilProfesorListener(new ProfesorUListener());
        ventanaBiblioteca.addBotonAgregarSolicitud(new SolicitudUListener());
        ventanaBiblioteca.addBotonesEmpleadoAdminListener(new EmpleadoListener());
        ventanaBiblioteca.addBotonesAreaAdminListener(new AreaConocimientoListener());
    }

    private void cerrarSesion()
    {
        ventanaBiblioteca.dispose();
        VentanaLogin a = new VentanaLogin();
        DaoEstudiante b = new DaoEstudiante();
        DaoProfesor d = new DaoProfesor();
        ControladorLogin c = new ControladorLogin(a, b, d);
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
            listarSolicitudesTablaU(usuario.getId());
        }
        if(!manejadorDao.listarPrestamosUsuario(id).isEmpty())
        {
            listarPrestamosTablaU(usuario.getId());
        }
    }

    public void listarTablasAdmin()
    {
        if(!manejadorDao.listarEmpleados().isEmpty())
        {
            listarEmpleadosTablaA();
        }
        if(!manejadorDao.listarUsuarios().isEmpty()) {
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
     * Estudiante - usuario
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
                ventanaBiblioteca.mostrarMensajeError("No se pudo crear la solicitud");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Verifique los campos isbn y titulo");
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
     * Prestamo - Usuario
     *************************************************************************/
    public void listarPrestamosTablaU(String id)
    {
        ArrayList<Prestamo> arrayPrestamo;
        arrayPrestamo = manejadorDao.listarPrestamosUsuario(id);
        if(arrayPrestamo != null)
        {
            int num;
            String nomE;
            Date fecha;
            String isbn;
            int numEje;
            Date fechaD;

            for (Prestamo prestamo : arrayPrestamo) {
                num = prestamo.getNumPrestamo();
                fecha = prestamo.getFecha();
                isbn = prestamo.getIsbn();
                numEje = prestamo.getNumEjemplar();
                fechaD = prestamo.getFechaDevolucion();
                nomE = manejadorDao.buscarNombreEmpleado(prestamo.getIdEmpleado());

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoUTableModel();
                auxModeloTabla.addRow(new Object[]{num, fecha, isbn, numEje, fechaD, nomE});
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
        if(arrayPrestamo != null)
        {
            int num;
            String nomE;
            Date fecha;
            String isbn;
            int numEje;
            Date fechaD;
            String tituloL;
            String idUsu;

            for (Prestamo prestamo : arrayPrestamo) {
                num = prestamo.getNumPrestamo();
                fecha = prestamo.getFecha();
                isbn = prestamo.getIsbn();
                numEje = prestamo.getNumEjemplar();
                fechaD = prestamo.getFechaDevolucion();
                idUsu = prestamo.getIdUsuario();
                tituloL = manejadorDao.buscarLibroIsbn(isbn).getTitulo();
                nomE = manejadorDao.buscarNombreEmpleado(prestamo.getIdEmpleado());

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getPrestamoAdminTableModel();
                auxModeloTabla.addRow(new Object[]{num, isbn, numEje, tituloL, idUsu, nomE, fecha, fechaD});
            }
        }
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
                    ventanaBiblioteca.mostrarMensajeError("No se pudo crear el empleado");
                }
            }
        }
        else
        {
                ventanaBiblioteca.mostrarMensajeError("Verifique que los campos hayan sido bien digitados");
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

        if(empleado != null)
        {
            correo = ventanaBiblioteca.getTxtCorreoEmpleadoA();
            if(comprobarCamposEmpleadoA() && comprobarCorreo(correo))
            {
                empleado.setContrasena(ventanaBiblioteca.getTxtClaveEmpleadoA());
                empleado.setNombre(ventanaBiblioteca.getTxtNombreEmpleadoA());
                empleado.setEmail(ventanaBiblioteca.getTxtCorreoEmpleadoA());
                empleado.setDireccion(ventanaBiblioteca.getTxtDireccionEmpleadoA());
                empleado.setTelefono(ventanaBiblioteca.getTxtTelefonoEmpleadoA());
                empleado.setCargo(ventanaBiblioteca.getTxtCargoEmpleadoA());

                if(manejadorDao.editarEmpleado(empleado))
                {
                    ventanaBiblioteca.mostrarMensaje("Empleado editado con exito");
                    listarEmpleadoTabAdEditar(empleado);
                    ventanaBiblioteca.deseleccionarFilaTablaEmpleado();
                    ventanaBiblioteca.limpiarEmpleadoAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No se pudo editar el empleado, puede que el correo ya este en uso");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Digite bien los campos");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Ocurrio un error");
        }
    }

    public void listarEmpleadoTabAdEditar(Empleado empleado)
    {
        if(empleado != null) {
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

        if(empleado != null)
        {
            if(manejadorDao.eliminarEmpleado(id))
            {
                ventanaBiblioteca.mostrarMensaje("Empleado eliminado");
                ventanaBiblioteca.limpiarEmpleadoAdmin();
                listarEmpleadoTabAdEliminar();
                ventanaBiblioteca.deseleccionarFilaTablaEmpleado();
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("No se pudo realizar la acción");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("No se encontro el empleado");
        }
    }

    public void listarEmpleadoTabAdEliminar()
    {
        DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getEmpleadoTableModel();
        int auxFila = ventanaBiblioteca.getFilaSeleccionadaEmpleado();
        auxModeloTabla.removeRow(auxFila);
    }

    /**************************************************************************
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
            if (e.getActionCommand().equalsIgnoreCase("relacionar"))
            {
               //relacionarAreas();
            }
        }
    }

    public void listarAreasTablaA()
    {
        ArrayList<AreaConocimiento> arrayArea;
        arrayArea = manejadorDao.listarAreas();
        if(arrayArea != null)
        {
            int codArea;
            String nomArea;
            String descripcion;
            String areaHija;

            for (AreaConocimiento area : arrayArea) {
                codArea = area.getCodigoArea();
                nomArea = area.getNomArea();
                descripcion = area.getDescripcion();
                areaHija = area.getAreaHija();

                DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();
                auxModeloTabla.addRow(new Object[]{codArea, nomArea, descripcion, areaHija});
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
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Deseleccione el area");
        }
    }

    public void listarAreaTabAdEditar(AreaConocimiento area){
        if(area != null)
        {
            String nomArea;
            String descripcion;
            String areaHija;

            nomArea = area.getNomArea();
            descripcion = area.getDescripcion();
            areaHija = area.getAreaHija();

            DefaultTableModel auxModeloTabla = (DefaultTableModel) ventanaBiblioteca.getAreaAdminTableModel();
            int auxFila = ventanaBiblioteca.getFilaSeleccionadaArea();

            auxModeloTabla.setValueAt(nomArea, auxFila, 1);
            auxModeloTabla.setValueAt(descripcion, auxFila, 2);
            auxModeloTabla.setValueAt(areaHija, auxFila, 3);
        }
    }

    public void editarArea()
    {
        AreaConocimiento area;
        int codArea;

        codArea = Integer.parseInt(ventanaBiblioteca.getTxtCodAreaA());
        area = manejadorDao.buscarArea(codArea);

        if(area != null)
        {
            if(comprobarCamposAreaA())
            {
                area.setNomArea(ventanaBiblioteca.getTxtNombreAreaA());
                area.setDescripcion(ventanaBiblioteca.getTxaDescripcionAreaA());

                if(manejadorDao.editarArea(area))
                {
                    ventanaBiblioteca.mostrarMensaje("Area editado con exito");
                    listarAreaTabAdEditar(area);
                    ventanaBiblioteca.deseleccionarFilaTablaArea();
                    ventanaBiblioteca.limpiarAreaAdmin();
                }
                else
                {
                    ventanaBiblioteca.mostrarMensajeError("No se pudo editar el area");
                }
            }
            else
            {
                ventanaBiblioteca.mostrarMensajeError("Llene el campo del nombre");
            }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("Ocurrio un error");
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

        if (area != null)
        {
           if (manejadorDao.eliminarArea(codArea))
           {
               ventanaBiblioteca.mostrarMensaje("Area eliminada");
               ventanaBiblioteca.limpiarAreaAdmin();
               listarAreaTablaAdEliminar(area);
               ventanaBiblioteca.deseleccionarFilaTablaArea();
           }
           else
           {
               ventanaBiblioteca.mostrarMensajeError("No se pudo realizar la acción");
           }
        }
        else
        {
            ventanaBiblioteca.mostrarMensajeError("No se encontró el area");
        }
    }
}
