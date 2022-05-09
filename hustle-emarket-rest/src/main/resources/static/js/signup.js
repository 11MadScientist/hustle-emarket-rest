document.addEventListener('keypress', function (e) {
    if (e.keyCode === 13 || e.which === 13) {
        e.preventDefault();
        return false;
    }
    
});


var currentTab = 0;

var step1 = document.getElementById("step1");
var step2 = document.getElementById("step2");
var step3 = document.getElementById("step3");

var next1 = document.getElementById("next1");
var next2 = document.getElementById("next2");

var back1 = document.getElementById("back1");
var back2 = document.getElementById("back2");

var progress = document.getElementById("progress");


// boolean for if the field is already acceptable
var isUsernameValid = true;
var isEmailValid = true;
// listener for fields
var username = document.getElementById("username");
var email = document.getElementById("email");


// first step
username.onchange = function()
{
    validateUsername();
}

next1.onclick = function()
{
    username.blur();
    if(isUsernameValid)
    {
       var isPassValid = (isNotNull() && validatePassword());
       console.log(isNotNull());
       if(isPassValid)
       {
         currentTab +=1;
         step1.style.left = "-450px";
         step2.style.left = "0px";
         progress.style.width = "240px";

       }
   }

}

back1.onclick = function()
{
    currentTab -=1;
    step1.style.left = "0px";
    step2.style.left = "+450";
    progress.style.width = "120px";
}

// second step
email.onchange = function()
{
    
    validateEmail();
}

next2.onclick = function()
{

    email.blur();
    var isPassValid = (isNotNull() && isEmailValid);
    if(isPassValid)
    {
        currentTab +=1;
        step2.style.left = "-450px";
        step3.style.left = "0px";
        progress.style.width = "360px";
    }

   
}



back2.onclick = function()
{
    currentTab -=1;
    step2.style.left = "0px";
    step3.style.left = "+450";
    progress.style.width = "240px";
}

/**********Validation**********/

// Not Null Validation

function isNotNull()
{
    var steps;
    var inputs;
    var i;
    var valid = true;
    steps = document.getElementsByClassName("step-container");
    inputs = steps[currentTab].getElementsByTagName("input");

    for(i = 0; i<inputs.length; i++)
    {
        // if fields is null
        if(inputs[i].value == "")
        {
            // adding "invalid" class to the field to 
            // make it red
            inputs[i].className += " invalid";

            valid = false;
        }
    }

    return valid
}

// password validation
function validatePassword()
{
    var password1 = document.getElementById("password1");
    var password2 = document.getElementById("password2");

    let result = password1.value.localeCompare(password2.value);

    if(result != 0)
    {
        password1.className += " invalid";
        password2.className += " invalid";
        password2.value = "";
        password2.placeholder = "password does not match confirm"

        return false;
    }
    else
    {
        result = password1.value.match
        (/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@$!%*?&_])[A-Za-z\d@$!%*?&_]{8,}$/);

        if(result == null)
        {
            password1.className += " invalid";
            password2.className += " invalid";
            password2.value = "";
            password2.placeholder = "At least 1 Uppercase 1 lowercase 1 special character"
        }
        else
        {
            password1.className = "form-field";
            password2.className = "form-field";
        }

       

        return result;
    }

    

}

// email validation

function validateEmail()
{
    
    if(String(email.value)
    .toLowerCase()
    .match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) 
    {
        var ajax = new XMLHttpRequest();
        var method = "GET";
        var url = "validation?" + "email="+email.value;
        var asynchronous = true;
    
        ajax.open(method, url, asynchronous);
    
        // sending ajax request
        ajax.send();
    
        // receiving response from url
        ajax.onreadystatechange = function()
        {
            if(this.readyState == 4 && this.status == 200)
            {
                if(this.responseText == "false")
                {
                    email.value = "";
                    email.placeholder = "Email Taken"
                    email.className += " invalid";
                    isEmailValid = false;
                    return;
                }
                email.className = "form-field";
                isEmailValid = true;
            }
            
        }
    }
    else
    {
        email.value = "";
        email.placeholder = "Email Invalid"
        email.className += " invalid";
        isEmailValid = false;
    }
}

// username validation

function validateUsername()
{
    var ajax = new XMLHttpRequest();
    var method = "GET";
    var url = "validation?" +"username="+ username.value;
    var asynchronous = true;

    ajax.open(method, url, asynchronous);

    // sending ajax request
    ajax.send();

    // receiving response from url
    ajax.onreadystatechange = function()
    {
        if(this.readyState == 4 && this.status == 200)
        {
            console.log(this.responseText);
            if(this.responseText == "false")
            {
                username.value = "";
                username.placeholder = "Username Taken"
                username.className += " invalid";
                isUsernaretumeValid = false;
                return;
            }
            username.className = "form-field";
            isUsernameValid = true;
        }
        
    }



}