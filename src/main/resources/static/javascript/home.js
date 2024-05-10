const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const logoutBtn = document.getElementById("login-btn")
const cardList = document.getElementById("card-list")

const addAppointmentBtn = document.getElementById

const headers = {
    "Content-Type": "application/json"
}


const baseUrl = "http://localhost:8080/"

function handleLogout() {
    let c = document.cookie.split(";");
    for(let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

// async function getAllDogsByUserId(userId) {
//     await fetch(`${baseUrl}dogs/users/${userId}`, {
//         method: "GET",
//         headers: headers
//     })
//         .then(response => response.json())
//         // .then(data => createCards(data))
//         .catch(err => console.error(err))
// }

async function getAllDogsByUserId(userId) {
    try {
        const response = await fetch(`${baseUrl}dogs/users/${userId}`, {
            method: "GET",
            headers: headers
        });
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        const appointmentsResponse = await fetch(`${baseUrl}appointments`);
        if (!appointmentsResponse.ok) {
            throw new Error(`HTTP error! Status: ${appointmentsResponse.status}`);
        }
        const appointmentsData = await appointmentsResponse.json();
        renderDogs(data, appointmentsData);
    } catch (error) {
        console.error(error);
    }
}

async function getAllAppointmentsByDogId(dogId) {
    await fetch(`${baseUrl}/dogs/${dogId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
    
        .catch(err => console.error(err))
}

async function deleteDogByDogId(dogId) {
    await fetch(`${baseUrl}/appointments/${dogId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getDogs(userId);
}

// async function deleteAppointmentByDogId(dogId) {
//     await fetch(baseUrl + dogId, {
//         method: "DELETE",
//         headers: headers
//     })
//         .catch(err => console.error(err))

//     return getAppointments(dogId);
// }

async function deleteAppointmentById(appointmentId) {
    await fetch(baseUrl + appointmentId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
}

async function editAppointmentById(AppointmentId) {
    await fetch(baseUrl + AppointmentId, {
        method: "PUT",
        headers: headers
    })
        .catch(err => console.error(err))
}

function createCards(dogs, appointments) {
    const cardList = document.getElementById('card-list');
    cardList.innerHTML = ''; 

    dogs.forEach(dog => {
        const card = document.createElement('div');
        card.classList.add('card');
        card.innerHTML = `
            <h2>${dog.name}</h2>
        `;
        const dogAppointments = appointments.filter(appointment => appointment.dogId === dog.id && !appointment.complete);
        if (dogAppointments.length > 0) {
            card.innerHTML += `
                <h3>Appointments:</h3>
                <ul>
                    ${dogAppointments.map(appointment => `
                        <li>
                            Date: ${appointment.date}, Service: ${appointment.service}, Time: ${appointment.time}
                            <button onclick="deleteAppointment(${appointment.id})">Delete</button>
                            <button onclick="editAppointment(${appointment.id})">Edit Appointment</button>
                        </li>`).join('')}
                </ul>
            `;
        } else {
            card.innerHTML += `
                <button onclick="deleteDog(${dog.id})">Delete Dog</button>
                <button onclick="scheduleAppointment(${dog.id})">Schedule Appointment</button>
            `;
        }
        cardList.appendChild(card);
    });
}

function renderDogs(data, appointmentsData) {
    if (!data) {
        console.error("No data received for dogs");
        return;
    }

    const dogs = Array.isArray(data) ? data : [data];
    const cardList = document.getElementById('card-list');
    cardList.innerHTML = '';

    dogs.forEach(dog => {
        const card = createCards(dog, appointmentsData);
        const cardListItem = document.createElement('li');
        cardListItem.classList.add('card');
        cardListItem.appendChild(card);
        cardList.appendChild(cardListItem);
    });
}


window.addEventListener("load", () => getAllDogsByUserId(userId));
window.addEventListener(renderDogs())
logoutBtn.addEventListener("click", handleLogout)