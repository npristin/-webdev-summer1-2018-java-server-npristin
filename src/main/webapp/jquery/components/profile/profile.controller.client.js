(function () {
    var $usernameFld, $phoneFld, $emailFld, $roleFld, $dateOfBirthFld;
    var $updateBtn, $logoutBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $updateBtn = $('#update');
        $logoutBtn = $('#logout');

        $updateBtn.click(updateData);
        $logoutBtn.click(logout);

        retrieveUserData();
    }

    function updateProfile(userResponse) {
        $usernameFld = $('#username').val();
        $phoneFld = $('#phone').val();
        $emailFld = $('#email').val();
        $roleFld = $('#role').val();
        $dateOfBirthFld = $('#dateOfBirth').val();

        var user = new User();
        user.setUsername($usernameFld);
        user.setPhone($phoneFld);
        user.setEmail($emailFld);
        user.setRole($roleFld);
        user.setDateOfBirth($dateOfBirthFld);

        userService.updateProfile(user);
        alert('Profile successfully saved!');
    }

    function logout() {
        console.log("logging out");
        userService.logout;
        window.location.href = 'http://localhost:8080/jquery/components/login/login.template.client.html';
    }

    function retrieveUserData() {
        // parses query parameters to get userId, these 3 lines of code are used in reference from:
        // https://forums.asp.net/t/1903621.aspx?how+can+I+get+two+parameters+values+from+Query+string+using+jquery+
        var queries = {};
        $.each(document.location.search.substr(1).split('&'),function(c,q){ var i = q.split('=');
        queries[i[0].toString()] = i[1].toString(); });

        console.log(queries.userId);
        userId = queries.userId;

        if (typeof userId === "undefined") {
           alert("No user selected for update");
        } else {
            userService.findUserById(userId).then(populateProfile);
        }
    }

    function updateData() {
        // parses query parameters to get userId, these 3 lines of code are used in reference from:
        // https://forums.asp.net/t/1903621.aspx?how+can+I+get+two+parameters+values+from+Query+string+using+jquery+
        var queries = {};
        $.each(document.location.search.substr(1).split('&'),function(c,q){ var i = q.split('=');
        queries[i[0].toString()] = i[1].toString(); });

        console.log(queries.userId);
        userId = queries.userId;

        if (typeof userId === "undefined") {
           alert("No user selected for update");
        } else {
            userService.findUserById(userId).then(updateProfile);
        }
    }

    function populateProfile(userResponse) {
        console.log("populating user form");

        $('#username').val(userResponse.username);
        $("#username").prop("readonly", true);

        $('#phone').val(userResponse.phone);
        $('#email').val(userResponse.email);
        $('#role').val(userResponse.role);
        $('#dateOfBirth').val(userResponse.dateOfBirth);
    }
})();
