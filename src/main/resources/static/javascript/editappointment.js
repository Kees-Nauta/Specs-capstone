const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const logoutBtn = document.getElementById("login-btn")
const submitBtn = document.getElementById("submit-btn")
const headers = {
    "Content-Type": "application/json"
}
const urlParams = new URLSearchParams(window.location.search);
const appointmentId = urlParams.get('appointmentId');
const dogId = urlParams.get('dogId');
const baseUrl = "http://localhost:8080/"

console.log('Appointment ID:', appointmentId);

function handleLogout() {
    let c = document.cookie.split(";");
    for(let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

async function editAppointment() {
    const service = document.getElementById('service-type-input').value;
    const date = document.getElementById('date-input').value;
    const time = document.getElementById('time-input').value;
    const groomerName = document.getElementById('preferd-groomer-select').value;

    const newAppointment = {
        service: service,
        date: date,
        time: time,
        groomerName: groomerName,
        complete: false,
        id: Number(appointmentId)
    };

    try {
        const response = await fetch(`${baseUrl}appointments/${appointmentId}`, {
            method: 'PUT',
            headers: headers,
            body: JSON.stringify(newAppointment)
        });

        if (response.ok) {
            window.location.href = './home.html';
        } else {
            throw new Error('Failed to schedule appointment');
        }
    } catch (error) {
        console.error('Error scheduling appointment:', error);
        alert('Failed to schedule appointment. Please try again.');
    }
}

async function populateAppointmentData(appointmentId) {
    try {
        const response = await fetch(`${baseUrl}appointments/${appointmentId}`,{
        method: "GET",
        headers: headers

    });
        if (response.ok) {
            const appointment = await response.json();
            document.getElementById('service-type-input').value = appointment.service;
            document.getElementById('date-input').value = appointment.date;
            document.getElementById('time-input').value = appointment.time;
            document.getElementById('preferd-groomer-select').value = appointment.groomerName;
        } else {
            throw new Error('failed to fetch appointment details');
        }
    } catch (error) {
        console.error('error fetching appointment details', error);
        alert('failed to fetch appointment details');
    }
}

submitBtn.addEventListener("click", () => {
    editAppointment();
});

document.addEventListener("DOMContentLoaded", () => {
    populateAppointmentData(appointmentId);
});