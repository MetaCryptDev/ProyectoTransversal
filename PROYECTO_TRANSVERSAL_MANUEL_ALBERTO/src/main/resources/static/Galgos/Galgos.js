function cargar() {
    // Inicializar el controlador
    controller.init();
    setInterval(actualizarEtiquetas, 1000);
}

function mostrarModal(idModal) {
    var modal = document.getElementById(idModal);
    modal.style.display = "block";  
}

function cerrarModal(idModal) {
    var modal = document.getElementById(idModal);
    modal.style.display = "none";
}

// HORA ACTUAL
function obtenerHoraActual() {
    const ahora = new Date();
    const horas = ahora.getHours().toString().padStart(2, '0');
    const minutos = ahora.getMinutes().toString().padStart(2, '0');
    const segundos = ahora.getSeconds().toString().padStart(2, '0');
    return `${horas}:${minutos}:${segundos}`;
}

function actualizarEtiquetas() {
    const etiquetaHora = document.getElementById('hora');
    etiquetaHora.textContent = obtenerHoraActual();
 
 // Actualizar las barras de progreso para cada galgo
 for (let i = 0; i < model.copia.length; i++) {
    const galgo = model.copia[i].avance;

    // Calcular el porcentaje de avance
    const avancePorcentaje = galgo;
    const progressBarId = `progressBar${i + 1}`;
    const progressBar = document.getElementById(progressBarId);
    actualizarProgressBar(progressBar, avancePorcentaje);
}

// ANIMACION DE RELLENO SUAVE
requestAnimationFrame(actualizarEtiquetas);
}

// Función auxiliar para actualizar la barra de progreso
function actualizarProgressBar(progressBar, value) {
if (progressBar) {
    progressBar.value = value;
}
}
 
  class Galgo{
      constructor(idGalgo, nombre, dorsal, color, ganancia, aceleracion, velocidad, experiencia, avance){
          this.idGalgo = idGalgo;
          this.nombre = nombre;
          this.dorsal = dorsal;
          this.color = color;
          this.ganancia = ganancia;
          this.aceleracion = Math.floor(Math.random()*20)+8;
          this.velocidad = Math.floor(Math.random()*20)+12;
          this.experiencia = Math.floor(Math.random()*20)+14;
          this.avance = avance;
      }
  }

  class Carrera{
      constructor(id, ciudad, hora){
          this.id = id;
          this.ciudad = ciudad;
          this.hora = hora;
          this.participantes = [];
          this.estado = false;
          this.clasificacion = [];
      }
  }

    var listaGalgos = []     // LISTA DE TODOS LOS GALGOS
	var listaCorredores = [] // LOS 8 GALGOS QUE CORREN
	var listaCarreras = []   // LISTA DE CARRERAS
	var carrerasAleatorias= [];

     // Modificar recibirGalgos para que retorne una promesa
function recibirGalgos() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/galgos/recuperarGalgos',
            type: 'GET',
            dataType: 'json',
            success: function(galgos) {
                console.log('GALGOS recogidos de BBDD correctamente');
                listaGalgos = []; // Limpiar la lista antes de agregar nuevos galgos
                galgos.forEach((item, i) => {
                    var galgo = new Galgo(
                        item.idGalgo, 
                        item.nombre, 
                        item.dorsal, 
                        item.color, 
                        item.ganancia, 
                        item.aceleracion, 
                        item.velocidad, 
                        item.experiencia, 
                        item.avance
                    );
                    listaGalgos.push(galgo);
                });
                console.log(listaGalgos);
                resolve(); // Resuelve la promesa cuando los galgos están listos
            },
            error: function(error) {
                console.error('Error al recoger GALGOS de BBDD: ', error);
                reject(error); // Rechaza la promesa si hay un error
            }
        });
    });
}

// Modificar recibirCarreras para que retorne una promesa
function recibirCarreras() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/galgos/recuperarCarreras',
            type: 'GET',
            dataType: 'json',
            success: function(carreras) {
                console.log('CARRERAS recogidas de BBDD correctamente');
                listaCarreras = []; // Limpiar la lista antes de agregar nuevas carreras
                carreras.forEach((item) => {
                    var carrera = new Carrera(
                        item.idCarrera, 
                        item.ciudad, 
                        item.hora
                    );
                    listaCarreras.push(carrera);
                });
                console.log(listaCarreras);
                resolve(); // Resuelve la promesa cuando las carreras están listas
            },
            error: function(error) {
                console.error('Error al recoger CARRERAS de BBDD:', error);
                reject(error); // Rechaza la promesa si hay un error
            }
        });
    });
}

function recibirSaldo() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: '/galgos/recuperarSaldo',
            type: 'GET',
            dataType: 'json',
            success: function(saldo) {
                console.log('SALDO recogido de BBDD correctamente');     
                resolve(saldo);
            },
            error: function(error) {
                console.error('Error al recoger el SALDO de BBDD: ', error);
                reject(error); 
            }
        });
    });
}

function enviarSaldo(idUsuario, saldo) {
    var idUsuario = document.getElementById("idUsuario").textContent;
    var saldo = document.getElementById("lblsaldo").textContent;
    
    console.log("VOY A ENVIAR EL ID: " + idUsuario);
	console.log("VOY A ENVIAR EL SALDO: " + saldo);

    $.ajax({
        url: '/galgos/actualizarSaldo',
        type: 'POST',
        data: JSON.stringify({ idUsuario: idUsuario, saldo: saldo }), 
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(response) {
            console.log('Saldo y usuario actualizados correctamente en el servidor:', response);
        },
        error: function(error) {
            console.error('Error al actualizar el saldo y usuario en el servidor:', error);
        }
    });
}

// Modificar recibirDatos para que espere a ambas promesas
function recibirDatos() {
    return Promise.all([recibirGalgos(), recibirCarreras()]);
}

// Modificar la inicialización para que espere los datos de Ajax
$(document).ready(function() {
    recibirDatos().then(() => {
        console.log('Todas las listas han sido cargadas correctamente');
        controller.init(); 
    	controller.seleccionGalgos();
		console.log(model.carreraSeleccionada);
		var saldoInicial = document.getElementById("lblsaldo").textContent;
    	model.saldo = Number(saldoInicial) || 0;
    	console.log("Saldo al entrar " + model.saldo);
    }).catch(error => {
        console.error('Hubo un error al cargar los datos', error);
    });
});

  
// MODELO
const model = {
    saldo: 0,
    apuesta : 0,
    carreraSeleccionada: null,
    turno: 1,
    perroApostado: "",
    ganador1: null,
    ganador2: null,
    ganador3: null,
    cantidadApuesta: 0,
    gananciaGanador: 0,
    copia: [],
    isApostando: false,
};

// VISTA
const view = {
    actualizarSaldo: function (saldo) {
        document.getElementById("lblsaldo").innerHTML = saldo;
    },
    resetSaldo: function () {
        this.actualizarSaldo(0);
    },
    mostrarTodo: function () {
    },

    actualizarSelectGalgos: function (corredores) {
        const selectGalgos = document.getElementById('galgos');
        selectGalgos.innerHTML = ''; // Limpiar el select antes de agregar las nuevas opciones

        // Agregar opciones al select
        corredores.forEach((galgo) => {
            const option = document.createElement('option');
            option.value = galgo.id;
            option.text = galgo.nombre;
            selectGalgos.appendChild(option);
        });
    },

    mostrarGalgos: function (corredores) {
        var a = 0;
        for (let i = 0; i < corredores.length; i++) {
            const galgo = corredores[i];
            a = i+1;
            document.getElementById("corredor"+a).innerHTML = galgo.nombre;
            document.getElementById("corredorInfo"+a).innerHTML = galgo.nombre;
            document.getElementById("id"+a).innerHTML = galgo.idGalgo;
            document.getElementById("ganancia"+a).innerHTML = galgo.ganancia;
        }
    },

    actualizarCantidadApostada: function (cantidad, galgo) {
        document.getElementById("cantidadApostada").textContent = cantidad;
        document.getElementById("perro").textContent = "al galgo " + galgo;  
    },

    mostrarCarreraSeleccionada: function (carrera) {
        document.getElementById("lugarCarrera").textContent = carrera.ciudad;
        document.getElementById("horaCarrera").textContent = carrera.hora;
    },

    actualizarGanador: function () {
      document.getElementById("ganador1").innerHTML = model.ganador1;
      document.getElementById("ganador2").innerHTML = model.ganador2;
      document.getElementById("ganador3").innerHTML = model.ganador3;
    },

    actualizarClasificacion: function (){
        for (let i = 0; i < model.carreraSeleccionada.clasificacion.length; i++) {
            const galgoClasificado = model.carreraSeleccionada.clasificacion[i];
            var a = i+1;

            document.getElementById("corredorInfo" + a).innerHTML = galgoClasificado.nombre;
            document.getElementById("id" + a).innerHTML = galgoClasificado.idGalgo;
            document.getElementById("ganancia" + a).innerHTML = galgoClasificado.ganancia;
        }
    },

    actualizarGananciaGanador: function (ganancias) {
        document.getElementById("lblganancias").innerHTML = ganancias; 
    },

    reiniciarGanadores: function (){
        document.getElementById("ganador1").innerHTML = "PRIMERO";
        document.getElementById("ganador2").innerHTML = "SEGUNDO";
        document.getElementById("ganador3").innerHTML = "TERCERO";
    },

    reiniciarApuesta: function (){
        document.getElementById("cantidadApostada").innerHTML = "0";
        document.getElementById("perro").innerHTML = "";
    }

};

// CONTROLADOR
const controller = {
    init: function () {
		//ALEATORIZAMOS LAS CARRERAS
		carrerasAleatorias = listaCarreras.slice().sort(() => Math.random() - 0.5);
		console.log(carrerasAleatorias);
		
        var btnAceptarSaldo = document.getElementById("btnAceptarSaldo");
         if (this.handlerIngresarSaldo) { 
            btnAceptarSaldo.removeEventListener("click", this.handlerIngresarSaldo);
        }
        enviarSaldo(idUsuario, saldo);
        
        
        this.handlerIngresarSaldo = this.ingresarSaldo.bind(this);
        btnAceptarSaldo.addEventListener("click", this.handlerIngresarSaldo);
        

        var btnRetirarSaldo = document.getElementById("btnRetirarSaldo");
        btnRetirarSaldo.addEventListener("click", this.retirarSaldo.bind(this));

        var btnApostar = document.getElementById("btnApostar");
        btnApostar.addEventListener("click", this.apostarGalgo.bind(this));

        var jugar = document.getElementById("jugar");
        jugar.addEventListener("click", this.carrera.bind(this));

        var nuevaCarrera = document.getElementById("nuevaCarrera");
        nuevaCarrera.addEventListener("click", this.seleccionGalgos.bind(this));
    },

    ingresarSaldo: function () {
        var ingreso = parseInt(document.getElementById("ingresarCantidad").value);

        if (isNaN(ingreso) || ingreso <= 0) {
            window.alert("Debes introducir una cantidad válida");
            return;
        }
        
        model.saldo += ingreso;
        view.actualizarSaldo(model.saldo);
        enviarSaldo(idUsuario, saldo);
        cerrarModal("modalIngresar");

        console.log("Cantidad ingresada: " + ingreso + "€");
    },

    retirarSaldo: function () {
        model.saldo = 0;

        view.resetSaldo();
		enviarSaldo(idUsuario, saldo);
		
        cerrarModal("modalRetirar");
    },

    seleccionGalgos: function(){
        var btnJugar = document.getElementById("jugar");
        btnJugar.disabled = false;
        listaGalgos.forEach((galgo) => {galgo.ganancia = 0;});
        listaGalgos.forEach((galgo) => {galgo.avance = 0;});
        model.apuesta = 0;
        model.perroApostado = "";
        var btnApostar = document.querySelector(".apuesta");
        btnApostar.disabled = false;

       //VACIAMOS LA LISTA DE CORREDORES PARA HACER UNA NUEVA CARRERA
        listaCorredores = [];

       //HACEMOS UNA COPIA DEL ARRAY DE TODOS LOS GALGOS ALEATORIZADO Y METEMOS 8 EN SELECCIONADOS 
        const galgosAleatorios = listaGalgos.slice().sort(() => Math.random() - 0.5);
        const galgosSeleccionados = galgosAleatorios.slice(0, 8);
       
       //FOR EACH QUE RECORRE LA LISTA DE GALGOS Y LOS METE A CORREDORES
        galgosSeleccionados.forEach((galgo, i) => {listaCorredores.push(galgo);
          //  console.log(`Galgo ${i + 1}: ${galgo.nombre}`);
        });

        galgosSeleccionados.forEach((galgo, i) => {model.copia.push(galgo);});
       

        if (carrerasAleatorias.length > 0) {
            //SELECCIONAMOS LA PRIMERA CARRERA Y METEMOS LOS PARTICIPANTES EN LA MISMA 
            const carrera = carrerasAleatorias.shift();
            carrera.participantes = listaCorredores;
            model.participantesCopia = carrera.participantes;

            console.log("Galgos seleccionados de la carrera:", carrera.participantes);
         
            model.carreraSeleccionada = carrera;
                     
            view.mostrarCarreraSeleccionada(model.carreraSeleccionada);
            view.actualizarSelectGalgos(listaCorredores);
            view.mostrarGalgos(listaCorredores);     
            view.reiniciarGanadores();
            view.reiniciarApuesta();
        }
    },

     carrera: function() {
            var btnJugar = document.getElementById("jugar");
            btnJugar.disabled = true;

            if(model.perroApostado == null || model.perroApostado == undefined || model.perroApostado == ""){
                window.alert("No has apostado a ningun perro");
                btnJugar.disabled = false;
            }else{
                console.log(model.carreraSeleccionada.participantes.length);
                var participantesRestantes= model.carreraSeleccionada.participantes.length;
                ejecutarCarrera();

                function ejecutarCarrera() {
                    for (var i = 0; i < participantesRestantes; i++) {
                        if (model.carreraSeleccionada.participantes[i].avance >= 100) {
                            model.carreraSeleccionada.clasificacion.push(model.carreraSeleccionada.participantes[i]);
                            console.log("El galgo " + model.carreraSeleccionada.participantes[i].nombre + " ha terminado la carrera");
                            model.carreraSeleccionada.participantes.splice(i, 1);
                            participantesRestantes--;
                            i--; // Reducir el índice ya que se eliminó un elemento
                        } else {
                            var avance = (model.carreraSeleccionada.participantes[i].velocidad + model.carreraSeleccionada.participantes[i].experiencia) / model.carreraSeleccionada.participantes[i].aceleracion;
                            // console.log(avance);
                            model.carreraSeleccionada.participantes[i].avance += avance;
                            if (model.carreraSeleccionada.participantes[i].avance > 100) {
                                model.carreraSeleccionada.participantes[i].avance = 100;
                            }
                            avance = 0;
                            console.log("El galgo " + model.carreraSeleccionada.participantes[i].nombre + " ha recorrido " + model.carreraSeleccionada.participantes[i].avance);
                        }
                    }
            
                    // Verificar si todos los galgos han terminado la carrera
                    if (participantesRestantes === 0) {
                        console.log("Todos los galgos han terminado la carrera");
                        model.carreraSeleccionada.estado = true;
                        finalizarCarrera();
                    } else {
                        // Llamar recursivamente a la función después de un retraso de 1000 milisegundos (1 segundo)
                        //ejecutarCarrera();
                        setTimeout(ejecutarCarrera, 500);
                    }
                }

                
                function finalizarCarrera() {
                    model.ganador1 = model.carreraSeleccionada.clasificacion[0].nombre;
                    model.ganador2 = model.carreraSeleccionada.clasificacion[1].nombre;
                    model.ganador3 = model.carreraSeleccionada.clasificacion[2].nombre;
                
                    console.log("Este es el perro apostado: " + model.perroApostado);
                    console.log("Esta es la carrera " + model.carreraSeleccionada.ciudad);
                
                    for (let i = 0; i < model.carreraSeleccionada.clasificacion.length; i++) {
                        const galgoClasificado = model.carreraSeleccionada.clasificacion[i];
                        console.log("Ganancia del galgo " + galgoClasificado.nombre + ": " + galgoClasificado.ganancia);
                
                        let factorGanancia = 0;
                        switch (i) {
                            case 0:
                                factorGanancia = 35;
                                break;
                            case 1:
                                factorGanancia = 25;
                                break;
                            case 2:
                                factorGanancia = 15;
                                break;
                            default:
                                factorGanancia = 10;
                        }
                
                        // Calcular la ganancia para todos los galgos
                        galgoClasificado.ganancia = model.cantidadApuesta * factorGanancia;
                        //    model.saldo = model.saldo + galgoClasificado.ganancia;
                        console.log("Ganancia para " + galgoClasificado.nombre + ": " + galgoClasificado.ganancia + "€");

                        if (galgoClasificado.nombre === model.perroApostado) {
                            console.log("El galgo apostado está en la clasificación");
                            // Actualizar el saldo y mostrar un mensaje específico para el galgo apostado
                            model.saldo += galgoClasificado.ganancia;
                            model.gananciaGanador += galgoClasificado.ganancia;
                            console.log("Ganaste " + galgoClasificado.ganancia + "€");
                            view.actualizarSaldo(model.saldo);
                            view.actualizarGananciaGanador(model.gananciaGanador);
                        }
                    }

                   // view.actualizarSaldo(model.saldo);
                    view.actualizarGanador();
                    view.actualizarClasificacion();
                    
                    enviarSaldo(idUsuario, saldo);
                    
                }
            }
    },

    apostarGalgo: function () {
		
		
		if (model.isApostando) {
		        console.log("La apuesta ya está en proceso.");
		        return; // Detiene la ejecución si ya se está apostando
		    }
		model.isApostando = true;
		
        var apuesta = parseInt(document.getElementById("apostarCantidad").value);
        model.cantidadApuesta = apuesta;
        var perro = document.getElementById("galgos");
        var opcionSeleccionada = perro.options[perro.selectedIndex];
        var nombreGalgoSeleccionado = opcionSeleccionada.text;
        model.perroApostado = nombreGalgoSeleccionado;



        if (isNaN(apuesta) || apuesta <= 0) {
            window.alert("Debes introducir una cantidad válida");
            return;
        }

        if (model.saldo <= 0 || apuesta > model.saldo){
            window.alert("No tienes saldo suficiente");
            return;
        }

        model.apuesta = apuesta;
        model.saldo -= apuesta;

        view.actualizarSaldo(model.saldo);
        view.actualizarCantidadApostada(model.apuesta, nombreGalgoSeleccionado);

        cerrarModal("modalApostar");

        var btnApostar = document.querySelector('.apuesta');
        var btnRetirarSaldo = document.querySelector('.retirar');
        btnApostar.disabled = true;
        btnRetirarSaldo = true;
        
        console.log("HAS A APOSTADO " + model.cantidadApuesta + "€ al galgo " + model.perroApostado);
    	model.isApostando = false;
    }, 
};

