const homeLink = document.getElementById("home-link");
const form = document.getElementById("create-form");
const usernameInputField = document.getElementById("usernameInput");
const passwordInputField = document.getElementById("passwordInput");
const content = document.getElementById("content");


form.addEventListener("submit", (event) => {
    event.preventDefault();
    let username = usernameInputField.value;
    let password = passwordInputField.value;

    console.log(username);

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password})
    };

    fetch("/", options)
    .then(response => response.json())
    .then(data => {
        console.log(JSON.stringify(data, null, 2));

        if (data.result === "created") {
            content.innerHTML = `
                <h3>• Token: ${data.token}</h3>
            `;
        } else {
            content.innerHTML = `
                <h3>• Result: ${data.token}</h3>
            `;
        }
    });
});
