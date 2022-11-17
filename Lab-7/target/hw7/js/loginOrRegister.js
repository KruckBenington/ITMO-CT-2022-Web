window.loginOrRegister = function (action, target){
    $(function () {
        $(target).submit(function () {
            const login = $(this).find("input[name='login']").val();
            const password = $(this).find("input[name='password']").val();
            const $error = $(this).find(".error");

            ownAjax({
                action: action,
                login: login,
                password: password
            }, function (response) {
                if (response["error"]) {
                    $error.text(response["error"]);
                } else {
                    location.href = response["redirect"];
                }
            });
            return false;
        });
    })
}