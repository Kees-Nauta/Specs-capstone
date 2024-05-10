const registerForm = document.getElementById("register-form")
const registerUsername = document.getElementById("username-input")
const registerPassword = document.getElementById("password-input")
const registerFirstName = document.getElementById("first-name-input")
const registerLastName = document.getElementById("last-name-input")
const registerEmail = document.getElementById("email-input")
const registerPhoneNumber = document.getElementById("phone-number-input")
const registerAddress = document.getElementById("address-input")



const headers = {
    "Content-Type":"application/json"
}

const baseUrl = 'http://localhost:8080/users'

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password_hash: registerPassword.value,
        first_name: registerFirstName.value,
        last_name: registerLastName.value,
        email: registerEmail.value,
        phone_number: registerPhoneNumber.value,
        address: registerAddress.value,
        admin: false,
        groomer: false
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        window.location.replace(responseArr[0])
    }
}

registerForm.addEventListener("submit", handleSubmit)