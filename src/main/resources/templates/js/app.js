const homeLink = document.getElementById("home-link");
const form = document.getElementById("create-form");
const inputField = document.getElementById("usernameInput");
const content = document.getElementById("content");


form.addEventListener("submit", (event) => {
    event.preventDefault();
    let username = inputField.value;

    console.log(username);

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username})
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



homeLink.addEventListener("click", (event) => {
    event.preventDefault();

    content.innerHTML = `
    <form id="create-form">
        <div>
            <label for="usernameInput">Username: </label>
            <input type="text" name="usernameInput" id="usernameInput">
        </div>
        <div>
            <input type="submit" value="CREATE" >
        </div>  
    </form>
    `;
});
