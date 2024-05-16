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

async function loadAppointments() {
    try {
        const userData = await getUserById(userId);
        const cornerName = document.getElementById('corner-name');
        cornerName.textContent = userData.first_name;
        
        if (userData.admin === true) {
            await getAllAppointments();
        } else {
            await getAllDogsByUserId(userId);
        }
    } catch (error) {
        console.error(error);
    }
}

async function getUserById(userId) {
    
    const response = await fetch(`${baseUrl}users/${userId}`, {
        method: "GET",
        headers: headers
    });

    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    } 

    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
        const userData = await response.json();
        console.log(userData.admin)
        return userData;
    } else {
        throw new Error("Invalid JSON response from server");
    }
}

async function getAllAppointments() {
    try {
        const response = await fetch(`${baseUrl}appointments/all`, {
            method: 'GET',
            headers: headers
        });
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        createAdminCards(data)
        return data;
    } catch (error) {
        console.error(error);
        return [];
    }
}

async function getAllDogsByUserId(userId) {
    try {
        const response = await fetch(`${baseUrl}dogs/users/${userId}`, {
            method: "GET",
            headers: headers
        });
        if (!response.ok) {
            throw new Error(`Failed to fetch dogs for user with ID ${userId}`);
        }
        const data = await response.json();
        createCards(data);
        console.log(data)
        return data;
    } catch (err) {
        console.error(err);
        return null; 
    }
}


async function getAllAppointmentsByDogId(dogId) {
    try {
        const response = await fetch(`${baseUrl}appointments/dogs/${dogId}`, {
            method: "GET",
            headers: headers
        });
        if (!response.ok) {
            throw new Error(`Failed to fetch appointments for dog with ID ${dogId}`);
        }
        
        return await response.json();
    } catch (err) {
        console.error(err);
        return null; 
    }
}

async function deleteDogByDogId(dogId) {
    try {
        await fetch(`${baseUrl}dogs/${dogId}`, {
            method: "DELETE",
            headers: headers
        });
        return getAllDogsByUserId(userId);
    } catch (err) {
        console.error(err);
    }
}

async function deleteAppointmentById(appointmentId) {
    try {
        await fetch(`${baseUrl}appointments/${appointmentId}`, {
            method: "DELETE",
            headers: headers
        });
        return getAllDogsByUserId(userId);
    } catch (err) {
        console.error(err);
    }
}

async function editAppointmentById(AppointmentId) {
    await fetch(baseUrl + AppointmentId, {
        method: "PUT",
        headers: headers
    })
        .catch(err => console.error(err))
}

async function getDogByDogId(dogId) {
    try {
        const response = await fetch(`${baseUrl}dogs/${dogId}`, {
            method: "GET",
            headers: headers
        });
        if (!response.ok) {
            if (response.status === 404) {
                throw new Error(`Dog with ID ${dogId} not found`);
            } else {
                throw new Error(`Failed to fetch dog with ID ${dogId}`);
            }
        }
        return await response.json();
    } catch (err) {
        console.error(err);
        throw err; 
    }
}

async function createCards(dogsData) {
    const cardList = document.getElementById('card-list');
    cardList.innerHTML = `
    <a href="./dog.html" id="add-dog-a"><li class="card" id="add-dog-card">ADD DOG</li></a>`;

    for (const dog of dogsData) {
        const card = document.createElement('div');
        card.classList.add('card');
        card.innerHTML = `
        `;
        console.log(dog.id)
        const appointments = await getAllAppointmentsByDogId(dog.id);
        const dogAppointments = appointments.filter(appointment => !appointment.complete);
        if (dogAppointments.length > 0) {
            card.innerHTML += `
                <h2 class="appointment-card">${dog.dog_name}</h2>
                <ul>
                    ${dogAppointments.map(appointment => `
                        <li>
                            <div class="appointment-date">Date: ${appointment.date}</div>
                            <div class="appointment-time">Time: ${appointment.time}</div>
                            <div class="appointment-service">Service: ${appointment.service}</div>
                            <button onclick="deleteAppointmentById(${appointment.id})" class="cancel-appointment">Cancel Appointment</button>
                            <a href="./editappointment.html?appointmentId=${appointment.id}&dogId=${dog.id}"><button class="edit-appointment">Edit Appointment</button></a>
                        </li>`).join('')}
                </ul>
            `;
        }
         else {
            card.innerHTML += `
            <h2 class="dog-card">${dog.dog_name}</h2>
    <button class="remove-dog-btn" onclick="deleteDogByDogId(${dog.id})">Remove Dog</button>
    <a href="./scheduleappointment.html?dogId=${dog.id}"><button class="schedule-appointment-btn">Schedule Appointment</button></a>
`;
        }

        cardList.appendChild(card);
    }
}

async function createAdminCards(appointmentsData) {
    console.log(appointmentsData)
    const cardList = document.getElementById('card-list');
    cardList.innerHTML = '';

    for (const appointmentData of appointmentsData) {
        const dogId = appointmentData.dog_id;
        const dog = await getDogByDogId(dogId);

        if (!dog) {
            console.error(`Failed to fetch dog with ID ${dogId}`);
            continue;
        }

        const card = document.createElement('div');
        card.classList.add('card');
        card.innerHTML = `
            <h2>${dog.dog_name}</h2>
        `;

        const appointments = await getAllAppointmentsByDogId(dogId);
        const uncompletedAppointments = appointments.filter(appointment => !appointment.complete);

        if (uncompletedAppointments.length > 0) {
            card.innerHTML += `
                <h3>Appointments:</h3>
                <ul>
                    ${uncompletedAppointments.map(appointment => `
                        <li>
                            Date: ${appointment.date}, Service: ${appointment.service}, Time: ${appointment.time}
                            <button onclick="deleteAppointmentById(${appointment.id})">Cancel Appointment</button>
                        </li>`).join('')}
                </ul>
            `;
        } else {
            card.innerHTML += `
                <p>No appointments scheduled.</p>
            `;
        }

        cardList.appendChild(card);
    }
}



window.addEventListener("load", () => loadAppointments());
logoutBtn.addEventListener("click", handleLogout)