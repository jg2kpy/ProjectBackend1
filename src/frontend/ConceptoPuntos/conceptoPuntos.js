// Definir la URL del servidor como una variable global
const API_URL = 'http://localhost:8080/prueba/concepto-puntos';

// Obtener los elementos del DOM
const formulario = document.querySelector("form");
const tablaBody = document.getElementById("tabla-body");
const editarFormulario = document.getElementById("editar-formulario");
const editarForm = editarFormulario.querySelector("form");

// Función para obtener todos los conceptos de la API
async function obtenerConceptos() {
  try {
    const response = await fetch(API_URL);
    const conceptos = await response.json();
    return conceptos;
  } catch (error) {
    console.error(error);
  }
}

// Función para agregar un concepto a la API
async function agregarConcepto(concepto) {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(concepto)
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
}

// Función para eliminar un concepto de la API
async function eliminarConcepto(id) {
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

// Función para actualizar un concepto en la API
async function actualizarConcepto(id, concepto) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(concepto)
    });
    //como nuestro servidor no retorna nada al actualizar, retorno true
    //const data = await response.json();
    //return data;
    return true;
  } catch (error) {
    console.error(error);
  }
}

// Función para crear un elemento de fila de la tabla de conceptos
function crearFilaConcepto(concepto) {
  const fila = document.createElement("tr");

  const id = document.createElement("td");
  id.textContent = concepto.idConceptoPuntos;
  fila.appendChild(id);

  const descripcion = document.createElement("td");
  descripcion.textContent = concepto.descripcion;
  fila.appendChild(descripcion);

  const puntosRequeridos = document.createElement("td");
  puntosRequeridos.textContent = concepto.puntosRequeridos;
  fila.appendChild(puntosRequeridos);

  const acciones = document.createElement("td");
  const editarBoton = document.createElement("button");
  editarBoton.textContent = "Editar";
  editarBoton.addEventListener("click", () => mostrarEditarFormulario(concepto));
  acciones.appendChild(editarBoton);
  const eliminarBoton = document.createElement("button");
  eliminarBoton.textContent = "Eliminar";
  eliminarBoton.addEventListener("click", () => eliminarConceptoClick(concepto));
  acciones.appendChild(eliminarBoton);
  fila.appendChild(acciones);

  return fila;
}

// Función para mostrar la tabla de conceptos
async function mostrarTablaConceptos() {
  const conceptos = await obtenerConceptos();

  tablaBody.innerHTML = "";

  conceptos.sort((a, b) => a.idConceptoPuntos - b.idConceptoPuntos);

  conceptos.forEach((concepto) => {
    const fila = crearFilaConcepto(concepto);
    tablaBody.appendChild(fila);
  });
}

// Función para mostrar el formulario de edición de concepto
function mostrarEditarFormulario(concepto) {
  editarForm["editar-id"].value = concepto.idConceptoPuntos;
  editarForm["editar-descripcion"].value = concepto.descripcion;
  editarForm["editar-puntosRequeridos"].value = concepto.puntosRequeridos;

  // habilito el display del form
  editarFormulario.style.display = "block";
}

// Función para ocultar el formulario de edición de concepto
function ocultarEditarFormulario() {
  editarFormulario.style.display = "none";
}

// Función para agregar un concepto al hacer clic en el botón de enviar formulario
formulario.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const concepto = {
    descripcion: formulario.descripcion.value,
    puntosRequeridos: formulario.puntosRequeridos.value,
  };

  const resultado = await agregarConcepto(concepto);

  if (resultado) {
    formulario.reset();
    mostrarTablaConceptos();
  }
});

// Función para eliminar un concepto al hacer clic en el botón de eliminar en la fila de la tabla
async function eliminarConceptoClick(concepto) {
  const confirmacion = confirm(`¿Desea eliminar al concepto ${concepto.descripcion}?`);
  if (confirmacion) {
    const resultado = await eliminarConcepto(concepto.idConceptoPuntos);
    mostrarTablaConceptos();
  }
}

// Función para actualizar un concepto al hacer clic en el botón de enviar formulario de edición
editarForm.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const concepto = {
    descripcion: editarForm["editar-descripcion"].value,
    puntosRequeridos: editarForm["editar-puntosRequeridos"].value
  };
  const resultado = await actualizarConcepto(editarForm["editar-id"].value, concepto);
  if (resultado) {
    ocultarEditarFormulario();
    mostrarTablaConceptos();
  }
});

// Mostrar la tabla de conceptos cuando se cargue la página
mostrarTablaConceptos();