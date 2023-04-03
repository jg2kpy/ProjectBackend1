// Definir la URL del servidor como una variable global
const API_URL = 'http://localhost:8080/prueba/cliente';

// Obtener los elementos del DOM
const formulario = document.querySelector("form");
const tablaBody = document.getElementById("tabla-body");
const editarFormulario = document.getElementById("editar-formulario");
const editarForm = editarFormulario.querySelector("form");

// Función para obtener todos los clientes de la API
async function obtenerClientes() {
    try {
        const response = await fetch(API_URL);
        const clientes = await response.json();
        return clientes;
    } catch (error) {
        console.error(error);
    }
}

// Función para agregar un cliente a la API
async function agregarCliente(cliente) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cliente)
        });
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

// Función para eliminar un cliente de la API
async function eliminarCliente(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        });
        //como nuestro servidor no retorna nada al actualizar, retorno true
        //const data = await response.json();
        //return data;
        return true;
    } catch (error) {
        console.error(error);
    }
}

// Función para actualizar un cliente en la API
async function actualizarCliente(id, cliente) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cliente)
        });
        //como nuestro servidor no retorna nada al actualizar, retorno true
        //const data = await response.json();
        //return data;
        return true;
    } catch (error) {
        console.error(error);
    }
}

// Función para crear un elemento de fila de la tabla de clientes
function crearFilaCliente(cliente) {
    const fila = document.createElement("tr");

    const id = document.createElement("td");
    id.textContent = cliente.idCliente;
    fila.appendChild(id);

    const nombre = document.createElement("td");
    nombre.textContent = cliente.nombre;
    fila.appendChild(nombre);

    const apellido = document.createElement("td");
    apellido.textContent = cliente.apellido;
    fila.appendChild(apellido);

    const nroDocumento = document.createElement("td");
    nroDocumento.textContent = cliente.nroDocumento;
    fila.appendChild(nroDocumento);

    const tipoDocumento = document.createElement("td");
    tipoDocumento.textContent = cliente.tipoDocumento;
    fila.appendChild(tipoDocumento);

    const nacionalidad = document.createElement("td");
    nacionalidad.textContent = cliente.nacionalidad;
    fila.appendChild(nacionalidad);

    const email = document.createElement("td");
    email.textContent = cliente.email;
    fila.appendChild(email);

    const telefono = document.createElement("td");
    telefono.textContent = cliente.telefono;
    fila.appendChild(telefono);

    const fechaNacimiento = document.createElement("td");
    fechaNacimiento.textContent = new Date(cliente.fechaNacimiento).toLocaleDateString();
    fila.appendChild(fechaNacimiento);

    const acciones = document.createElement("td");
    const editarBoton = document.createElement("button");
    editarBoton.textContent = "Editar";
    editarBoton.addEventListener("click", () => mostrarEditarFormulario(cliente));
    acciones.appendChild(editarBoton);
    const eliminarBoton = document.createElement("button");
    eliminarBoton.textContent = "Eliminar";
    eliminarBoton.addEventListener("click", () => eliminarClienteClick(cliente));
    acciones.appendChild(eliminarBoton);
    fila.appendChild(acciones);

    return fila;
}

// Función para mostrar la tabla de clientes
async function mostrarTablaClientes() {
    const clientes = await obtenerClientes();

    tablaBody.innerHTML = "";

    clientes.sort((a, b) => a.idCliente - b.idCliente);

    clientes.forEach((cliente) => {
        const fila = crearFilaCliente(cliente);
        tablaBody.appendChild(fila);
    });
}

// Función para mostrar el formulario de edición de cliente
function mostrarEditarFormulario(cliente) {
    editarForm["editar-id"].value = cliente.idCliente;
    editarForm["editar-nombre"].value = cliente.nombre;
    editarForm["editar-apellido"].value = cliente.apellido;
    editarForm["editar-nroDocumento"].value = cliente.nroDocumento;
    editarForm["editar-tipoDocumento"].value = cliente.tipoDocumento;
    editarForm["editar-nacionalidad"].value = cliente.nacionalidad;
    editarForm["editar-email"].value = cliente.email;
    editarForm["editar-telefono"].value = cliente.telefono;
    editarForm["editar-fechaNacimiento"].valueAsNumber = cliente.fechaNacimiento;

    // habilito el display del form
    editarFormulario.style.display = "block";
}

// Función para ocultar el formulario de edición de cliente
function ocultarEditarFormulario() {
    editarFormulario.style.display = "none";
}

// Función para agregar un cliente al hacer clic en el botón de enviar formulario
formulario.addEventListener("submit", async (evento) => {
    evento.preventDefault();

    const cliente = {
        nombre: formulario.nombre.value,
        apellido: formulario.apellido.value,
        nroDocumento: formulario.nroDocumento.value,
        tipoDocumento: formulario.tipoDocumento.value,
        nacionalidad: formulario.nacionalidad.value,
        email: formulario.email.value,
        telefono: formulario.telefono.value,
        fechaNacimiento: formulario.fechaNacimiento.value
    };

    const resultado = await agregarCliente(cliente);

    if (resultado) {
        formulario.reset();
        mostrarTablaClientes();
    }
});

// Función para eliminar un cliente al hacer clic en el botón de eliminar en la fila de la tabla
async function eliminarClienteClick(cliente) {
    const confirmacion = confirm(`¿Desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}?`);
    if (confirmacion) {
        const resultado = await eliminarCliente(cliente.idCliente);
        mostrarTablaClientes();
    }
}

// Función para actualizar un cliente al hacer clic en el botón de enviar formulario de edición
editarForm.addEventListener("submit", async (evento) => {
    evento.preventDefault();

    const cliente = {
        nombre: editarForm["editar-nombre"].value,
        apellido: editarForm["editar-apellido"].value,
        nroDocumento: editarForm["editar-nroDocumento"].value,
        tipoDocumento: editarForm["editar-tipoDocumento"].value,
        nacionalidad: editarForm["editar-nacionalidad"].value,
        email: editarForm["editar-email"].value,
        telefono: editarForm["editar-telefono"].value,
        fechaNacimiento: editarForm["editar-fechaNacimiento"].value
    };
    const resultado = await actualizarCliente(editarForm["editar-id"].value, cliente);
    if (resultado) {
        ocultarEditarFormulario();
        mostrarTablaClientes();
    }
});

// Mostrar la tabla de clientes cuando se cargue la página
mostrarTablaClientes();