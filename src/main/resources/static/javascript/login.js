const loginForm = document.getElementById("login-form")
const loginUsername = document.getElementById("username-input")
const loginPassword = document.getElementById("password-input")


const headers = {
    "Content-Type":"application/json"
}

const baseUrl = 'http://localhost:8080/users'

const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: loginUsername.value,
        password_hash: loginPassword.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        document.cookie = `userId=${responseArr[1]}`
        window.location.replace(responseArr[0])
    }
}

loginForm.addEventListener("submit", handleSubmit)