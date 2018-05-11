(function () {
    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;

    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    $(main);

    function main() {
        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template');

        $createBtn = $('.wbdv-create');
        $removeBtn = $('.wbdv-remove');
        $editBtn = $('.wbdv-edit');

        var userId = $('.wbdv-tbody').on('click', '.wbdv-edit', selectUser);

        $createBtn.click(createUser);
        $('.wbdv-tbody').on('click', '.wbdv-remove', deleteUser);
        $('.wbdv-update').click(updateUser);

        findAllUsers();
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

        fetch('http://localhost:8080/api/user', {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
        renderUser(user);
    }

    function findAllUsers() {
        console.log("find users")

        fetch('http://localhost:8080/api/user')
            .then(function (response) {
                return response.json();
        }).then(renderUsers);
    }

    function findUserById(userId) {
        console.log("fetching user")

        fetch('http://localhost:8080/api/user/' + userId)
            .then(function (response) {
                return response.json();
        });
    }

    function deleteUser(event) {
        var userId = $(event.target)
            .parent().parent().parent().attr("wbdv-user-id");
        console.log(userId);
        console.log('deleting user');

        fetch('http://localhost:8080/api/user/' + userId, {
            method: 'delete'
        });
        location.reload();
    }

    function selectUser(event) {
        userId = $(event.target)
            .parent().parent().parent().attr("wbdv-user-id");
        console.log(userId);
        return userId;
    }

    function updateUser(event) {
        console.log('updating user');

        $usernameFld = $('#usernameFld').val();
        $passwordFld = $('#passwordFld').val();
        $firstNameFld = $('#firstNameFld').val();
        $lastNameFld = $('#lastNameFld').val();
        $roleFld = $('#roleFld').val();

        var user = {
            id: userId,
            username: $usernameFld,
            password: $passwordFld,
            firstName: $firstNameFld,
            lastName: $lastNameFld,
            role: $roleFld
        };

        fetch('http://localhost:8080/api/user/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
        location.reload();
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

        $tbody.append(clone);
    }

    function renderUsers(users) {
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            renderUser(user);
        }
    }
})();
