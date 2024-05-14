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

submitBtn.addEventListener("click", () => addDog(userId));
logoutBtn.addEventListener("click", handleLogout)



