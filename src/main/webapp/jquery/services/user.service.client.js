function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.url = 'http://localhost:8080/api/user';
    var self = this;

    function createUser(user, callback) {
        fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }

    function findAllUsers(callback) {
        return fetch('http://localhost:8080/api/user')
            .then(function (response) {
                return response.json();
        });
    }

    function findUserById(userId, callback) {
        fetch('http://localhost:8080/api/user/' + userId)
            .then(function (response) {
                return response.json();
        });
    }
    
    function updateUser(userId, user, callback) {}
    function deleteUser(userId, callback) {}
}