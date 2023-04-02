// Definir la URL del servidor como una variable global
const API_URL = 'http://localhost:8080/prueba/vencimiento-puntos';

// Obtener los elementos del DOM
const formulario = document.querySelector("form");
const tablaBody = document.getElementById("tabla-body");
const editarFormulario = document.getElementById("editar-formulario");
const editarForm = editarFormulario.querySelector("form");

// Función para obtener todos los vencimientos de la API
async function obtenerVencimientos() {
  try {
    const response = await fetch(API_URL);
    const vencimientos = await response.json();
    return vencimientos;
  } catch (error) {
    console.error(error);
  }
}

// Función para agregar un vencimiento a la API
async function agregarVencimiento(vencimiento) {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(vencimiento)
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
}

// Función para eliminar un vencimiento de la API
async function eliminarVencimiento(id) {
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

// Función para actualizar un vencimiento en la API
async function actualizarVencimiento(id, vencimiento) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(vencimiento)
    });
    //como nuestro servidor no retorna nada al actualizar, retorno true
    //const data = await response.json();
    //return data;
    return true;
  } catch (error) {
    console.error(error);
  }
}

// Función para crear un elemento de fila de la tabla de vencimientos
function crearFilaVencimiento(vencimiento) {
  const fila = document.createElement("tr");

  const id = document.createElement("td");
  id.textContent = vencimiento.idVencimientoPuntos;
  fila.appendChild(id);

  const fechaInicioValidez = document.createElement("td");
  fechaInicioValidez.textContent = new Date(vencimiento.fechaInicioValidez).toLocaleDateString();
  fila.appendChild(fechaInicioValidez);

  const fechaFinValidez = document.createElement("td");
  fechaFinValidez.textContent = new Date(vencimiento.fechaFinValidez).toLocaleDateString();
  fila.appendChild(fechaFinValidez);

  const diasDuracion = document.createElement("td");
  diasDuracion.textContent = vencimiento.diasDuracion;
  fila.appendChild(diasDuracion);

  const acciones = document.createElement("td");
  const editarBoton = document.createElement("button");
  editarBoton.textContent = "Editar";
  editarBoton.addEventListener("click", () => mostrarEditarFormulario(vencimiento));
  acciones.appendChild(editarBoton);
  const eliminarBoton = document.createElement("button");
  eliminarBoton.textContent = "Eliminar";
  eliminarBoton.addEventListener("click", () => eliminarVencimientoClick(vencimiento));
  acciones.appendChild(eliminarBoton);
  fila.appendChild(acciones);

  return fila;
}

// Función para mostrar la tabla de vencimientos
async function mostrarTablaVencimientos() {
  const vencimientos = await obtenerVencimientos();

  tablaBody.innerHTML = "";

  vencimientos.sort((a, b) => a.idVencimientoPuntos - b.idVencimientoPuntos);

  vencimientos.forEach((vencimiento) => {
    const fila = crearFilaVencimiento(vencimiento);
    tablaBody.appendChild(fila);
  });
}

// Función para mostrar el formulario de edición de vencimiento
function mostrarEditarFormulario(vencimiento) {
  editarForm["editar-id"].value = vencimiento.idVencimientoPuntos;
  editarForm["editar-fechaInicioValidez"].valueAsNumber = vencimiento.fechaInicioValidez;
  editarForm["editar-fechaFinValidez"].valueAsNumber = vencimiento.fechaFinValidez;
  editarForm["editar-diasDuracion"].value = vencimiento.diasDuracion;

  // habilito el display del form
  editarFormulario.style.display = "block";
}

// Función para ocultar el formulario de edición de vencimiento
function ocultarEditarFormulario() {
  editarFormulario.style.display = "none";
}

// Función para agregar un vencimiento al hacer clic en el botón de enviar formulario
formulario.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const vencimiento = {
    fechaInicioValidez: formulario.fechaInicioValidez.value,
    fechaFinValidez: formulario.fechaFinValidez.value,
    diasDuracion: formulario.diasDuracion.value,
  };

  const resultado = await agregarVencimiento(vencimiento);

  if (resultado) {
    formulario.reset();
    mostrarTablaVencimientos();
  }
});

// Función para eliminar un vencimiento al hacer clic en el botón de eliminar en la fila de la tabla
async function eliminarVencimientoClick(vencimiento) {
  const confirmacion = confirm(`¿Desea eliminar al vencimiento ${vencimiento.idVencimientoPuntos}?`);
  if (confirmacion) {
    const resultado = await eliminarVencimiento(vencimiento.idVencimientoPuntos);
    mostrarTablaVencimientos();
  }
}

// Función para actualizar un vencimiento al hacer clic en el botón de enviar formulario de edición
editarForm.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const vencimiento = {
    fechaInicioValidez: editarForm["editar-fechaInicioValidez"].value,
    fechaFinValidez: editarForm["editar-fechaFinValidez"].value,
    diasDuracion: editarForm["editar-diasDuracion"].value
  };
  const resultado = await actualizarVencimiento(editarForm["editar-id"].value, vencimiento);
  if (resultado) {
    ocultarEditarFormulario();
    mostrarTablaVencimientos();
  }
});

// Mostrar la tabla de vencimientos cuando se cargue la página
mostrarTablaVencimientos();