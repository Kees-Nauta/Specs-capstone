const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const logoutBtn = document.getElementById("login-btn")
const submitBtn = document.getElementById("submit-btn")



const headers = {
    "Content-Type": "application/json"
}
const urlParams = new URLSearchParams(window.location.search);
const dogId = urlParams.get('dogId');


const baseUrl = "http://localhost:8080/"

function handleLogout() {
    let c = document.cookie.split(";");
    for(let i in c) {
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

async function scheduleAppointment() {
    const service = document.getElementById('service-type-input').value;
    const date = document.getElementById('date-input').value;
    const time = document.getElementById('time-input').value;
    const groomerName = document.getElementById('preferd-groomer-select').value;

    const newAppointment = {
        service: service,
        date: date,
        time: time,
        groomerName: groomerName,
        complete: false
    };

    try {
        const response = await fetch(`${baseUrl}appointments/dogs/${dogId}`, {
            method: 'POST',
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

submitBtn.addEventListener("click", function() {
    console.log("Submit button clicked");
    scheduleAppointment();
});

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
        const cornerName = document.getElementById('corner-name');
        cornerName.textContent = userData.first_name;
        console.log(userData.first_name)
        return userData;
    } else {
        throw new Error("Invalid JSON response from server");
    }
}

window.addEventListener("load", () => getUserById(userId));
logoutBtn.addEventListener("click", handleLogout)