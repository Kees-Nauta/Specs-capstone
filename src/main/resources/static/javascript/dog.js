const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const logoutBtn = document.getElementById("login-btn")
const submitBtn = document.getElementById("submit-btn")
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

async function addDog(userId) {
    const dogName = document.getElementById('dog-name-input').value;
    const breed = document.getElementById('breed-input').value;
    const weight = document.getElementById('weight-input').value;

    const newDog = {
        dog_name: dogName,
        breed: breed,
        weight: weight
    };

    try {
        const response = await fetch(`${baseUrl}dogs/users/${userId}`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(newDog)
        });
        if (response.ok) {
            window.location.href = './home.html';
        } else {
            throw new Error('Failed to add dog');
        }
    } catch (error) {
        console.error('Error adding dog:', error);
        alert('Failed to add dog. Please try again.');
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
        const cornerName = document.getElementById('corner-name');
        cornerName.textContent = userData.first_name;
        console.log(userData.first_name)
        return userData;
    } else {
        throw new Error("Invalid JSON response from server");
    }
}

window.addEventListener("load", () => getUserById(userId));
submitBtn.addEventListener("click", () => addDog(userId));
logoutBtn.addEventListener("click", handleLogout)



