window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
};

window.ownAjax = function (dataS, successFunc) {
    $.ajax({
            type: "POST",
            dataType: "json",
            url: "",
            data: dataS,
            success: function (response) {
                successFunc(response);
            }
        }
    );
};
