// Definir la URL del servidor como una variable global
const API_URL = 'http://localhost:8080/prueba/servicios/carga-puntos';

// Obtener los elementos del DOM
const formCargarPuntos = document.getElementById("cargar-puntos");
const tablaBody = document.getElementById("tabla-body");
const formUsarPuntos = document.getElementById("usar-puntos");
const editarForm = formUsarPuntos.querySelector("form");

// Función para obtener todos los reglas de la API
async function obtenerReglas() {
  try {
    const response = await fetch(API_URL);
    const reglas = await response.json();
    return reglas;
  } catch (error) {
    console.error(error);
  }
}

// Función para agregar un regla a la API
async function agregarRegla(regla) {
  try {
    const response = await fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(regla)
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error(error);
  }
}

// Función para eliminar un regla de la API
async function eliminarRegla(id) {
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

// Función para actualizar un regla en la API
async function actualizarRegla(id, regla) {
  try {
    const response = await fetch(`${API_URL}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(regla)
    });
    //como nuestro servidor no retorna nada al actualizar, retorno true
    //const data = await response.json();
    //return data;
    return true;
  } catch (error) {
    console.error(error);
  }
}

// Función para crear un elemento de fila de la tabla de reglas
function crearFilaRegla(regla) {
  const fila = document.createElement("tr");

  const id = document.createElement("td");
  id.textContent = regla.idReglasAsigPuntos;
  fila.appendChild(id);

  const limiteInferior = document.createElement("td");
  limiteInferior.textContent = regla.limiteInferior;
  fila.appendChild(limiteInferior);

  const limiteSuperior = document.createElement("td");
  limiteSuperior.textContent = regla.limiteSuperior;
  fila.appendChild(limiteSuperior);

  const montoEquivalencia = document.createElement("td");
  montoEquivalencia.textContent = regla.montoEquivalencia;
  fila.appendChild(montoEquivalencia);

  const acciones = document.createElement("td");
  const editarBoton = document.createElement("button");
  editarBoton.textContent = "Editar";
  editarBoton.addEventListener("click", () => mostrarEditarFormulario(regla));
  acciones.appendChild(editarBoton);
  const eliminarBoton = document.createElement("button");
  eliminarBoton.textContent = "Eliminar";
  eliminarBoton.addEventListener("click", () => eliminarReglaClick(regla));
  acciones.appendChild(eliminarBoton);
  fila.appendChild(acciones);

  return fila;
}

// Función para mostrar la tabla de reglas
async function mostrarTablaReglas() {
  const reglas = await obtenerReglas();

  tablaBody.innerHTML = "";

  reglas.sort((a, b) => a.idReglasAsigPuntos - b.idReglasAsigPuntos);

  reglas.forEach((regla) => {
    const fila = crearFilaRegla(regla);
    tablaBody.appendChild(fila);
  });
}

// Función para mostrar el formulario de edición de regla
function mostrarEditarFormulario(regla) {
  editarForm["editar-id"].value = regla.idReglasAsigPuntos;
  editarForm["editar-limiteInferior"].value = regla.limiteInferior;
  editarForm["editar-limiteSuperior"].value = regla.limiteSuperior;
  editarForm["editar-montoEquivalencia"].value = regla.montoEquivalencia;

  // habilito el display del form
  formUsarPuntos.style.display = "block";
}

// Función para ocultar el formulario de edición de regla
function ocultarEditarFormulario() {
  formUsarPuntos.style.display = "none";
}

// Función para agregar un regla al hacer clic en el botón de enviar formulario
formCargarPuntos.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const regla = {
    limiteInferior: formCargarPuntos.limiteInferior.value,
    limiteSuperior: formCargarPuntos.limiteSuperior.value,
    montoEquivalencia: formCargarPuntos.montoEquivalencia.value,
  };

  const resultado = await agregarRegla(regla);

  if (resultado) {
    formulario.reset();
    mostrarTablaReglas();
  }
});

// Función para eliminar un regla al hacer clic en el botón de eliminar en la fila de la tabla
async function eliminarReglaClick(regla) {
  const confirmacion = confirm(`¿Desea eliminar al regla ${regla.idReglasAsigPuntos}?`);
  if (confirmacion) {
    const resultado = await eliminarRegla(regla.idReglasAsigPuntos);
    mostrarTablaReglas();
  }
}

// Función para actualizar un regla al hacer clic en el botón de enviar formulario de edición
editarForm.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const regla = {
    limiteInferior: editarForm["editar-limiteInferior"].value,
    limiteSuperior: editarForm["editar-limiteSuperior"].value,
    montoEquivalencia: editarForm["editar-montoEquivalencia"].value
  };
  const resultado = await actualizarRegla(editarForm["editar-id"].value, regla);
  if (resultado) {
    ocultarEditarFormulario();
    mostrarTablaReglas();
  }
});

// Mostrar la tabla de reglas cuando se cargue la página
mostrarTablaReglas();