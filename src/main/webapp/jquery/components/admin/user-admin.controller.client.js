(function () {
    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;

    var $removeBtn, $editBtn, $createBtn;
    var $userRowTemplate, $tbody;
    var userService = new AdminUserServiceClient();
    $(main);

    function main() {
        $tbody = $('.wbdv-tbody');
        $userRowTemplate = $('.wbdv-template wbdv-user wbdv-hidden');
        $('#wbdv-remove').click(createUser);
        $('#wbdv-edit').click(updateUser);

    }

    function createUser() { … }
    function findAllUsers() { … }
    function findUserById() { … }
    function deleteUser() { … }
    function updateUser() { … }
    function renderUser(user) { … }
    function renderUsers(users) { … }
})();
