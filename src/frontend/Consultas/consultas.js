// Definir la URL del servidor como una variable global
const API_URL= 'http://localhost:8080/prueba';

// Obtener los elementos del DOM
const buscarUsoPuntosForm = document.getElementById("buscar-uso-puntos");
const codeBuscarUsoPuntos = document.getElementById("code-buscar-uso-puntos");
const buscarBolsaPuntos = document.getElementById("bolsa-puntos");
const codeBolsaPuntos = document.getElementById("code-bolsa-puntos");
const puntosVencer = document.getElementById("puntos-vencer");
const codepuntosVencer = document.getElementById("code-puntos-vencer");
const cliente = document.getElementById("cliente");
const codeCliente = document.getElementById("code-cliente");


cliente.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  let URL = `${API_URL}/cliente/?`

  const nombre = cliente.nombre.value
  const apellido = cliente.apellido.value
  const fechaNacimiento = cliente.fechaNacimiento.value

  if (nombre !== "") {
    URL += `nombre=${nombre}&`
  }

  if (apellido !== "") {
    URL += `apellido=${apellido}&`
  }

  if (fechaNacimiento !== "") {
    URL += `fechaNacimiento=${fechaNacimiento}&`
  }

  try {
    const response = await fetch(URL, {
      method: "GET",
    });
    const data = await response.json();
    codeCliente.innerHTML = JSON.stringify(data, null, 2)
    return data;
  } catch (error) {
    console.error(error);
    return "error"
  }
});


puntosVencer.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const dias = puntosVencer.dias.value

  let URL = `${API_URL}/cliente/puntos-a-vencer?dias=${dias}`

  try {
    const response = await fetch(URL, {
      method: "GET",
    });
    const data = await response.json();
    codepuntosVencer.innerHTML = JSON.stringify(data, null, 2)
    return data;
  } catch (error) {
    console.error(error);
    return "error"
  }
});

buscarUsoPuntosForm.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  let URL = `${API_URL}/servicios/buscar?`

  const idConceptoPuntos = buscarUsoPuntosForm.idConceptoPuntos.value
  const idCliente = buscarUsoPuntosForm.idCliente.value
  const fecha = buscarUsoPuntosForm.fecha.value

  if (idConceptoPuntos !== "") {
    URL += `idConceptoPuntos=${idConceptoPuntos}&`
  }

  if (idCliente !== "") {
    URL += `idCliente=${idCliente}&`
  }

  if (fecha !== "") {
    URL += `fecha=${fecha}&`
  }

  try {
    const response = await fetch(URL, {
      method: "GET",
    });
    const data = await response.json();
    codeBuscarUsoPuntos.innerHTML = JSON.stringify(data, null, 2)
    return data;
  } catch (error) {
    console.error(error);
    return "error"
  }
});

buscarBolsaPuntos.addEventListener("submit", async (evento) => {
  evento.preventDefault();

  const idCliente = buscarBolsaPuntos.idCliente.value
  const minPuntos = buscarBolsaPuntos.minPuntos.value
  const maxPuntos = buscarBolsaPuntos.maxPuntos.value

  let URL = `${API_URL}/cliente/bolsa-puntos/${idCliente}/rango?`

  if (minPuntos !== "") {
    URL += `minPuntos=${minPuntos}&`
  }

  if (maxPuntos !== "") {
    URL += `maxPuntos=${maxPuntos}&`
  }

  try {
    const response = await fetch(URL, {
      method: "GET",
    });
    const data = await response.json();
    codeBolsaPuntos.innerHTML = JSON.stringify(data, null, 2)
    return data;
  } catch (error) {
    console.error(error);
    return "error"
  }
});
