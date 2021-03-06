(function () {
    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;

    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template');

        $createBtn = $('.wbdv-create');
        $removeBtn = $('.wbdv-remove');
        $editBtn = $('.wbdv-edit');

        $editingUserId = $('.wbdv-tbody').on('click', '.wbdv-edit', selectUser);

        $createBtn.click(createUser);
        $('.wbdv-tbody').on('click', '.wbdv-remove', deleteUser);
        $('.wbdv-update').click(updateUser);

        findAllUsers().then(renderUsers);
    }

    function createUser() {
        console.log("create user")

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();

        var user = {
            username: $usernameFld,
            password: $passwordFld,
            firstName: $firstNameFld,
            lastName: $lastNameFld,
            role: $roleFld
        };

        userService.createUser(user);
        renderUser(user);
    }

    function findAllUsers() {
        console.log("find users")
        return userService.findAllUsers();
    }

    function findUserById() {
        console.log("fetching user");
        return userService.findUserById(userId);
    };

    function deleteUser(event) {
        var userId = $(event.target)
            .parent().parent().parent().attr("wbdv-user-id");
        console.log('deleting user');

        userService.deleteUser(userId);
        location.reload();
    }

    function selectUser(event) {
        userId = $(event.target)
            .parent().parent().parent().attr("wbdv-user-id");

        userService.findUserById(userId)
            .then(function (userResponse) {
                $('#usernameFld').val(userResponse.username);
                $('#passwordFld').val(userResponse.password);
                $('#firstNameFld').val(userResponse.firstName);
                $('#lastNameFld').val(userResponse.lastName);
                $('#roleFld').val(userResponse.role);

                return userId;
            })
    }

    function updateUser() {
        if (typeof userId === "undefined") {
           alert("No user selected for update");
        } else {
            findUserById(userId).then(function (userResponse) {
                console.log('updating user');

                $usernameFld = $('#usernameFld').val();
                $passwordFld = $('#passwordFld').val();
                $firstNameFld = $('#firstNameFld').val();
                $lastNameFld = $('#lastNameFld').val();
                $roleFld = $('#roleFld').val();

                var user = new User();
                user.setUsername($usernameFld);
                user.setPassword($passwordFld);
                user.setFirstName($firstNameFld);
                user.setLastName($lastNameFld);
                user.setRole($roleFld);

                user.setEmail(userResponse.email);
                user.setPhone(userResponse.phone);
                user.setDateOfBirth(userResponse.dateOfBirth);

                userService.updateUser(userId, user);
                location.reload();
            });
        }
    }

    function renderUser(user) {
        var clone = $userRowTemplate.clone();
        clone.find('.wbdv-username')
            .html(user.username);
        clone.find('.wbdv-first-name')
            .html(user.firstName);
        clone.find('.wbdv-last-name')
            .html(user.lastName);
        clone.find('.wbdv-role')
            .html(user.role);
        clone.attr('wbdv-user-id', user.id);
        clone.removeClass("wbdv-hidden");

        $tbody.append(clone);
    }

    function renderUsers(users) {
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            renderUser(user);
        }
    }
})();
